package com.company.lostandfound.Adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.lostandfound.Model.Model;
import com.company.lostandfound.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

public class NotificationAdapter extends FirebaseRecyclerAdapter<Model, NotificationAdapter.Viewholder> {

    public NotificationAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {

        super(options);

    }



    @Override
    protected void onBindViewHolder(@NonNull NotificationAdapter.Viewholder holder, int position, @NonNull Model model) {


        //for loading Notifications into recycler view
        holder.itemName.setText(model.getItemName());
        holder.itemLocation.setText(model.getItemLocation());
        holder.userName.setText(model.getUserName());
        holder.userGmail.setText(model.getGmailId());
        holder.message.setText(model.getMessage());

    }


    @NonNull
    @Override
    public NotificationAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_data_file, parent, false);
        return new NotificationAdapter.Viewholder(view);
    }

    class Viewholder extends RecyclerView.ViewHolder {


        TextView itemName, itemLocation, userName, userGmail, message;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            //assigning the address of the materials
            itemName = (TextView) itemView.findViewById(R.id.ItemNameTxt);
            itemLocation = (TextView) itemView.findViewById(R.id.ItemLocationTxt);
            userName = (TextView) itemView.findViewById(R.id.UserNameTxt);
            userGmail = (TextView) itemView.findViewById(R.id.GmailIdTxt);
            message = (TextView) itemView.findViewById(R.id.MessageTxt);

        }
    }

}

