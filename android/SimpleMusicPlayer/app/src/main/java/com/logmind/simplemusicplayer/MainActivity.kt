package com.logmind.simplemusicplayer

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btn_play: Button
    lateinit var btn_pause: Button
    lateinit var btn_stop: Button
    var mService: MusicPlayerService? = null // 뮤직 플레이어 서비스를 담을 변수

    /**
     * 서비스와 구성요소의 연결 상태를 모니터링 해줌.
     */
    val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // MusicPlayerBinder로 형변환 해줌
            mService = (service as MusicPlayerService.MusicPlayerBinder).getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            // 만약 서비스가 끊기면, mService를 null로 만들어줌
            mService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn_play = findViewById(R.id.btn_play)
        btn_pause = findViewById(R.id.btn_pause)
        btn_stop = findViewById(R.id.btn_stop)

        btn_play.setOnClickListener(this)
        btn_pause.setOnClickListener(this)
        btn_stop.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_play -> {
                play()
            }

            R.id.btn_pause -> {
                pause()
            }

            R.id.btn_stop -> {
                stop()
            }
        }
    }

    /**
     * [onResume] 콜백 함수는 액티비티가 사용자에게 보이기 시작할때마다 실행되는 콜백 함수이다.
     * 여기서 바로 서비스를 시작해준다.
     * [mService] 가 null이면 "아직 서비스가 액티비티와 연결되지 않았다" 는 말이므로 버전에 따라 [startService]
     * 함수나 [startForegroundService] 함수를 호출해준다.
     * 이 두 함수는 아무리 많이 불러도 이미 해당 서비스가 만들어진 상태이면 서비스를 새로 만들지 않는다.
     */
    override fun onResume() {
        super.onResume()

        // 서비스 실행
        if (mService == null) {
            // 안드로이드 O 이상이면 startForegroundService를 사용해야 함
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(Intent(this, MusicPlayerService::class.java))
            } else {
                startService(Intent(applicationContext, MusicPlayerService::class.java))
            }


            // 이 액티비티를 서비스와 바인드 시킴.
            val intent = Intent(this, MusicPlayerService::class.java)

            /**
             *
             * [bindService]
             *
             * @param intent 서비스를 실행시키는 인텐트
             *
             * @param conn 바인드 시킬 서비스의 콜백 함수
             * 서비스와 액티비티가 연결되면 [onServiceConnected] 콜백 함수가 호출,
             * 연결이 끊기면 [onServiceDisconnected] 콜백 함수가 호출된다.
             *
             * @param flag 바인드 시킬 때의 플래그
             * [BIND_AUTO_CREATE] 바인드할 시점에 서비스가 실행되지 않은 상태라면 서비스를 생성
             */
            bindService(intent, mServiceConnection, BIND_AUTO_CREATE)
        }
    }

    /**
     *
     * 사용자가 액티비티를 떠나게 되면 실행되는 콜백함수.
     * if문을 사용하여 만약 mService가 null이 아닌 상태일 때, 즉 바인딩된 상태일 때, 즉 서비스가 실행된 상태일 때,
     * 만약 음악이 실행되고 있지 않은 상태라면 서비스를 종료하고 바인딩을 해제한다.
     * 음악이 실행 중이면 바인딩만 해제하여 다시 앱으로 돌아왔을 때 해당 서비스에 바인딩할 수 있게끔 한다.
     */
    override fun onPause() {
        super.onPause()

        if (mService != null) {
            if (!mService!!.isPlaying()) {
                mService!!.stopSelf()
            }
            unbindService(mServiceConnection)
            mService = null
        }
    }

    private fun play() {
        mService?.play()
    }

    private fun pause() {
        mService?.pause()
    }

    private fun stop() {
        mService?.stop()
    }

}