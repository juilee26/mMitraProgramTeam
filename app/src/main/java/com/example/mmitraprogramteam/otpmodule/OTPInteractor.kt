package com.example.mmitraprogramteam.otpmodule

import android.content.Context
import android.database.Cursor
import com.example.mmitraprogramteam.data.database.DBHelper


class OTPInteractor : IOTPInteractor {

    var mContext: Context?=null
    var dbHelper: DBHelper

    constructor(mContext: Context?) {
        this.mContext = mContext
        this.dbHelper = DBHelper(mContext)
    }

    override fun fetchUserDetails() : Cursor {

        return  dbHelper.fetchUserDetails()
    }
}