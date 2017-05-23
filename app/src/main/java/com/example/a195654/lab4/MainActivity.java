package com.example.a195654.lab4;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener, OnWyborOpcjiListener{

    MainFragment mainFragment;
    OptionsFragment optionsFragment;
    BookListFragment listFragment;
    MovieListFragment movieListFragment;
    FragmentTransaction ft;

    BookFormFragment bookFormFragment;
    MovieFormFragment movieFormFragment;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        optionsFragment = new OptionsFragment();
        listFragment = new BookListFragment();
        movieListFragment = new MovieListFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment, mainFragment);
        ft.detach(mainFragment);
        ft.add(R.id.fragment, optionsFragment);
        ft.detach(optionsFragment);
        ft.add(R.id.fragment, listFragment);
        ft.detach(listFragment);
        ft.add(R.id.fragment, movieListFragment);
        ft.detach(movieListFragment);
        ft.commit();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = actionBar.newTab().setText("Main").setIcon(R.drawable.info).setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Dodaj pozycję").setIcon(R.drawable.plus).setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Książki").setIcon(R.drawable.books).setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Filmy").setIcon(R.drawable.movie).setTabListener(this);
        actionBar.addTab(tab);

        bookFormFragment = new BookFormFragment();
        movieFormFragment = new MovieFormFragment();

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.kontener, bookFormFragment);
        transaction.detach(bookFormFragment);
        transaction.add(R.id.kontener, movieFormFragment);
        transaction.detach(movieFormFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        int frag = tab.getPosition();
        switch(frag){
            case 0:
                fragmentTransaction.attach(mainFragment);
                //fragmentTransaction.detach(optionsFragment);
                //fragmentTransaction.detach(listFragment);
                break;
            case 1:
                fragmentTransaction.attach(optionsFragment);
                //fragmentTransaction.detach(mainFragment);
                //fragmentTransaction.detach(listFragment);
                break;
            case 2:
                fragmentTransaction.attach(listFragment);
                //fragmentTransaction.detach(mainFragment);
                //fragmentTransaction.detach(optionsFragment);
                break;
            case 3:
                fragmentTransaction.attach(movieListFragment);
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        int frag = tab.getPosition();
        switch(frag){
            case 0:
                fragmentTransaction.detach(mainFragment);
                break;
            case 1:
                fragmentTransaction.detach(optionsFragment);
                break;
            case 2:
                fragmentTransaction.detach(listFragment);
                break;
            case 3:
                fragmentTransaction.detach(movieListFragment);
                break;
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onWyborOpcji(int opcja) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (opcja){
            case 1:
                transaction.detach(movieFormFragment);
                transaction.attach(bookFormFragment);
                break;
            case 2:
                transaction.detach(bookFormFragment);
                transaction.attach(movieFormFragment);
                break;
        }
        transaction.commit();
    }
}
