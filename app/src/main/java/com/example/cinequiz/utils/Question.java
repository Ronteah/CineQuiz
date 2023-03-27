package com.example.cinequiz.utils;

import android.media.Image;

import com.example.cinequiz.R;

public class Question {

    private String mode;
    private int question;

    private int reponse;

    private int image;


    public Question(String mode, int reponse, int image) {
        this.mode = mode;
        this.reponse = reponse;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public String getMode() {
        return mode;
    }
}
