package com.example.practiceandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class WidgetActivity extends Activity {
    private TextView textPoll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_main);
        Button btnGood = findViewById(R.id.good);
        Button btnBad = findViewById(R.id.bad);
        textPoll = findViewById(R.id.question);

        // 익명 클래스
        btnGood.setOnClickListener(new Button.OnClickListener() {
            public void onClick(android.view.View v) {
                textPoll.setText("Good~!");
            }
        });
        // 람다 함수
        btnBad.setOnClickListener(v -> textPoll.setText("Bad~ㅠㅠ"));
    }
}
