package com.example.cinequiz.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.content.Intent;

import androidx.core.view.GestureDetectorCompat;

import com.example.cinequiz.R;

import java.util.Objects;

public class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private final Activity activity;

    private final Class secondActivity;
    private GestureDetectorCompat gestureDetector;

    private String actualActivity;

    public CustomGestureListener(Activity activity, Class secondActivity, GestureDetectorCompat gestureDetector, String actualActivity) {
        System.out.println("CustomGestureListener: " + actualActivity);

        this.activity = activity;
        this.secondActivity = secondActivity;
        this.gestureDetector = gestureDetector;
        this.actualActivity = actualActivity;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;

        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();

            System.out.println("Y: " + diffY);
            System.out.println("X: " + diffX);

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX < 0 && !Objects.equals(this.actualActivity, "StatActivity")) {
                        // Swipe de droite à gauche

                        System.out.println("Swipe <<<");

                        Intent intent = new Intent(activity, secondActivity);
                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                    if (diffX > 0 && !Objects.equals(this.actualActivity, "MainActivity")) {
                        // Swipe de gauche à droite

                        System.out.println("Swipe >>>");

                        Intent intent = new Intent(activity, secondActivity);
                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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