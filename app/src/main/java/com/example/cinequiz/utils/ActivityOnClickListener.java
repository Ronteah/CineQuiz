package com.example.cinequiz.utils;

import android.app.Activity;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;

import com.example.cinequiz.GameActivity;

public class ActivityOnClickListener implements View.OnClickListener {
//    private CustomGestureListener listener;
    private GestureDetectorCompat gesture;
    private Activity mylovelyActivity;
    private Boolean point;
    private String points;
    public ActivityOnClickListener(GestureDetectorCompat gestureDetector,Activity mylovelyActivity, Boolean point, String points) {
        this.mylovelyActivity = mylovelyActivity;
        this.point = point;
        this.points = points;
        this.gesture = new GestureDetectorCompat(mylovelyActivity, new CustomGestureListener(mylovelyActivity, GameActivity.class, gestureDetector, ">", points));

        System.out.println(mylovelyActivity.toString());
        System.out.println(point.toString());
        System.out.println(points);
    }

    @Override
    public void onClick(View v)
    {
        addPoints(point);
        System.out.println("boubou");
        System.out.println(mylovelyActivity.toString());
        System.out.println(point.toString());
        System.out.println(points);
        this.gesture = new GestureDetectorCompat(mylovelyActivity, new CustomGestureListener(mylovelyActivity, GameActivity.class, gesture, ">", points));
    }

    public void addPoints(boolean point){
        if (point){
            points += "o";
        }else {
            points += "x";
        }
    }

};