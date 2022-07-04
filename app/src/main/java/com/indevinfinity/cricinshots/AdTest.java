package com.indevinfinity.cricinshots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.view.View;

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

import java.util.HashMap;
import java.util.Map;

public class AdTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_test);


        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                RequestQueue queue = Volley.newRequestQueue(AdTest.this);
                String url = "https://cricinshots.com/xapi/ads/";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                   System.out.println(response);
                                    AdTest.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            //FancyToast.makeText(Sign_In.this, message, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                                            //Intent intent = new Intent(Sign_In.this, choose_match.class);
                                            //startActivity(intent);
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
                        System.out.println("No response, sirf error "+error.toString());
                    }
                }) {

                };
                queue.add(stringRequest);
                Looper.loop();

            }
        }).start();


    }
}
