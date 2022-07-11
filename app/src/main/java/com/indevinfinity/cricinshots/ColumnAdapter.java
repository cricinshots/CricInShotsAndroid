package com.indevinfinity.cricinshots;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ColumnAdapter extends  RecyclerView.Adapter<ColumnAdapter.Holder > {

    ArrayList<scdata> mList = new ArrayList<scdata>();

    public ColumnAdapter(ArrayList<scdata> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.column, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final scdata _scdata = mList.get(i);
        holder.tit.setText(_scdata.Title);
        holder.con.setText(_scdata.Content);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class Holder extends RecyclerView.ViewHolder {

        TextView tit,con;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tit = itemView.findViewById(R.id.tv_col_tit);
            con = itemView.findViewById(R.id.tv_col_con);
        }
    }

}