package com.example.a195654.lab4;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends ListFragment {

    private ArrayList<Movie> list = new ArrayList<>();

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        load();
        MovieAdapter adapter = new MovieAdapter(getContext(), list);
        //setListAdapter(adapter);
        ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = list.get(position);
                Toast.makeText(getContext(), "tekst", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void load(){
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(getContext().getFilesDir() + "/" + "movies.txt"));
            list.removeAll(list);
            while (true) {
                list.add((Movie) in.readObject());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "FileNotFoundException list", Toast.LENGTH_SHORT).show();
        } catch (EOFException e) {
            if (in != null) try {
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "IOException list", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "ClassNotFoundEsception list", Toast.LENGTH_SHORT).show();
        }
    }
}
