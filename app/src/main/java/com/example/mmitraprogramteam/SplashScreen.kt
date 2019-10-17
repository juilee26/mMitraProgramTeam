package com.example.mmitraprogramteam

import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import bsh.org.objectweb.asm.Constants
import com.daimajia.androidanimations.library.Techniques
import com.google.android.material.animation.AnimationUtils
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash

class SplashScreen : AwesomeSplash() {
   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

    }*/
    override fun initSplash(configSplash: ConfigSplash?) {
        var actionBar : ActionBar? = supportActionBar
        actionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        configSplash?.backgroundColor = R.color.splash_screen
        configSplash?.animCircularRevealDuration=1000
        configSplash?.revealFlagX=Flags.REVEAL_RIGHT
        configSplash?.revealFlagX=Flags.REVEAL_TOP
        configSplash?.logoSplash=R.drawable.splash_logo
        configSplash?.animLogoSplashDuration=500
        configSplash?.animLogoSplashTechnique=Techniques.FadeIn
        configSplash?.titleSplash= "mMitra"
        configSplash?.titleTextColor=R.color.colorPrimary
        configSplash?.titleTextSize =50f
        configSplash?.animTitleDuration=500
        configSplash?.animTitleTechnique = Techniques.FadeIn


    }
    override fun animationsFinished() {
        var intent : Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}