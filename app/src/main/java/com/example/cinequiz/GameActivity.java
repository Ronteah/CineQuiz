package com.example.cinequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinequiz.utils.CustomGestureListener;
import com.squareup.picasso.Picasso;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private List<Button> listBtnChoix;
    private List<String> listChoix;
    private List<ImageView> listPoints;
    private String reponse;
    private ImageButton back;
    private ImageView image;
    private TextView temps;
    private String points;

    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide the title bar
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        points = intent.getStringExtra("points");
        System.out.println("bou" + points);

        listBtnChoix = new ArrayList<>();
        listChoix = new ArrayList<>();
        listPoints = new ArrayList<>();

        reponse = "choix 1";

        back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> {
            Intent intent1 = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent1);
        });

        image = findViewById(R.id.imageFilm);
        String imageUrl = "https://www.liberation.fr/resizer/2vCD5EFaJW82pYD8ax7xGr3dQCs=/600x0/filters:format(jpg):quality(70)/cloudfront-eu-central-1.images.arcpublishing.com/liberation/QYURNGWR2KB6JOKDXAW6LTMQW4.jpg";
        Picasso.get().load(imageUrl).into(image);

        temps = findViewById(R.id.temps);

        InitialiseList();

        InitialisePoints();

    }

    private void InitialisePoints(){
        listPoints.add(findViewById(R.id.point1));
        listPoints.add(findViewById(R.id.point2));
        listPoints.add(findViewById(R.id.point3));
        listPoints.add(findViewById(R.id.point4));
        listPoints.add(findViewById(R.id.point5));
        listPoints.add(findViewById(R.id.point6));
        listPoints.add(findViewById(R.id.point7));
        listPoints.add(findViewById(R.id.point8));
        listPoints.add(findViewById(R.id.point9));
        listPoints.add(findViewById(R.id.point10));



        for (int i = 0; i < points.length(); i++){
            if (points.charAt(i) == 'o'){
                listPoints.get(i).setImageResource(R.drawable.green_dot);
            }else {
                listPoints.get(i).setImageResource(R.drawable.red_dot);
            }
        }

        listPoints.get(points.length()).getLayoutParams().width = 60;
        listPoints.get(points.length()).requestLayout();
    }

    private void InitialiseList(){

        listBtnChoix.add(findViewById(R.id.choix1));
        listBtnChoix.add(findViewById(R.id.choix2));
        listBtnChoix.add(findViewById(R.id.choix3));
        listBtnChoix.add(findViewById(R.id.choix4));

        listChoix.add("choix 1");
        listChoix.add("choix 2");
        listChoix.add("choix 3");
        listChoix.add("choix 4");

        for (int i = 0; i < listBtnChoix.size(); i++){
            listBtnChoix.get(i).setText(listChoix.get(i));
            if (listChoix.get(i).equals(reponse)){

                listBtnChoix.get(i).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        points += "o";
                        CreateGestureDetector();
                        SetButtonsUnclickable();
                        v.setBackgroundColor(Color.GREEN);
                        temps.setText("Swipe >>>");
                    }
                });
            }else {
                listBtnChoix.get(i).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        points += "x";
                        CreateGestureDetector();
                        SetButtonsUnclickable();
                        v.setBackgroundColor(Color.RED);
                        temps.setText("Swipe >>>");
                    }
                });
            }

        }
    }

    private void CreateGestureDetector(){
        gestureDetector = new GestureDetectorCompat(this, new CustomGestureListener(this, GameActivity.class, gestureDetector, ">", points));
    }

    private void SetButtonsUnclickable(){
        for (int i = 0; i < listBtnChoix.size(); i++) {
            listBtnChoix.get(i).setClickable(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

}