package com.indevinfinity.cricinshots;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;
import java.util.ArrayList;

public class teamfragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private Context mContext;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    ListView lv_players;
    ArrayList<playersdata> arrayList;
    SwitchMaterial sw;
    RelativeLayout rel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String ht = "homeTeam", at = "awayTeam";
    TextView tv_pt1,tv_pt2;
    String t1n="",t2n="",t1b="",t2b="",t1bos="",t2bos="",t1bas="",t2bas="",t1url="",t2url="";
    String[] teamcolors, t1nammes,t2names,t1bio,t2bio,t1urls,t2urls,t1boss,t2boss,t1bass,t2bass;
    boolean[] onedone = new boolean[2];
    public teamfragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static teamfragment newInstance(String param1, String param2, String param3) {
        teamfragment fragment = new teamfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View tf = inflater.inflate(R.layout.fragment_teamfragment, container, false);
        lv_players = tf.findViewById(R.id.lv_players);
        sw = tf.findViewById(R.id.sw_wtp);
        tv_pt1 = tf.findViewById(R.id.tv_pt1);
        tv_pt2 = tf.findViewById(R.id.tv_pt2);
        rel = tf.findViewById(R.id.rel_plpage);
        String[] teams = new  String[2];
        teams = mParam2.split(",");
        tv_pt1.setText(teams[0]);
        tv_pt2.setText(teams[1]);
        teamcolors = new  String[2];
        teamcolors = mParam3.split(",");

        showplayers(ht);
        onedone[0] = true;
        //rel.setBackgroundColor(Color.parseColor(teamcolors[0]));
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    showplayers(at);
                    onedone[1] = true;
                    //rel.setBackgroundColor(Color.parseColor(teamcolors[1]));
                }
                else {
                    showplayers(ht);
                    //rel.setBackgroundColor(Color.parseColor(teamcolors[0]));
                    //FancyToast.makeText(stat_activity.this,"Will not show ball details after every ball",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                }
            }
        });

        return tf;
    }

    public void showplayers(String team){

        arrayList = new ArrayList<playersdata>();
        myRef = database.getReference(mParam1).child("teams").child("playersInMatch").child(team).child("players");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    int g = 0;
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        playerdetails pobj = child.getValue(playerdetails.class);

                        arrayList.add(new playersdata(pobj.getBattingStyle(),pobj.getBio(),pobj.getBowlingStyle(),pobj.getDidYouKnow(),pobj.getDob(),pobj.getFirstName(),pobj.getFullName(),pobj.getHeight(),pobj.getImageURL(),pobj.getLastName(),pobj.getOdiDebutDate(),pobj.getPlayerId(),pobj.getPlayerType(),pobj.getT20DebutDate(),pobj.getTestDebutDate()));

                    }
                    listadapter listadapter = new listadapter(getActivity(), arrayList);
                    lv_players.setAdapter(listadapter);

                    /**SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                    prefEditor.putString("T1P", listData.Shield);
                    prefEditor.putString("T2P", listData.Hname);
                    prefEditor.putString("T1BAS", listData.Aname);
                    prefEditor.putString("T2BAS", listData.Link1);
                    prefEditor.putString("T1BOS", listData.Link2);
                    prefEditor.putString("T2BOS", listData.Hbgurl);
                    prefEditor.putString("T1Bio", listData.Abgurl);
                    prefEditor.putString("T2Bio", listData.Hcol);
                    prefEditor.putString("T1PURL", listData.Asname);
                    prefEditor.putString("T2PURL", listData.Hisbat);
                    prefEditor.apply();**/

                }catch(Exception pe){
                    FancyToast.makeText(getActivity(),"Sorry some players do not exist on the database!",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}