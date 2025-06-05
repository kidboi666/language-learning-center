package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class DrawActivity extends Activity {
    private MyView vw;
    private Button btnClear, btnSave;

    public class Vertex {
        Vertex(float ax, float ay, boolean ad) {
            x = ax;
            y = ay;
            Draw = ad;
        }

        float x;
        float y;
        boolean Draw;
    }

    ArrayList<Vertex> arVertex;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_main);

        arVertex = new ArrayList<Vertex>();

        FrameLayout drawingArea = findViewById(R.id.drawing_area);
        vw = new MyView(this);
        drawingArea.addView(vw);

        btnClear = findViewById(R.id.btn_clear);
        btnSave = findViewById(R.id.btn_save);

        btnClear.setOnClickListener((View v) -> {
            arVertex.clear();
            vw.invalidate();
        });
        btnSave.setOnClickListener((View v) -> {
            System.out.println("Save button clicked");
        });
    }

    // 커스텀뷰
    protected class MyView extends View {
        Paint Pnt; // 선을 그리기 위한 Paint 객체를 담을 필드 선언

        // 생성자 함수
        public MyView(Context context) {
            super(context);
            Pnt = new Paint(); // 선을 그리기 위한 Paint 객체 인스턴스 생성
            Pnt.setColor(Color.BLUE); // 선 색상 설정
            Pnt.setStrokeWidth(3); // 선 굵기 설정
            Pnt.setAntiAlias(true); // 선 부드럽게 설정
        }

        // 터치 좌표들을 얻어와 ArrayList객체에 저장된 좌표들을 이용하여 선을 이어주는 함수
        public void onDraw(Canvas canvas) { // onDraw의 매개변수 canvas는 View에서 자기 자신을 그릴 때 사용하는 Canvas 객체임.
            int i;
            canvas.drawColor(Color.LTGRAY); // 캔버스 색상 설정
            for (i = 0; i < arVertex.size(); i++) { // 터치 좌표들을 얻어와 선을 이어줌.
                if (arVertex.get(i).Draw) {
                    canvas.drawLine(arVertex.get(i - 1).x, arVertex.get(i - 1).y, arVertex.get(i).x, arVertex.get(i).y, Pnt);
                }
            }
        }

        // 터치 이벤트를 처리하는 메소드
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) { // 터치 이벤트가 발생했을 때
                arVertex.add(new Vertex(event.getX(), event.getY(), false)); // 좌표 추가 (draw는 false)
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) { // 터치한 상태로 손가락이 움직이고 있을 때(드래그)
                arVertex.add(new Vertex(event.getX(), event.getY(), true)); // 좌표 추가 (draw는 true)
                invalidate(); // 화면 다시 그리기 요청
                return true;
            }
            return false;
        }
    }
}
