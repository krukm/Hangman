package com.matthewkruk.jh5_hangman_mkruk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class MyDrawView1 extends View {

    Context context;
    int drawingCount = 0;

    Paint translucentRedPen, bluePen;
    RectF oval = new RectF();
    RectF oval2 = new RectF();

    int backgroundColor;

    private void init() {
        backgroundColor = getResources().getColor(R.color.backgroundColor);

        translucentRedPen = new Paint();
        translucentRedPen.setColor(getResources().getColor(R.color.translucentRedPen));

        bluePen = new Paint();
        bluePen.setColor(getResources().getColor(R.color.bluePen));
        bluePen.setStyle(Paint.Style.STROKE);
        bluePen.setStrokeWidth(2);
    }


    public MyDrawView1(Context context) {
        super(context);
        init();
        this.context = context;
    }

    public MyDrawView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        this.context = context;
    }

    public MyDrawView1(Context context,
                       AttributeSet ats,
                       int defaultStyle) {
        super(context, ats, defaultStyle);
        init();

        this.context = context;
    }

    public void increment() {
        drawingCount += 1;
        invalidate();
    }

    public void reset() {
        drawingCount = 0;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        float width = getWidth();
        float height = getHeight();

        canvas.save();
        canvas.scale(width / 600, height / 650);
        canvas.drawColor(backgroundColor);

        for (int i = 1; i <= drawingCount; i++) {
            switch (i) {
                case 1:
                    oval.set(100, 50, 550, 600);
                    canvas.drawOval(oval, translucentRedPen); // sun
                    break;
                case 2:
                    canvas.drawLine(550, 600, 550, 50, bluePen); // post
                    break;
                case 3:
                    canvas.drawLine(550, 50, 100, 50, bluePen); // vertical beam
                    break;
                case 4:
                    canvas.drawLine(100, 50, 100, 100, bluePen); // rope
                    break;
                case 5:
                    oval2.set(75, 100, 125, 150);
                    canvas.drawOval(oval2, bluePen);  // head
                    break;
                case 6:
                    canvas.drawLine(100, 150, 100, 300, bluePen);  // body
                    break;
                case 7:
                    canvas.drawLine(100, 200, 175, 250, bluePen); // right arm
                    break;

                case 8:
                    canvas.drawLine(100, 200, 25, 250, bluePen); // left arm
                    break;
                case 9:
                    canvas.drawLine(100, 300, 175, 375, bluePen); // right leg
                    break;
                case 10:
                    canvas.drawLine(100, 300, 25, 375, bluePen); // left leg
                    break;
            }
        }

        canvas.restore();
    }
}
