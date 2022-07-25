package com.indevinfinity.cricinshots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

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

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_In extends AppCompatActivity {
    TextView new_user, pass_forgot;
    ProgressBar login_progressBar;
    EditText edit_email, edit_password;
    Button button_login;
    String responsefs;
    int appVersion = 0;
    HttpURLConnection conn;

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        new_user = findViewById(R.id.new_user);
        pass_forgot = findViewById(R.id.pass_forgot);
        login_progressBar = findViewById(R.id.progressbarsign);
        login_progressBar.setVisibility(View.INVISIBLE);
        edit_email = findViewById(R.id.login_email);
        edit_password = findViewById(R.id.login_pass);
        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_progressBar.setVisibility(View.VISIBLE);
                hideKeyboard(Sign_In.this);
                button_login.setEnabled(false);
                check_details();
            }
        });
        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sign_In.this, register_activity.class));
            }
        });
        pass_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_progressBar.setVisibility(View.VISIBLE);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Sign_In.this);
                View mView = getLayoutInflater().inflate(R.layout.forgotpass_dialog, null);
                Button okay = mView.findViewById(R.id.submit_fgtemail);
                final EditText edit_fgt_email = mView.findViewById(R.id.fgt_email);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.show();
                dialog.setCancelable(true);
                okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fgtEmail = edit_fgt_email.getText().toString().trim();
                        if (TextUtils.isEmpty(fgtEmail)) {
                            FancyToast.makeText(Sign_In.this, "Please enter your email id", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                            //Toast.makeText(Sign_In.this,"Please enter your email id",Toast.LENGTH_LONG).show();
                            login_progressBar.setVisibility(View.INVISIBLE);
                            return;
                        }
                        if (!isEmailValid(fgtEmail)) {
                            FancyToast.makeText(Sign_In.this, "Please enter a valid email", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                            //Toast.makeText(Sign_In.this,"Please enter a valid email", Toast.LENGTH_LONG).show();
                            login_progressBar.setVisibility(View.INVISIBLE);
                            return;
                        }
                        reset_pwd(fgtEmail);
                        dialog.dismiss();
                    }
                });

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

    public void check_details() {
        final String email = edit_email.getText().toString().trim();
        final String password = edit_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            FancyToast.makeText(this, "Please enter your email id", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            //Toast.makeText(this,"Please enter your email id",Toast.LENGTH_LONG).show();
            login_progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        if (!isEmailValid(email)) {
            FancyToast.makeText(this, "Please enter a valid email", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
            //Toast.makeText(this,"Please enter a valid email", Toast.LENGTH_LONG).show();
            login_progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            FancyToast.makeText(this, "Please enter your password", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            //Toast.makeText(this,"Please enter your password",Toast.LENGTH_LONG).show();
            login_progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        da_login(email, password);

    }

    public void reset_pwd(String wht_email) {
        FancyToast.makeText(this, "An email with instructions to reset your password has been sent to the above email is", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();
        //Toast.makeText(Sign_In.this,"An email with instructions to reset your password has been sent to the above email is", Toast.LENGTH_LONG).show();
    }

    public void da_login(final String email, final String password) {

        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                //RequestQueue queue = Volley.newRequestQueue(Sign_In.this);
                //String url = "https://cricinshots.com/auth/signin.php";

                RequestQueue queue = Volley.newRequestQueue(Sign_In.this);
                String url = "https://cricinshots.com/auth/signin.php";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    final String message = jsonObject.getString("m");
                                    final String androidversion = jsonObject.getString("t");
                                    PreferenceManager.getDefaultSharedPreferences(Sign_In.this).edit().putString("and_ver", androidversion).apply();
                                    System.out.println(response);
                                    Sign_In.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            edit_email.setText("");
                                            edit_password.setText("");
                                            FancyToast.makeText(Sign_In.this, message, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                                            Intent intent = new Intent(Sign_In.this, MainActivity.class);
                                            login_progressBar.setVisibility(View.INVISIBLE);
                                            startActivity(intent);
                                        }
                                    });

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    System.out.println("Error: " + e);

                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        button_login.setEnabled(true);
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            //byte[] erMsg = error.networkResponse.data;
                            try {
                                JSONObject job = new JSONObject(new String(error.networkResponse.data));
                                String errMsg = job.getString("m");
                                FancyToast.makeText(Sign_In.this, errMsg, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

                            } catch (JSONException je) {
                                FancyToast.makeText(Sign_In.this, "Something went wrong.", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                            }
                        } else
                            FancyToast.makeText(Sign_In.this, "Please check your connection and try again.", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

                    }
                }) {
                    @Override
                    public Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", email);
                        params.put("auth", password);

                        return params;
                    }
                };
                queue.add(stringRequest);
                Looper.loop();

            }
        }).start();


        //Toast.makeText(this,"Login Done",Toast.LENGTH_LONG).show();
    }
}
