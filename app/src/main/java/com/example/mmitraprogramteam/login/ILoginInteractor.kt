package com.example.mmitraprogramteam.login

import android.content.Context
import com.example.mmitraprogramteam.data.model.UserDetails
import org.json.JSONObject

interface ILoginInteractor {

    fun saveUserDetails(username: String, password: String, jsonObject: JSONObject)

    fun login(userDetails: UserDetails, onLoginFinished:OnLoginFinished, context:Context)

    fun deleteUserDetails()

    fun userAlreadyLoggedIn(): Boolean

    interface OnLoginFinished
    {
            fun onSuccess(jsonObject: JSONObject)

            fun onFailure(message:String)
    }

}