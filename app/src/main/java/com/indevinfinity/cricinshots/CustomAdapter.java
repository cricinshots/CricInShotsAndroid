package com.indevinfinity.cricinshots;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

class CustomAdapter implements ListAdapter {
    ArrayList<ListData> arrayList;
    Context context;

    public CustomAdapter(Context context, ArrayList<ListData> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
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
        final ListData listData = arrayList.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.custom_row, null);
          //  AdView mAdView = convertView.findViewById(R.id.adView);
            CardView cv = convertView.findViewById(R.id.cv_mdata);
             //oc = convertView.findViewById(R.id._1card);
            //AdSize adSize = new AdSize(320, 60);
//            String[] smid =  listData.Matchid.split("-");
           // if (smid[0].equals("2514")) {
//            if (listData.Counter == 1) {
//
//                MobileAds.initialize(context, new OnInitializationCompleteListener() {
//                    @Override
//                    public void onInitializationComplete(InitializationStatus initializationStatus) {
//                    }
//                });
//                AdRequest adRequest = new AdRequest.Builder().build();
//                mAdView.setVisibility(View.VISIBLE);
//                //mAdView.setAdSize(adSize);
//                cv.setVisibility(View.GONE);
//                mAdView.loadAd(adRequest);
           // } else {

                /**convertView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                }
                });**/

                final TextView title = convertView.findViewById(R.id.tv_rowtit);
                final TextView currstat = convertView.findViewById(R.id.tv_currstat);
                final TextView mDate = convertView.findViewById(R.id.tv_mdate);
                ImageView lg1 = convertView.findViewById(R.id.iv_lg1);
                ImageView lg2 = convertView.findViewById(R.id.iv_lg2);
                //ImageView sv = convertView.findViewById(R.id.iv_sl);
                Glide.with(context).load(listData.Link1).into(lg1);
                Glide.with(context).load(listData.Link2).into(lg2);
                //Glide.with(context).load(listData.Shield).into(sv);
                final String mname = listData.Hname + " VS " + listData.Aname;
                final String state = listData.CurrentState;
                title.setText(mname);
                currstat.setText(state);
                //mDate.setText(listData.StartDate);

                try{
                    GifImageView live = convertView.findViewById(R.id.gif_live);
                    if (listData.IsLive || ("X"+state.toUpperCase()).contains("LIVE")) {
                        live.setVisibility(View.VISIBLE);
                    } else {
                        live.setVisibility(View.GONE);
                    }
                }catch (Exception j){
                    System.out.println("Error");
                }


                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listData.CurrentState.contains("abandoned") || listData.CurrentState.equals("UPCOMING")) {

                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.setTitle("Yikes!");
                            alertDialog.setMessage("This match is yet to start or is over!");
                            alertDialog.setCancelable(false);
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Okay",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });

                            alertDialog.show();

                        } else {
                            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                            prefEditor.putString("Shield", listData.Shield);
                            prefEditor.putString("T1", listData.Hname);
                            prefEditor.putString("T2", listData.Aname);
                            prefEditor.putString("F1", listData.Link1);
                            prefEditor.putString("F2", listData.Link2);
                            prefEditor.putString("Bg1", listData.Hbgurl);
                            prefEditor.putString("Bg2", listData.Abgurl);
                            prefEditor.putString("Hcol", listData.Hcol);
                            prefEditor.putString("Acol", listData.Acol);
                            prefEditor.putString("Hsname", listData.Hsname);
                            prefEditor.putString("Asname", listData.Asname);
                            prefEditor.putBoolean("Hisbat", listData.Hisbat);
                            prefEditor.putBoolean("Aisbat", listData.Aisbat);
                            prefEditor.apply();
                            //Intent intent = new Intent(context, front_land.class);
                            Intent intent = new Intent(context, front_land.class);
                            intent.putExtra("matchid", listData.Matchid);
                            FancyToast.makeText(context, "Match Selected : " + mname, FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();
                            context.startActivity(intent);
                        }
                    }
                });
           // }
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