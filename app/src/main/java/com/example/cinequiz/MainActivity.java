package com.example.cinequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import com.example.cinequiz.utils.CustomGestureListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide(); //hide the title bar
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        ImageButton easy = findViewById(R.id.btnEasy);
        ImageButton medium = findViewById(R.id.btnMedium);
        ImageButton hard = findViewById(R.id.btnHard);

        if(Locale.getDefault().getDisplayLanguage().equals("français")){
            easy.setImageResource(R.drawable.btn_facile);
            medium.setImageResource(R.drawable.btn_moyen);
            hard.setImageResource(R.drawable.btn_difficile);
        }

        System.out.println("MainActivity");
        System.out.println("My locale: "+ Locale.getDefault().getDisplayLanguage());
        gestureDetector = new GestureDetectorCompat(this, new CustomGestureListener(this, StatActivity.class, gestureDetector, "MainActivity"));

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                intent.putExtra("difficulty", "easy");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                intent.putExtra("difficulty", "medium");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                intent.putExtra("difficulty", "hard");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


//    private void buildDialog() {
//        String prenom = this.prenomInput.getText().toString();
//        String nom = this.nomInput.getText().toString();
//
//        AlertDialog.Builder builder = new AlertDialog.Builder((this));
//        builder.setMessage("nom: " + nom + "\nprenom: " + prenom)
//                .setTitle("Info")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // Action à effectuer lorsque l'utilisateur clique sur le bouton OK
//                    }
//                });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
}