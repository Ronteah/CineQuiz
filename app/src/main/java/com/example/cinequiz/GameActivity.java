package com.example.cinequiz;

import java.io.IOException;
import java.util.Random;

import com.example.cinequiz.utils.ListQuestions;
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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.Angle;

public class GameActivity extends AppCompatActivity {

    private List<Button> listBtnChoix;
    private List<ImageView> listPoints;
    private int reponse;
    private ImageButton back;
    private ImageView image;
    private TextView temps;
    private TextView nbRep;
    private TextView textReplique;
    private TextView phraseQuestion;
    private String points;
    private String mode;

    private static String modeInit;

    public static void setModeInit(String mode) {
        modeInit = mode;
    }

    private String modePourQuestionSuivante;
    private String difficulty;
    private LinearLayout bgTimer;
    private ImageButton btnSound;

    private Resources res;

    private List<Question> questions;

    private Question question;

    private GestureDetectorCompat gestureDetector;

    private MediaPlayer mediaPlayer;

    public static Dialog dialog;

    @Override
    public void onBackPressed() {}




    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide the title bar

        res = this.getResources();

        Intent intent = getIntent();
        points = intent.getStringExtra("points");
        mode = intent.getStringExtra("mode");
        modePourQuestionSuivante = mode;
        difficulty = intent.getStringExtra("difficulty");

        this.listBtnChoix = new ArrayList<>();
        this.listPoints = new ArrayList<>();
        this.questions = new ArrayList<>();

        this.question = ChoisieQuestion();
        ListQuestions.popQuestion(difficulty, this.question);
        this.reponse = this.question.getReponse();

        System.out.println(mode);

