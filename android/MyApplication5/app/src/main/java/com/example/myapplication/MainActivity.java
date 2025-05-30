package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// AppCompatActivity 를 상속 받는 메인 Activity 클래스
public class MainActivity extends AppCompatActivity {

    // Activity 가 생성될 때 호출 되는 생명 주기 메서드
    @Override
    public void onCreate(Bundle savedInstanceState) { // Bundle savedInstanceState: 이전 상태 정보가 저장된 객체 (앱이 재시작될 때 사용)
        // 기본 적인 Activity 생성
        super.onCreate(savedInstanceState);
        // EdgeToEdge 기능 활성화 (앱 콘텐츠가 상태바와 네비게이션바 영역까지 확장되도록 설정 - API35 부터 적용)
        EdgeToEdge.enable(this);
        // 레이아웃 파일 연결 (R.layout.activity_main: activity_main.xml 파일과 연결)
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void mOnClick(View v) {
        Intent intent = new Intent(this, SubActivity.class);
        startActivity(intent);
    }
}