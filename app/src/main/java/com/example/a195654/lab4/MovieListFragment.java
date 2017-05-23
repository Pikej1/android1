package com.example.a195654.lab4;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
public class MovieListFragment extends Fragment {//implements AdapterView.OnItemSelectedListener {

    private ArrayList<Movie> list = new ArrayList<>();
    private ArrayList<String> titleList = new ArrayList<>();
    private TextView textView;
    private ListView listView;
    //private ArrayAdapter<String> adapter;
    private newAdapter adapter;

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_movie_list, container, false);

        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        load();
        listView = (ListView) getActivity().findViewById(R.id.movieList);
        listView.setAdapter(new newAdapter(getActivity(), R.layout.fragment_movie_list, list));

        return view;
    }

    //@SuppressLint("WrongViewCast")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView = (TextView) getActivity().findViewById(R.id.movieListText);
        listView = (ListView) getActivity().findViewById(R.id.movieList);
        load();
        if (list.size() > 0){
            //adapter = new newAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
            //textView.setText(list.get(0).getTitle());
            //adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titleList);
            //listView.setAdapter(adapter);

        }else Toast.makeText(getContext(), "dane nie zostały wczytane", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        load();
        if (list.size() > 0){
            //textView.setText(list.get(list.size()-1).getTitle());
            //listView.setAdapter(adapter);
        }else Toast.makeText(getContext(), "dane nie zostały wczytane", Toast.LENGTH_SHORT).show();
    }

    private void load(){
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(getContext().getFilesDir() + "/" + "movies.txt"));
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

    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Movie movie = this.list.get(position);
        Toast.makeText(getContext(), movie.getTitle() + movie.getDirector() + movie.getGenre() + movie.getLanguage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}
