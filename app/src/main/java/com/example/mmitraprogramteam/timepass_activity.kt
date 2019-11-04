package com.example.mmitraprogramteam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.example.mmitraprogramteam.settingactivity.Settings

class timepass_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timepass_activity)
    }

    fun fetch(view: View)
    {
       startActivity(Intent(this@timepass_activity,Settings::class.java));
    }


    fun display(view: View)
    {
        System.out.println("Display")
        val myToast = Toast.makeText(applicationContext,"Display",Toast.LENGTH_SHORT)
        myToast.setGravity(Gravity.LEFT,200,200)
        myToast.show()
    }
}
