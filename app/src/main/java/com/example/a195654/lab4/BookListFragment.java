package com.example.a195654.lab4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class BookListFragment extends Fragment {

    private ArrayList<Book> list = new ArrayList<>();
    private TextView textView;


    public BookListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //textView = (TextView) getActivity().findViewById(R.id.book_text);
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        textView = (TextView) getActivity().findViewById(R.id.book_text);
        load();
        if (list.size() > 0){
            textView.setText("Tytuł: " + list.get(list.size()-1).getTitle() +
                    ", Autor: " + list.get(list.size() - 1).getAuthor() +
                    ", Gatunek: " + list.get(list.size() - 1).getGenre() +
                    ", Polska werjsa językowa: " + list.get(list.size() - 1).isIfPolish());
        }else Toast.makeText(getContext(), "dane nie zostały wczytane", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        load();
        if (list.size() > 0){
            textView.setText("Tytuł: " + list.get(list.size()-1).getTitle() +
            ", Autor: " + list.get(list.size() - 1).getAuthor() +
            ", Gatunek: " + list.get(list.size() - 1).getGenre() +
            ", Polska werjsa językowa: " + list.get(list.size() - 1).isIfPolish());
            //listView.setAdapter(adapter);
        }else Toast.makeText(getContext(), "dane nie zostały wczytane", Toast.LENGTH_SHORT).show();
    }

    private void load(){
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(getContext().getFilesDir() + "/" + "books.txt"));
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

}
