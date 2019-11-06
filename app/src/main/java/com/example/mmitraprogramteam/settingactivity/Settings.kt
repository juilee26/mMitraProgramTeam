package com.example.mmitraprogramteam.settingactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.home.MainActivity

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setTitle(R.string.action_settings)
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        toolbar?.setTitle(R.string.action_settings)
//        if (supportActionBar != null) {
//            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        }

        fragmentManager.beginTransaction().replace(R.id.frame_settings_menu, MainPreferenceFragment()).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
