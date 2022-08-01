package com.indevinfinity.cricinshots;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.mikhaellopez.circularimageview.CircularImageView;


public class Profile extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageButton backButton;
    //private CircularImageView profileImg;
    private TextView name,team,points;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    FirebaseUser fuser = mAuth.getCurrentUser();
    private String mParam1;
    private String mParam2, _points;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    public Profile() {

    }


    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View stf = inflater.inflate(R.layout.fragment_profile, container, false);
        name = stf.findViewById(R.id.tv_name);              // full name textview
        team = stf.findViewById(R.id.tv_team);              // team name textview
        points = stf.findViewById(R.id.tv_points);          // points textview

        name.setText(fuser.getDisplayName()); //set name
        myRef = database.getReference("Users").child(fuser.getUid());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    _points = dataSnapshot.child("points").getValue().toString();
                    points.setText(_points);
                    team.setText("Chennai Royale"); //set team name
                    team.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.teeam,0,0,0);
                    // set team points
                }catch (Exception bg){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return stf;
    }
}