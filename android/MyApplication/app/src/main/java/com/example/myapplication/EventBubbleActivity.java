package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class EventBubbleActivity extends Activity {
    @SuppressLint("ClickableViewAccessibility")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View vw = new MyView(this);
        // setOnTouchListener는 boolean을 반환하는 onTouch 메소드를 가진 이벤트 리스너를 인자로 받음.
        vw.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Toast.makeText(EventBubbleActivity.this, "No.1 View: Touch Event Received", Toast.LENGTH_SHORT).show();
//                return true;
            }
            return false;
        });
        setContentView(vw);
    }

    protected class MyView extends View {
        public MyView(Context context) {
            super(context);
        }

        @SuppressLint("ClickableViewAccessibility")
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Toast.makeText(EventBubbleActivity.this, "No.2 View: Touch Event Received", Toast.LENGTH_SHORT).show();
//                return true;
            }
            return false;
        } // View의 터치를 처리하므로 event 인수는 View의 좌상단을 기준으로 한 좌표를 가짐.
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(EventBubbleActivity.this, "No.3 Activity: Touch Event Receive", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    } // 액티비티에 속한 모든 View에 대한 터치 이벤트를 최종적으로 처리하므로 액티비티의 좌상단을 기준으로 한 좌표가 전달됨.
}
