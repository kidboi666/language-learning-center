package com.example.practiceandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {
    int value = 0;
    TextView mText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_main);
        mText = findViewById(R.id.text);
        mHandler.sendEmptyMessage(0); // 메시지 없이 핸들러 호출
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(@NonNull Message msg) {
            value++; // 필드 값 증가
            mText.setText("Timer Value = " + value); // 텍스트뷰에 값 출력
            mHandler.sendEmptyMessageDelayed(0, 1000); // 1초 뒤에 핸들러 호출
        }
    };
}
