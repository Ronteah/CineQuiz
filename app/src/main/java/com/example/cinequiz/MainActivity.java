package com.example.cinequiz;
import com.example.cinequiz.utils.OscarCounter;
import com.example.cinequiz.utils.PreferencesSaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import com.example.cinequiz.utils.CustomGestureListener;
import com.example.cinequiz.utils.Question;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureDetector;
    private TextView nbOscars;
    private ScrollView scrollView;

    private static int CLICK_THRESHOLD = 120;

    SharedPreferences sharedPreferences;

    @Override
    public void onBackPressed() {}

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide(); //hide the title bar
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        //Load les données sauvegardées
        sharedPreferences = getApplicationContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        PreferencesSaver.LoadPreferences(sharedPreferences);


        ImageButton easy = findViewById(R.id.btnEasy);
        ImageButton medium = findViewById(R.id.btnMedium);
        ImageButton hard = findViewById(R.id.btnHard);

        nbOscars = findViewById(R.id.nbOscars);

        nbOscars.setText(OscarCounter.getNbOscarsString());

        if(Locale.getDefault().getDisplayLanguage().equals("français")){
            easy.setImageResource(R.drawable.btn_facile);
            medium.setImageResource(R.drawable.btn_moyen);
            hard.setImageResource(R.drawable.btn_difficile);
        }

        gestureDetector = new GestureDetectorCompat(this, new CustomGestureListener(this, StatActivity.class, gestureDetector, ">"));

        scrollView = findViewById(R.id.scrollView);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });

        easy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                long duration = event.getEventTime() - event.getDownTime();

                if (event.getAction() == MotionEvent.ACTION_UP && duration < CLICK_THRESHOLD) {

                    Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                    intent.putExtra("difficulty", "easy");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                }
                else {
                    gestureDetector.onTouchEvent(event);
                }

                return false;
            }
        });

        medium.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                long duration = event.getEventTime() - event.getDownTime();

                if (event.getAction() == MotionEvent.ACTION_UP && duration < CLICK_THRESHOLD) {

                    Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                    intent.putExtra("difficulty", "medium");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                }
                else {
                    gestureDetector.onTouchEvent(event);
                }

                return false;
            }
        });

        hard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                long duration = event.getEventTime() - event.getDownTime();

                if (event.getAction() == MotionEvent.ACTION_UP && duration < CLICK_THRESHOLD) {

                    Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                    intent.putExtra("difficulty", "hard");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                }
                else {
                    gestureDetector.onTouchEvent(event);
                }

                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}