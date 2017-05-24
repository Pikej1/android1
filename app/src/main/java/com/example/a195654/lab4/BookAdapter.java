package com.example.a195654.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PiET on 2017-05-23.
 */

public class BookAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private ArrayList<Book> list;

    public BookAdapter(Context context, ArrayList<Book> list){
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get view for row item
        View rowView = inflater.inflate(R.layout.book_item, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.book_item_title);

        Book book = (Book) getItem(position);
        textView.setText(book.getTitle());

        return rowView;
    }
}
