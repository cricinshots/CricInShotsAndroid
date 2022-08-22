package com.indevinfinity.cricinshots;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewAnimator;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
public class front_activity extends AppCompatActivity {
    FrameLayout fl;
    LinearLayout l1,l2,l3;
    int show_case =1;
    ViewAnimator va1;
    Button b1,b2,b3,b4;
    public ListView commenlist;
    VideoView vw1;
    TextView batsman1_tag,batsman2_tag,bowler_tag,country1_score,country2_score,current,country1,country2,batting_team,curr_overs,frontscore,crr_view,req_view,needs2win,curr_batsmanview,curr_batstat,target_view,over_view,b1view,b2view,b3view,b4view,b5view,b6view;
    ImageView country1_flag,country2_flag,iw;
    private Handler handler = new Handler();
    //ViewFlipper vf;
    String matchid,matchurl;
    SwipeRefreshLayout pullToRefresh;
    String teamA,teamB,teamA_score,teamB_score,match_current,batsman1="---",batsman2="---";
    ImageButton but_set;
    String ID,t1furl,t2furl;
    String[] idarray;
    public ArrayList<String> commen,batsmen,bowlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_activity);
        MainActivity.whereisuser=1;
        country1 = findViewById(R.id.c1_name);
        country2 = findViewById(R.id.c2_name);
        matchid = getIntent().getExtras().get("matchid").toString();
        matchurl = "https://cricinshots-functions.azurewebsites.net/api/matchinfo/" + matchid + "?code=H7j6c4RUNw3pMhuXGEQoMjUwxjkw7USYBGMmoMsS3uTZs4GfCpZFZQ==";
        getContentfromserver(matchurl);
        //vf = findViewById(R.id.frontflip);
        //vf.setDisplayedChild(1);
        commen  =  new ArrayList<String>();
        batsmen = new ArrayList<String>();
        bowlers = new ArrayList<String>();
        batsmen.add("Rohit Sharma 🏏");
        batsmen.add("MS Dhoni");
        batsmen.add("Hardik Pandya");
        batsmen.add("Virat Kohli");
        batsmen.add("Shikhar Dhawan");
        bowlers.add("Kane Williamson");
        bowlers.add("Mitchel Santner");
        bowlers.add("Martin Guptil");
        bowlers.add("Ross Taylor 🥎");
        bowlers.add("Ish Sodhi");
        commen.add("Viljoen to Junior Dala, 1 run, Shamsi cannot believe it, Faf cannot believe it and Viljoen of course didn't beleive it! It surely wasn't Viljoen fault. He bowls a brilliant slower delivery, Junior Dala was beaten by it totally ends up lobbing it up in the air off the leading edge. Shamsi at short cover lost it in the lights and he never went for the catch instead put his hands in front of his eyes. Faf didn't go for the catch because he thought it's Shamsi's catch. In the end, Junior Dala survived!");
        commen.add("Viljoen to Tahir, no run, short ball down the leg-side, Tahir lets it go");
        commen.add("Viljoen to Onke Nyaku, no run, slower one wide of off, Onke Nyaku swings and misses");
        commen.add("Viljoen to Junior Dala, 1 run, wanted to work it to the on-side, it comes off the leading edge and comes back to Viljoen, he fails to react in time and ends up spilling it");
        commen.add("Udana to B Hendricks, 2 runs, pushes it away wide of cover point and comes back for a double");
        commen.add("Ferisco Adams to Ben Dunk, 1 run, slower ball on a back of a length, Ben Dunk pulls it away to deep backward square leg");
        commen.add("Ferisco Adams to Chris Morris, 2 runs, very full and wide of off, Chris Morris carves it away to long-off");
        commen.add("Viljoen to Junior Dala, 1 run, wanted to work it to the on-side, it comes off the leading edge and comes back to Viljoen, he fails to react in time and ends up spilling it");
       // commenlist = findViewById(R.id.commentaryListView);
        fl = findViewById(R.id.rl);
        iw = findViewById(R.id.sv);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        //vw1 = findViewById(R.id.video_view);
        l3 = findViewById(R.id.l3);
        va1 = findViewById(R.id.viewanim);
        va1.setDisplayedChild(1);
        batsman1_tag = findViewById(R.id.bat1_tag);
        batsman2_tag = findViewById(R.id.bat2_tag);
        bowler_tag = findViewById(R.id.bowler_tag);
        country1_score = findViewById(R.id.c1_score);
        country2_score = findViewById(R.id.c2_score);
        //current = findViewById(R.id.current);
        country1_flag = findViewById(R.id.flag1);
        country2_flag = findViewById(R.id.flag2);
        b1 = findViewById(R.id.button_com);
        b2 = findViewById(R.id.button_teams);
        b3 = findViewById(R.id.button_players);
        b4 = findViewById(R.id.button_chat);
        but_set = findViewById(R.id.button_settings);
        batting_team = findViewById(R.id.stat_battingteam);
        curr_overs = findViewById(R.id.stat_currentscoreover);
        frontscore = findViewById(R.id.stat_currentscore);
        crr_view = findViewById(R.id.crr);
        req_view = findViewById(R.id.reqrr);
        needs2win = findViewById(R.id.howmanytowin);
        curr_batsmanview = findViewById(R.id.stat_currentbat);
        curr_batstat = findViewById(R.id.stat_batsmanstat);
        target_view = findViewById(R.id.stat_target);
        over_view = findViewById(R.id.overview_no);
        b1view = findViewById(R.id.oball1);
        b2view = findViewById(R.id.oball2);
        b3view = findViewById(R.id.oball3);
        b4view = findViewById(R.id.oball4);
        b5view = findViewById(R.id.oball5);
        b6view = findViewById(R.id.oball6);
        //pullToRefresh = findViewById(R.id.pullToRefresh);
        b2.setTextColor(getResources().getColor(R.color.MyGray));
        bg_tasks();

        iw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(front_activity.this,stat_activity.class);
                intent.putExtra("matchid",matchid);
                intent.putExtra("matchurl",matchurl);
                startActivity(intent);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alltextnormal();
                b1.setTextColor(getResources().getColor(R.color.MyGray));
                va1.setDisplayedChild(0);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                               // commentary();
                                //Toast.makeText(getApplicationContext(),"I am running",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                },1000,1000);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alltextnormal();
                b2.setTextColor(getResources().getColor(R.color.MyGray));
                va1.setDisplayedChild(1);
                stats();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**  vf.setDisplayedChild(1);
                b3.setTextColor(getResources().getColor(R.color.MyGray));
                String vimeoVideo = "<html><body><iframe src=\"https://cricbuzz.tv/world-cup-2019-live-streaming/fvp-6/\" allowfullscreen  width=\"640\" height=\"362\" frameborder=\"0\" style=\"max-width:100%\"></iframe></body></html>";

                WebView webView =  findViewById(R.id.myWebView);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {

                        webView.loadUrl(request.getUrl().toString());
                        return true;
                    }
                });
               WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setPluginState(WebSettings.PluginState.ON);
                webView.getSettings().setMediaPlaybackRequiresUserGesture(true);
                webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                webView.loadData(vimeoVideo, "text/html", "utf-8");
                alltextnormal();
                b3.setTextColor(getResources().getColor(R.color.MyGray));
                //va1.setDisplayedChild(2);
                //players();
                Intent intent = new Intent(front_activity.this,webactivity.class);
                startActivity(intent);

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alltextnormal();
                b4.setTextColor(getResources().getColor(R.color.MyGray));
                va1.setDisplayedChild(3);
                chat();

            }
        });
        but_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(front_activity.this,settings_activity.class));

            }
        });

        /**pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FancyToast.makeText(front_activity.this,"Refreshing...",FancyToast.LENGTH_LONG,FancyToast.CONFUSING,false).show();
                bg_tasks();
                pullToRefresh.setRefreshing(false);
            }
        });


    }
    @Override
    public void onBackPressed() {
        MainActivity.whereisuser=0;
        startActivity(new Intent(front_activity.this,choose_match.class));
    }
    protected void onPause()
    {
        super.onPause();
        MainActivity.whereisuser=0;
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }
    protected void onResume()
    {
        super.onResume();
        MainActivity.whereisuser=1;
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

    }
    public void onDestroy(){
        MainActivity.whereisuser=0;
        deleteCache(front_activity.this);
        super.onDestroy();
    }
   /** public void commentary(){
        fetchData process = new fetchData();
        process.execute();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,R.layout.custom_listitem, commen);
        // Set The Adapter
        //commenlist.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        commenlist.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                String selectedmatch = commen.get(position);
                Toast.makeText(getApplicationContext(), "Match Selected : " + selectedmatch,   Toast.LENGTH_LONG).show();
                startActivity(new Intent(front_activity.this, front_activity.class));
            }
        });
    }
    public void stats(){
        ListView batsmen_view = findViewById(R.id.list_batsmen);
        ListView bowlers_view = findViewById(R.id.list_bowlers);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,R.layout.custom_listitem2, batsmen);
        ArrayAdapter<String> arrayAdapter2 =
                new ArrayAdapter<String>(this,R.layout.custom_listitem2, bowlers);
        // Set The Adapter
        batsmen_view.setAdapter(arrayAdapter);
        bowlers_view.setAdapter(arrayAdapter2);

        // register onClickListener to handle click events on each item
        batsmen_view.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                String selectedbatsman = batsmen.get(position);
                Toast.makeText(getApplicationContext(), selectedbatsman,   Toast.LENGTH_LONG).show();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(front_activity.this);
                View mView = getLayoutInflater().inflate(R.layout.batsman_dialog, null);
               /** Button okay = mView.findViewById(R.id.bat_okay);
                ImageView batsman_photo = mView.findViewById(R.id.iv_batsman);
                TextView pname = mView.findViewById(R.id.text_pname);
                pname.setText(selectedbatsman);
                TextView about = mView.findViewById(R.id.text_aboutbatsman);
                final RelativeLayout pbg = mView.findViewById(R.id.player_bg);
                Glide.with(front_activity.this).load("https://codersera.tech/cricinshort/forapp/bg.jpg").into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {

                            pbg.setBackground(resource);

                    }
                });
                teamA = "India";
                String playername = selectedbatsman.replaceAll("\\s+","");;
                Glide.with(front_activity.this).load("https://codersera.tech/cricinshort/forapp/TEAM_MEMBERS/INTERNATIONAL/"+teamA+"/"+playername+".png").into(batsman_photo);
                about.setText(Html.fromHtml(getString(R.string.about_bats)));
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.setCancelable(true);
                dialog.show();
                dialog.setCancelable(false);
                okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });
    }
    public void players(){

    }
    public void chat(){
        ImageButton button_send;
        final EditText edit_message;
        button_send = findViewById(R.id.sendButton);
        edit_message = findViewById(R.id.messageEditText);
        edit_message.requestFocus();
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String message;
                message = edit_message.getText().toString();
                edit_message.setText("");
            }
        });
    }
    public void alltextnormal(){
        b1.setTextColor(getResources().getColor(R.color.Gray));
        b2.setTextColor(getResources().getColor(R.color.Gray));
        b3.setTextColor(getResources().getColor(R.color.Gray));
        b4.setTextColor(getResources().getColor(R.color.Gray));
    }
    public void bg_tasks(){
        /**Thread thread = new Thread() {
            @Override
            public void run() {

            }
        };
        thread.start();

        stats();
        bg_animation();


    }

    public void bg_animation(){
update();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        if(show_case==1){
                            l1.setVisibility(View.INVISIBLE);
                            l2.setVisibility(View.INVISIBLE);
                            l3.setVisibility(View.INVISIBLE);
                            show_case++;
                        }else if(show_case==2){
                            l1.setVisibility(View.VISIBLE);
                            l2.setVisibility(View.VISIBLE);
                            l3.setVisibility(View.INVISIBLE);
                            show_case++;
                        }else if(show_case==3){
                            l1.setVisibility(View.INVISIBLE);
                            l2.setVisibility(View.INVISIBLE);
                            l3.setVisibility(View.INVISIBLE);
                            show_case++;
                        }else if (show_case==4){
                            l1.setVisibility(View.INVISIBLE);
                            l2.setVisibility(View.INVISIBLE);
                            l3.setVisibility(View.VISIBLE);
                            show_case=1;
                        }

                    }
                });
            }
        },0,1000);
    }
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
    public void update(){
        Timer timeru = new Timer();
        timeru.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        front_activity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                               // batsman1_tag.setText(" ");
                               // batsman2_tag.setText(MyAsyncService.name_of_batsman);
                               // country1_score.setText(MyAsyncService.score+"/");
                                //bowler_tag.setText(MyAsyncService.name_of_bowler);
                            }
                        });
                    }
                });

            }
        },0,9000);

    }
    public void getContentfromserver(final String sURL){

        new Thread(new Runnable() {
            public void run(){
                Looper.prepare();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                final RequestQueue queue = Volley.newRequestQueue(front_activity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, sURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String response) {
                                // Display the first 500 characters of the response string.
                                // textView.setText("Response is: "+ response.substring(0,500));
                                // System.out.println(response);
                                try {
                                    JSONObject myObject = new JSONObject(response);
                                    // System.out.println(response);
                                    JSONObject matchdetailsObject = myObject.getJSONObject("matchdetails");
                                    JSONObject matchObject = matchdetailsObject.getJSONObject("match");
                                    JSONObject hometeamObject = matchObject.getJSONObject("homeTeam");
                                    teamA = hometeamObject.get("name").toString();
                                    if(teamA.contains(" Men")){
                                        teamA = teamA.replace(" Men","");
                                    }else if(teamA.contains(" Women")){
                                        teamA = teamA.replace(" Women","");
                                    }
                                    t1furl = hometeamObject.get("logoUrl").toString();
                                    JSONObject awayteamObject = matchObject.getJSONObject("awayTeam");
                                    teamB = awayteamObject.get("name").toString();
                                    if(teamB.contains(" Men")){
                                        teamB = teamB.replace(" Men","");
                                    }else if(teamB.contains(" Women")){
                                        teamB = teamB.replace(" Women","");
                                    }
                                    t2furl = awayteamObject.get("logoUrl").toString();
                                    front_activity.this.runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            Glide.with(front_activity.this).load(t1furl).into(country1_flag);
                                            Glide.with(front_activity.this).load(t2furl).into(country2_flag);
                                            country1.setText(teamA);
                                            country2.setText(teamB);

                                        }
                                    });

                                    //System.out.println(k_val);
                                    /**JSONArray matchesArray = matchListObject.getJSONArray("matches");
                                    idarray = new String[matchesArray.length()];
                                    for (int i=0; i < matchesArray.length(); i++)
                                    {
                                        try {
                                            JSONObject oneObject = matchesArray.getJSONObject(i);
                                            String bID = oneObject.getString("id");
                                            JSONObject sinfoObject = oneObject.getJSONObject("series");
                                            String mname = sinfoObject.getString("name");
                                            String sID = sinfoObject.getString("id");
                                            ID = sID + "-" + bID;
                                            //matches.add(mname);
                                            idarray[i] = ID;
                                            System.out.println(ID+" " + mname);
                                        } catch (JSONException e) {
                                            // Oops
                                        }
                                    }
                                    //System.out.println(matches.toString());
                                }catch (JSONException e){
                                    System.out.println("Blue "+e.toString());
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(front_activity.this,"That didn't work!",Toast.LENGTH_LONG).show();
                        System.out.println("That didn't work!");
                    }
                });
                queue.add(stringRequest);
                Looper.loop();

            }
        }).start();

    }
}**/
