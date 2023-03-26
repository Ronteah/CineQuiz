package com.example.cinequiz.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class RepCounter {
    private static int bonneRep = 0;

    public static int getBonneRep() {
        return bonneRep;
    }

    public static void addBonneRep() {
        bonneRep += 1;
    }

    public static void resetBonneRep() {
        bonneRep = 0;
    }


    private static int totalRep = 0;

    public static int getTotalRep() {
        return totalRep;
    }

    public static void addTotalRep() {
        totalRep += 1;
    }

    public static void resetTotalRep() {
        totalRep = 0;
    }


    private static int nbPerfect = 0;

    public static void setNbPerfect(int nbPerfect) {
        RepCounter.nbPerfect = nbPerfect;
    }

    public static int getNbPerfect() {
        return nbPerfect;
    }

    public static void addNbPerfect() {
        nbPerfect += 1;
    }


    private static double mean = 0;

    private static List<Double> scores = new ArrayList<>();


    public static void setMean(double mean) {
        RepCounter.mean = mean;
    }

    public static double getMean() {
        return mean;
    }

    public static void addScore(double score) {
        scores.add(score);
        mean = 0.0;
        for (double s:
             scores) {
            mean += s;
        }
        mean = mean/scores.size();
        mean = round(mean, 2); //Limite à 2 chiffres après la virgule
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