        switch (mode){
            case "replique":

                setContentView(R.layout.activity_game_replique);
                textReplique = findViewById(R.id.textReplique);

                if (this.questions.isEmpty()){
                    textReplique.setText(":[");
                }else {
                    textReplique.setText(question.getReplique());
                }

                phraseQuestion = findViewById(R.id.question);
                phraseQuestion.setText(R.string.question_replique);
                break;

            case "blindtest":

                setContentView(R.layout.activity_game_blindtest);
                phraseQuestion = findViewById(R.id.question);
                phraseQuestion.setText(R.string.question_blindtest);

                mediaPlayer = MediaPlayer.create(GameActivity.this, question.getReplique());
                mediaPlayer.start();

                btnSound = findViewById(R.id.btnSound);
                btnSound.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                        mediaPlayer.start();
                    }
                });
                break;

            case "celebrity":

                setContentView(R.layout.activity_game);

                phraseQuestion = findViewById(R.id.question);
                phraseQuestion.setText(R.string.question_celebrite);
                break;

            case "image":

                setContentView(R.layout.activity_game);

                phraseQuestion = findViewById(R.id.question);
                phraseQuestion.setText(R.string.question_image);
                break;
        }

        temps = findViewById(R.id.timer);

        back = findViewById(R.id.btnBack);
        InitialiseBack();

        image = findViewById(R.id.imageFilm);

        if(!Objects.equals(mode, "replique") && !Objects.equals(mode, "blindtest")) {
            Picasso.get().load(question.getLien()).into(image);
        }

        nbRep = findViewById(R.id.nbRep);
        nbRep.setText(RepCounter.getBonneRep() + "/" + RepCounter.getTotalRep());


        //Fond qui descend en fonction du temps
        bgTimer = findViewById(R.id.bgTimer);

        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, // translation x de départ
                Animation.RELATIVE_TO_SELF, 0f, // translation x d'arrivée
                Animation.RELATIVE_TO_SELF, 0f, // translation y de départ
                Animation.RELATIVE_TO_SELF, 1f // translation y d'arrivée
        );
        animation.setDuration(30000); // durée de l'animation en millisecondes
        animation.setStartOffset(0); // délai avant le démarrage de l'animation en millisecondes

        bgTimer.startAnimation(animation);

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

    private Question ChoisieQuestion(){
        Random random = new Random();
        switch (difficulty){
            case "easy":
                 this.questions = ListQuestions.getFacile();
                break;
            case "medium":
                this.questions= ListQuestions.getMoyen();
                break;
            case "hard":
                this.questions= ListQuestions.getDifficile();
                break;
            default:
                this.questions= ListQuestions.getFacile();
                break;
        }

        if (mode.equals("normal")){
            //faire des truc pour qu'il y est un seul mode et la question qui vas bien
            switch (random.nextInt(3)){
                case 0: mode = "image";
                    this.questions= filterQuestionsByMode(this.questions, mode);
                    break;
                case 1: mode = "celebrity";
                    this.questions= filterQuestionsByMode(this.questions, mode);
                    break;
                case 2: mode = "replique";
                    this.questions= filterQuestionsByMode(this.questions, mode);
                    break;
                default: mode = "image";
                    this.questions= filterQuestionsByMode(this.questions, mode);
                    System.out.println("ERREUR DANS SWITCH CHOIX MODE");
                    break;
            }
        }else {
            this.questions= filterQuestionsByMode(this.questions, mode);
        }
        try {
            return this.questions.remove(random.nextInt(this.questions.size()));
        }catch (IllegalArgumentException e){
            System.out.println("pas assez de question pour ce mode et cette difficulté");
            e.printStackTrace();
            return ListQuestions.getFacile().get(random.nextInt(ListQuestions.getFacile().size()));
        }
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

            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            listPoints.get(points.length()).setImageResource(R.drawable.white_dot);
            listPoints.get(points.length()).getLayoutParams().width = 160;

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

        CountDownTimer timer = new CountDownTimer(30000, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                temps.setText("" + (millisUntilFinished / 1000));
            }

            @SuppressLint({"SetTextI18n", "ResourceAsColor"})
            public void onFinish() {
                points += "x";

                if(RepCounter.getTotalRep() < 9) {
                    CreateGestureDetector();
                }
                SetButtonsUnclickable();

                bgTimer.setAlpha(0);

                for (int i = 0; i < listBtnChoix.size(); i++){
                    if (listBtnChoix.get(i).getText().equals(res.getString(reponse))){
                        listBtnChoix.get(i).setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.green)));
                    } else {
                        listBtnChoix.get(i).setAlpha((float) 0.2);
                    }
                }

                RepCounter.setTotalRep(points.length());
                nbRep.setText(RepCounter.getBonneRep() + "/" + RepCounter.getTotalRep());

                if (mediaPlayer != null){
                    mediaPlayer.stop();
                }

                if(RepCounter.getTotalRep() == 10) {
                    GameFinisher();
                } else {
                    temps.setText("Swipe >>>");
                }
            }
        };

        timer.start();

        try {
            int numrep = random.nextInt(listBtnChoix.size());


            for (int i = 0; i < listBtnChoix.size(); i++){
                if (i == numrep){
                    listBtnChoix.get(i).setText(reponse);
                }else {
                    listBtnChoix.get(i).setText(questions.remove(random.nextInt(questions.size())).getReponse());
                }
                if ((listBtnChoix.get(i).getText()).equals(res.getString(this.reponse))){
                    //Bonne réponse
                    listBtnChoix.get(i).setOnClickListener(new View.OnClickListener() {

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(View v) {
                            points += "o";

                            if (mediaPlayer != null){
                                mediaPlayer.stop();
                            }


                            if(RepCounter.getTotalRep() < 9) {
                                CreateGestureDetector();
                            }
                            SetButtonsUnclickable();

                            timer.cancel();

                            v.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.green)));

                            for (int i = 0; i < listBtnChoix.size(); i++){
                                if (!listBtnChoix.get(i).getText().equals(res.getString(reponse))){
                                    listBtnChoix.get(i).setAlpha((float) 0.2);
                                }
                            }

                            RepCounter.setTotalRep(points.length());
                            RepCounter.setBonneRep(Math.toIntExact(points.chars().filter(c -> c == 'o').count()));
                            nbRep.setText(RepCounter.getBonneRep() + "/" + RepCounter.getTotalRep());

                            bgTimer.setAlpha(0);

                            if(RepCounter.getTotalRep() >= 10) {
                                GameFinisher();
                                temps.setText("GG!");
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

                            if (mediaPlayer != null){
                                mediaPlayer.stop();
                            }

                            if(RepCounter.getTotalRep() < 9) {
                                CreateGestureDetector();
                            }
                            SetButtonsUnclickable();

                            timer.cancel();

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

                            bgTimer.setAlpha(0);

                            if(RepCounter.getTotalRep() == 10) {
                                GameFinisher();
                            } else {
                                temps.setText("Swipe >>>");
                            }
                        }
                    });
                }
            }
        } catch (IllegalArgumentException e) {
            SetButtonsUnclickable();
            listBtnChoix.get(0).setText("pas assez");
            listBtnChoix.get(1).setText("de questions");
            listBtnChoix.get(2).setText("pour ce mode");
            listBtnChoix.get(3).setText("veillez quitter");
            e.printStackTrace();
        }

    }

    private void InitialiseBack(){

        Dialog dialog1 = new Dialog(GameActivity.this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog1.setContentView(R.layout.dialog_layout);
                dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog1.setCancelable(false);
                dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog1.getWindow().getAttributes().windowAnimations = R.style.animation;

                Button btnYes = dialog1.findViewById(R.id.btnYes);
                Button btnNo = dialog1.findViewById(R.id.btnNo);

                image.setImageAlpha(80);
                for (int i = 0; i < listBtnChoix.size(); i++) {
                    listBtnChoix.get(i).setAlpha(0.3F);
                }

                btnYes.setBackgroundColor(Color.GREEN);
                btnNo.setBackgroundColor(Color.RED);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mediaPlayer != null){
                            mediaPlayer.stop();
                        }
                        dialog1.dismiss();
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
                        dialog1.dismiss();
                    }
                });

                dialog1.show();
            }
        });
    }
    private void GameFinisher(){
        //Écran de victoire
        switch (modeInit){
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
        PreferencesSaver.SavePreferences();

        (new Handler()).postDelayed(this::VictoryScreen, 800);
        temps.setText("GG!");
    }

    @SuppressLint("SetTextI18n")
    private void VictoryScreen(){
        dialog = new Dialog(GameActivity.this);

        GestureDetectorCompat gestureDetector1 = CreateGestureDetectorVictory();

        dialog.setContentView(R.layout.victory_layout);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        //GestureListener
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

        KonfettiView konfettiView = dialog.findViewById(R.id.KonfettiView);

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

        if(!((Activity) this).isFinishing())
        {
            dialog.show();
        }

        rain(konfettiView);

    }

    private void CreateGestureDetector(){
        gestureDetector = new GestureDetectorCompat(this, new CustomGestureListener(this, GameActivity.class, gestureDetector, ">", points, modePourQuestionSuivante, difficulty, "GameActivity"));
    }

    private GestureDetectorCompat CreateGestureDetectorVictory(){
        return new GestureDetectorCompat(this, new CustomGestureListener(this, MainActivity.class, gestureDetector, ">", "GameActivity1"));
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

    public void rain(KonfettiView konfettiView) {
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
}