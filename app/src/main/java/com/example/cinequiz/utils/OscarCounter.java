package com.example.cinequiz.utils;

public class OscarCounter {
    private static int nbOscars = 0;

    public static void setNbOscars(int nbOscars) {
        OscarCounter.nbOscars = nbOscars;
    }

    public static int getNbOscars() {
        return nbOscars;
    }

    public static void refreshNbOscars() {
        nbOscars = nbNormal + nbBlindtest + nbImage + nbCelebrity + nbReplique;
    }

    public static String getNbOscarsString(){
        return ("" + nbOscars);
    }




    private static int nbNormal = 0;

    public static void setNbNormal(int nbNormal) {
        OscarCounter.nbNormal = nbNormal;
    }

    public static int getNbNormal() {
        return nbNormal;
    }

    public static void addNbNormal(int nb) {
        nbNormal += nb;
    }

    public static String getNbNormalString(){
        return ("" + nbNormal);
    }




    private static int nbBlindtest = 0;

    public static void setNbBlindtest(int nbBlindtest) {
        OscarCounter.nbBlindtest = nbBlindtest;
    }

    public static int getNbBlindtest() {
        return nbBlindtest;
    }

    public static void addNbBlindtest(int nb) {
        nbBlindtest += nb;
    }

    public static String getNbBlindtestString(){
        return ("" + nbBlindtest);
    }




    private static int nbImage = 0;

    public static void setNbImage(int nbImage) {
        OscarCounter.nbImage = nbImage;
    }

    public static int getNbImage() {
        return nbImage;
    }

    public static void addNbImage(int nb) {
        nbImage += nb;
    }

    public static String getNbImageString(){
        return ("" + nbImage);
    }




    private static int nbReplique = 0;

    public static void setNbReplique(int nbReplique) {
        OscarCounter.nbReplique = nbReplique;
    }

    public static int getNbReplique() {
        return nbReplique;
    }

    public static void addNbReplique(int nb) {
        nbReplique += nb;
    }

    public static String getNbRepliqueString(){
        return ("" + nbReplique);
    }




    private static int nbCelebrity = 0;

    public static void setNbCelebrity(int nbCelebrity) {
        OscarCounter.nbCelebrity = nbCelebrity;
    }

    public static int getNbCelebrity() {
        return nbCelebrity;
    }

    public static void addNbCelebrity(int nb) {
        nbCelebrity += nb;
    }

    public static String getNbCelebrityString(){
        return ("" + nbCelebrity);
    }
}
