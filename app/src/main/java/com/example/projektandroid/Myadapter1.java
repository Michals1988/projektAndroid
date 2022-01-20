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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Myadapter1 extends RecyclerView.Adapter {


    private ArrayList<Remind> mRemind = new ArrayList<Remind>();


    private RecyclerView mRecyclerView;


    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mNote;
        public TextView mDate;
        public TextView mTime;



        public MyViewHolder(View pItem) {
            super(pItem);
            mTitle= (TextView) pItem.findViewById(R.id.reminderListViewTitle);
            mNote = (TextView) pItem.findViewById(R.id.reminderListViewNote);
            mDate= (TextView) pItem.findViewById(R.id.reminderListViewDate);
            mTime = (TextView) pItem.findViewById(R.id.reminderListViewTime);
        }
    }

    // konstruktor adaptera
    public Myadapter1(ArrayList<Remind> pRemind, RecyclerView pRecyclerView) {
        mRemind = pRemind;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mylistviewreminder, viewGroup, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {


        Remind remind = mRemind.get(i);


        SimpleDateFormat timeString = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateString = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println(remind.getRemindTitle());

        ((MyViewHolder) viewHolder).mTitle.setText(remind.getRemindTitle());
        ((MyViewHolder) viewHolder).mNote.setText(remind.getRemindNote());
        ((MyViewHolder) viewHolder).mTime.setText(timeString.format(remind.getRemindTime()));
        ((MyViewHolder) viewHolder).mDate.setText(dateString.format(remind.getRemindTime()));

    }

    @Override
    public int getItemCount() {
        return mRemind.size();
    }
}