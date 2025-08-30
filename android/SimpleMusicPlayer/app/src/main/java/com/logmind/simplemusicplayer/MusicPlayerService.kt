package com.logmind.simplemusicplayer

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.Toast

/**
 * 백그라운드 서비스: 사용자가 보이지 않는 곳에서 조용히 작업을 수행함.
 * 포그라운드 서비스: 사용자가 서비스가 실행되고 있음을 능동적으로 인지할 수 있는 서비스. (예를 들어 음악 재생시 상단 상태 표시줄에 알림 표시)
 * API 레벨 26(안드로이드 O) 부터는 포그라운드 서비스를 통해 상태 표시줄에서 서비스가 실행되고 있음을 사용자에게 알리고
 * 다른 서비스보다 높은 우선순위를 가지게끔 함.
 * [startForegroundService] 함수는 [startService] 와는 다르게 [startForegroundService] 를 호출하고 나서
 * 서비스 생성 이후 5초 이내에 [startForeground] 함수를 통해 알림을 보여주어야 함.
 *
 * @param id 식별자
 * @param notification 사용자에게 보여질 알림
 * startForeground(id, notification)
 */
class MusicPlayerService : Service() {

    var mMediaPlayer: MediaPlayer? = null

    var mBinder: MusicPlayerBinder = MusicPlayerBinder()

    /**
     * [onBind] 에서는 [bindService] 를 호출한 구성요소에 [IBinder] 인터페이스를 구현한 객체를 반환값으로 넘겨주어야 한다.
     * 서비스 내부에 내부 클래스 [MusicPlayerBinder] 를 생성하여 [Binder]를 상속한다.
     * [Binder] 클래스는 [IBinder] 인터페이스를 구현해주므로 [onBind] 에서 [MusicPlayerBinder] 클래스 객체를 반환해 주어도 된다.
     * 이 클래스는 [getService] 함수를 가지고 있으며 반환값으로 현재 서비스를 반환한다.
     * 서비스와 연결하려는 액티비티와 같은 구성요소에 현재 서비스를 반환함으로써, 연결된 구성요소가 서비스의 함수를 사용할 수 있게 된다.
     */
    inner class MusicPlayerBinder : Binder() {
        fun getService(): MusicPlayerService {
            return this@MusicPlayerService
        }
    }

    /**
     * 서비스가 생성될 때 딱 한 번만 실행
     */
    override fun onCreate() {
        super.onCreate()
        startForegroundService() // 포그라운드 서비스 시작
    }

    /**
     * [onBind]: 액티비티와 같은 구성요소에서 [bindService] 함수를 호출할 때 실행되는 함수.
     * 여기서 서비스와 구성요소를 이어주는 매개체 역할을 하는 [IBinder] 를 반환함.
     * 바인드가 필요 없는 서비스 (예를 들어 시작된 서비스) 라면 null 을 반환하면 됨
     * 여기선 서비스가 시작되고 바인드된 서비스이기에 꼭 구현해주어야 함.
     */
    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    /**
     * [onStartCommand]: [startService] 나 [startForegroundService] 를 호출할 때 실행되는 함수임.
     * 이 함수가 실행되면 서비스는 시작된 상태가 되며, 백그라운드에서 쭉 존재할 수 있게 됨
     *
     * [START_STICKY]: 시스템이 서비스를 중단하면 서비스를 다시 실행하고 [onStartCommand] 함수를 호출한다.
     *
     * [START_NOT_STICKY]: 시스템이 서비스를 중단시키면 서비스를 재생성 하지 않음.
     *
     * [START_REDELIVER_INTENT]: 시스템이 서비스를 중단하면 서비스를 다시 실행하고 [onStartCommand] 함수를 호출한다.
     * 또한 서비스가 종료되기 전 마지막으로 전달된 인텐트를 재전달한다. 반드시 명령을 실행해야 하는 경우 쓰임.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    /**
     * [startForegroundService]: 알림 채널을 만들고 [startForeground] 함수를 실행함.
     * 안드로이드 O (API Level 26) 버전부터는 반드시 [startService] 가 아닌 [startForegroundService] 를 실행하여
     * 사용자로 하여금 서비스가 실행되고 있다는 사실을 알림과 함께 알려야 함
     */
    fun startForegroundService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val mChannel = NotificationChannel(
                "CHANNEL_ID",
                "CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(mChannel)
        }

        val notification: Notification = Notification.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_play)
            .setContentTitle("뮤직 플레이어 앱")
            .setContentText("앱이 실행 중입니다.")
            .build()

        startForeground(1, notification)
    }

    /**
     * [onDestroy]: 서비스 생명 주기의 마지막 단계임.
     * 여기서 [onCreate] 에서 상태 표시줄에 보여주었던 알림을 해제함.
     */
    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true)
        }
    }


    fun isPlaying(): Boolean {
        return mMediaPlayer != null && mMediaPlayer?.isPlaying ?: false
    }

    fun play() {
        /**
         * 음악 파일의 리소스를 가져와 미디어 플레이어 객체를 할당해줌.
         */
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.chocolate)

            mMediaPlayer?.setVolume(1.0f, 1.0f) // 볼륨 지정
            mMediaPlayer?.isLooping = true // 반복 재생 여부
            mMediaPlayer?.start() // 음악 재생
        } else { // 음악 재생 중인 경우
            if (mMediaPlayer!!.isPlaying) {
                Toast.makeText(this, "이미 음악이 실행 중입니다.", Toast.LENGTH_SHORT).show()
            } else {
                mMediaPlayer?.start()
            }
        }
    }

    fun pause() {
        mMediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
            }
        }
    }

    fun stop() {
        mMediaPlayer?.let {
            if (it.isPlaying) {
                it.stop() // 음악을 멈춤
                it.release() // 미디어 플레이어에 할당된 자원을 해제시켜줌.
                mMediaPlayer = null
            }
        }
    }

}

