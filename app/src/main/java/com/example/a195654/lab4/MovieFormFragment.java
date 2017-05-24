package com.example.a195654.lab4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFormFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    //static final String PREFERENCES_NAME = "myPreferences";
    //private SharedPreferences preferences;

    private EditText title;
    private EditText director;
    private Spinner genreSpinner;
    private Spinner langSpinner;

    Movie movie;

    public MovieFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_form, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movie = new Movie();

        /*
         *title
         */
        title = (EditText) getActivity().findViewById(R.id.movieTitle_editText);

        /*
         *director
         */
        director = (EditText) getActivity().findViewById(R.id.movieDirector_editText);

        /*
         *genreSpinner
         */
        genreSpinner = (Spinner) getActivity().findViewById(R.id.movie_genre_spinner);

        ArrayAdapter<CharSequence> genreAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.movie_genre_array, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);
        genreSpinner.setOnItemSelectedListener(this);

        /*
         *langSpinner
         */
        langSpinner = (Spinner) getActivity().findViewById(R.id.movie_lang_spinner);

        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.movie_lang_array, android.R.layout.simple_spinner_item);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        langSpinner.setAdapter(langAdapter);
        langSpinner.setOnItemSelectedListener(this);

        /*
         *saveButton
         */
        Button saveButton = (Button) getActivity().findViewById(R.id.movie_save);
        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(title.getText() != null && title.getText().length() > 0){
                    movie.setTitle(title.getText().toString());
                    movie.setDirector(director.getText().toString());

                    title.setText("");
                    director.setText("");

                    ArrayList<Movie> list = new ArrayList<>();
                    File file = new File(getContext().getFilesDir(), "movies.txt");
                    ObjectInputStream in = null;
                    try {
                        in = new ObjectInputStream(new FileInputStream(file));
                        while (true) {
                            list.add((Movie) in.readObject());
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "FileNotFoundException 1", Toast.LENGTH_SHORT).show();
                    } catch (EOFException e) {
                        if (in != null) try {
                            in.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "IOException 1", Toast.LENGTH_SHORT).show();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "ClassNotFoundEsception 1", Toast.LENGTH_SHORT).show();
                    }

                    ObjectOutputStream out;
                    try{
                        out = new ObjectOutputStream(new FileOutputStream(file));
                        for(Movie movie : list){
                            out.writeObject(movie);
                        }
                        out.writeObject(movie);
                        out.flush();
                        out.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "FileNotFoundException 2", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "IOException 2", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getContext(), "Zapisano", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Uzupełnij tytuł!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == genreSpinner.getId())
            movie.setGenre(parent.getSelectedItem().toString());
        if(parent.getId() == langSpinner.getId())
            movie.setLanguage(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
