package com.example.practiceandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

// 액티비티에서 직접 이벤트 리스너 인터페이스를 가져와 구현체로서 사용하는 방법
public class EventListenerActivity extends Activity implements View.OnTouchListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View vw = new View(this);
        vw.setOnTouchListener(this); // 이벤트 리스너 등록
        setContentView(vw);
    }

    // onTouch 메소드 오버라이드함.
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(this, "Touch Event Received", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

}
