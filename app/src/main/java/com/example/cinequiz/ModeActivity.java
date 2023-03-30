package com.example.cinequiz;
import com.example.cinequiz.utils.OscarCounter;
import com.example.cinequiz.utils.RepCounter;
import com.example.cinequiz.GameActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Objects;

public class ModeActivity extends AppCompatActivity {

    private TextView nbOscars;

    @Override
    public void onBackPressed() {}

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


        nbOscars = findViewById(R.id.nbOscars);
        nbOscars.setText(OscarCounter.getNbOscarsString());

        RepCounter.resetBonneRep();
        RepCounter.resetTotalRep();


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
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        modeNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeActivity.this, GameActivity.class);
                intent.putExtra("points", "");
                intent.putExtra("mode", "normal");
                intent.putExtra("difficulty", difficulty);

                GameActivity.setModeInit("normal");

                startActivity(intent);
                finish();
            }
        });

        modeBlindtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeActivity.this, GameActivity.class);
                intent.putExtra("points", "");
                intent.putExtra("mode", "blindtest");
                intent.putExtra("difficulty", difficulty);

                GameActivity.setModeInit("blindtest");

                startActivity(intent);
                finish();
            }
        });

        modeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeActivity.this, GameActivity.class);
                intent.putExtra("points", "");
                intent.putExtra("mode", "image");
                intent.putExtra("difficulty", difficulty);

                GameActivity.setModeInit("image");

                startActivity(intent);
                finish();
            }
        });

        modeCelebrity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeActivity.this, GameActivity.class);
                intent.putExtra("points", "");
                intent.putExtra("mode", "celebrity");
                intent.putExtra("difficulty", difficulty);

                GameActivity.setModeInit("celebrity");

                startActivity(intent);
                finish();
            }
        });

        modeReplique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeActivity.this, GameActivity.class);
                intent.putExtra("points", "");
                intent.putExtra("mode", "replique");
                intent.putExtra("difficulty", difficulty);

                GameActivity.setModeInit("replique");

                startActivity(intent);
                finish();
            }
        });
    }

}