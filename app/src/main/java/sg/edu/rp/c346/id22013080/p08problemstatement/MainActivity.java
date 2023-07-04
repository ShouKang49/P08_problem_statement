package sg.edu.rp.c346.id22013080.p08problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    EditText title, singer, year;
    RadioGroup ratingGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        title = findViewById(R.id.etSongTitle);
        singer = findViewById(R.id.etSingers);
        year = findViewById(R.id.etYear);
        ratingGroup = findViewById(R.id.rating);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                String newTitle = title.getText().toString();
                String newSinger = singer.getText().toString();
                int newYear = Integer.parseInt(year.getText().toString());
                int ratingtype = ratingGroup.getCheckedRadioButtonId();
                int rating = 0;

                if(ratingtype == R.id.oneStar){
                    rating = 1;
                }
                else if(ratingtype == R.id.twoStar){
                    rating = 2;
                }
                else if(ratingtype == R.id.threeStar){
                    rating = 3;
                }
                else if(ratingtype == R.id.fourStar){
                    rating = 4;
                }
                else if(ratingtype == R.id.fiveStar){
                    rating = 5;
                }

                db.insertSong(newTitle, newSinger, newYear, rating);

                Toast.makeText(MainActivity.this, "Song added", Toast.LENGTH_SHORT).show();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, songDisplayActivity.class);
                startActivity(intent);
            }
        });
    }
}