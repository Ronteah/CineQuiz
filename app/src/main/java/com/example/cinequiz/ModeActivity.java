package com.example.cinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ModeActivity extends AppCompatActivity {

    String prenom;

    String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        Intent intent = getIntent();

        this.prenom = intent.getStringExtra(prenom);

        this.nom = intent.getStringExtra(nom);

        TextView bou = findViewById(R.id.bou);

        bou.setText("bonjour " + prenom + " " + nom);
    }
}