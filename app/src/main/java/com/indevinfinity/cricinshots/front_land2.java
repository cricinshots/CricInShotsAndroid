package com.indevinfinity.cricinshots;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.HashMap;

public class front_land2 extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private String matchid, matchurl, whobatting, cb_score = "0", curballid, currbatsman, offstrikebatid, offstriker, shield, wtb,wickets;
    String[] homeTeam = new String[6];
    String[] awayTeam = new String[6];
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef, myRef2, myRef3, myRef4, myRef5, myRef6, myRef7, myRef8, myRef9;
    //ImageButton btn_hide;
    //RelativeLayout rl_top;
    boolean hidden = false,iswide = false,iswicket=false;
    int currinn, jo = 0, i, cbt,commenlen;
    public static String[] cover = {"no", "-", "-", "-", "-", "-", "-"};
    String[] team = new String[2];
    String[] teamcolor = new String[2];
    String[] teambgurl = new String[2];
    String[] teamlogourl = new String[2];
    String[] teamsname = new String[2];
    String[] commens,commenhead,commenballov;
    int[] teamid = new int[2];
    private fragmentinterface listener ;
    private HashMap<DatabaseReference, ValueEventListener> hashMap = new HashMap<>();
    ImageView c1_flag, c2_flag, iv_shield;
    TextView c1_title, c2_title, tv_cbs, tv_cov, tv_currbat, tv_onstrike, tv_offstrike;
    FrameLayout fm_pers,fm_holder;
    LinearLayout rel_nstat;
    String coverid, co_balls,nonballtext,currbowler,shscore="0/0",older_t_score = "-1";
    TextView b1,b2,b3,b4,b5,b6,overrev,overextras,tv_ooid,tv_rino,tv_wino,tv_ato,tv_hto;
    ImageView iv_pff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_land2_light);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        //rl_top = findViewById(R.id.toprl);
        //rl_top.setVisibility(View.VISIBLE);
        //iv_shield = findViewById(R.id.iv_shield);
        matchid = getIntent().getExtras().get("matchid").toString();
        System.out.println("Matchid = "+matchid);
        c1_flag = findViewById(R.id.iv_flag_bat);
        c2_flag = findViewById(R.id.iv_flag_bowl);
        c1_title = findViewById(R.id.tv_bat_team_name);
        //note: this tv also shows the score
        c2_title = findViewById(R.id.tv_bowl_team_name);
        //btn_hide = findViewById(R.id.ibtn_hide);
        tv_cbs = findViewById(R.id.tv_batting_team_score);
        tv_cov = findViewById(R.id.tv_overs);
        tv_currbat = findViewById(R.id.tv_score_bowl);
        tv_onstrike = findViewById(R.id.tv_score_bat_1);
        tv_offstrike = findViewById(R.id.tv_score_bat_2);
        /*fm_pers = findViewById(R.id.landpersstad);
        fm_holder = findViewById(R.id.container);
        rel_nstat = findViewById(R.id.rel_nayastat);*/
        //openFragment(StatFrag.newInstance(matchid,""));
        //overextras = findViewById(R.id.tv_extras);
        //overrev = findViewById(R.id.tv_ballingfigs);
        //tv_ooid = findViewById(R.id.tv_overoverviewid);
        /*tv_rino = findViewById(R.id.tv_rino);
        tv_wino = findViewById(R.id.tv_wino);*/
        /*tv_hto = findViewById(R.id.tv_hteamoverview);
        tv_ato = findViewById(R.id.tv_ateamoverview);*/
        iv_pff = findViewById(R.id.iv_bowl);
        /*fm_holder.setVisibility(View.GONE);
        rel_nstat.setVisibility(View.VISIBLE);*/

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(front_land2.this);
        team[0] = prefs.getString("T1", "null");
        team[1] = prefs.getString("T2", "null");
        teamsname[0] = prefs.getString("Hsname", "null");
        teamsname[1] = prefs.getString("Asname", "null");
        teambgurl[0] = prefs.getString("Bg1", "null");
        teambgurl[1] = prefs.getString("Bg2", "null");
        teamlogourl[0] = prefs.getString("F1", "null");
        teamlogourl[1] = prefs.getString("F2", "null");
        teamcolor[0] = prefs.getString("Hcol", "null");
        teamcolor[1] = prefs.getString("Acol", "null");
        shield = prefs.getString("Shield", "null");
        boolean hbat = prefs.getBoolean("Hisbat", false);
        boolean abat = prefs.getBoolean("Aisbat", false);

        if (hbat) {
            whobatting = team[0];
            wtb = "homeTeam";
            cbt = 0;
        } else if (abat) {
            whobatting = team[1];
            wtb = "awayTeam";
            cbt = 1;
        }

        if (team[0].contains(" Men")) {
            team[0] = team[0].replace(" Men", "");
        } else if (team[0].contains(" Women")) {
            team[0] = team[0].replace(" Women", "");
        }
        if (team[1].contains(" Men")) {
            team[1] = team[1].replace(" Men", "");
        } else if (team[1].contains(" Women")) {
            team[1] = team[1].replace(" Women", "");
        }
        /*if (!shield.equals("null")) {
            Glide.with(front_land2.this).load(shield).into(iv_shield);
        }*/
        Glide.with(front_land2.this).load(teamlogourl[0]).into(c1_flag);
        Glide.with(front_land2.this).load(teamlogourl[1]).into(c2_flag);
        String batting = team[cbt].substring(0,3);
        //String batting = team[cbt];
        if (cbt == 0) {
            c1_title.setText(batting);
            c2_title.setText(team[1].substring(0,3));
        } else if (cbt == 1) {
            c2_title.setText(team[0].substring(0,3));
            c1_title.setText(batting.substring(0,3));
        }
        getdatafromFirebase();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                 .setAction("Action", null).show();**//*
                FancyToast.makeText(front_land2.this,"Animation mode is under maintenance, please try again later!",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();*/
                Intent intent = new Intent(front_land2.this, stat_activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("matchid", matchid);
                startActivity(intent);

//                Intent intent = new Intent(front_land2.this, isworking.class);
//                 startActivity(intent);

            }
        });

