package com.example.cinequiz.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesSaver {

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static void createSharedPreferences(Context context) {
        if(!running)
            sharedPreferences = context.getSharedPreferences("UserPreferences",Context.MODE_PRIVATE);
    }

    private static SharedPreferences sharedPreferences;

    private static Boolean running = false;

    public static void setRunning(Boolean bool) {
        running = bool;
    }

    public static Boolean getRunning() {
        return running;
    }

    public static void SavePreferences(){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("nbOscars", OscarCounter.getNbOscars());
        editor.putInt("nbNormal", OscarCounter.getNbNormal());
        editor.putInt("nbBlindtest", OscarCounter.getNbBlindtest());
        editor.putInt("nbImage", OscarCounter.getNbImage());
        editor.putInt("nbCelebrity", OscarCounter.getNbCelebrity());
        editor.putInt("nbReplique", OscarCounter.getNbReplique());

        editor.putFloat("mean", (float) RepCounter.getMean());
        editor.putInt("scoresSize", RepCounter.getScoresSize());
        editor.putInt("nbPerfect", RepCounter.getNbPerfect());

        editor.apply();
    }

    public static void LoadPreferences(){
        OscarCounter.setNbOscars(sharedPreferences.getInt("nbOscars", 0));
        OscarCounter.setNbNormal(sharedPreferences.getInt("nbNormal", 0));
        OscarCounter.setNbBlindtest(sharedPreferences.getInt("nbBlindtest", 0));
        OscarCounter.setNbImage(sharedPreferences.getInt("nbImage", 0));
        OscarCounter.setNbCelebrity(sharedPreferences.getInt("nbCelebrity", 0));
        OscarCounter.setNbReplique(sharedPreferences.getInt("nbReplique", 0));

        RepCounter.setMean((double) sharedPreferences.getFloat("mean", 0));
        RepCounter.setScores(sharedPreferences.getInt("scoresSize", 0));
        RepCounter.setNbPerfect(sharedPreferences.getInt("nbPerfect", 0));
    }
}
