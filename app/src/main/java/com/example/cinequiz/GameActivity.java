package com.example.cinequiz;

import java.util.Random;
import com.example.cinequiz.utils.Question;
import com.example.cinequiz.utils.RepCounter;
import com.example.cinequiz.utils.OscarCounter;
import com.example.cinequiz.utils.PreferencesSaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinequiz.utils.CustomGestureListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.Spread;
import nl.dionsegijn.konfetti.xml.KonfettiView;
import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.core.Angle;

public class GameActivity extends AppCompatActivity {

    private List<Button> listBtnChoix;
    private List<String> listChoix;
    private List<ImageView> listPoints;
    private int reponse;
    private ImageButton back;
    private ImageView image;
    private TextView temps;
    private TextView nbRep;
    private String points;
    private String mode;
    private String difficulty;

    private List<Question> facile;
    private List<Question> moyen;
    private List<Question> difficile;
    private List<Question> marvel;
    private List<Question> dc;

    private List<Question> questions;

    private Question question;

    private GestureDetectorCompat gestureDetector;
    SharedPreferences sharedPreferences;
    private KonfettiView konfettiView = null;

    @Override
    public void onBackPressed() {}

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide the title bar
        setContentView(R.layout.activity_game);



        Intent intent = getIntent();
        points = intent.getStringExtra("points");
        mode = intent.getStringExtra("mode");
        difficulty = intent.getStringExtra("difficulty");

        this.listBtnChoix = new ArrayList<>();
        this.listChoix = new ArrayList<>();
        this.listPoints = new ArrayList<>();
        this.facile = new ArrayList<>();
        this.moyen = new ArrayList<>();
        this.difficile = new ArrayList<>();
        this.marvel = new ArrayList<>();
        this.dc = new ArrayList<>();
        this.questions = new ArrayList<>();

        InitialiseQuestions();

        this.question = ChoisieQuestion();

        this.reponse = this.question.getReponse();

        Dialog dialog = new Dialog(GameActivity.this);

