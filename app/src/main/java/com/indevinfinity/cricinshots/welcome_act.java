package com.indevinfinity.cricinshots;

import android.content.Intent;
import android.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class welcome_act extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private SliderAdapter sliderAdapter;
    private Button mNextBtn,mPrevBtn;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_act);

         mSlideViewPager = findViewById(R.id.SlideViewPager);
         mDotLayout = findViewById(R.id.DotLayout);

         mNextBtn = findViewById(R.id.nextBtn);
         mPrevBtn = findViewById(R.id.prevBtn);

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListner);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentPage<mDots.length-1){
                    mSlideViewPager.setCurrentItem(mCurrentPage+1);
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("Load_State", "welcomed").apply();
                    Intent intent = new Intent(getApplicationContext(), register_activity.class);
                    startActivity(intent);
                }
            }
        });
        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage-1);
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

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for(int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);

            mCurrentPage = i;

            if(i==0){
                mNextBtn.setEnabled(true);
                mPrevBtn.setEnabled(false);
                mPrevBtn.setVisibility(View.INVISIBLE);
                mNextBtn.setText("Next");
                mPrevBtn.setText("");
            }else if(i== mDots.length-1){
                mNextBtn.setEnabled(true);
                mPrevBtn.setEnabled(true);
                mPrevBtn.setVisibility(View.VISIBLE);
                mNextBtn.setText("Finish");
                mPrevBtn.setText("Back");
            }else{
                mNextBtn.setEnabled(true);
                mPrevBtn.setEnabled(true);
                mPrevBtn.setVisibility(View.VISIBLE);
                mNextBtn.setText("Next");
                mPrevBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}