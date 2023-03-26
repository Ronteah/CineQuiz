package com.example.cinequiz.utils;

import android.content.SharedPreferences;

public class PreferencesSaver {

    public static void SavePreferences(SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("nbOscars", OscarCounter.getNbOscars());
        editor.putInt("nbNormal", OscarCounter.getNbNormal());
        editor.putInt("nbBlindtest", OscarCounter.getNbBlindtest());
        editor.putInt("nbImage", OscarCounter.getNbImage());
        editor.putInt("nbCelebrity", OscarCounter.getNbCelebrity());
        editor.putInt("nbReplique", OscarCounter.getNbReplique());

        editor.putFloat("mean", (float) RepCounter.getMean());
        editor.putInt("nbPerfect", RepCounter.getNbPerfect());

        editor.apply();
    }

    public static void LoadPreferences(SharedPreferences sp){
        OscarCounter.setNbOscars(sp.getInt("nbOscars", 0));
        OscarCounter.setNbNormal(sp.getInt("nbNormal", 0));
        OscarCounter.setNbBlindtest(sp.getInt("nbBlindtest", 0));
        OscarCounter.setNbImage(sp.getInt("nbImage", 0));
        OscarCounter.setNbCelebrity(sp.getInt("nbCelebrity", 0));
        OscarCounter.setNbReplique(sp.getInt("nbReplique", 0));

        RepCounter.addScore((double) sp.getFloat("mean", 0));
        RepCounter.setNbPerfect(sp.getInt("nbPerfect", 0));
    }
}
