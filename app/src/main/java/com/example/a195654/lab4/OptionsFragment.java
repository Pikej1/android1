package com.example.a195654.lab4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    AppCompatActivity appActivity;
    OnWyborOpcjiListener listener;

    public OptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        ((RadioGroup) getActivity().findViewById(R.id.opcje)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radioButtonBook:
                listener.onWyborOpcji(1);
                break;
            case R.id.radioButtonMovie:
                listener.onWyborOpcji(2);
                break;
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            appActivity = (AppCompatActivity) context;
            listener = (OnWyborOpcjiListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(appActivity.toString() + "musi implementować OnWyborOpcjiListener");
        }
    }
}
