package com.example.cinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.view.GestureDetectorCompat;

import com.example.cinequiz.utils.CustomGestureListener;

import java.util.Locale;
import java.util.Objects;

public class StatActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide the title bar
        setContentView(R.layout.activity_stat);

        System.out.println("StatActivity");
        gestureDetector = new GestureDetectorCompat(this, new CustomGestureListener(this, MainActivity.class, gestureDetector, "<"));

        ImageView textStat = findViewById(R.id.textStat);
        if(Locale.getDefault().getDisplayLanguage().equals("français")){
            textStat.setImageResource(R.drawable.statistiques);
        } else {
            textStat.setImageResource(R.drawable.statistics);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

}