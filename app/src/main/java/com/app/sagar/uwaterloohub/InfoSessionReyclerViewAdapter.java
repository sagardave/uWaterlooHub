package com.app.sagar.uwaterloohub;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sagar.uwaterloohub.Models.InfoSession;
import com.app.sagar.uwaterloohub.Models.RecyclerViewRow;

import java.util.Collections;
import java.util.List;

/**
 * Created by SagarkumarDave on 12/23/2015.
 */
public class InfoSessionReyclerViewAdapter extends RecyclerView.Adapter<InfoSessionReyclerViewAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    List<InfoSession> data = Collections.emptyList();
    Context context;

    public InfoSessionReyclerViewAdapter(Context context, List<InfoSession> data, FragmentTransaction fragmentTransaction) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public void delete(int pos) {
        data.remove(pos);
        notifyItemRemoved(pos);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.row_info_session, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        InfoSession current = data.get(i);
        myViewHolder.employerName.setText(current.getEmployer());
        myViewHolder.date.setText(current.getDate());
        myViewHolder.location.setText(current.getLocation());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView employerName;
        TextView date;
        TextView location;
        ImageView favBtn;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //On entire row click listener
                }
            });

            employerName = (TextView) itemView.findViewById(R.id.employerName);
            date = (TextView) itemView.findViewById(R.id.date);
            location = (TextView) itemView.findViewById(R.id.location);
            favBtn = (ImageView) itemView.findViewById(R.id.favBtn);
        }

        @Override
        public void onClick(View v) {
            delete(getPosition());
        }
    }
}