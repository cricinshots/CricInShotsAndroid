package com.indevinfinity.cricinshots;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class overballadapter extends  RecyclerView.Adapter<overballadapter.Holder > {

    ArrayList<overballdata> mList = new ArrayList<overballdata>();

    public overballadapter(ArrayList<overballdata> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
       // View view = inflater.inflate(R.layout.single_ball, viewGroup, false);
        View view = inflater.inflate(R.layout.single_ball, viewGroup, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final overballdata _overballdata = mList.get(i);
        holder.ball.setText(_overballdata.Toshow);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class Holder extends RecyclerView.ViewHolder {

        TextView ball;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ball = itemView.findViewById(R.id.singleball);
        }
    }

}