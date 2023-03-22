package com.example.cinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Locale;
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

        ImageButton modeNormal = findViewById(R.id.modeNormal);
        ImageButton modeBlindtest = findViewById(R.id.modeBlindtest);
        ImageButton modeImage = findViewById(R.id.modeImage);
        ImageButton modeCelebrity = findViewById(R.id.modeCelebrity);
        ImageButton modeReplique = findViewById(R.id.modeReplique);

        System.out.println(difficulty);

        if(Locale.getDefault().getDisplayLanguage().equals("français")){
            switch (difficulty) {
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
            modeNormal.setImageResource(R.drawable.modenormal);
            modeBlindtest.setImageResource(R.drawable.modeblindtest);
            modeImage.setImageResource(R.drawable.modeimage);
            modeCelebrity.setImageResource(R.drawable.modecelebrity);
            modeReplique.setImageResource(R.drawable.modereplique);
        } else {
            switch (difficulty) {
                case "easy":
                    textDifficulty.setImageResource(R.drawable.easy);
                    break;
                case "medium":
                    textDifficulty.setImageResource(R.drawable.medium);
                    break;
                case "hard":
                    textDifficulty.setImageResource(R.drawable.hard);
                    break;
            }
        }

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}