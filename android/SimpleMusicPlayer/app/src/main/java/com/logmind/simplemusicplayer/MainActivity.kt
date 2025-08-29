package com.logmind.simplemusicplayer

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            TODO("Not yet implemented")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
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
        playMusic()
    }

    fun playMusic() {
        val mPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.chocolate)
        mPlayer?.start()
    }

    fun getUriMusic() {
        val myUri: Uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.chocolate)
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setDataSource(applicationContext, myUri)
            prepare()
            start()
        }
    }
}