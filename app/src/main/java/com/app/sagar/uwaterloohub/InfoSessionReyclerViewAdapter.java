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
        View view = inflater.inflate(R.layout.recycler_view_row, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        InfoSession current = data.get(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv;
        ImageView iv;
        ImageView remove;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //On entire row click listener
                }
            });

            tv = (TextView) itemView.findViewById(R.id.textView);
            iv = (ImageView) itemView.findViewById(R.id.image);
            remove = (ImageView) itemView.findViewById(R.id.remove_img);
            remove.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            delete(getPosition());
        }
    }
}