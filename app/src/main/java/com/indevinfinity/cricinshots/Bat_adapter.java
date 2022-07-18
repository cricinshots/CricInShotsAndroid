package com.indevinfinity.cricinshots;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.shashank.sony.fancytoastlib.FancyToast;
import java.util.ArrayList;

class Bat_adapter implements ListAdapter {
    ArrayList<batstat_data> arrayList;
    Context context;
    public Bat_adapter(Context context, ArrayList<batstat_data> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final batstat_data batstat_data = arrayList.get(position);

        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.batsman_dialog, null);
            TextView b,r,f,s,sr,n;
            r = convertView.findViewById(R.id.tv_b_r);
            b = convertView.findViewById(R.id.tv_b_b);
            f = convertView.findViewById(R.id.tv_b_4);
            s = convertView.findViewById(R.id.tv_b_6);
            sr = convertView.findViewById(R.id.tv_b_sr);
            n = convertView.findViewById(R.id.tv_b_n);

            r.setText(batstat_data.Runs);
            b.setText(batstat_data.Balls);
            f.setText(batstat_data.Fours);
            s.setText(batstat_data.Sixes);
            sr.setText(batstat_data.StrikeRate);
            n.setText(batstat_data.Name);
            /**convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
            });**/
        }

        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}