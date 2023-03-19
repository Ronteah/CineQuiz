package com.example.cinequiz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import androidx.core.view.GestureDetectorCompat;

import com.example.cinequiz.utils.CustomGestureListener;

import java.util.Objects;

public class StatActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide the title bar
        setContentView(R.layout.activity_stat);
        System.out.println("boubou6");
        gestureDetector = new GestureDetectorCompat(this, new CustomGestureListener(this, StatActivity.class, gestureDetector));

        RelativeLayout home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatActivity.this, MainActivity.class);
                intent.putExtra("difficulty", "easy");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("boubou1");
        return gestureDetector.onTouchEvent(event);
    }

}