        back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.dialog_layout);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);

                image.setImageAlpha(80);
                for (int i = 0; i < listBtnChoix.size(); i++) {
                    listBtnChoix.get(i).setAlpha(0.3F);
                }

                btnYes.setBackgroundColor(Color.GREEN);
                btnNo.setBackgroundColor(Color.RED);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Intent intent1 = new Intent(GameActivity.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                });

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        image.setImageAlpha(255);
                        for (int i = 0; i < listBtnChoix.size(); i++) {
                            listBtnChoix.get(i).setAlpha(1);
                        }
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });




        image = findViewById(R.id.imageFilm);
        image.setImageResource(question.getImage());

        LayoutInflater victory = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View victoyView = victory.inflate(R.layout.victory_layout, null);

        konfettiView = victoyView.findViewById(R.id.KonfettiView);

        temps = findViewById(R.id.temps);

        nbRep = findViewById(R.id.nbRep);
        nbRep.setText(RepCounter.getBonneRep() + "/" + RepCounter.getTotalRep());

        InitialiseList();

        InitialisePoints();


    }

    private static List<Question> filterQuestionsByMode(List<Question> questions, String mode) {
        List<Question> filteredQuestions = new ArrayList<>();
        for (Question question : questions) {
            if (question.getMode().equals(mode)) {
                filteredQuestions.add(question);
            }
        }
        return filteredQuestions;
    }

    private static List<Question> filterQuestionsNormal(List<Question> questions) {
        List<Question> filteredQuestions = new ArrayList<>();
        for (Question question : questions) {
            if (!question.getMode().equals("Blindtest")) {
                filteredQuestions.add(question);
            }
        }
        return filteredQuestions;
    }

    private Question ChoisieQuestion(){
        Random random = new Random();
        switch (difficulty){
            case "easy":
                 this.questions = facile;
                break;
            case "medium":
                this.questions= moyen;
                break;
            case "hard":
                this.questions= difficile;
                break;
            default:
                this.questions= difficile;
                break;
        }

        if (mode.equals("normal")){
            this.questions= filterQuestionsNormal(this.questions);
        }else {
            this.questions= filterQuestionsByMode(this.questions, mode);
        }

        return this.questions.get(random.nextInt(this.questions.size()));
    }

    private int NombreOscars(){
        if (RepCounter.getBonneRep() < 3){
            return 0;
        } else if (RepCounter.getBonneRep() <= 5){
            return 1;
        } else if (RepCounter.getBonneRep() == 10){
            return 3;
        } else {
            return 2;
        }
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

        if (points.length() > 9) {
            //Écran de victoire

            switch (mode){
                case "normal":
                    OscarCounter.addNbNormal(NombreOscars());
                    break;
                case "blindtest":
                    OscarCounter.addNbBlindtest(NombreOscars());
                    break;
                case "image":
                    OscarCounter.addNbImage(NombreOscars());
                    break;
                case "celebrity":
                    OscarCounter.addNbCelebrity(NombreOscars());
                    break;
                case "replique":
                    OscarCounter.addNbReplique(NombreOscars());
                    break;
            }

            OscarCounter.refreshNbOscars();

            if(RepCounter.getBonneRep() == 10)
                RepCounter.addNbPerfect();

            RepCounter.addScore((double)RepCounter.getBonneRep());

            //Sauvegarde les données
            sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
            PreferencesSaver.SavePreferences(sharedPreferences);

            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            listPoints.get(points.length()).setImageResource(R.drawable.white_dot);
            listPoints.get(points.length()).getLayoutParams().width = 120;

            for (int i = 0; i < points.length(); i++){
                if (points.charAt(i) == 'o'){
                    listPoints.get(i).setImageResource(R.drawable.green_dot);
                }else {
                    listPoints.get(i).setImageResource(R.drawable.red_dot);
                }
            }
        }
    }

    private void InitialiseList(){

        listBtnChoix.add(findViewById(R.id.choix1));
        listBtnChoix.add(findViewById(R.id.choix2));
        listBtnChoix.add(findViewById(R.id.choix3));
        listBtnChoix.add(findViewById(R.id.choix4));
        Random random = new Random();



        listChoix.add("choix 1");
        listChoix.add("choix 2");
        listChoix.add("choix 3");
        listChoix.add("choix 4");

        int numrep = random.nextInt(questions.size());

        Resources res = this.getResources();

        for (int i = 0; i < listBtnChoix.size(); i++){
            if (i == numrep){
                listBtnChoix.get(i).setText(reponse);
            }else {
                listBtnChoix.get(i).setText(questions.get(random.nextInt(questions.size())).getReponse());
            }
            if ((listBtnChoix.get(i).getText()).equals(res.getString(this.reponse))){
                System.out.println("ligne 326");
                //Bonne réponse
                listBtnChoix.get(i).setOnClickListener(new View.OnClickListener() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        points += "o";
                        CreateGestureDetector();
                        SetButtonsUnclickable();

                        v.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.green)));

                        for (int i = 0; i < listBtnChoix.size(); i++){
                            if (!listBtnChoix.get(i).getText().equals(res.getString(reponse))){
                                listBtnChoix.get(i).setAlpha((float) 0.2);
                            }
                        }

                        RepCounter.addTotalRep();
                        RepCounter.addBonneRep();
                        nbRep.setText(RepCounter.getBonneRep() + "/" + RepCounter.getTotalRep());

                        if(RepCounter.getTotalRep() == 10) {
                            rain();
                            VictoryScreen();
                            temps.setText("");
                        } else {
                            temps.setText("Swipe >>>");
                        }
                    }
                });
            } else {
                //Mauvaise réponse
                listBtnChoix.get(i).setOnClickListener(new View.OnClickListener() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        points += "x";
                        CreateGestureDetector();
                        SetButtonsUnclickable();

                        v.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.red)));

                        for (int i = 0; i < listBtnChoix.size(); i++){
                            if (!listBtnChoix.get(i).getText().equals(res.getString(reponse)) && !listBtnChoix.get(i).equals(v)){
                                listBtnChoix.get(i).setAlpha((float) 0.2);
                            }
                            if (listBtnChoix.get(i).getText().equals(res.getString(reponse))){
                                listBtnChoix.get(i).setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.green)));
                            }
                        }

                        RepCounter.addTotalRep();
                        nbRep.setText(RepCounter.getBonneRep() + "/" + RepCounter.getTotalRep());

                        if(RepCounter.getTotalRep() == 10) {
                            rain();
                            VictoryScreen();
                            temps.setText("");
                        } else {
                            temps.setText("Swipe >>>");
                        }
                    }
                });
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private void VictoryScreen(){
        Dialog dialog = new Dialog(GameActivity.this);

        GestureDetectorCompat gestureDetector1 = CreateGestureDetectorVictory();

        dialog.setContentView(R.layout.victory_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean eventConsumed = gestureDetector1.onTouchEvent(motionEvent);
                if (eventConsumed) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        TextView score = dialog.findViewById(R.id.score);
        TextView scoreComment = dialog.findViewById(R.id.scoreComment);
        TextView oscarsEarned = dialog.findViewById(R.id.oscarsEarned);

        score.setText(RepCounter.getBonneRep() + "/10");
        switch (NombreOscars()){
            case 0:
                scoreComment.setText(R.string.not_enough);
                oscarsEarned.setText("0");
                break;
            case 1:
                scoreComment.setText(R.string.not_bad);
                oscarsEarned.setText("1");
                break;
            case 2:
                scoreComment.setText(R.string.good);
                oscarsEarned.setText("2");
                break;
            case 3:
                scoreComment.setText(R.string.perfect);
                oscarsEarned.setText("3");
                break;
        }

        image.setImageAlpha(80);
        for (int i = 0; i < listBtnChoix.size(); i++) {
            listBtnChoix.get(i).setAlpha(0.3F);
        }

        dialog.show();
    }

    private void CreateGestureDetector(){
        gestureDetector = new GestureDetectorCompat(this, new CustomGestureListener(this, GameActivity.class, gestureDetector, ">", points, mode, difficulty));
    }

    private GestureDetectorCompat CreateGestureDetectorVictory(){
        return new GestureDetectorCompat(this, new CustomGestureListener(this, MainActivity.class, gestureDetector, ">"));
    }

    private void SetButtonsUnclickable(){
        for (int i = 0; i < listBtnChoix.size(); i++) {
            listBtnChoix.get(i).setClickable(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector == null) return false;
        return gestureDetector.onTouchEvent(event);
    }

    public void rain() {
        System.out.println("Pluie !!!");
        EmitterConfig emitterConfig = new Emitter(5, TimeUnit.SECONDS).perSecond(100);
        konfettiView.start(
                new PartyFactory(emitterConfig)
                        .angle(Angle.BOTTOM)
                        .spread(Spread.ROUND)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(0f, 15f)
                        .position(new Position.Relative(0.0, 0.0).between(new Position.Relative(1.0, 0.0)))
                        .build()
        );
    }

    private void InitialiseQuestions(){
        this.facile.add(new Question("image", R.string.kill_bill, R.drawable.modeimage));
        this.facile.add(new Question("image", R.string.le_parain, R.drawable.modeimage));
        this.facile.add(new Question("image", R.string.le_parain, R.drawable.modeimage));
        this.facile.add(new Question("image", R.string.le_parain, R.drawable.modeimage));
        this.facile.add(new Question("image", R.string.le_parain, R.drawable.modeimage));

        this.moyen.add(new Question("image", R.string.le_parain, R.drawable.modeimage));
        this.moyen.add(new Question("image", R.string.le_parain, R.drawable.modeimage));
        this.moyen.add(new Question("image", R.string.le_parain, R.drawable.modeimage));
        this.moyen.add(new Question("image", R.string.le_parain, R.drawable.modeimage));

        this.difficile.add(new Question("image", R.string.le_parain, R.drawable.modeimage));
        this.difficile.add(new Question("image", R.string.le_parain, R.drawable.modeimage));
        this.difficile.add(new Question("image", R.string.le_parain, R.drawable.modeimage));
        this.difficile.add(new Question("image", R.string.le_parain, R.drawable.modeimage));

    }

}