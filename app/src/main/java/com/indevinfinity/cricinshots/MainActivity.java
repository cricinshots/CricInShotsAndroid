package com.indevinfinity.cricinshots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public boolean loadNotComplete = true;
    Handler handler;
    TextView loadingMessage;
    int numberOFTitles, curr,maintaining;
    Random random;
    boolean network_status = false;
    String restoredState = "notwelcomed";
    public static int which_req =0,whereisuser=0;
    static boolean authtoken = false,maintainence_status = false,s;
    String isAuth = "";
    MatchProvider matchProvider;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef,myRef2,myRef3;
    boolean checked;
    BackTask backTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isAuth = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("and_ver", "null");
        initialize();
        startText();
        backTask = new BackTask(this);
        backTask.execute();
    }

    String[] titles = {
            "Sprinting Sideways",
            "Performing Batting Drills",
            "Performing press ups",
            "Performing fielding drills",
            "Performing Zig Zag Sprints",
            "Throwing Practice",
            "Tactical Briefing Underway",
            "Practising Spin Bowling",
            "Analyzing pitch conditions",
            "Analyzing weather conditions",
            "Head movement drills",
            "Shadow practice",
            "Light Jogging",
            "Hamstring stretches",
            "Stretching quads",
            "Finding the other sock",
            "Net practice",
            "Searching live matches"

    };

    void initialize() {
        try{
            getSupportActionBar().hide();
        }catch (Exception e){}

        loadingMessage = findViewById(R.id.txtRandomWarmUp);
        numberOFTitles = titles.length;
        random = new Random();
        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime)*7;
        myRef = database.getReference("matches");
        matchProvider = new MatchProvider(myRef);
    }
    private static final int WAITTIME = 4000;
    private int shortAnimationDuration;
    private boolean FIRSTTIME = false;
    void startText() {
        new Thread() {
            public void run() {
                System.out.println("Waiting for data");
                while(!matchProvider.dataReady) {
                    curr = random.nextInt(numberOFTitles);
                    try {
                        if(FIRSTTIME){
                            Thread.sleep(WAITTIME);
                        }else{
                            FIRSTTIME = !FIRSTTIME;
                            Thread.sleep(WAITTIME/2);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {

                            loadingMessage.setAlpha(1f);
                            loadingMessage.animate()
                                    .alpha(0f)
                                    .setDuration(shortAnimationDuration)
                                    .setListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animator) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animator) {
                                            loadingMessage.setText(titles[curr]);
                                            loadingMessage.animate()
                                                    .alpha(1f)
                                                    .setDuration(shortAnimationDuration)
                                                    .setListener(null);

                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animator) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animator) {

                                        }
                                    });
                        }
                    });
                }
                System.out.println("Data is ready");
                Intent intent = new Intent(MainActivity.this,choose_match.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("matchProvider",matchProvider);
                startActivity(intent);
            }
        }.start();
    }
