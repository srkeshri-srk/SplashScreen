package com.example.srk.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text_splash;
    ImageView image_splash;
    Animation fromtopbottom, smalltobig;

    private static int SPLASH_TIME_OUT = 3500;
    boolean firststart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_splash = (TextView)findViewById(R.id.text_splash);
        image_splash = (ImageView)findViewById(R.id.image_splash);

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        firststart = prefs.getBoolean("firststart",true);

        animationofsplash();
    }

    private void animationofsplash() {
        //for fonts
        Typeface GothamBook = Typeface.createFromAsset(getAssets(),"fonts/GothamBook.ttf");
        text_splash.setTypeface(GothamBook);

        // for animation
        fromtopbottom = AnimationUtils.loadAnimation(this,R.anim.fromtopbottom);
        smalltobig = AnimationUtils.loadAnimation(this,R.anim.smalltobig);
        text_splash.setAnimation(fromtopbottom);
        image_splash.setAnimation(smalltobig);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(firststart){
                    Intent sliderintent = new Intent(MainActivity.this,sliderimage.class);
                    startActivity(sliderintent);
                    finish();

                    SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("firststart",false);
                    editor.apply();

                }
                else {
                    Intent homeIntent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(homeIntent);
                    finish();
                }

            }
        },SPLASH_TIME_OUT);
    }
}
