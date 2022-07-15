//destroy timer ondestrop onstop
package com.indevinfinity.cricinshots;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import android.os.Bundle;
import android.os.Looper;
import android.os.PowerManager;
import android.os.StrictMode;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;

public class stat_activity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef, myRef2, myRef3, myRef4, myRef5, myRef6,myRef7,myRef8,myRef9;
    String currwickets,currbowler="Loading..",currcomment,ballsinco,commentarylivid_i="0",commentarylivid_o="0",commentarylivid_b="0",wtb,liveballid,sdata = "nada", curballid = "-1", useratball = "0", lastball = "b0", matchid, ID, whobatting, s_val = "fail", onstrikebtm = "---", offstrikebtm = "---", tscore, twickets, onstrikeid, offstrikeid, cbowler, cbowlerid, cbruns;

    String[] team = new String[2];
    String[] teamcolor = new String[2];
    String[] teambgurl = new String[2];
    String[] teamlogourl = new String[2];
    String[] teamsname = new String[2];

    CircleImageView l1,l2;
    int[] teamid = new int[2];
    int locale,range_counter = 0,ballsinover, swic,currover,oldover=-1;
    private HashMap<DatabaseReference, ValueEventListener> hashMap = new HashMap<>();
    Thread threadgif; //bX calced 54.62963f
    int cbt, quadrant, rundone = 0, currinn=2, pspeed = 9, ballSpeed = speed_generator(), counter = 0, player_counter, single_run_counter = 0;
    float ballxpercentage = 51.018517f, bX, bY,bYpercent = 70.208336f,bXpercent = 51.018517f,ballypercentage = 55.208332f, p1xpercentage = 53.981483f, p1ypercentage = 55.208332f, p2xpercentage = 50.0f, p2ypercentage = 40.625f,distanceToRun;
    private float canvasWidth, canvasHeight, ballX, ballY, p1X, p1Y, p2X, p2Y, tag1X, tag1Y, tag2X, tag2Y, p1yextent, p2yextent,tag3X,tag3Y;
    private long Interval = 80;
    double ballangle, balldistance, btox, btoy;
    boolean isBowlingAnim = true, isBattingAnim = false, a_running = true,animate = false,ballstop = false, accuser = false, golive = true, hua = false, ifgif = false, one_run = false, run_state = false, two_run = false, three_run = false, actually1 = false, runbtwwckt_comp = false, run_allow = true, canceltask = true, cancel2 = true, wicket = false, four4 = false, six6 = false, wideball = false, noball = false, out = false;
    ImageButton bck, btprevball,btnextball,btgolive,bt_review,bt_predict;
    ImageView iv, gv;
    Canvas canvas;
    GifImageView gif;
    Bitmap bitmap;
    LinearLayout ball_lin,lin_statguess_over;
    TextView over_deets, scwic;
    long WAITTIME = 100;
    Timer timer = new Timer();
    Timer global_t;
    TextView mScore, wsBatting, wsOvers, tv_bat_score1,tv_bat_score2;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    FirebaseUser fuser = mAuth.getCurrentUser();
    private Paint ballPaint = new Paint();
    private Paint btmPaint = new Paint();
    private Paint tagPaint = new Paint();
    private Paint bowPaint = new Paint();
    private Handler handler = new Handler();

    int ctr = 0, jo = 0;

    ViewStub stub,stub2;
    LinearLayout overl,balll;
    View inflatedView;
    SwitchMaterial sw_detail;

    FrameLayout fl_stat;
    AnimTask animTask;
    //= 51.018517 55.208332 53.981483 55.208332 50.0 40.625

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stat_activity);
        matchid = getIntent().getExtras().getString("matchid","null");
        init();
        int_controls();
        animTask = new AnimTask(this);
        animTask.execute();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(stat_activity.this, front_land.class);
        intent.putExtra("matchid", matchid);
        startActivity(intent);
        finish();
    }

    public void onDestroy() {
        super.onDestroy();
        finish();
        /**MainActivity.whereisuser = 0;
         deleteCache(stat_activity.this);
         super.onDestroy();**/
    }

    public void onPause(){
        super.onPause();
        finish();
    }


    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    public int speed_generator() {
        int finalspeed;
        Random rand = new Random();
        finalspeed = rand.nextInt((17 - 7) + 1) + 7;
        return finalspeed;
    }

    public void show() {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        ballSpeed = speed_generator();
        ballX = ballxpercentage / 100 * canvasWidth;
        ballY = p2ypercentage / 100 * canvasHeight;
        bX = bXpercent / 100 * canvasWidth;
        bY = bYpercent / 100 * canvasHeight;
        canvas.drawCircle(ballX, ballY, 10, ballPaint);
        canvas.drawCircle(p1X, p1Y, 15, btmPaint);
        canvas.drawCircle(p2X, p2Y, 15, btmPaint);
        canvas.drawText(onstrikebtm, tag1X, tag1Y, tagPaint);
        canvas.drawText(offstrikebtm, tag2X, tag2Y, tagPaint);
        canvas.drawCircle(bX,bY,15,bowPaint);
        canvas.drawText(currbowler,bX-10,bY-35,tagPaint);
        iv.setImageBitmap(bitmap);
    }
    public void show_after() {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawCircle(p1X, p1Y, 15, btmPaint);
        canvas.drawCircle(p2X, p2Y, 15, btmPaint);
        canvas.drawText(offstrikebtm, tag1X, tag1Y, tagPaint);
        canvas.drawText(onstrikebtm, tag2X, tag2Y, tagPaint);
        canvas.drawCircle(bX,bY,15,bowPaint);
        canvas.drawText(currbowler,bX-10,bY-35,tagPaint);
        iv.setImageBitmap(bitmap);
    }


    public double getX(double radians, double distance) {
        double x;
        double cosValue = Math.cos(radians);
        x = cosValue * distance;
        return x;
    }

    public double getY(double radians, double distance) {
        double y;
        double sinValue = Math.sin(radians);
        y = sinValue * distance;
        return y;
    }

    public void ageka() {
        ballstop = false;
        runbtwwckt_comp = false;
        ballX = ballxpercentage / 100 * canvasWidth;
        ballY = p2ypercentage / 100 * canvasHeight;//ballypercentage/100*canvasHeight;
        show();
        System.out.println("Ball ID: " + curballid + " Ball to look: " + String.valueOf(Integer.parseInt(curballid) - 1));
        myRef3 = database.getReference(matchid).child("innings").child(String.valueOf(currinn)).child("battingWheel").child("shots").child(String.valueOf(Integer.parseInt(curballid) - 1));//

        myRef3.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Ball ballobj = dataSnapshot.getValue(Ball.class);
                try {
                    ballangle = Double.parseDouble(ballobj.getAngle());
                    balldistance = Double.parseDouble(ballobj.getDistance());
                    cbruns = ballobj.getRun();
                    System.out.println("Distance: " + balldistance + " Angle: " + ballangle + " Runs: " + cbruns);
                    onstrikeid = String.valueOf(ballobj.getBatsmanId());
                    getbatsmannames();
                    System.out.println("In here 2....");
                    //onstrikebtm
//                    btmPaint.setColor(Color.parseColor(teamcolor[cbt]));
//                    bowPaint.setColor(Color.parseColor(teamcolor[(cbt==1?0:1)]));

                    switch (teamsname[cbt]){
                        case "MI":
                            btmPaint.setColor(getColor(R.color.Blue));
                            break;
                        case "CHE":
                            btmPaint.setColor(getColor(R.color.Gold));
                            break;
                        case "RAJ":
                            btmPaint.setColor(getColor(R.color.DeepPink));
                            break;
                        case "RCB":
                            btmPaint.setColor(getColor(R.color.Red));
                            break;
                        case "KKR":
                            btmPaint.setColor(getColor(R.color.Purple));
                            break;
                        case "SUN":
                            btmPaint.setColor(getColor(R.color.DarkOrange));
                            break;
                        case "KXI":
                            btmPaint.setColor(getColor(R.color.DarkRed));
                            break;
                        case "DEL":
                            btmPaint.setColor(getColor(R.color.RoyalBlue));
                            break;
                        default:
                            break;
                    }

                    switch (teamsname[cbt==1?0:1]){
                        case "MI":
                            bowPaint.setColor(getColor(R.color.Blue));
                            break;
                        case "CHE":
                            bowPaint.setColor(getColor(R.color.Gold));
                            break;
                        case "RAJ":
                            bowPaint.setColor(getColor(R.color.DeepPink));
                            break;
                        case "RCB":
                            bowPaint.setColor(getColor(R.color.Red));
                            break;
                        case "KKR":
                            bowPaint.setColor(getColor(R.color.Purple));
                            break;
                        case "SUN":
                            bowPaint.setColor(getColor(R.color.DarkOrange));
                            break;
                        case "KXI":
                            bowPaint.setColor(getColor(R.color.DarkRed));
                            break;
                        case "DEL":
                            bowPaint.setColor(getColor(R.color.RoyalBlue));
                            break;
                        default:
                            break;
                    }

                    double radians = Math.toRadians(ballangle);
                    btox = getX(radians, balldistance);
                    btoy = getY(radians, balldistance);
                    System.out.println("X: " + btox + " Y: " + btoy);
                    if (ballangle < 90.00 && ballangle > 0.00) {
                        quadrant = 1;
                    } else if (ballangle < 180.00 && ballangle > 90.00) {
                        quadrant = 2;
                    } else if (ballangle < 270.00 && ballangle > 180.00) {
                        quadrant = 3;
                    } else if (ballangle < 360.00 && ballangle > 270.00) {
                        quadrant = 4;
                    }

                    switch (cbruns) {
                        case "0":
                            System.out.println("Its one");
                            System.out.println(wicket+"");
                            if(wicket){
                                out = true;
                                ifgif = true;
                                hua = false;
                                runbtwwckt_comp = true;
                                System.out.println("Its out");
                            }else if(ballangle==0.00 && balldistance==0.00){
                                    ballstop = true;
                                    runbtwwckt_comp = true;
                                }

                            break;
                        case "1":
                            one_run = true;
                            actually1 = true;
                            two_run = false;
                            three_run = false;
                            break;
                        case "2":
                            one_run = true;
                            actually1 = false;
                            two_run = true;
                            three_run = false;
                            break;
                        case "3":
                            one_run = true;
                            actually1 = false;
                            two_run = false;
                            three_run = true;
                            break;
                        case "4":
                            ifgif = true;
                            four4 = true;
                            hua = false;
                            break;
                        case "5":
                            break;
                        case "6":
                            ifgif = true;
                            six6 = true;
                            hua = false;
                            break;
                        default:
                            break;
                    }
                    System.out.println("Calling Kronos now!");
                    animate = true;
                    isBattingAnim = false;
                    isBowlingAnim = true;
                    load_predictions();
                    timerFunc();
                } catch (NullPointerException e) {
                    System.out.println("We fucked up " + e.toString());
                    //ageka();
                    //return;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Opening error " + databaseError.toString());
            }
        });
    }

    public void gifdisplay() {
        threadgif = new Thread() {
            @Override
            public void run() {
                stat_activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gv.setEnabled(true);
                        gv.setVisibility(View.VISIBLE);
                        if (six6) {
                            Glide.with(stat_activity.this).load(R.drawable.anim6).apply(new RequestOptions().override(Target.SIZE_ORIGINAL)).into(gv);
                            ballX = ballX - ballSpeed;
                            six6 = false;
                        } else if (four4) {
                            Glide.with(stat_activity.this).load(R.drawable.anim4).apply(new RequestOptions().override(Target.SIZE_ORIGINAL)).into(gv);
                            ballX = ballX - ballSpeed;
                            four4 = false;
                        } else if (out) {
                            System.out.println("Inside the wicket display");
                            Glide.with(stat_activity.this).load(R.drawable.outanim).apply(new RequestOptions().override(Target.SIZE_ORIGINAL)).into(gv);
                            out = false;
                        } else if (wideball) {
                            Glide.with(stat_activity.this).load(R.drawable.wideanim).apply(new RequestOptions().override(Target.SIZE_ORIGINAL)).into(gv);
                            wideball = false;
                        } else if (noball) {
                            Glide.with(stat_activity.this).load(R.drawable.noball).apply(new RequestOptions().override(Target.SIZE_ORIGINAL)).into(gv);
                            noball = false;

                        }
                        ifgif = false;
                        hua = true;
                        runbtwwckt_comp = true;
                    }
                });

            }
        };
        threadgif.start();

    }

    public void afterball(){
        stub.setLayoutResource(R.layout.ballreview);
        inflatedView = stub.inflate();
        stub.setVisibility(View.VISIBLE);
        stub.setVisibility(View.GONE);
        TextView v_crr,v_rrr,tv_angle,tv_distance,tv_batsman,tv_bowler,tv_br_hl,tv_commen,tv_score;
        final SwitchMaterial sw_sbr;
        v_crr = inflatedView.findViewById(R.id.tv_crr);
        v_rrr = inflatedView.findViewById(R.id.tv_rrr);
        tv_angle = inflatedView.findViewById(R.id.tv_angle);
        tv_distance = inflatedView.findViewById(R.id.tv_distance);
        tv_br_hl = inflatedView.findViewById(R.id.tv_br_hl);
        tv_batsman = inflatedView.findViewById(R.id.tv_br_batsman);
        tv_bowler = inflatedView.findViewById(R.id.tv_br_bowler);
        tv_commen = inflatedView.findViewById(R.id.tv_revcomment);
        tv_score = inflatedView.findViewById(R.id.tv_br_score);
        Button reviewdone = inflatedView.findViewById(R.id.bt_rvd);
        sw_sbr = inflatedView.findViewById(R.id.sw_ballrev);
        sw_sbr.setChecked(true);
        tv_commen.setText(currcomment);
        tv_angle.setText(String.valueOf(ballangle));
        tv_distance.setText(String.valueOf(balldistance)+"m");
        tv_batsman.setText(onstrikebtm);
        tv_bowler.setText(currbowler);

        String showscore  = currwickets+"/"+tscore;
        tv_score.setText(showscore);
        if(wicket){
            tv_br_hl.setText("W");
        }else{
            tv_br_hl.setText(cbruns);
        }

        sw_sbr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    sw_sbr.setText(getString(R.string.show_on));
                    FancyToast.makeText(stat_activity.this,"Will show ball details after every ball",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                    PreferenceManager.getDefaultSharedPreferences(stat_activity.this).edit().putBoolean("revaeb", true).apply();
                }
                else {
                    sw_sbr.setText(getString(R.string.show_off));
                    FancyToast.makeText(stat_activity.this,"Will not show ball details after every ball",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                    PreferenceManager.getDefaultSharedPreferences(stat_activity.this).edit().putBoolean("revaeb", false).apply();
                }
            }
        });

        reviewdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
                stub.setVisibility(View.GONE);
                //predict_over();
            }
        });
    }

    public void getbatsmannames(){
        System.out.println("I am in gbtnames");
        myRef4 = database.getReference(matchid).child("commentary").child("commentary").child("innings").child(commentarylivid_i).child("overs").child(commentarylivid_o).child("balls");//

        myRef4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int oc = 1;
                wicket = false;
                ballsinover = (int) dataSnapshot.getChildrenCount();
                for (DataSnapshot child : dataSnapshot.child(commentarylivid_b).child("comments").getChildren()) {
                    if (child.child("ballType").getValue().toString().equals("LegitOffBat") ||child.child("ballType").getValue().toString().equals("Wide") ) {
                        if(child.child("ballType").getValue().toString().equals("Wide")){
                            wideball = true;
                            ifgif = true;
                            hua = false;
                        }
                        System.out.println("I am heeeeeeeeee");

                        //cover[0] = "ready";
                        wicket = (boolean) child.child("isFallOfWicket").getValue();
                        System.out.println("Wicket stat: " + wicket);

                        if (wicket) {
                            // cover[oc] = "W";
                        } else {
                            //cover[oc] = child.child("runs").getValue().toString();
                        }
                        oc++;
                        tscore = child.child("battingTeamScore").getValue().toString();
                        currcomment = child.child("text").getValue().toString();
                        System.out.println("I am here at bowlerName but not the 1st time!");
                        currbowler = child.child("bowlerName").getValue().toString();
                        System.out.println("Current bowler: "+currbowler);
                        currwickets = child.child("wickets").getValue().toString();
                        onstrikebtm = child.child("batsmanName").getValue().toString();
                        offstrikeid = child.child("offStrikeBatsmanId").getValue().toString();
                        System.out.println("CBT: " + teamid[cbt]);
                        myRef6 = database.getReference(matchid).child("commentary").child("commentary").child("innings").child(commentarylivid_i).child("overs").child(commentarylivid_o).child("balls").child(commentarylivid_b).child("ballNumber");
                        myRef6.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                ballsinco = dataSnapshot.getValue().toString();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        myRef5 = database.getReference(matchid).child("teams").child("playersInMatch").child(wtb).child("players");
                        myRef5.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    if (child.child("playerId").getValue().toString().equals(offstrikeid)) {
                                        offstrikebtm = child.child("fullName").getValue().toString();

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

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
    public void predict_over(int allow){
        lin_statguess_over.setVisibility(View.VISIBLE);
        lin_statguess_over.setEnabled(true);
        ImageButton runup = findViewById(R.id.kbt_runup);
        ImageButton rundwn = findViewById(R.id.kbt_rundwn);
        ImageButton wup = findViewById(R.id.kbt_wup);
        ImageButton wdwn = findViewById(R.id.kbt_wdwn);
        Button op_submit = findViewById(R.id.kbt_osub);
        TextView tv_optit = findViewById(R.id.ktv_optit);
        final TextView et_run = findViewById(R.id.ket_r);
        final EditText et_w = findViewById(R.id.ket_w);
        final String[] range ={"0-5","5-10","10-15","15-20","20-25","25-30","30-35","35-40","40+"};
        swic = 0;
        range_counter = 0;
        String message = "What will happen in over " + (currover + 1);
        tv_optit.setText(message);
        runup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(range_counter<range.length-1){
                    range_counter++;
                    et_run.setText(range[range_counter]);
                }else{
                    FancyToast.makeText(stat_activity.this,"This is the highest range",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                }
            }
        });

        rundwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(range_counter>0){
                    range_counter--;
                    et_run.setText(range[range_counter]);
                }else{
                    FancyToast.makeText(stat_activity.this,"This is the lowest range",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                }
            }
        });

        wup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = et_w.getText().toString();
                int wickets = Integer.parseInt(data);
                if(data.equals(" ")||data.isEmpty()||wickets<0){
                    FancyToast.makeText(stat_activity.this,"Enter valid data",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                }else {
                    wickets++;
                    et_w.setText(String.valueOf(wickets));
                }
                swic = wickets;
            }
        });
        wdwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = et_w.getText().toString();
                int wickets = Integer.parseInt(data);
                if(data.equals(" ")||data.isEmpty()||wickets<=0){
                    FancyToast.makeText(stat_activity.this,"Cannot reduce further. Enter valid wickets",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                }else {
                    wickets--;
                    et_w.setText(String.valueOf(wickets));
                }

                swic = wickets;

            }
        });

        op_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currover == 20){
                    FancyToast.makeText(stat_activity.this,"Predictions over for current match",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    lin_statguess_over.setVisibility(View.GONE);
                    lin_statguess_over.setEnabled(false);
                }
//                else if(oldover <= currover) {
//                    FancyToast.makeText(stat_activity.this,"Predictions over for current over",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
//                    lin_statguess_over.setVisibility(View.GONE);
//                    lin_statguess_over.setEnabled(false);
//                }
                //else
                else{
                    sendPredictionRequest(range_counter,swic);
                }
            }
        });
    }
    public void sendPredictionRequest(int p1, int p2) {
        if (fuser == null) {
            Intent intent = new Intent(stat_activity.this, Login.class);
            intent.putExtra("matchid", matchid);
            startActivity(intent);
        } else {
            try {
                myRef9 = database.getReference("Users").child(fuser.getUid()).child("predictions").child(matchid).child(String.valueOf(currinn)).child("overs").child(String.valueOf(currover + 1));
                myRef9.child("runrange").setValue(p1);
                myRef9.child("wickets").setValue(p2);
                myRef9.child("calc").setValue(false);
                FancyToast.makeText(stat_activity.this, "Predictions submitted! Results will be out after the match!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                lin_statguess_over.setVisibility(View.GONE);
                lin_statguess_over.setEnabled(false);
            } catch (Exception pe) {
                FancyToast.makeText(stat_activity.this, "Please try again", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

            }

        }
    }
    public void predict_ball(){
        stub2.setLayoutResource(R.layout.guessball);
        View inflatedViewin = stub2.inflate();
    }


    public void startgettingdata(){
        myRef = database.getReference(matchid).child("matchdetails").child("currentInningId");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                currinn = dataSnapshot.getValue(Integer.class);
                myRef2 = database.getReference(matchid).child("innings").child(String.valueOf(currinn)).child("battingWheel").child("ball");
                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //String data = dataSnapshot.getValue().toString();
                        liveballid = dataSnapshot.getValue().toString();
                        myRef3 = database.getReference(matchid).child("commentary").child("commentary").child("innings").child("0").child("overs").child("0");
                        myRef3.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String coverid = String.valueOf((long) dataSnapshot.child("id").getValue());
                                currover = Integer.parseInt(coverid)  + 1;

                                if (!curballid.equals(liveballid) && golive) {
                                    curballid = liveballid;
                                    stub.setVisibility(View.GONE);
                                    gv.setEnabled(false);
                                    gv.setVisibility(View.GONE);
                                    Thread.currentThread().interrupt();
                                    mScore.setText(tscore + " - " + currwickets);
                                    wsOvers.setText("OVERS - " + currover);
                                    tv_bat_score1.setText(onstrikebtm);
                                    tv_bat_score2.setText(offstrikebtm);
                                    ageka();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        currinn = 1;
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                currinn = 1;
            }
        });



    }
    public void init(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(stat_activity.this);
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
        l1 = findViewById(R.id.iv_left_icon);
        l2 = findViewById(R.id.iv_right_icon);
        Glide.with(stat_activity.this).load(teamlogourl[0]).into(l1);
        Glide.with(stat_activity.this).load(teamlogourl[1]).into(l2);
        String batting = team[cbt] + " 🏏";
        //String batting = team[cbt];
        if (cbt == 0) {
            //c1_title.setText(batting);
            //c2_title.setText(team[1]);
        } else if (cbt == 1) {
           // c1_title.setText(team[0]);
           // c2_title.setText(batting);
        }
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        bitmap = Bitmap.createBitmap(
                width, // Width
                height, // Height
                Bitmap.Config.ARGB_8888 // Config
        );
        canvas = new Canvas(bitmap);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        System.out.println("Available X: " + canvasWidth + " Available Y: " + canvasHeight);
        float w = 100*bX/canvasWidth;
        float h = 100*bY/canvasHeight;
        System.out.println("Width: "+w+" Height: "+h);
        ballX = ballxpercentage / 100 * canvasWidth;
        ballY = p2ypercentage / 100 * canvasHeight;
        //ballypercentage/100*canvasHeight;
        p1X = p1xpercentage / 100 * canvasWidth;
        p1Y = p1ypercentage / 100 * canvasHeight;
        p1yextent = p1Y;
        p2X = p2xpercentage / 100 * canvasWidth;
        p2Y = p2ypercentage / 100 * canvasHeight;
        distanceToRun = p1Y - p2Y;
        player_counter = (int) distanceToRun;
        System.out.println("Run: "+distanceToRun);
        p2yextent = p2Y;
        tag1X = p1X - 10;
        tag1Y = p1Y - 35;
        tag2X = p2X - 10;
        tag2Y = p2Y + 45;
        ballPaint.setColor(Color.RED);
        ballPaint.setAntiAlias(true);
        btmPaint.setAntiAlias(true);
        bowPaint.setAntiAlias(true);
        bowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        ballPaint.setDither(true);
        btmPaint.setDither(true);
        bowPaint.setDither(true);
        tagPaint.setColor(Color.BLACK);
        tagPaint.setAntiAlias(false);
        tagPaint.setTextSize(25);

        iv = findViewById(R.id.civ);
        gv = findViewById(R.id.iffygif);
        stub = findViewById(R.id.vs_rev);
        mScore = findViewById(R.id.tv_sascore);
        wsBatting = findViewById(R.id.tv_wsbatting);
        wsOvers = findViewById(R.id.sa_overs);
        tv_bat_score1 = findViewById(R.id.tv_bat_score1);
        tv_bat_score2 = findViewById(R.id.tv_bat_score2);
        lin_statguess_over = findViewById(R.id.lin_statguess_over);
        //stub2  = findViewById(R.id.vs_guess);
        overl = findViewById(R.id.lin_over);
        balll = findViewById(R.id.lin_nextball);
        fl_stat = findViewById(R.id.fl_statparent);
        sw_detail = findViewById(R.id.sw_pitchdetails);        bck = findViewById(R.id.button_statback);
        btgolive = findViewById(R.id.ibt_golive);
        btprevball = findViewById(R.id.ibt_ballminus);
        btnextball = findViewById(R.id.ibt_ballplus);
        bt_review = findViewById(R.id.bt_rev);
        bt_predict = findViewById(R.id.bt_pred);

    }
    private void int_controls() {
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(stat_activity.this, front_land.class);
                intent.putExtra("matchid", matchid);
                startActivity(intent);
            }
        });

        bt_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afterball();
            }
        });

        bt_predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //FancyToast.makeText(stat_activity.this,"This feature is coming soon",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                load_predictions();
                //Todo-Make UI like Maps contribution
            }
        });

        btgolive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(golive){
                    btgolive.setImageResource(R.drawable.ic_offline);
                    FancyToast.makeText(stat_activity.this,"Live mode is off. Use arrow keys to view custom balls! Note that this feature is in beta and might be buggy!",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                }else{
                    canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    iv.setImageBitmap(bitmap);
                    btgolive.setImageResource(R.drawable.ic_live);
                    FancyToast.makeText(stat_activity.this,"You are now live! Fetching the latest ball!",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                    curballid = liveballid;
                    commentarylivid_b = "0";
                    commentarylivid_i = String.valueOf(currinn);
                    commentarylivid_o = "0";
                    gv.setEnabled(false);
                    gv.setVisibility(View.GONE);
                    Thread.currentThread().interrupt();
                    ageka();
                }
                golive = !golive;
            }
        });

        btnextball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!golive) {
                    curballid = String.valueOf((Integer.parseInt(curballid) + 1));
                    if(!curballid.equals(liveballid)){
                        stub.setVisibility(View.GONE);
                        FancyToast.makeText(stat_activity.this, curballid, FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                        if (commentarylivid_b.equals(String.valueOf(ballsinover))) {
                            commentarylivid_o = String.valueOf((Integer.parseInt(commentarylivid_o) - 1));
                            commentarylivid_b = "0";
                        } else {
                            commentarylivid_b = String.valueOf((Integer.parseInt(commentarylivid_b) - 1));
                        }
                        gv.setEnabled(false);
                        gv.setVisibility(View.GONE);
                        Thread.currentThread().interrupt();
                        ageka();
                    }else{
                        FancyToast.makeText(stat_activity.this,"You are already on the latest ball",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                    }
                }else{
                    FancyToast.makeText(stat_activity.this,"Please disable live mode to watch custom ball",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                }
            }
        });

        btprevball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!golive){
                    //alreadyprev = true;
                    curballid = String.valueOf((Integer.parseInt(curballid)-1));
                    stub.setVisibility(View.GONE);
                    FancyToast.makeText(stat_activity.this,curballid,FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                    if(commentarylivid_b.equals(String.valueOf(ballsinover))){
                        commentarylivid_o = String.valueOf((Integer.parseInt(commentarylivid_o)+1));
                        commentarylivid_b = "0";
                    }else{
                        commentarylivid_b = String.valueOf((Integer.parseInt(commentarylivid_b)+1));
                    }
                    gv.setEnabled(false);
                    gv.setVisibility(View.GONE);
                    Thread.currentThread().interrupt();
                    ageka();
                }else{
                    FancyToast.makeText(stat_activity.this,"Please disable live mode to watch custom ball",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                }
            }
        });

        sw_detail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    sw_detail.setText(getString(R.string.detailed_mode_on));
                    fl_stat.setBackground(getDrawable(R.drawable.top_d));

                }
                else {
                    sw_detail.setText(getString(R.string.detailed_mode_off));
                    fl_stat.setBackground(getDrawable(R.drawable.top_new));
                }
            }
        });
    }

    public void timerFunc() {

        new Thread(){
            public void run(){
                while (animate){

                    canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    if(isBowlingAnim){
                        boolean cowboy = false;
                        if(run_state){
                            cowboy = bY>p2Y;
                        }else{
                            cowboy = bY>p1Y;
                        }
                        while(cowboy){
                            bY = bY - 10;
                            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                            canvas.drawCircle(ballX, ballY, 10, ballPaint);
                            canvas.drawCircle(p1X, p1Y, 15, btmPaint);
                            canvas.drawCircle(p2X, p2Y, 15, btmPaint);
                            canvas.drawText(offstrikebtm, tag1X, tag1Y, tagPaint);
                            canvas.drawText(onstrikebtm, tag2X, tag2Y, tagPaint);
                            canvas.drawText(currbowler,bX-10,bY-35,tagPaint);
                            canvas.drawCircle(bX,bY,15,bowPaint);
                            iv.setImageBitmap(bitmap);
                            try {
                                Thread.sleep(WAITTIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                System.out.println(e.toString());
                            }
                            if(run_state){
                                cowboy = bY>p2Y;
                            }else{
                                cowboy = bY>p1Y;
                            }
                        }

                        float innerball;
                        boolean cch = false;
                        if(!run_state){
                            innerball  = p1Y;
                            cch = innerball>p2Y;
                        }else{
                            innerball  = p2Y;
                            cch = innerball>p1Y;
                        }

                        while(cch){
                            innerball = innerball- 10;
                            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                            canvas.drawCircle(ballX, innerball, 10, ballPaint);
                            canvas.drawCircle(p1X, p1Y, 15, btmPaint);
                            canvas.drawCircle(p2X, p2Y, 15, btmPaint);
                            canvas.drawText(offstrikebtm, tag1X, tag1Y, tagPaint);
                            canvas.drawText(onstrikebtm, tag2X, tag2Y, tagPaint);
                            canvas.drawText(currbowler,bX-10,bY-35,tagPaint);
                            canvas.drawCircle(bX,bY,15,bowPaint);
                            iv.setImageBitmap(bitmap);
                            try {
                                Thread.sleep(WAITTIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(!run_state){

                                cch = innerball>p2Y;
                            }else{

                                cch = innerball>p1Y;
                            }
                        }

                        isBattingAnim = true;
                        isBowlingAnim = false;

                    }else if(isBattingAnim){
                        ballSpeed = speed_generator();
                        if (ballangle == 0.00 && balldistance == 0.00 && !wicket) {
                            ballstop = true;
                        }
                        if (!ballstop) {
                            ctr++;
                            switch (quadrant) {
                                case 1:
                                case 4:
                                    if (ballX < btox) {
                                        //System.out.println("H1");
                                        ballX = ballX + ballSpeed;
                                    }
                                    break;
                                case 2:
                                case 3:
                                    if (ballX > btox) {
                                        //System.out.println("H2");
                                        ballX = ballX - ballSpeed;
                                    }
                                    break;
                            }

                            switch (quadrant) {
                                case 4:
                                case 3:
                                    if (ballY < btoy) {
                                        //System.out.println("H3");
                                        ballY = ballY + ballSpeed;
                                    }
                                    break;
                                case 2:
                                case 1:
                                    if (ballY > btoy) {
                                        //System.out.println("H4");
                                        ballY = ballY - ballSpeed;
                                    }
                                    break;
                            }

                        }
                        if (player_counter <= 0) {
                            run_state = !run_state;
                            player_counter = (int) distanceToRun;
                            single_run_counter++;
                            if (actually1) {
                                if (single_run_counter == 1) {
                                    one_run = false;
                                    actually1 = false;
                                    single_run_counter = 0;
                                    run_allow = false;
                                    runbtwwckt_comp = true;
                                }
                            } else if (two_run) {
                                if (single_run_counter == 2) {
                                    one_run = false;
                                    two_run = false;
                                    run_allow = false;
                                    single_run_counter = 0;
                                    runbtwwckt_comp = true;
                                }
                            } else if (three_run) {
                                if (single_run_counter == 3) {
                                    one_run = false;
                                    three_run = false;
                                    single_run_counter = 0;
                                    run_allow = false;
                                    runbtwwckt_comp = true;
                                }
                            }
                            stat_activity.this.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    //Toast.makeText(stat_activity.this, single_run_counter + "", Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        if (one_run) {
                            if (!run_state) {
                                if (p1Y > p2yextent || p2Y < p1yextent) {
                                    p1Y = p1Y - pspeed;
                                    p2Y = p2Y + pspeed;
                                    tag1Y = tag1Y - pspeed;
                                    tag2Y = tag2Y + pspeed;

                                }
                            }
                            if (run_state) {
                                if (p1Y < p1yextent || p2Y > p2yextent) {
                                    p1Y = p1Y + pspeed;
                                    p2Y = p2Y - pspeed;
                                    tag1Y = tag1Y + pspeed;
                                    tag2Y = tag2Y - pspeed;
                                }
                            }
                            player_counter = player_counter - pspeed;
                        }
                        switch (quadrant) {
                            case 1:
                                if (ballX >= btox && ballY <= btoy) {
                                    ballstop = true;
                                }
                                break;
                            case 2:
                                if (ballX <= btox && ballY <= btoy) {
                                    ballstop = true;
                                }
                                break;
                            case 3:
                                if (ballX <= btox && ballY >= btoy) {
                                    ballstop = true;
                                }
                                break;
                            case 4:
                                if (ballX >= btox && ballY >= btoy) {
                                    ballstop = true;
                                }
                                break;
                        }

                        if (ifgif) {
                            if (!hua) {
                                gifdisplay();
                            }

                        }

                        if (ballstop && runbtwwckt_comp) {
                            System.out.println("I reached here!");
                            ballX = ballxpercentage / 100 * canvasWidth;
                            if(!run_state){
                                ballY = p2ypercentage / 100 * canvasHeight;
                            }else{
                                ballY = p1ypercentage / 100 * canvasHeight;
                            }

                            animate = false;
                            //7026870268 - MyStartUpIndia
                            show_after();
                            stat_activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    gv.setVisibility(View.GONE);
                                    gv.setEnabled(false);
                                }
                            });

                            //((GifDrawable)gv.getDrawable()).stop();
                            boolean sbr = android.preference.PreferenceManager.getDefaultSharedPreferences(stat_activity.this).getBoolean("revaeb", true);

//                            if(sbr && !currbowler.equals("yaya")){
//                                afterball();
//                            }

                            System.out.println("Ending now! Brace yourself!");

                        } else {
                            canvas.drawCircle(ballX,ballY,10,ballPaint);
                            canvas.drawCircle(p1X, p1Y, 15, btmPaint);
                            canvas.drawCircle(p2X, p2Y, 15, btmPaint);
                            canvas.drawText(offstrikebtm, tag1X, tag1Y, tagPaint);
                            canvas.drawText(onstrikebtm, tag2X, tag2Y, tagPaint);
                            canvas.drawText(currbowler,bX-10,bY-35,tagPaint);
                            canvas.drawCircle(bX,bY,15,bowPaint);

                            iv.setImageBitmap(bitmap);
                            try {
                                Thread.sleep(WAITTIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }//end while
                Thread.currentThread().interrupt();
            }
        }.start();
//        timer = new Timer();
//        this.global_t = timer;
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//
//
//
//
//
//
//
//
//                    }
//                });
//            }
//        }, 0, Interval);
    }

    private class AnimTask extends AsyncTask<Void, Void, Void> {

        private Context context;
        //use this to download data even if screen turns off and prevent OS from killing thread
        //permission add kar manifest madhe
        private PowerManager.WakeLock mWakeLock;

        public AnimTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            assert pm != null;
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire(2*60*1000L /*2 minutes*/);
            //DO THINGS BEFORE YOU START LOADING: LIKE SHOWING SPLASH SCREEN
        }

        @Override
        protected Void doInBackground(Void... a){
            //DO ACTUAL DOWNLOAD STUFF
            //MAIN CODE IS CALLED HERE
            startgettingdata();
            return null;
        }

        @Override
        protected void onPostExecute(Void a) {
            mWakeLock.release();
            //AFTER COMPLETION CALLBACK
        }
    }
    public void load_predictions(){
        try{
            if((currover+1)<=20){
                myRef = database.getReference("Users").child(fuser.getUid()).child("predictions").child(matchid).child(String.valueOf(currinn)).child("overs");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot pdataSnapshot) {
                        if (!pdataSnapshot.hasChild(String.valueOf(currover+1))) {
                            predict_over(1);
                            oldover = currover;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        }catch (Exception nuser){
            //predict_over(0);
        }
    }
}

//mImageView.setImageBitmap(bitmap);