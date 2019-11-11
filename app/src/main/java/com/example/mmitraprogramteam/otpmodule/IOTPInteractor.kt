package com.example.mmitraprogramteam.otpmodule

import android.database.Cursor

interface IOTPInteractor {

    fun fetchUserDetails() :Cursor

}