//Todo crazy stuff
    public void network_check() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network true
            network_status = true;
            versionCheck();
        } else {
            network_status = false;
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Connection Error");
                    alertDialog.setMessage("Unable to connect with the server.\nPlease check your internet connection and try again.");
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
                                    network_check();
                                }
                            });
                    alertDialog.show();
                }
            });


        }
        //return network_status;
    }

    public void openNext(){
        restoredState = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Load_State", "welcomed");
        //Todo: Change to notwelcomed
        if (restoredState.equals("notwelcomed")) {
            startActivity(new Intent(MainActivity.this, welcome_act.class));
        }else  if (restoredState.equals("welcomed")) {
            isAuth = "bbki";
            //Todo remove above line
            if(!isAuth.equals("null")){
                loadchoose();
                /**
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://cricinshots.com/auth/signin.php";
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonObject = new JSONObject(response);
                                    s = jsonObject.getBoolean("s");
                                    if(s){
                                        loadchoose();
                                    }else{
                                        //Todo remove loadchoose();
                                        loadchoose();
                                        //startActivity(new Intent(MainActivity.this, register_activity.class));

                                    }

                                    System.out.println(response);

                                }catch (JSONException e) {
                                    e.printStackTrace();
                                    System.out.println("Error "+e);
                                }
//Randed office - 020 24455311
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try{
                            byte[] erMsg = error.networkResponse.data;
                            String errMsg="";
                            for(int i=0;i<erMsg.length;i++) {
                                errMsg+=erMsg[i];
                            }
                        }catch (Exception x){
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("Uhoh! Server error");
                            alertDialog.setMessage("Unable to connect with the server. Don't worry, it's not you, it's us.\nPlease try again in some time!");
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
                                            startActivity(new Intent(MainActivity.this,MainActivity.class));
                                        }
                                    });
                            alertDialog.show();
                        }


                    }
                }){
                    @Override
                    public Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("qst",isAuth);
                        return params;
                    }
                };
                queue.add(stringRequest);
                **/
            }else {
                startActivity(new Intent(MainActivity.this, register_activity.class));
            }
        }
        //restoredState = "welcomed";




    }

    public void loadchoose(){
        System.out.println("Calling getData");
        matchProvider.getData();
    }

    public void versionCheck(){
        final int versionCode = BuildConfig.VERSION_CODE;
        myRef3 = database.getReference("ads").child("controls").child("leastV");
        myRef3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String control = dataSnapshot.getValue().toString();
                String[] controls = control.split(",");
                int lowestVersion = Integer.parseInt(controls[0]);
                maintaining = Integer.parseInt(controls[1]);

                    if(versionCode>=lowestVersion){
                        maintenanceCheck();
                    }else{
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("New update available!");
                        alertDialog.setMessage("We're psyched for you to see what we've been working on. Please click below to get the latest updates!");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Update",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                                        try {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                        } catch (android.content.ActivityNotFoundException anfe) {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                        }
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                        System.exit(0);
                                    }
                                });
                        alertDialog.show();
                    }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void betacheck(){
        boolean betastate = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getBoolean("Beta1", false);
        //Todo remove ! from !betastate
        if(!betastate){
            openNext();
        }else{
         MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.beta_dialog);

                    final EditText pass =  dialog.findViewById(R.id.getpass);
                    final TextView veri = dialog.findViewById(R.id.tv_verifying);
                    Button dialogButton = dialog.findViewById(R.id.bt_beta);
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            veri.setVisibility(View.VISIBLE);
                            veri.setText(R.string.verifying_tester);
                            final String entered = pass.getText().toString().trim();
                            checked = false;
                            if(entered.equals("")||entered.equals(" ")){
                                FancyToast.makeText(MainActivity.this,"Please enter a value!",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                            }else{
                                myRef2 = database.getReference("ads").child("controls").child("betakeys");
                                myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @SuppressLint("SetTextI18n")
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String key = "null";
                                        for(DataSnapshot child: dataSnapshot.getChildren()){
                                            key = child.getValue().toString();
                                            if(entered.equals(key)){
                                                checked = true;
                                            }

                                        }
                                        if(!checked){
                                            FancyToast.makeText(MainActivity.this,"Please try again!",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                                            veri.setText("Attempt failed!");
                                        }else{
                                            veri.setText(R.string.verified);
                                            FancyToast.makeText(MainActivity.this,"Welcome!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                                            dialog.dismiss();
                                            terms();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }



                        }
                    });
                    dialog.show();
                }
            });

        }

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
    public void terms(){

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.layout_privacy_policy);

                final WebView wv =  dialog.findViewById(R.id.pwebView);
                final Button bt_accept = dialog.findViewById(R.id.btn_privacy_accept);
                final Button bt_decline = dialog.findViewById(R.id.btn_privacy_decline);
                final EditText terms_name = dialog.findViewById(R.id.et_privacy_name);
                terms_name.requestFocus();
                wv.setWebViewClient(new WebViewClient());
                wv .loadUrl("https://cricinshots.com/beta/terms");
                bt_accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putBoolean("Beta1", true).apply();
                        dialog.dismiss();
            openNext();
                    }
                });
                bt_decline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        System.exit(0);
                    }
                });
                dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


    }

    public void maintenanceCheck(){
        if(maintaining==1) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Maintenance break");
            alertDialog.setMessage("The servers are under maintenance please try again in some time!");
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Retry",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            versionCheck();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    });
            alertDialog.show();
        }else {
            betacheck();
        }
    }

    private class BackTask extends AsyncTask<Void, Void, Void>{

        private Context context;
        //use this to download data even if screen turns off and prevent OS from killing thread
        //permission add kar manifest madhe
        private PowerManager.WakeLock mWakeLock;

        public BackTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();

            //DO THINGS BEFORE YOU START LOADING: LIKE SHOWING SPLASH SCREEN
        }

        @Override
        protected Void doInBackground(Void... a){
            //DO ACTUAL DOWNLOAD STUFF
            //MAIN CODE IS CALLED HERE
            network_check();
            deleteCache(MainActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void a) {
            mWakeLock.release();
            //AFTER COMPLETION CALLBACK
            loadNotComplete = false;
        }
    }

}
