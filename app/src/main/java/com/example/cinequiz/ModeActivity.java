package com.example.cinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Objects;

public class ModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide the title bar
        setContentView(R.layout.activity_mode);

        Intent intent = getIntent();
        String difficulty = intent.getStringExtra("difficulty");

        ImageView textDifficulty = findViewById(R.id.textDifficulty);

        System.out.println(difficulty);

        switch (difficulty){
            case "easy":
                textDifficulty.setImageResource(R.drawable.facile);
                break;
            case "medium":
                textDifficulty.setImageResource(R.drawable.moyen);
                break;
            case "hard":
                textDifficulty.setImageResource(R.drawable.difficile);
                break;
        }

        ImageButton btnBack = findViewById(R.id.btnBack);

        ImageButton btnNormal = findViewById(R.id.modeNormal);
        ImageButton btnBlindtest = findViewById(R.id.modeBlindtest);
        ImageButton btnImage = findViewById(R.id.modeImage);
        ImageButton btnCelebrity = findViewById(R.id.modeCelebrity);
        ImageButton btnReplique = findViewById(R.id.modeReplique);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}