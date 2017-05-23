package com.example.a195654.lab4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
public class BookFormFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    static final String PREFERENCES_NAME = "myPreferences";

    EditText title;
    EditText author;
    Spinner spinner;
    CheckBox checkBox;

    Book book;

    public BookFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_form, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        book = new Book();
        /*
         *EditText title:
         */
        title = (EditText) getActivity().findViewById(R.id.title_editText);

        /*
         *EditText author:
         */
        author = (EditText) getActivity().findViewById(R.id.author_editText);

        /*
         *Spinner spinner
         */
        spinner = (Spinner) getActivity().findViewById(R.id.book_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.book_genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        /*
         *CheckBox checkBox
         */
        checkBox = (CheckBox) getActivity().findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    book.setIfPolish(true);
                else book.setIfPolish(false);
            }
        });

        Button saveButton = (Button) getActivity().findViewById(R.id.book_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText() != null && title.getText().length() > 0){
                    book.setTitle(title.getText().toString());
                    book.setAuthor(author.getText().toString());

                    ArrayList<Book> list = new ArrayList<>();
                    File file = new File(getContext().getFilesDir(), "books.txt");
                    ObjectInputStream in = null;
                    try {
                        in = new ObjectInputStream(new FileInputStream(file));
                        while (true) {
                            list.add((Book) in.readObject());
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
                        for(Book book : list){
                            out.writeObject(book);
                        }
                        out.writeObject(book);
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
        book.setGenre(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
