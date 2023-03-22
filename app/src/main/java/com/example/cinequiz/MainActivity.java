package com.example.cinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

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

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                intent.putExtra("difficulty", "easy");
                startActivity(intent);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                intent.putExtra("difficulty", "medium");
                startActivity(intent);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                intent.putExtra("difficulty", "hard");
                startActivity(intent);
            }
        });
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