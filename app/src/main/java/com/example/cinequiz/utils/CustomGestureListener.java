package com.example.cinequiz.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.content.Intent;

import androidx.core.view.GestureDetectorCompat;

import java.util.List;
import java.util.Objects;

public class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private final Activity activity;

    private final Class secondActivity;
    private GestureDetectorCompat gestureDetector;

    private String sens;
    private String points;

    public CustomGestureListener(Activity activity, Class secondActivity, GestureDetectorCompat gestureDetector, String sens) {
        System.out.println("CustomGestureListener: " + sens);

        this.activity = activity;
        this.secondActivity = secondActivity;
        this.gestureDetector = gestureDetector;
        this.sens = sens;
        this.points = null;
    }


    public CustomGestureListener(Activity activity, Class secondActivity, GestureDetectorCompat gestureDetector, String sens, String points) {
        System.out.println("CustomGestureListener: " + sens);

        this.activity = activity;
        this.secondActivity = secondActivity;
        this.gestureDetector = gestureDetector;
        this.sens = sens;
        this.points = points;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;

        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();

            System.out.println("Y: " + diffY);
            System.out.println("X: " + diffX);

            Intent intent = new Intent(activity, secondActivity);



            if (points != null) {
                intent.putExtra("points", points);
            }

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX < 0 && this.sens.equals(">")) {
                        // Swipe de droite à gauche

                        System.out.println("Swipe >>>");

                        activity.startActivity(intent);
                    }
                    if (diffX > 0 && this.sens.equals("<")) {
                        // Swipe de gauche à droite

                        System.out.println("Swipe <<<");
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
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}