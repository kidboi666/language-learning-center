package com.example.practiceandroid;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm; // 프래그먼트 매니저
    FragmentTransaction ft; // 프래그먼트 트랜잭션

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager(); // 프래그먼트 매니저 참조 객체 생성
        ft = fm.beginTransaction(); // 프래그먼트 매니저 시작
    }

    public void showFragment(View view) {
        ft = fm.beginTransaction();
        if (view == findViewById(R.id.button1)) {
            FragmentFirst fr1 = new FragmentFirst();
            ft.replace(R.id.fragment_container, fr1); // 프래그먼트 교체 메서드 replace(교체될 프래그먼트가 보여질 최상위 루트뷰, 교체될 프래그먼트)
            ft.commit(); // 변경 내용 적용을 위한 프래그먼트 커밋
        } else {
            FragmentSecond fr2 = new FragmentSecond();
            ft.replace(R.id.fragment_container, fr2); // 프래그먼트 교체 메서드 replace(교체될 프래그먼트가 보여질 최상위 루트뷰, 교체될 프래그먼트)
            ft.commit();
        }
    }
}
