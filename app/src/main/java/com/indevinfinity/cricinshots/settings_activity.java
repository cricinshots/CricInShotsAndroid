package com.indevinfinity.cricinshots;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

public class settings_activity extends AppCompatActivity {
ImageButton button_back;
TextView feed_bug,text_about,sign_out,chk_updates,creds;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activity);
        creds = findViewById(R.id.creditstv);
        button_back = findViewById(R.id.button_back);
        feed_bug = findViewById(R.id.feed_bug);
        text_about = findViewById(R.id.about);
        sign_out = findViewById(R.id.signout);
        chk_updates = findViewById(R.id.chk_updates);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        if (!MainActivity.authtoken){
            sign_out.setText("Sign In or Register");
        }
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(settings_activity.this,front_activity.class));
            }
        });
        feed_bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog(1);
            }
        });
        text_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog(0);
            }
        });
        creds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog(2);
            }
        });
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.authtoken){
                    startActivity(new Intent(settings_activity.this,register_activity.class));
                }else {
                    dialog(3);
                }
            }
        });

        chk_updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                FancyToast.makeText(settings_activity.this,"Checking for updates",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                //Toast.makeText(settings_activity.this,"Checking for updates",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        //startActivity(new Intent(settings_activity.this,front_activity.class));
    }
public void dialog(int which_dialog){

    if(which_dialog==0){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(settings_activity.this);
        View mView = getLayoutInflater().inflate(R.layout.about_dialog, null);
        Button okay = mView.findViewById(R.id.cancel);
        Button website = mView.findViewById(R.id.vwebsite);
        TextView about = mView.findViewById(R.id.text_about_info);
        about.setText(Html.fromHtml(getString(R.string.about_app)));
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.setIcon(R.mipmap.ic_launcher_round);
        dialog.show();
        dialog.setCancelable(false);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                Toast.makeText(settings_activity.this,"Taking you to CodersEra!",Toast.LENGTH_LONG).show();
            }
        });
    }else if(which_dialog==1){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(settings_activity.this);
        View mView = getLayoutInflater().inflate(R.layout.feedback_dialog, null);
        final EditText edit_name =  mView.findViewById(R.id.sendername);
        final EditText edit_feedback =  mView.findViewById(R.id.senderquery);
        Button submit_feed = mView.findViewById(R.id.submit_feed);
        Button cancel_feed = mView.findViewById(R.id.cancel_feed);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        dialog.setIcon(R.mipmap.ic_launcher_round);
        dialog.setCancelable(false);
        submit_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whosent = edit_name.getText().toString();
                String ac_feedback = edit_feedback.getText().toString();
                if(whosent.equals("") || ac_feedback.equals("")){
                    FancyToast.makeText(settings_activity.this,"Please enter required information",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                    //Toast.makeText(settings_activity.this,"Please enter required information",Toast.LENGTH_LONG).show();
                }else{
                    edit_name.setText("");
                    edit_feedback.setText("");
                    //sending feedback code will go here
                    dialog.dismiss();
                    FancyToast.makeText(settings_activity.this,"Thank you for your feedback",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                    //Toast.makeText(settings_activity.this,"Thank you for your feedback",Toast.LENGTH_LONG).show();
                }
            }
        });
        cancel_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_name.setText("");
                edit_feedback.setText("");
                dialog.dismiss();
            }
        });
    }else if(which_dialog ==2){
        AlertDialog.Builder builder = new AlertDialog.Builder(settings_activity.this);
        builder.setTitle("Credits")
                .setMessage(Html.fromHtml(getString(R.string.icon_credit)))
                .setCancelable(false)
                .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }else if(which_dialog==3){
        AlertDialog.Builder builder = new AlertDialog.Builder(settings_activity.this);
        builder.setTitle("Sign out?")
                .setMessage("This will sign you out of the app. Continue?")
                .setCancelable(false)
                .setPositiveButton("Continue",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        progressBar.setVisibility(View.VISIBLE);
                        MainActivity.authtoken = false;
                        startActivity(new Intent(settings_activity.this,Sign_In.class));
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
        }
}
