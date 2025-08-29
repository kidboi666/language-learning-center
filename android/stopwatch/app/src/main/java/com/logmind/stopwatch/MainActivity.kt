package com.logmind.stopwatch

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var isRunning = false
    var timer: Timer? = null
    var time = 0

    val timeTask = mutableListOf(0)
    private val addedTextViews = mutableListOf<TextView>()

    private lateinit var btn_start: Button
    private lateinit var btn_refresh: Button
    private lateinit var tv_millisecond: TextView
    private lateinit var tv_second: TextView
    private lateinit var tv_minute: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        btn_start = findViewById(R.id.btn_start)
        btn_refresh = findViewById(R.id.btn_refresh)
        tv_millisecond = findViewById(R.id.tv_millisecond)
        tv_second = findViewById(R.id.tv_second)
        tv_minute = findViewById(R.id.tv_minute)

        btn_start.setOnClickListener(this)
        btn_refresh.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_start -> {
                when (isRunning) {
                    true -> pause()
                    false -> start()
                }
            }

            R.id.btn_refresh -> {
                refresh()
            }
        }
    }

    private fun start() {
        isRunning = true
        btn_start.text = "일시정지"
        btn_start.setBackgroundColor(getColor(R.color.red))

        /// timer(period: millisecond) {}: 코틀린에서 제공하는, 일정한 주기로 반복하는 동작을 수행할 때 쓰이는 함수
        /// {} 안에 작성한 코드들은 모두 백그라운드 스레드에서 실행함
        timer = timer(period = 10) {
            time++
            println(time)

            val milli_second = time % 100
            val second = (time % 6000) / 100
            val minute = time / 6000

            // ui 변경은 ui 스레드에서만 가능
            runOnUiThread {
                if (isRunning) {
                    tv_millisecond.text =
                        if (milli_second < 10) ".0$milli_second" else ".$milli_second"
                    tv_second.text = if (second < 10) ":0$second" else ":$second"
                    tv_minute.text = "$minute"
                }
            }
        }
    }

    private fun pause() {
        btn_start.text = "시작"
        btn_start.setBackgroundColor(getColor(R.color.blue))
        isRunning = false
        timer?.cancel()
        timeTask.add(time)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.main)

        val newTextView = TextView(this).apply {
            id = View.generateViewId()
            text = "${addedTextViews.size + 1}. ${formatTime(timeTask.last())}"
            textSize = 20f
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.black))
            setPadding(32, 16, 32, 16)
        }
        
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            startToStart = constraintLayout.id
            endToEnd = constraintLayout.id
            marginStart = 50
            marginEnd = 50
            
            if (addedTextViews.isEmpty()) {
                topToTop = constraintLayout.id
                topMargin = 120
            } else {
                topToBottom = addedTextViews.last().id
                topMargin = 16
            }
        }
        
        newTextView.layoutParams = layoutParams
        addedTextViews.add(newTextView)
        constraintLayout.addView(newTextView)
    }
    
    private fun formatTime(time: Int): String {
        val milliSecond = time % 100
        val second = (time % 6000) / 100
        val minute = time / 6000
        
        val milliStr = if (milliSecond < 10) "0$milliSecond" else "$milliSecond"
        val secondStr = if (second < 10) "0$second" else "$second"
        
        return "$minute:$secondStr.$milliStr"
    }

    private fun refresh() {
        timer?.cancel()
        btn_start.text = "시작"
        btn_start.setBackgroundColor(getColor(R.color.blue))
        isRunning = false
        time = 0
        tv_millisecond.text = ".00"
        tv_second.text = ":00"
        tv_minute.text = "00"
        
        val constraintLayout = findViewById<ConstraintLayout>(R.id.main)
        addedTextViews.forEach { textView ->
            constraintLayout.removeView(textView)
        }
        addedTextViews.clear()
        timeTask.clear()
        timeTask.add(0)
    }
}