/*
    Khoa Dang
    ktd160030
    Fingerpainting app

    This class defines the Canvas and the Paint used in the Fingerpainting app.
 */


package com.example.ica1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

public class PaintView extends View {
    private int x;
    private int y;
    private int radius;
    List<List<CustomPoint>> lineList = new LinkedList<>(); // List of lines drawn
    List<CustomPoint> pointsList; // List of points that make up a line
    Paint pColor;

    public PaintView(Context context) {
        super(context);
    }
    
    public PaintView(Context context, AttributeSet attrs){
        super(context, attrs);
        setupDrawing();
    }

    public void setupDrawing() {
        // Set up Paint attributes
        pColor = new Paint();
        pColor.setColor(Color.BLACK);
        pColor.setStyle(Paint.Style.FILL);
        pColor.setStrokeCap(Paint.Cap.ROUND);

        // Set up default brush size
        x = this.getWidth()/2;
        y = this.getHeight()/2;
        radius = 3;
    }


    public void onDraw(Canvas canvas) {

        // Draw each list of CustomPoints (lines) in the line list
        for(List<CustomPoint> pointsList: lineList){
            for (CustomPoint p: pointsList){
                canvas.drawCircle(p.getX(), p.getY(), p.getRadius(), p.getPaint());
            }
        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Create new points list for line and add to master line list
                pointsList = new LinkedList<>();
                lineList.add(pointsList);

                // Get coordinates for starting point
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();

                // Create new point and add to pointsList
                CustomPoint p = new CustomPoint(x,y);
                p.setRadius(radius);
                p.getPaint().setColor(pColor.getColor());
                pointsList.add(p);
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                // Get coordinates and add point to pointsList
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();

                // Create new point and add to pointsList
                p = new CustomPoint(x, y, pointsList.get(0).getRadius(), pointsList.get(0).getPaint());
                pointsList.add(p);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                // Get coordinates and add point to pointsList
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();

                // Create new point and add to pointsList
                p = new CustomPoint(x,y,pointsList.get(0).getRadius(), pointsList.get(0).getPaint());
                pointsList.add(p);
                invalidate();
                break;
        }
        return true;
    }

    public void changeColor(String s){
        // Set color to red, green, blue, or black
        if (s.equals("Red"))
            pColor.setColor(Color.RED);
        else if (s.equals("Green"))
            pColor.setColor(Color.GREEN);
        else if (s.equals("Blue"))
            pColor.setColor(Color.BLUE);
        else if (s.equals("Black"))
            pColor.setColor(Color.BLACK);
        invalidate();
    }

    public void changeSize(int i) {
        // Change circle radius size
        radius = i;
        invalidate();
    }

}
