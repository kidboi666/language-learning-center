package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class KeyboardActivity extends Activity {
    private MyView vw;

    public void onCreate(Bundle state) {
        super.onCreate(state);
        vw = new MyView(this);
        vw.setFocusable(true);
        vw.setFocusableInTouchMode(true);
        setContentView(vw);
    }

    protected class MyView extends View {
        float mX, mY, mR, mL;
        int mColor;

        public MyView(Context context) {
            super(context);
            mX = 500;
            mY = 500;
            mR = 600;
            mL = 600;
            mColor = Color.RED;
        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            Paint Pnt = new Paint();
            Pnt.setColor(mColor);
            Pnt.setAntiAlias(true);
            canvas.drawRect(mX, mY, mR, mL, Pnt);
        }
        public boolean onKeyDown(int KeyCode, KeyEvent event) {
            super.onKeyDown(KeyCode, event);
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (KeyCode) {
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        mX -= 5;
                        mR -= 5;
                        invalidate(); // 화면을 다시 그리라는 걸 전파해주는 메소드. 이후 onDraw 메소드 재호출
                        return true;
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        mX += 5;
                        mR += 5;
                        invalidate();
                        return true;
                    case KeyEvent.KEYCODE_DPAD_UP:
                        mY -= 5;
                        mL -= 5;
                        invalidate();
                        return true;
                    case KeyEvent.KEYCODE_DPAD_DOWN:
                        mY += 5;
                        mL += 5;
                        invalidate();
                        return true;
                }
            }
            return false;
        }
        public void setFocusable(boolean b) {

        }
    }
}
