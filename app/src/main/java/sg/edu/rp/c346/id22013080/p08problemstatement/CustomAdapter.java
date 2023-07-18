package sg.edu.rp.c346.id22013080.p08problemstatement;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> object){
        super(context, resource, object);

        parent_context = context;
        layout_id = resource;
        songList = object;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvYears = rowView.findViewById(R.id.tvYear);
        TextView tvStars = rowView.findViewById(R.id.tvStars);
        TextView tvName = rowView.findViewById(R.id.tvSingers);

        Song currentSong = songList.get(position);

        tvTitle.setText(currentSong.getTitle());
        tvTitle.setTextColor(Color.parseColor("#891DDE"));

        tvYears.setText("" + currentSong.getYear());

        tvStars.setText(currentSong.toString());
        tvStars.setTextColor(Color.RED);

        tvName.setText(currentSong.getSingers());
        tvName.setTextColor(Color.parseColor("#5CEEF4"));

        return rowView;
    }
}
