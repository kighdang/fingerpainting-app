/*
    Khoa Dang
    ktd160030
    Fingerpainting App

    This class is used to define a CustomPoint object, which tells the PaintView where to draw the
    circles, how big to draw them, and what color to use.

 */
package com.example.ica1;

import android.graphics.Paint;

public class CustomPoint {
    private int x;
    private int y;
    private int radius;
    private Paint paint = new Paint();

    public CustomPoint(int x1, int y1){
        x = x1;
        y = y1;
    }

    public CustomPoint(int x1, int y1, int r, Paint p) {
        x = x1;
        y = y1;
        radius = r;
        paint = p;
    }

    public void setRadius(int r){radius =r;}
    public int getX(){return x;}
    public int getY(){return y;}
    public int getRadius(){return radius;}
    public Paint getPaint(){return paint;}

}
