package com.example.mmitraprogramteam.otpmodule

import android.database.Cursor
import com.example.mmitraprogramteam.data.database.DatabaseContract
import com.example.mmitraprogramteam.data.model.UserDetails
import com.example.mmitraprogramteam.utility.Utility

class OTPPresenter : IOTPPresenter<IOTPView> {

    var mIOTPView : IOTPView?=null
    var util = Utility()
    var mUserDetails = UserDetails()
    internal lateinit var interactor: OTPInteractor
    var utility= Utility()
    var cur : Cursor?=null
    var arrayUserDetails : ArrayList<String>?=null


    override fun attachView(view: IOTPView) {
        this.mIOTPView = view
        this.interactor = OTPInteractor(view.getContext())
    }

    override fun detachView() {
        mIOTPView = null
    }

    override fun getUserDetails() : ArrayList<String>? {
        cur = interactor.fetchUserDetails()
        var uname = ""
        var pswd = ""

        if (cur!=null && cur!!.moveToFirst()) {
            uname = cur!!.getString(cur!!.getColumnIndex(DatabaseContract.LoginTable.COLUMN_USERNAME))
            pswd  = cur!!.getString(cur!!.getColumnIndex(DatabaseContract.LoginTable.COLUMN_PASSWORD))
        }
        arrayUserDetails?.add(uname)
        arrayUserDetails?.add(pswd)
        return arrayUserDetails
    }
}