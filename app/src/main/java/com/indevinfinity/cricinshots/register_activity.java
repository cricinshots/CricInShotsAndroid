package com.indevinfinity.cricinshots;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register_activity extends AppCompatActivity {
TextView old_user;
ProgressBar reg_progressBar;
EditText edit_email;/*edit_username, edit_password, edit_confirmpass;*/
Button button_register;
CheckBox terms;
int appVersion=0;
String[] reply_sh = {"","","",""};
String response,emailid;
HttpURLConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_new);
        String locale = register_activity.this.getResources().getConfiguration().locale.getISO3Country();
        //edit_username = findViewById(R.id.reg_username);
        edit_email = findViewById(R.id.reg_email);
        //edit_password = findViewById(R.id.reg_pass);
        //edit_confirmpass = findViewById(R.id.reg_confirmpass);
        old_user = findViewById(R.id.old_user);
        reg_progressBar = findViewById(R.id.progressbarreg);
        reg_progressBar.setVisibility(View.INVISIBLE);
        button_register = findViewById(R.id.button_register);
        terms = findViewById(R.id.terms_check);

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersion = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg_progressBar.setVisibility(View.VISIBLE);
                check_data();
            }
        });
        old_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register_activity.this,Sign_In.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
    protected void onPause()
    {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }
    protected void onResume()
    {
        super.onResume();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);

    }
