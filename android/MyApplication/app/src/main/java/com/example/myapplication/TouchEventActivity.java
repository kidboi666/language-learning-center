package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TouchEventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnotherMyView vw = new AnotherMyView(this);
        vw.setOnTouchListener(vw);
        setContentView(vw);

    }

    // 콜백 메소드를 이용한 이벤트 처리
    class MyView extends View {
        public MyView(Context context) {
            super(context);
        }

        public boolean onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Toast.makeText(TouchEventActivity.this, "콜백 메소드", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        }
    }
    // View를 통한 콜백 메소드는 특정 터치 이벤트만 미리 지정, 이벤트 처리에 제한적임

    // 이벤트 리스너 인터페이스를 통한 구현체
    class TouchListenerClass implements View.OnTouchListener {
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Toast.makeText(TouchEventActivity.this, "이벤트 리스너 인터페이스", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        }
    }
    TouchListenerClass TouchListener = new TouchListenerClass();
    // 처리 가능한 이벤트가 제한적이지 않으므로 범용적이며, View를 상속받을 필요 없음.
    // 하지만 이벤트 마다 다른 클래스를 만든다면 소스 코드의 양도 많아지며 각 클래스마다 다른 명칭 부여. 혼란.

    // View를 통한 이벤트 리스너의 구현. 액티비티에서가 아니라 View에서 직접 이벤트 리스너를 등록. 재사용에서 유리.
    class AnotherMyView extends View implements View.OnTouchListener {
        public AnotherMyView(Context context) {
            super(context);
        }

        // onTouch라는 추상 메서드를 통해 터치 이벤트 구현.
        // onCreate에서 AnotherMyView 클래스의 인스턴스를 생성할 때 onTouchListener 인터페이스를 구현한다.
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Toast.makeText(TouchEventActivity.this, "View 이벤트 리스너", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        }
    }
}

// 이벤트 핸들러의 일반적인 우선 순위
// 1. View의 인터페이스 리스너
// 2. View의 콜백 메소드
// 3. 액티비티의 콜백 메소드
// 내부에 중첩된 안쪽 범위일수록 우선순위가 높음.
// 각 이벤트 핸들러에서 반환되는 반환값을 통해 다음 순위의 이벤트 핸들러에 대한 호출 여부를 결정.

