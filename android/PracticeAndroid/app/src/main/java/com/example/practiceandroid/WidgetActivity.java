package com.example.practiceandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class WidgetActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_main);
        Button btnGood = findViewById(R.id.good);
        btnGood.setOnClickListener(v -> {
            TextView textPoll = findViewById(R.id.question);
            textPoll.setText("Good!^^");
        });
        Button btnBad = findViewById(R.id.bad);
        btnBad.setOnClickListener(v -> {
            TextView textPoll = findViewById(R.id.question);
            textPoll.setText("Bad~ㅠㅠ");
        });
    }
}
