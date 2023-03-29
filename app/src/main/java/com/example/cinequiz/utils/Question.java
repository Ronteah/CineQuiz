package com.example.cinequiz.utils;

import com.example.cinequiz.R;

public class Question {

    private String mode;
    private int question;

    private int reponse;

    private String lien;

    private int replique;


    public Question(String mode, int reponse, String lien) {
        this.mode = mode;
        this.reponse = reponse;
        this.lien = lien;
    }

    public Question(String mode, int reponse, int replique) {
        this.mode = mode;
        this.reponse = reponse;
        this.replique = replique;
    }

    public int getQuestion() {
        switch (mode){
            case "blindtest":
                return R.string.question_blindtest;
            case "image":
                return R.string.question_image;
            case "celebrity":
                return R.string.question_celebrite;
            case "replique":
                return R.string.question_replique;
        }
        return R.string.question_replique;
    }

    public int getReponse() {
        return reponse;
    }

    public String getLien() {
        return lien;
    }

    public String getMode() {
        return mode;
    }

    public int getReplique() {
        return replique;
    }
}
