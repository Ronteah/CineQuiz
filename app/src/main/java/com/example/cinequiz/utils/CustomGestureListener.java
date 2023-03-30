package com.example.cinequiz.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.core.view.GestureDetectorCompat;

import com.example.cinequiz.GameActivity;
import com.example.cinequiz.R;

import java.util.Objects;


//ESSAYER AVEC LE GESTURE LISTENER DANS GAMEACTIVITY
public class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private final Activity activity;

    private final Class secondActivity;
    private final GestureDetectorCompat gestureDetector;

    private final String sens;
    private final String points;
    private final String mode;
    private final String difficulty;
    private final String activityCall;

    public CustomGestureListener(Activity activity, Class secondActivity, GestureDetectorCompat gestureDetector, String sens, String activityCall) {

        this.activity = activity;
        this.secondActivity = secondActivity;
        this.gestureDetector = gestureDetector;
        this.sens = sens;
        this.points = null;
        this.mode = null;
        this.difficulty = null;
        this.activityCall = activityCall;
    }


    public CustomGestureListener(Activity activity, Class secondActivity, GestureDetectorCompat gestureDetector, String sens, String points, String mode, String difficulty, String activityCall) {

        this.activity = activity;
        this.secondActivity = secondActivity;
        this.gestureDetector = gestureDetector;
        this.sens = sens;
        this.points = points;
        this.mode = mode;
        this.difficulty = difficulty;
        this.activityCall = activityCall;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;

        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();

            Intent intent = new Intent(activity, secondActivity);


            if (points != null) {
                intent.putExtra("points", points);
            }

            if (mode != null) {
                intent.putExtra("mode", mode);
            }

            if (difficulty != null) {
                intent.putExtra("difficulty", difficulty);
            }

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX < 0 && this.sens.equals(">")) {
                        // Swipe de droite à gauche

                        if(Objects.equals(activityCall, "GameActivity1")) {
                            GameActivity.dialog.dismiss();
                        }

                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        activity.finish();
                    }
                    if (diffX > 0 && this.sens.equals("<")) {
                        // Swipe de gauche à droite

                        if(Objects.equals(activityCall, "GameActivity1")) {
                            GameActivity.dialog.dismiss();
                        }

                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        activity.finish();
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