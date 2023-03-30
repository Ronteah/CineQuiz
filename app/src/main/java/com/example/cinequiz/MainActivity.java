package com.example.cinequiz;
import com.example.cinequiz.utils.ListQuestions;
import com.example.cinequiz.utils.OscarCounter;
import com.example.cinequiz.utils.PreferencesSaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import com.example.cinequiz.utils.CustomGestureListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureDetector;
    private TextView nbOscars;
    private ScrollView scrollView;

    private static int CLICK_THRESHOLD = 120;

    @Override
    public void onBackPressed() {}

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide(); //hide the title bar
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        ListQuestions.init();

        PreferencesSaver.createSharedPreferences(getApplicationContext());

        if(!PreferencesSaver.getRunning()) {
            PreferencesSaver.LoadPreferences();
            PreferencesSaver.setRunning(true);
        }


        ImageButton easy = findViewById(R.id.btnEasy);
        ImageButton medium = findViewById(R.id.btnMedium);
        ImageButton hard = findViewById(R.id.btnHard);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        LinearLayout layoutMedium = findViewById(R.id.layoutMedium);
        LinearLayout layoutHard = findViewById(R.id.layoutHard);

        TextView needMedium = findViewById(R.id.needMedium);
        TextView needHard = findViewById(R.id.needHard);

        if(OscarCounter.getNbOscars() < 15) {
            medium.setColorFilter(filter);
            needMedium.setText(OscarCounter.getNbOscarsString() + "/15");

            medium.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    gestureDetector.onTouchEvent(event);
                    return false;
                }
            });

        } else {
            layoutMedium.setAlpha(0);

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
        }

        if(OscarCounter.getNbOscars() < 30) {
            hard.setColorFilter(filter);
            needHard.setText(OscarCounter.getNbOscarsString() + "/30");

            hard.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    gestureDetector.onTouchEvent(event);
                    return false;
                }
            });

        } else {
            layoutHard.setAlpha(0);

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

        nbOscars = findViewById(R.id.nbOscars);

        nbOscars.setText(OscarCounter.getNbOscarsString());

        if(Locale.getDefault().getDisplayLanguage().equals("français")){
            easy.setImageResource(R.drawable.btn_facile);
            medium.setImageResource(R.drawable.btn_moyen);
            hard.setImageResource(R.drawable.btn_difficile);
        }

        gestureDetector = new GestureDetectorCompat(this, new CustomGestureListener(this, StatActivity.class, gestureDetector, ">", "MainActivity"));

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
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}