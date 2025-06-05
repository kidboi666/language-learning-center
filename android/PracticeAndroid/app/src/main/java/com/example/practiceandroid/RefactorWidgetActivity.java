package com.example.practiceandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RefactorWidgetActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_main);
        Button btnGood = findViewById(R.id.good);
        Button btnBad = findViewById(R.id.bad);
        btnGood.setOnClickListener(this);
        btnBad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView textPoll = findViewById(R.id.question);
        if (v.getId() == R.id.good) {
            textPoll.setText("Good~!");
        } else if (v.getId() == R.id.bad) {
            textPoll.setText("Bad~ㅠㅠ");
        }
    }
}
