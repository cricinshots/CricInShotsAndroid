package com.indevinfinity.cricinshots;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.preference.PreferenceManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shashank.sony.fancytoastlib.FancyToast;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

class listadapter implements ListAdapter {
    private ArrayList<playersdata> arrayList;
    Context context;
    public listadapter(Context context, ArrayList<playersdata> arrayList) {
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
        final playersdata playersdata = arrayList.get(position);
        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.player_row, null);

            final TextView tv_pname = convertView.findViewById(R.id.tv_pname);
            final TextView tv_pbas = convertView.findViewById(R.id.tv_pbstyle);
            final TextView tv_pbos = convertView.findViewById(R.id.tv_pbbstyle);
            final TextView tv_psinfo = convertView.findViewById(R.id.tv_psinfo);
            final ImageButton deets = convertView.findViewById(R.id.bt_vdeets);
            final ImageButton hdeets = convertView.findViewById(R.id.bt_hidedeets);
            final LinearLayout lin_pbot = convertView.findViewById(R.id.lin_prbot);
            final RelativeLayout rel_pim = convertView.findViewById(R.id.rel_imgplayer);
            final ImageView iv_player = convertView.findViewById(R.id.iv_player);
            final NestedScrollView sv_plr = convertView.findViewById(R.id.sv_plr);
            //iv_player.setBackgroundColor(Color.parseColor(front_land.teamcolor[front_land.cbt]));
            Glide.with(context).load(playersdata.imageURL).into(iv_player);
            tv_pname.setText(playersdata.fullName);
            tv_pbas.setText(playersdata.battingStyle);
            tv_pbos.setText(playersdata.bowlingStyle);
            try{
                tv_psinfo.setText(Html.fromHtml(playersdata.bio));
            }catch (Exception e){
                tv_psinfo.setText("Loading...");
            }

            final int big = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100, context.getResources().getDisplayMetrics());
            final int small = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40, context.getResources().getDisplayMetrics());
            deets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        sv_plr.setVisibility(View.VISIBLE);
                        lin_pbot.setVisibility(View.VISIBLE);
                        hdeets.setVisibility(View.VISIBLE);
                        deets.setVisibility(View.GONE);

                        rel_pim.getLayoutParams().width = big;
                        rel_pim.getLayoutParams().height = big;
                        iv_player.getLayoutParams().width = big;
                        iv_player.getLayoutParams().height = big;

                        Glide.with(context).load(playersdata.imageURL).apply(new RequestOptions().override(big, big)).into(iv_player);

                }
            });

            hdeets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sv_plr.setVisibility(View.GONE);
                    lin_pbot.setVisibility(View.GONE);
                    deets.setVisibility(View.VISIBLE);
                    hdeets.setVisibility(View.GONE);
                    rel_pim.getLayoutParams().width = small;
                    rel_pim.getLayoutParams().height = small;
                    iv_player.getLayoutParams().width = small;
                    iv_player.getLayoutParams().height = small;

                    Glide.with(context).load(playersdata.imageURL).apply(new RequestOptions().override(small, small)).into(iv_player);

                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
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