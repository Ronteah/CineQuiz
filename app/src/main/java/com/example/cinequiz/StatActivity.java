package com.example.cinequiz;
import com.example.cinequiz.utils.OscarCounter;
import com.example.cinequiz.utils.RepCounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.GestureDetectorCompat;

import com.example.cinequiz.utils.CustomGestureListener;

import java.util.Locale;
import java.util.Objects;

public class StatActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureDetector;

    private TextView nbOscars;
    private TextView textTotal;
    private TextView mean;
    private TextView nbPerfect;
    private TextView nbNormal;
    private TextView nbBlindtest;
    private TextView nbImage;
    private TextView nbCelebrity;
    private TextView nbReplique;

    @Override
    public void onBackPressed() {}

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide the title bar
        setContentView(R.layout.activity_stat);

        gestureDetector = new GestureDetectorCompat(this, new CustomGestureListener(this, MainActivity.class, gestureDetector, "<", "StatActivity"));

        ImageView textStat = findViewById(R.id.textStat);
        if(Locale.getDefault().getDisplayLanguage().equals("français")){
            textStat.setImageResource(R.drawable.statistiques);
        } else {
            textStat.setImageResource(R.drawable.statistics);
        }

        nbOscars = findViewById(R.id.nbOscars);
        textTotal = findViewById(R.id.textTotal);
        mean = findViewById(R.id.mean);
        nbPerfect = findViewById(R.id.nbPerfect);
        nbNormal = findViewById(R.id.nbNormal);
        nbBlindtest = findViewById(R.id.nbBlindtest);
        nbImage = findViewById(R.id.nbImage);
        nbCelebrity = findViewById(R.id.nbCelebrity);
        nbReplique = findViewById(R.id.nbReplique);

        nbOscars.setText(OscarCounter.getNbOscarsString());
        textTotal.setText("Total = " + OscarCounter.getNbOscars());
        mean.setText("" + RepCounter.getMean());
        nbPerfect.setText("" + RepCounter.getNbPerfect());
        nbNormal.setText(OscarCounter.getNbNormalString());
        nbBlindtest.setText(OscarCounter.getNbBlindtestString());
        nbImage.setText(OscarCounter.getNbImageString());
        nbCelebrity.setText(OscarCounter.getNbCelebrityString());
        nbReplique.setText(OscarCounter.getNbRepliqueString());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

}