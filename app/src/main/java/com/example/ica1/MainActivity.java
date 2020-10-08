/*
    Khoa Dang
    ktd160030
    Fingerpainting app

    This app allows users to draw lines on the screen. As the user drags their finger across the screen,
    circles are drawn where their finger touches.  The size and color of the circles can be set using
    the seek bar and buttons.  There is also an undo button that allows the user to undo the last line
    that they drew.

 */
package com.example.ica1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    PaintView pv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pv = findViewById(R.id.paintView);
        final TextView textView = findViewById(R.id.sizeValue);


        // Set listener for PaintView
        ActivitySwipeDetector activitySwipeDetector = new ActivitySwipeDetector();
        pv.setOnTouchListener(activitySwipeDetector);

        // Set up PaintView and draw the canvas
        pv.setupDrawing();
        pv.invalidate();

        // Set ids for button clicks
        Button red, green, blue, black, undo;
        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);
        black = findViewById(R.id.black);
        undo = findViewById(R.id.undo);


        // Definition for color button listener
        View.OnClickListener colorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                setColor(b.getText().toString());
            }
        };

        // Set color buttons listener
        red.setOnClickListener(colorListener);
        green.setOnClickListener(colorListener);
        blue.setOnClickListener(colorListener);
        black.setOnClickListener(colorListener);

        // Definition for undo button listener
        View.OnClickListener undoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (!pv.lineList.isEmpty()){
                    pv.lineList.remove(pv.lineList.size()-1);
                    pv.invalidate();
                }
            }
        };

        // Set undo button listener
        undo.setOnClickListener(undoListener);

        // Set id for SeekBar and default value
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(0);

        // Definition for SeekBar listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // On change, set circle radius to new value
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(Integer.toString(3 + progress));
                setSize(progress+3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Set label for progress to new value
        textView.setText(Integer.toString(seekBar.getProgress()+3));

    }

    // Function for color change
    public void setColor(String s){
        pv.changeColor(s);
    }

    // Fucntion for circle radius change
    public void setSize(int i){pv.changeSize(i);}


}
