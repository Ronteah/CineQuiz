package com.example.cinequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.app.Activity;
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

import com.example.cinequiz.utils.ActivityOnClickListener;
import com.example.cinequiz.utils.CustomGestureListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private List<Button> listBtnChoix;
    private List<String> listChoix;
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

        listBtnChoix = new ArrayList<Button>();
        listChoix = new ArrayList<String>();

        reponse = "choix 1";

        back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        image = findViewById(R.id.imageFilm);
        String imageUrl = "https://www.liberation.fr/resizer/2vCD5EFaJW82pYD8ax7xGr3dQCs=/600x0/filters:format(jpg):quality(70)/cloudfront-eu-central-1.images.arcpublishing.com/liberation/QYURNGWR2KB6JOKDXAW6LTMQW4.jpg";
        Picasso.get().load(imageUrl).into(image);

        temps = findViewById(R.id.temps);

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

                listBtnChoix.get(i).setOnClickListener(new ActivityOnClickListener(gestureDetector,this, true, points) {

                    @Override
                    public void onClick(View v) {
                        super.onClick(v);
                        SetButtonsUnclickable();
                        v.setBackgroundColor(Color.GREEN);
                        temps.setText("Swipe >>>");
                    }
                });
            }else {
                listBtnChoix.get(i).setOnClickListener(new ActivityOnClickListener(gestureDetector,this, false, points) {
                    @Override
                    public void onClick(View v) {
                        super.onClick(v);
                        SetButtonsUnclickable();
                        v.setBackgroundColor(Color.RED);
                        temps.setText("Swipe >>>");
                    }
                });
            }

        }
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