/*        btn_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hidden) {
                    fm_pers.setVisibility(View.VISIBLE);
                    //rl_top.setVisibility(View.VISIBLE);
                    btn_hide.setImageResource(R.drawable.ic_up);
                } else if (!hidden) {
                    //rl_top.setVisibility(View.GONE);
                    fm_pers.setVisibility(View.GONE);
                    btn_hide.setImageResource(R.drawable.ic_down);
                }

                hidden = !hidden;

            }
        });*/

/*        fm_pers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FancyToast.makeText(front_land2.this,"Animation mode is under maintenance, please try again later!",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                //Intent intent = new Intent(front_land2.this, stat_activity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //intent.putExtra("matchid", matchid);
                //startActivity(intent);
            }
        }); */
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(front_land2.this, MainActivity.class));
    }

    @Override
    public void onStop() {

        super.onStop();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_view, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    ConstraintLayout cbottom, cParent;

                    cParent = findViewById(R.id.cons_main);
                    cbottom = findViewById(R.id.cons_commentary);

                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(cParent);

                    ListView lvCom = findViewById(R.id.lv_commentary);

                    View f = findViewById(R.id.frag_view);
                    switch (item.getItemId()) {
                        case R.id.navigation_stats:
                            //fm_holder.setVisibility(View.GONE);
                            //rel_nstat.setVisibility(View.VISIBLE);
                            //openFragment(StatFrag.newInstance("", ""));
                            f.setVisibility(View.GONE);
                            lvCom.setVisibility(View.VISIBLE);
                            constraintSet.connect(R.id.cons_commentary,ConstraintSet.TOP,R.id.cons_score_snippet,ConstraintSet.BOTTOM,8);
                            constraintSet.applyTo(cParent);
                            cbottom.setElevation(5);
                            return true;
                        case R.id.navigation_teams:
                            //rel_nstat.setVisibility(View.GONE);
                            //fm_holder.setVisibility(View.VISIBLE);
                            openFragment(teamfragment.newInstance(matchid, teamsname[0] + "," + teamsname[1], teamcolor[0] + "," + teamcolor[1]));
                            f.setVisibility(View.VISIBLE);
                            lvCom.setVisibility(View.INVISIBLE);
                            constraintSet.connect(R.id.cons_commentary,ConstraintSet.TOP,R.id.cons_score_collapse,ConstraintSet.BOTTOM,8);
                            constraintSet.applyTo(cParent);
                            cbottom.setElevation(15);
                            return true;
                        case R.id.navigation_news:
                            //rel_nstat.setVisibility(View.GONE);
                            //fm_holder.setVisibility(View.VISIBLE);
                            openFragment(News.newInstance("", ""));
                            f.setVisibility(View.VISIBLE);
                            lvCom.setVisibility(View.INVISIBLE);
                            constraintSet.connect(R.id.cons_commentary,ConstraintSet.TOP,R.id.cons_score_collapse,ConstraintSet.BOTTOM,8);
                            constraintSet.applyTo(cParent);
                            cbottom.setElevation(15);
                            return true;
                        /**case R.id.navigation_settings:
                            //rel_nstat.setVisibility(View.GONE);
                            //fm_holder.setVisibility(View.VISIBLE);
                            openFragment(SettingsFrag.newInstance("", ""));
                            f.setVisibility(View.VISIBLE);
                            lvCom.setVisibility(View.INVISIBLE);
                            constraintSet.connect(R.id.cons_commentary,ConstraintSet.TOP,R.id.cons_score_collapse,ConstraintSet.BOTTOM,8);
                            constraintSet.applyTo(cParent);
                            cbottom.setElevation(15);
                            return true;**/
                    }
                    return false;
                }
            };


    public void getdatafromFirebase() {

        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                myRef3 = database.getReference(matchid).child("commentary").child("commentary").child("innings").child("0").child("overs").child("0");
                myRef3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshotx) {

                        try{
                            co_balls  =  String.valueOf((long) dataSnapshotx.child("balls").child("0").child("ballNumber").getValue());

                            coverid = String.valueOf((long) dataSnapshotx.child("id").getValue());

                            nonballtext = " ";
                            for (DataSnapshot child : dataSnapshotx.child("balls").child("0").child("comments").getChildren()) {

                                String btype = child.child("ballType").getValue().toString();

                                if(btype.equals("NonBallComment")){
                                    nonballtext = child.child("text").getValue().toString();
                                }
                                if (btype.equals("LegitOffBat") || btype.equals("Wide")) {
                                    commentarydetails cdobj = child.getValue(commentarydetails.class);

                                    if(btype.equals("Wide")){
                                        iswide = true;
                                    }
                                    cb_score = String.valueOf(cdobj.getBattingTeamScore());
                                    iswicket = cdobj.getIsFallOfWicket();
                                    currbatsman = cdobj.getBatsmanName();
                                    final String b1url = cdobj.getBatsmanImageURL();
                                    final String bowlerurl = cdobj.getBowlerImageURL();
                                    offstrikebatid = String.valueOf(cdobj.getOffStrikeBatsmanId());
                                    wickets = String.valueOf(cdobj.getWickets());
                                    currbowler = cdobj.getBowlerName();
                                    myRef6 = database.getReference(matchid).child("teams").child("playersInMatch").child(wtb).child("players");
                                    System.out.println("CBT: " + teamid[cbt]);
                                    myRef6.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                                            for (DataSnapshot child : dataSnapshot2.getChildren()) {
                                                if (child.child("playerId").getValue().toString().equals(offstrikebatid)) {
                                                    offstriker = child.child("fullName").getValue().toString();
                                                    String q = "null",r="null",s="null",t="null",u="null";
                                                    try{
                                                        q = child.child("imageURL").getValue().toString();

                                                    }catch(Exception j){
                                                        //
                                                    }
                                                    try{

                                                        r=  dataSnapshotx.child("overSummary").child("extrasConcededinOver").getValue().toString();

                                                    }catch(Exception j){
                                                        //
                                                    }
                                                    try{

                                                        s = dataSnapshotx.child("overSummary").child("wicketsTakeninOver").getValue().toString();

                                                    }catch(Exception j){
                                                        //
                                                    }
                                                    try{

                                                        t = dataSnapshotx.child("overSummary").child("runsConcededinOver").getValue().toString();

                                                    }catch(Exception j){
                                                        //
                                                    }
                                                    try{
                                                        u = dataSnapshotx.child("overSummary").child("bowlersBowlingFigures").getValue().toString();
                                                    }catch(Exception j){
                                                        //
                                                    }

                                                    final String b2url = q;
                                                    final String extrasConcededinOver = r;
                                                    final String wicketsTakeninOver = s;
                                                    final String runsConcededinOver = t;
                                                    final String bowlersBowlingFigures = u;

                                                    front_land2.this.runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try{
                                                                tv_offstrike.setText(offstriker);
                                                                tv_onstrike.setText(currbatsman);
                                                                String kyaz = cb_score+" / "+wickets;
                                                                if(!kyaz.equals("0 / 0")){
                                                                    shscore = kyaz;
                                                                }

                                                                tv_cbs.setText(shscore);

                                                                String ots = null;
                                                                if(co_balls.equals("6") && !iswide){
                                                                    ots = "Overs: " + String.valueOf((Integer.parseInt(coverid)+1));
                                                                }else{
                                                                    ots = "Overs: " + coverid + "." + co_balls;
                                                                }

                                                                String ots2 = "Over " + coverid + " summary:";

                                                                //String cut1 = mainfig.substring(mainfig.lastIndexOf(":") + 1);
                                                                String temp = bowlersBowlingFigures;
                                                                temp = temp.replaceAll("\\D+",",");
                                                                String[] cutter = temp.split(",");
                                                                String mainfig = cutter[2] + "-" + cutter[1]+" ";
                                                                String br = currbowler +": " + mainfig;
                                                                tv_currbat.setText(br);
                                                                tv_cov.setText(ots);
                                                                //overrev.setText(br);
                                                                //overextras.setText(extrasConcededinOver);
                                                                //tv_ooid.setText(nonballtext);
                                                                //tv_rino.setText(runsConcededinOver);
                                                                //tv_wino.setText(wicketsTakeninOver);
                                                                System.out.println("I am trying to load the pics");
                                                                Glide.with(front_land2.this).load(bowlerurl).into(iv_pff);
                                                                ImageView b1p,b2p;
                                                                b1p = findViewById(R.id.iv_score_bat_1);
                                                                b2p = findViewById(R.id.iv_score_bat_2);
                                                                Glide.with(front_land2.this).load(b1url).into(b2p);
                                                                Glide.with(front_land2.this).load(b2url).into(b1p);
                                                            }catch (Exception e){
                                                                System.out.println("I am not trying to load the pics as "+e.toString());
                                                            }

                                                            //getcurrinfromFirebase();

                                                            myRef5 =  database.getReference(matchid).child("matchdetails").child("currentInningId");
                                                            myRef5.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    long bruh = (long) dataSnapshot.getValue();
                                                                    currinn = (int) bruh;

                                                                    if(currinn>1 && older_t_score.equals("-1")){
                                                                        myRef2 = database.getReference(matchid).child("commentary").child("commentary").child("innings").child(String.valueOf(currinn-1)).child("overs").child("0").child("overSummary").child("battingTeamsScore");
                                                                        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                older_t_score = dataSnapshot.getValue().toString();
                                                                                String[] oscore = new String[2];
                                                                                oscore = older_t_score.split("-");
                                                                                older_t_score = oscore[1] + "/" + oscore[0];
                                                                                front_land2.this.runOnUiThread(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        if(cbt==0){
                                                                                            tv_cbs.setText(older_t_score);

                                                                                        }
                                                                                            //tv_ato.setText(older_t_score);

                                                                                        if(cbt==1){
                                                                                            c2_title.setText(c2_title.getText().subSequence(0,3) + ": "+older_t_score);
                                                                                        }
                                                                                            //tv_hto.setText(older_t_score);

                                                                                    }
                                                                                });

                                                                            }

                                                                            @Override
                                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                            }
                                                                        });
                                                                    }

                                                                    if(cbt==0){
                                                                        //tv_hto.setText(shscore);
                                                                        c1_title.setText(shscore);
                                                                        if(currinn<=1){
                                                                            //tv_ato.setText("YTB");
                                                                            c2_title.setText(c2_title.getText().subSequence(0,3) + ": YTB");
                                                                        }
                                                                        //todo: set other team string
                                                                    }else if(cbt==1){
                                                                        //tv_ato.setText(shscore);
                                                                        c2_title.setText(c2_title.getText().subSequence(0,3) + ": "+shscore);
                                                                        if(currinn<=1){
                                                                            //tv_hto.setText("YTB");
                                                                            c2_title.setText(c2_title.getText().subSequence(0,3) + ": YTB");
                                                                           }
                                                                    }

                                                                }

                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {

                                                                    currinn = 0;

                                                                }
                                                            });

                                                            myRef4 = database.getReference(matchid).child("commentary").child("commentary").child("innings").child("0").child("overs").child("0").child("balls");

                                                            myRef4.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                    commenlen = (int) dataSnapshot.getChildrenCount();
                                                                    commens = new String[commenlen];
                                                                    commenhead = new String[commenlen];
                                                                    commenballov = new String[commenlen];
                                                                    boolean[] iswic = new boolean[commenlen];
                                                                    int k = 0;
                                                                    for(DataSnapshot child : dataSnapshot.getChildren()){

                                                                        for(DataSnapshot child2 : child.child("comments").getChildren()){

                                                                            String btype = child2.child("ballType").getValue().toString();

                                                                            if(btype.equals("LegitOffBat") || btype.equals("Wide")){
                                                                                commens[k] = child2.child("text").getValue().toString();
                                                                                commenhead[k] = coverid +"."+ child.child("ballNumber").getValue().toString();
                                                                                commenballov[k] = child2.child("runs").getValue().toString();
                                                                                iswic[k] = (boolean) child2.child("isFallOfWicket").getValue();

                                                                            }

                                                                        }
                                                                        k++;
                                                                    }

                                                                    updatecommen();
                                                                    myRef7 = database.getReference(matchid).child("scorecard").child("fullScorecard").child("innings").child("0");
                                                                    myRef7.addValueEventListener(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                            ArrayList<batstat_data> barrayList = new ArrayList<batstat_data>();

                                                                            for(DataSnapshot child : dataSnapshot.child("batsmen").getChildren()){
                                                                                //laters = new String[(int) dataSnapshot.child("batsmen").getChildrenCount()][10];
                                                                                batstat _batstat = child.getValue(com.indevinfinity.cricinshots.batstat.class);
                                                                                if(child.child("name").getValue().toString().equals(currbatsman)){
                                                                                    barrayList.add(0,new batstat_data(_batstat.getBalls(),_batstat.getFours(),_batstat.fowOrder,_batstat.howOut,_batstat.getId(),_batstat.getImageURL(),_batstat.getName(),_batstat.getRuns(),_batstat.getSixes(),_batstat.getStrikeRate()));
                                                                                }else if(child.child("name").getValue().toString().equals(offstriker)){
                                                                                    barrayList.add(((barrayList.size()>0)?1:0),new batstat_data(_batstat.getBalls(),_batstat.getFours(),_batstat.fowOrder,_batstat.howOut,_batstat.getId(),_batstat.getImageURL(),_batstat.getName(),_batstat.getRuns(),_batstat.getSixes(),_batstat.getStrikeRate()));
                                                                                }else{
                                                                                    if(!_batstat.getBalls().isEmpty()){
                                                                                        barrayList.add(((barrayList.size()>1)?2:((barrayList.size()>0)?1:0)),new batstat_data(_batstat.getBalls(),_batstat.getFours(),_batstat.fowOrder,_batstat.howOut,_batstat.getId(),_batstat.getImageURL(),_batstat.getName(),_batstat.getRuns(),_batstat.getSixes(),_batstat.getStrikeRate()));
                                                                                    }
                                                                                }

                                                                            }
                                                                            barrayList.add(0,new batstat_data("B","4",0,"",0,""," ","R","6","SR"));



                                                                            ArrayList<scdata> mList = new ArrayList<scdata>();
                                                                            ArrayList<overballdata> bList = new ArrayList<overballdata>();
                                                                            int size = 0,size2=0;

                                                                            mList.add(new scdata("Bye",dataSnapshot.child("bye").getValue().toString())); size++;
                                                                            mList.add(new scdata("Extra",dataSnapshot.child("extra").getValue().toString())); size++;
                                                                            mList.add(new scdata("Leg Bye",dataSnapshot.child("legBye").getValue().toString())); size++;
                                                                            mList.add(new scdata("No ball",dataSnapshot.child("noBall").getValue().toString())); size++;
                                                                            mList.add(new scdata("Wide",dataSnapshot.child("wide").getValue().toString())); size++;
                                                                            mList.add(new scdata("CRR",dataSnapshot.child("runRate").getValue().toString())); size++;
                                                                            String ch = dataSnapshot.child("requiredRunRate").getValue().toString();
                                                                            if(!ch.equals(" ") && !ch.equals("")){
                                                                                mList.add(new scdata("RRR",ch)); size++;
                                                                            }
                                                                            int ll = commenballov.length;
                                                                            if(ll<6){
                                                                                ll = 6;
                                                                            }
                                                                            for(int l = 0; l<ll;l++){
                                                                                if(l>=commenballov.length){
                                                                                    bList.add(new overballdata(l,"-"));
                                                                                }else{
                                                                                    bList.add(new overballdata(l,commenballov[l]));
                                                                                }

                                                                                size2++;
                                                                            }

                                                                            final ColumnAdapter cadapter = new ColumnAdapter(mList);
                                                                            final overballadapter odapter = new overballadapter(bList);
                                                                            final Bat_adapter bat_adapter = new Bat_adapter(front_land2.this, barrayList);
                                                                            final int s = size;
                                                                            final int s2 = size2;
                                                                            front_land2.this.runOnUiThread(new Runnable() {
                                                                                @Override
                                                                                public void run() {

                                                                                    //ListView lv = findViewById(R.id.ls_bat_info);
                                                                                    //lv.setAdapter(bat_adapter);
                                                                                    //don't show extras and all that here
                                                                                    /*RecyclerView rv = findViewById(R.id.rv_col);
                                                                                    rv.setHasFixedSize(true);
                                                                                    rv.setLayoutManager(new GridLayoutManager(front_land2.this, s, RecyclerView.VERTICAL, false));
                                                                                    rv.setAdapter(cadapter);*/

                                                                                    RecyclerView rv_eb = findViewById(R.id.rv_overView);
                                                                                    rv_eb.setHasFixedSize(true);
                                                                                    rv_eb.setLayoutManager(new GridLayoutManager(front_land2.this, s2, RecyclerView.VERTICAL, false));
                                                                                    rv_eb.setAdapter(odapter);
                                                                                }
                                                                            });


                                                                        }

                                                                        @Override
                                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                        }
                                                                    });
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        }catch (Exception crash){
                            front_land2.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog alertDialog = new AlertDialog.Builder(front_land2.this).create();
                                    alertDialog.setTitle("Server Error");
                                    alertDialog.setMessage("Please try again");
                                    alertDialog.setCancelable(false);
                                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Exit",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    finish();
                                                    System.exit(0);
                                                }
                                            });
                                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Retry",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Looper.loop();

            }
        }).start();


    }

    public void getcurrinfromFirebase() {



    }

    void getBall() {


    }


    public void updatecommen(){
        ArrayList<commentarydata> arrayList = new ArrayList<commentarydata>();
        int ad = 1, noad = 0;
        for(int y=0;y<commenlen;y++){

            arrayList.add(new commentarydata(commens[y],commenhead[y]));


        }
        ListView lv = findViewById(R.id.lv_commentary);
        Commenadapter2 commenadapter = new Commenadapter2(front_land2.this, arrayList);
        lv.setAdapter(commenadapter);
    }
}
