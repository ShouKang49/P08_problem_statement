package sg.edu.rp.c346.id22013080.p08problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class songDisplayActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Song> listedSongs;

    Button back, btnShowStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_display);

        lv = findViewById(R.id.list);
        back = findViewById(R.id.btnBack);
        btnShowStars = findViewById(R.id.btnShow5Star);

        Intent intentReceived = getIntent();
        listedSongs = new ArrayList<Song>();


        ArrayAdapter allSongs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listedSongs);
        lv.setAdapter(allSongs);

        DBHelper db = new DBHelper(songDisplayActivity.this);
        listedSongs.clear();
        listedSongs.addAll(db.getSong());
        allSongs.notifyDataSetChanged();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(songDisplayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Song data = listedSongs.get(position);
                Intent i = new Intent(songDisplayActivity.this, MaintainSong.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnShowStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(songDisplayActivity.this);
                int starsNeeded = 5;
                listedSongs.clear();
                listedSongs.addAll(db.getSong(starsNeeded));

                allSongs.notifyDataSetChanged();
            }
        });
    }
}