package com.example.mmitraprogramteam.otpmodule

import com.example.mmitraprogramteam.utility.IBasePresenter

interface IOTPPresenter<V> : IBasePresenter<V> {
    fun getUserDetails() : ArrayList<String>?
}