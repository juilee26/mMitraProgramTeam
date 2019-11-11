package com.example.mmitraprogramteam.otpmodule

import android.content.Context

interface IOTPView  {
    fun fetchUserDetails()
    fun getContext(): Context
    fun openNextActivity()
    fun setAuthenticationFailedError()
    fun showProgressBar()

    fun hideProgressBar()
    fun validateMobileNo()



}