package com.company.lostandfound.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.lostandfound.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddLostItemFragment extends Fragment {

    EditText lostItemNameEditTxt, lostItemColorEditTxt, lostItemLocationEditTxt, lostItemPlaceEditTxt, lostItemUserEditTxt, lostItemPhoneNumberEditTxt;
    Button addItemBtn;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddLostItemFragment() {
    }

    public static AddLostItemFragment newInstance(String param1, String param2) {
        AddLostItemFragment fragment = new AddLostItemFragment();
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

        View view = inflater.inflate(R.layout.fragment_add_lost_item, container, false);


        lostItemNameEditTxt = (EditText) view.findViewById(R.id.LostItemName);
        lostItemColorEditTxt = (EditText) view.findViewById(R.id.LostItemColor);
        lostItemLocationEditTxt = (EditText) view.findViewById(R.id.LostItemLocation);
        lostItemPlaceEditTxt = (EditText) view.findViewById(R.id.LostItemPlace);
        lostItemUserEditTxt = (EditText) view.findViewById(R.id.LostItemUserName);
        lostItemPhoneNumberEditTxt = (EditText) view.findViewById(R.id.LostItemPhoneNumber);

        addItemBtn = (Button) view.findViewById(R.id.AddLostItemBtn);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String ItemName = lostItemNameEditTxt.getText().toString();
                String ItemColor = lostItemColorEditTxt.getText().toString();
                String ItemLocation = lostItemLocationEditTxt.getText().toString();
                String ItemPlace = lostItemPlaceEditTxt.getText().toString();
                String UserName = lostItemUserEditTxt.getText().toString();
                String UserPhoneNumber = lostItemPhoneNumberEditTxt.getText().toString();

                if (ItemName.isEmpty() || ItemColor.isEmpty() || ItemLocation.isEmpty() || ItemPlace.isEmpty() || UserName.isEmpty() || UserPhoneNumber.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter all details", Toast.LENGTH_SHORT).show();
                } else {

                    addItem(ItemName, ItemColor, ItemLocation, ItemPlace, UserName, UserPhoneNumber);
                }
            }
        });
        return view;
    }

    private void addItem(String itemName, String itemColor, String itemLocation, String itemPlace, String userName, String userPhoneNumber) {

        HashMap<String, Object> item_details = new HashMap<>();

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


        String key = FirebaseDatabase.getInstance().getReference().child("lostItems").push().getKey();

        item_details.put("itemName", itemName);
        item_details.put("itemColor", itemColor);
        item_details.put("itemLocation", itemLocation);
        item_details.put("itemPlace", itemPlace);
        item_details.put("userName", userName);
        item_details.put("userPhoneNumber", userPhoneNumber);
        item_details.put("userId", userId);


        FirebaseDatabase.getInstance().getReference().child("lostItems")
                .child(key)
                .updateChildren(item_details).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(getContext(), "Item details added successfully", Toast.LENGTH_SHORT).show();
                    lostItemNameEditTxt.setText("");
                    lostItemColorEditTxt.setText("");
                    lostItemLocationEditTxt.setText("");
                    lostItemPlaceEditTxt.setText("");
                    lostItemUserEditTxt.setText("");
                    lostItemPhoneNumberEditTxt.setText("");

                }

            }
        });


    }
}