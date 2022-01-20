package com.example.projektandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter {


    private ArrayList<Contact> mContact = new ArrayList<>();


    private RecyclerView mRecyclerView;


    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mNumber;
        public ImageView mAvatar;
        public Button buttonDelete;
        public Button buttonEdit;
        public Button buttonShow;

        public MyViewHolder(View pItem) {
            super(pItem);
            mName = (TextView) pItem.findViewById(R.id.showContactName);
            mNumber = (TextView) pItem.findViewById(R.id.showContactNumber);
            mAvatar = (ImageView) pItem.findViewById(R.id.showContactAvatar);
        }
    }

    // konstruktor adaptera
    public Myadapter(ArrayList<Contact> pContacts, RecyclerView pRecyclerView) {
        mContact = pContacts;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mylistview, viewGroup, false);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact();
                int positionToOpen = mRecyclerView.getChildAdapterPosition(v);

                contact = mContact.get(positionToOpen);

                String idContact = String.valueOf(contact.getId());

                Intent intentOpenContact = new Intent(v.getContext(), MainShowOneContact.class);
                int id = contact.getId();

                intentOpenContact.putExtra("idContact", id);

                v.getContext().startActivity(intentOpenContact);


            }
        });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {


        Contact contact = mContact.get(i);
        String avatarName = contact.getAvatar();

        if (avatarName.equals("Avatar1")) {
            ((MyViewHolder) viewHolder).mAvatar.setImageResource(R.drawable.man1);
        }
        if (avatarName.equals("Avatar2")) {
            ((MyViewHolder) viewHolder).mAvatar.setImageResource(R.drawable.woman1);
        }
        if (avatarName.equals("Avatar3")) {
            ((MyViewHolder) viewHolder).mAvatar.setImageResource(R.drawable.man2);
        }
        if (avatarName.equals("Avatar4")) {
            ((MyViewHolder) viewHolder).mAvatar.setImageResource(R.drawable.woman2);
        }

        ((MyViewHolder) viewHolder).mName.setText(contact.getImie() + " " + contact.getNazwisko());
        ((MyViewHolder) viewHolder).mNumber.setText(contact.getNumerTelefonu());
    }

    @Override
    public int getItemCount() {
        return mContact.size();
    }
}