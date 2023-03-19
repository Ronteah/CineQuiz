package com.example.cinequiz.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.content.Intent;

import androidx.core.view.GestureDetectorCompat;

import com.example.cinequiz.MainActivity;
import com.example.cinequiz.StatActivity;

public class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private final Activity activity;

    private final Class secondActivity;
    private GestureDetectorCompat gestureDetector;

    public CustomGestureListener(Activity activity, Class secondActivity, GestureDetectorCompat gestureDetector) {
        System.out.println("boubou5");
        this.activity = activity;
        this.secondActivity = secondActivity;
        this.gestureDetector = gestureDetector;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        System.out.println("boubou");
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        // Swipe de gauche à droite
                        activity.finish();
                    } else {
                        // Swipe de droite à gauche
                        Intent intent = new Intent(activity, secondActivity);
                        activity.startActivity(intent);
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        System.out.println("boubou");
        return true;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}

