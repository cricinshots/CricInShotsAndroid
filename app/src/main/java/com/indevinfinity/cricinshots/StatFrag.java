package com.indevinfinity.cricinshots;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class StatFrag extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context mContext;
    TextView b1,b2,b3,b4,b5,b6,overrev,overextras;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public StatFrag() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static StatFrag newInstance(String param1, String param2) {
        StatFrag fragment = new StatFrag();
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
        final View stf = inflater.inflate(R.layout.fragment_stat, container, false);
        b1 = stf.findViewById(R.id.oball1);
        b2 = stf.findViewById(R.id.oball2);
        b3 = stf.findViewById(R.id.oball3);
        b4 = stf.findViewById(R.id.oball4);
        b5 = stf.findViewById(R.id.oball5);
        b6 = stf.findViewById(R.id.oball6);
        overrev = stf.findViewById(R.id.tv_ballingfigs);
        overextras = stf.findViewById(R.id.tv_extras);

        return stf;
    }

    public void updateOvers(String[] overstat){

            b1.setText(overstat[1]);
            b2.setText(overstat[2]);
            b3.setText(overstat[3]);
            b4.setText(overstat[4]);
            b5.setText(overstat[5]);
            b6.setText(overstat[6]);

    }

    public void updatecommentary(){

    }

    public void updateoversummary(String rev, String extras){

        overrev.setText(rev);
        overextras.setText(extras);

    }
}