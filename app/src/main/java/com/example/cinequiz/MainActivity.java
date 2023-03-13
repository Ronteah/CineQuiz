package com.example.cinequiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button stat;
    AlertDialog dialog;
    EditText nomInput;
    EditText prenomInput;

    public String getNom() {
        return nom;
    }

    String nom;

    public String getPrenom() {
        return prenom;
    }

    String prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        stat = findViewById(R.id.valider);

        this.nomInput = (EditText) findViewById(R.id.nom);

        this.prenomInput = (EditText) findViewById(R.id.prenom);

        this.prenom = this.prenomInput.getText().toString();
        this.nom = this.nomInput.getText().toString();

//        stat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                buildDialog();
//            }
//        });
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                intent.putExtra("nom", nom);
                intent.putExtra("prenom", prenom);
                startActivity(intent);
            }
        });
    }

    private void buildDialog() {
        String prenom = this.prenomInput.getText().toString();
        String nom = this.nomInput.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder((this));
        builder.setMessage("nom: " + nom + "\nprenom: " + prenom)
                .setTitle("Info")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action à effectuer lorsque l'utilisateur clique sur le bouton OK
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}