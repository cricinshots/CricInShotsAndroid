package com.indevinfinity.cricinshots;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.os.StrictMode;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class choose_match extends AppCompatActivity {
    ListView choose;
    //String[] idarray,hlist,alist,l1_list,l2_list,hcolor,acolor,shield,hbgurl,hshortname,abgurl,ashortname;
    //boolean[] hisbatting,aisbatting;
    ArrayList<String> matches;
    String ID,version,s_val="fail", url = "https://cricinshots-functions.azurewebsites.net/api/matchinfo/MatchList?code=H7j6c4RUNw3pMhuXGEQoMjUwxjkw7USYBGMmoMsS3uTZs4GfCpZFZQ%3D%3D";
    ProgressBar progressBar;
    //int ln;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    String team1,team2,matchid;
    MatchProvider matchProvider;

    public static boolean match_chosen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_match);

        matchProvider = (MatchProvider) getIntent().getExtras().get("matchProvider");
        matches =  new ArrayList<String>();
        choose = findViewById(R.id.match_ListView);
        progressBar = findViewById(R.id.progressbarchoose);
        progressBar.setVisibility(View.VISIBLE);

        try {
            PackageInfo pInfo = choose_match.this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        list();

    }
    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
        /**Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);**/
    }
    public void onResume(){
        super.onResume();
        match_chosen = false;
    }

    public void list(){
        ArrayList<ListData> arrayList = new ArrayList<ListData>();
        int ad = 1, noad = 0;
        System.out.println("Size: " + matchProvider.ln);
        for(int y=0;y<matchProvider.ln;y++){
//            if(y%2==0&&y!=0){
//                arrayList.add(new ListData("","", "","","","","","",ad,"","","","",false,false,false,"",""));
//                arrayList.add(new ListData(matchProvider.hlist[y],matchProvider.alist[y], matchProvider.l1_list[y],matchProvider.l2_list[y],matchProvider.idarray[y],matchProvider.shield[y],matchProvider.hcolor[y],matchProvider.acolor[y],noad,matchProvider.hbgurl[y],matchProvider.abgurl[y],matchProvider.hshortname[y],matchProvider.ashortname[y],matchProvider.hisbatting[y],matchProvider.aisbatting[y],matchProvider.isLive[y],matchProvider.currentStat[y],matchProvider.DateTime[y]));
//            }else{
                arrayList.add(new ListData(matchProvider.hlist[y],matchProvider.alist[y], matchProvider.l1_list[y],matchProvider.l2_list[y],matchProvider.idarray[y],matchProvider.shield[y],matchProvider.hcolor[y],matchProvider.acolor[y],noad,matchProvider.hbgurl[y],matchProvider.abgurl[y],matchProvider.hshortname[y],matchProvider.ashortname[y],matchProvider.hisbatting[y],matchProvider.aisbatting[y],matchProvider.isLive[y],matchProvider.currentStat[y],matchProvider.DateTime[y]));
           // }//matchProvider.isLive[y],matchProvider.currentStat[y],matchProvider.DateTime[y]

        }
        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        choose.setAdapter(customAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }


public void getContentfromserver(){
    Thread threadgdata = new Thread() {
        @Override
        public void run() {
            Looper.prepare();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            choose_match.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });

            Looper.loop();
        }
    };
    threadgdata.start();
}

}




/**public void getContentfromserver(final String sURL){
 final RequestQueue queue = Volley.newRequestQueue(choose_match.this);
 new Thread(new Runnable() {
 public void run(){
 Looper.prepare();
 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
 StrictMode.setThreadPolicy(policy);
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
JSONObject metaObject = myObject.getJSONObject("meta");
JSONObject matchListObject = myObject.getJSONObject("matchList");
final String k_val = metaObject.get("inProgressMatchCount").toString();
System.out.println(k_val);
JSONArray matchesArray = matchListObject.getJSONArray("matches");
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
matches.add(mname);
idarray[i] = ID;
System.out.println(ID+" " + mname);
} catch (JSONException e) {
// Oops
}
}
//System.out.println(matches.toString());
list();
}catch (JSONException e){
System.out.println("Blue "+e.toString());
}

}
}, new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {
Toast.makeText(choose_match.this,"That didn't work!",Toast.LENGTH_LONG).show();
System.out.println("That didn't work! Error:" + error.toString());
getContentfromserver(sURL);
}
});
 queue.add(stringRequest);
 Looper.loop();

 }
 }).start();

 }

 myRef = database.getReference("matches");
 myRef.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
ln = (int) dataSnapshot.getChildrenCount();
idarray = new String[ln];
hlist = new String[ln];
alist = new String[ln];
l1_list = new String[ln];
l2_list = new String[ln];
hcolor = new String[ln];
acolor = new String[ln];
shield = new String[ln];
hbgurl = new String[ln];
hshortname = new String[ln];
abgurl = new String[ln];
ashortname = new String[ln];
hisbatting = new boolean[ln];
aisbatting = new boolean[ln];
int i=0;
for(DataSnapshot child : dataSnapshot.getChildren() ){
Team hteamobj = child.child("homeTeam").getValue(Team.class);
Team ateamobj = child.child("awayTeam").getValue(Team.class);
shield[i] = child.child("series").child("shieldImageUrl").getValue().toString();

String sID = child.child("series").child("id").getValue().toString();
String mID = child.child("id").getValue().toString();
ID = sID + "-" + mID;


hlist[i] = hteamobj.getName();
alist[i] = ateamobj.getName();
l1_list[i] = hteamobj.getLogoUrl();
l2_list[i] = ateamobj.getLogoUrl();

hbgurl[i] = hteamobj.getBackgroundImageUrl();
hshortname[i] = hteamobj.getShortName();
abgurl[i] = ateamobj.getBackgroundImageUrl();
ashortname[i] = ateamobj.getShortName();

hcolor[i] = hteamobj.getTeamColour();
acolor[i] = ateamobj.getTeamColour();

hisbatting[i] = hteamobj.getIsBatting();
aisbatting[i] = ateamobj.getIsBatting();

idarray[i] = ID;
i++;

choose_match.this.runOnUiThread(new Runnable() {
@Override
public void run() {
list();
}
});
}
}

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

}
});

 **/

