package com.example.a195654.lab4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class BookListFragment extends ListFragment implements AdapterView.OnItemSelectedListener {

    private ArrayList<Book> list = new ArrayList<>();

    public BookListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //textView = (TextView) getActivity().findViewById(R.id.book_text);
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        load();
        BookAdapter adapter = new BookAdapter(getContext(), list);
        setListAdapter(adapter);
        return view;
    }

    private void load(){
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(getContext().getFilesDir() + "/" + "books.txt"));
            list.removeAll(list);
            while (true) {
                list.add((Book) in.readObject());
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
