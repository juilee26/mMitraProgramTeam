package com.example.mmitraprogramteam

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        //declare animation
        val anim = AnimationUtils.loadAnimation(this, R.anim.splash_screen_efffect)
        val imageView : ImageView = findViewById(R.id.logoImage)

        imageView.startAnimation(anim)
        Timer().schedule(1000)
        {
            startActivity(Intent(this@SplashScreen, Login::class.java))
        }
    }
}