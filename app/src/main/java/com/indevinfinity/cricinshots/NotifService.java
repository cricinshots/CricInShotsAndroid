package com.indevinfinity.cricinshots;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotifService extends Service {
    private NotificationProvider np;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    String title,content;
    public NotifService() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public void onCreate(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        }
        //Problem solved
        final Context context = this;
        myRef = database.getReference("ads").child("notif").child("tosend");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                NotifData n = dataSnapshot.getValue(NotifData.class);
                boolean send = n.getSend();
                if(send){
                    title = n.getTitle();
                    content = n.getContent();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        np = new NotificationProvider(context);
                        np.build2(title,content,NotificationProvider.NOTIFICATION_TYPE_CLEARABLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //use this code to send notification


        //dev-k: todo: remove all todos after you "do" :)
        //dev-k: todo: set a delay so that service can sleep for that much time and save cycles (use postdelayed)
        //dev-k: todo: implement onstart() and ondestroy()

    }



}
