package com.example.mmitraprogramteam.userprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mmitraprogramteam.R
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity(){
  var presenter = UserProfilePresenter()
   var username : String =""
   var name : String =""
    var userDetails = ArrayList<String>()
    var regCount : Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        presenter.getUserDetails(userDetails)
       regCount=presenter.getRegCount()
        textUserName?.text=userDetails.get(1)
        textName.text=userDetails.get(0)

        if(regCount>0)
            textCount.text= regCount.toString()
    else
            textCount.setText(R.string.no_reg)
    }
}