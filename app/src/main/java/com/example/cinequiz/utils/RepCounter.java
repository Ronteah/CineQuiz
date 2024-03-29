package com.example.cinequiz.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

    public static void setBonneRep(int nb) {
        bonneRep = nb;
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

    public static void setTotalRep(int nb) {
        totalRep = nb;
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

    public static void setMean(Double m) {
        mean = m;
    }

    public static int getScoresSize() {
        return scores.size();
    }

    public static void setScores(int scoresSize) {
        for (int i=0; i < scoresSize; i++){
            addScore(RepCounter.mean);
        }
    }

    public static void addScore(double score) {
        scores.add(score);
        mean = 0.0;
        for (double s:
             scores) {
            mean += s;
        }
        mean = mean/(scores.size());
    }
}
