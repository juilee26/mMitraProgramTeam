package com.example.mmitraprogramteam.settingactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.example.mmitraprogramteam.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
       /* val toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        toolbar.setTitle(R.string.action_settings)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }*/

        fragmentManager.beginTransaction().replace(R.id.frame_settings_menu, MainPreferenceFragment()).commit()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        NavUtils.navigateUpFromSameTask(this)
    }

}