public void check_data(){
    final String email = edit_email.getText().toString().trim();
    /**final String password  = edit_password.getText().toString().trim();
    final String conPass = edit_confirmpass.getText().toString().trim();
    final String username = edit_username.getText().toString().trim();

    if(TextUtils.isEmpty(username)){
        FancyToast.makeText(this,"Please enter a username",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
        //Toast.makeText(this,"Please enter a username",Toast.LENGTH_LONG).show();
        reg_progressBar.setVisibility(View.INVISIBLE);
        return;
    }**/
    if(TextUtils.isEmpty(email)){
        FancyToast.makeText(this,"Please enter an email id",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
        //Toast.makeText(this,"Please enter an email id",Toast.LENGTH_LONG).show();
        reg_progressBar.setVisibility(View.INVISIBLE);
        return;
    }
    if(!isEmailValid(email)){
        FancyToast.makeText(this,"Please enter a valid email",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
        //Toast.makeText(this,"Please enter a valid email", Toast.LENGTH_LONG).show();
        reg_progressBar.setVisibility(View.INVISIBLE);
        return;
    }/**
    if(TextUtils.isEmpty(password)){
        FancyToast.makeText(this,"Please enter a password",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
        //Toast.makeText(this,"Please enter a password",Toast.LENGTH_LONG).show();
        reg_progressBar.setVisibility(View.INVISIBLE);
        return;
    }
    if( password.length() < 6){
        Toast.makeText(this,"Please enter a password of minimum 6 characters",Toast.LENGTH_LONG).show();
        reg_progressBar.setVisibility(View.INVISIBLE);
        return;
    }
    if(!isValidPassword(password) || password.length() < 6){
        reg_progressBar.setVisibility(View.INVISIBLE);
        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Invalid Password");
        alertDialog.setMessage(Html.fromHtml(getString(R.string.passcheck)));
        //getString(R.string.p_cont)
        alertDialog.setCancelable(true);
        alertDialog.setIcon(R.mipmap.ic_launcher_round);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }

        });
        alertDialog.show();
        return;
    }
    if(TextUtils.isEmpty(conPass)){
        FancyToast.makeText(this,"Please confirm your password",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
        //Toast.makeText(this,"Please confirm your password",Toast.LENGTH_LONG).show();
        reg_progressBar.setVisibility(View.INVISIBLE);
        return;
    }
    if (!conPass.equals(password)){
        FancyToast.makeText(this,"Your passwords do not match",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
        //Toast.makeText(this,"Your passwords do not match", Toast.LENGTH_LONG).show();
        reg_progressBar.setVisibility(View.INVISIBLE);
        return;
    }
    **//**if (!terms.isChecked()){
        reg_progressBar.setVisibility(View.INVISIBLE);
        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Terms and Conditions");
        alertDialog.setMessage(Html.fromHtml(getString(R.string.tandccheck)));
        //getString(R.string.p_cont)
        alertDialog.setCancelable(true);
        alertDialog.setIcon(R.mipmap.ic_launcher_round);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }

        });
        alertDialog.show();
        return;
    }**/

    da_register(email);
    /**
    edit_username.setText("");

    edit_password.setText("");
    edit_confirmpass.setText("");

    **/

}
public static boolean isEmailValid(String email) {
    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
    }
/**public boolean isValidPassword(final String password) {
    Pattern pattern;
    Matcher matcher;
    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    pattern = Pattern.compile(PASSWORD_PATTERN);
    matcher = pattern.matcher(password);
    return matcher.matches();
    }**/
public void da_register(final String emailidb){
    emailid = emailidb;
    Thread thread = new Thread() {
    @Override
    public void run() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Looper.prepare();

        RequestQueue queue = Volley.newRequestQueue(register_activity.this);
        String url = "https://cricinshots.com/auth/signup.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            final JSONObject myObject = new JSONObject(response);
                            final boolean s_val = myObject.getBoolean("s");
                            final String toast_c = myObject.getString("m");
                            if(s_val){
                                FancyToast.makeText(register_activity.this,toast_c,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                                startActivity(new Intent(register_activity.this,Sign_In.class));
                            }else{
                                FancyToast.makeText(register_activity.this,toast_c,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("Error: "+e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(error.networkResponse!=null && error.networkResponse.data!=null) {
                    //byte[] erMsg = error.networkResponse.data;
                    try {
                        JSONObject job = new JSONObject(new String(error.networkResponse.data));
                        String errMsg = job.getString("m");
                        FancyToast.makeText(register_activity.this, errMsg, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

                    } catch (JSONException je) {
                        FancyToast.makeText(register_activity.this, "Something went wrong.", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    }
                }
                else
                    FancyToast.makeText(register_activity.this, "Please check your connection and try again.", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

            }
        }){
            @Override
            public Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",emailidb);
                return params;
            }
        };
        queue.add(stringRequest);
    /**BufferedReader reader = null;

        try
        {

            URL url = new URL("https://cricinshots.com/auth/signup.php");

            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("User-Agent","CiSAndroid/"+appVersion);
            OutputStream os=new BufferedOutputStream(conn.getOutputStream());
            os.write(("email="+ Uri.encode(emailid,"US-ASCII")).getBytes());
            os.flush();
            os.close();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            response = sb.toString();
            final JSONObject myObject = new JSONObject(response);
            final String s_val = myObject.get("s").toString();
            final String toast_c = myObject.get("m").toString();

            register_activity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    if(s_val.equals("true")){
                        FancyToast.makeText(register_activity.this,toast_c,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                        startActivity(new Intent(register_activity.this,Sign_In.class));
                    }else if(s_val.equals("false")){
                        FancyToast.makeText(register_activity.this,toast_c,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    }
                    System.out.println(response);

                    // Toast.makeText(register_activity.this,response,Toast.LENGTH_LONG).show();
                }
            });
        }
        catch(final Exception ex)
        {
            register_activity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(register_activity.this,ex.toString(),Toast.LENGTH_LONG).show();
                }
            });

        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }**/

        // Show response on activity
        Looper.loop();
    }
    };
     thread.start();


    //content.setText(response);

    //
    //Toast.makeText(this,"Registration done",Toast.LENGTH_LONG).show();
    reg_progressBar.setVisibility(View.INVISIBLE);
}




}

/**
 Thread thread = new Thread() {
@Override
public void run() {
Looper.prepare();
 try {
 // Create a URL for the desired page
 URL url = new URL("mysite.com/thefile.txt");

 // Read all the text returned by the server
 BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
 String str;
 while ((str = in.readLine()) != null) {
 // str is one line of text; readLine() strips the newline character(s)
 }
 in.close();
 } catch (MalformedURLException e) {
 } catch (IOException e) {
 }

 Looper.loop();
 }
 };
 thread.start();

 **/
