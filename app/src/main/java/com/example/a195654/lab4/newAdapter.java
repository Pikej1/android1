package com.example.a195654.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.Pack200;

/**
 * Created by PiET on 2017-05-22.
 */

public class newAdapter extends ArrayAdapter<Movie> {

    private ArrayList<Movie> list;

    public newAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_movie_list, null);
        }
        Movie movie = list.get(position);
        return view;
    }
}
