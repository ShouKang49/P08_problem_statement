package sg.edu.rp.c346.id22013080.p08problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MaintainSong extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText title, singer, year, songID;
    RadioGroup ratingGroup;

    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_song);

        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        title = findViewById(R.id.etSongTitle);
        singer = findViewById(R.id.etSingers);
        year = findViewById(R.id.etYear);
        ratingGroup = findViewById(R.id.rating);
        songID = findViewById(R.id.etSOngID);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        songID.setText("" + data.get_id());
        songID.setEnabled(false);

        title.setText(data.getTitle());
        singer.setText(data.getSingers());
        year.setText("" + data.getYear());
        int ratingGiven = data.getStars();
        if(ratingGiven == 1){
            ratingGroup.check(R.id.oneStar);
        }
        else if(ratingGiven == 2){
            ratingGroup.check(R.id.twoStar);
        }
        else if(ratingGiven == 3){
            ratingGroup.check(R.id.threeStar);
        }
        else if(ratingGiven == 4){
            ratingGroup.check(R.id.fourStar);
        }
        else if(ratingGiven == 5){
            ratingGroup.check(R.id.fiveStar);
        }

            btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MaintainSong.this);
                
                int ratingType = ratingGroup.getCheckedRadioButtonId();
                int rating = 0;

                if(ratingType == R.id.oneStar){
                    rating = 1;
                }
                else if(ratingType == R.id.twoStar){
                    rating = 2;
                }
                else if(ratingType == R.id.threeStar){
                    rating = 3;
                }
                else if(ratingType == R.id.fourStar){
                    rating = 4;
                }
                else if(ratingType == R.id.fiveStar){
                    rating = 5;
                }
                
                
                data.setTitle(title.getText().toString());
                data.setSingers(singer.getText().toString());
                data.setYear(Integer.parseInt(year.getText().toString()));
                data.setStars(rating);

                dbh.updateSong(data);

                Toast.makeText(MaintainSong.this, "Song Updated", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MaintainSong.this, songDisplayActivity.class);
                startActivity(i);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MaintainSong.this);
                dbh.deleteSong(data.get_id());

                Toast.makeText(MaintainSong.this, "Song Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MaintainSong.this, songDisplayActivity.class);
                startActivity(i);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintainSong.this, songDisplayActivity.class);
                startActivity(intent);

            }
        });
    }
}