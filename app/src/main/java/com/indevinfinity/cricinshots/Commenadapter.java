package com.indevinfinity.cricinshots;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.preference.PreferenceManager;
import com.shashank.sony.fancytoastlib.FancyToast;
import java.util.ArrayList;
import java.util.Locale;

class Commenadapter implements ListAdapter {
    ArrayList<commentarydata> arrayList;
    Context context;
    TextToSpeech t1;
    public Commenadapter(Context context, ArrayList<commentarydata> arrayList,TextToSpeech t1) {
        this.arrayList=arrayList;
        this.context=context;
        this.t1 = t1;
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
        final commentarydata commentarydata = arrayList.get(position);

        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.commentary_view, null);
            TextView com = convertView.findViewById(R.id.tv_accom);
            TextView comhead = convertView.findViewById(R.id.tv_accomhead);
            com.setText(commentarydata.Text);
            comhead.setText(commentarydata.Head);
            ImageButton speaker = convertView.findViewById(R.id.bt_talk);

            speaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        t1.speak(commentarydata.Text,TextToSpeech.QUEUE_FLUSH,null,null);
                    } else {
                        t1.speak(commentarydata.Text, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });
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