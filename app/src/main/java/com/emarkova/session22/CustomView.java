package com.emarkova.session22;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;

public class CustomView extends View {
    private Paint paint;
    float x = 0.0f;
    float y = 0.0f;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        paint.setColor(attributes.getColor(R.styleable.CustomView_android_color, Color.BLUE));
        attributes.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0, canvas.getWidth(), canvas.getHeight(), paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            changeColor();
            return true;
        }
        else if(event.getAction() == MotionEvent.ACTION_DOWN) {
            x = event.getRawX() - getTranslationX();
            y = event.getRawY() - getTranslationY();
            return true;
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE) {
            setTranslationX(event.getRawX() - x);
            setTranslationY(event.getRawY() - y);
            return true;
        }
        return false;
    }

    public void changeColor(){
        Random random = new Random();
        paint.setARGB(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        invalidate();
    }
}
