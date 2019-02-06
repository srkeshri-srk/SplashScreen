package com.example.srk.splashscreen;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class sliderimage extends AppCompatActivity {

    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;
    private slideradapter slideradapter;

    private Button mnextbtn;
    private Button mbackbtn;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliderimage);

        mSliderViewPager = (ViewPager)findViewById(R.id.slideviewpager);
        mDotLayout = (LinearLayout)findViewById(R.id.dotslayout);

        mnextbtn = (Button)findViewById(R.id.nextbtn);
        mbackbtn = (Button)findViewById(R.id.prevbtn);

        slideradapter = new slideradapter(this);
        mSliderViewPager.setAdapter(slideradapter);

        addDotsIndicator(0);
        mSliderViewPager.addOnPageChangeListener(viewlistener);

        mnextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mnextbtn.getText().equals("Finish")){
                    Intent main2activity = new Intent(sliderimage.this,Main2Activity.class);
                    startActivity(main2activity);
                    finish();
                }
                else {
                    mSliderViewPager.setCurrentItem(mCurrentPage+1);
                }
            }
        });

        mbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSliderViewPager.setCurrentItem(mCurrentPage-1);
            }
        });
    }

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for(int i=0;i<mDots.length;i++)
        {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length>0){

            mDots[position].setTextColor(getResources().getColor(R.color.colorwhite));
        }
    }

    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);
            mCurrentPage = i;

            if(i==0)
            {
                mnextbtn.setEnabled(true);
                mbackbtn.setEnabled(false);
                mbackbtn.setVisibility(View.INVISIBLE);

                mnextbtn.setText("Next");
                mbackbtn.setText("");
            }
            else if (i==mDots.length-1)
            {
                mnextbtn.setEnabled(true);
                mbackbtn.setEnabled(true);
                mbackbtn.setVisibility(View.VISIBLE);

                mnextbtn.setText("Finish");
                mbackbtn.setText("Back");
            }
            else
            {
                mnextbtn.setEnabled(true);
                mbackbtn.setEnabled(true);
                mbackbtn.setVisibility(View.VISIBLE);

                mnextbtn.setText("Next");
                mbackbtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
