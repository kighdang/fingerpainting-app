/*
    Khoa Dang
    ktd160030
    Fingerpainting App

    This class is used to handle all touch events made in the PaintView class.

 */
package com.example.ica1;

import android.view.MotionEvent;
import android.view.View;

public class ActivitySwipeDetector implements View.OnTouchListener {
    public ActivitySwipeDetector() {
    }

    // Handle the touch event
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.onTouchEvent(event);

        return true;

    }

}
