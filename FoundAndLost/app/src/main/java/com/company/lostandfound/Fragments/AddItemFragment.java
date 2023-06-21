package com.company.lostandfound.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.company.lostandfound.R;


public class AddItemFragment extends Fragment {

    SwitchCompat mSwitch;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddItemFragment() {
    }

    public static AddItemFragment newInstance(String param1, String param2) {
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);


        mSwitch = (SwitchCompat) view.findViewById(R.id.AddItemSwitch);
        mSwitch.setChecked(false);
        getFragmentManager().beginTransaction().replace(R.id.AddItemContainer, new AddLostItemFragment()).commit();
        mSwitch.setOnClickListener(view1 -> {
            if (mSwitch.isChecked()) {
                getFragmentManager().beginTransaction().replace(R.id.AddItemContainer, new AddFoundItemFragment()).commit();
            } else {

                getFragmentManager().beginTransaction().replace(R.id.AddItemContainer, new AddLostItemFragment()).commit();
            }
        });


        return view;
    }
}