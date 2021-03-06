package com.example.mmitraprogramteam.home

import android.content.Context
import android.database.Cursor
import com.example.mmitraprogramteam.utility.IBasePresenter
import java.util.ArrayList

interface IMainActivityPresentor<V> : IBasePresenter<V> {
    fun getLoginDetail(userDetails : ArrayList<String>)
    fun fetchRegistrationData()
    fun onFetchedRegistrationData(cursor: Cursor)
    fun fetchUnsentFormsCount()
    fun syncUnsentForms()
    interface OnQueryFinished {

        fun onSuccess(cursor: Cursor, id: Int)

        fun onSuccess()

        fun onFailure()
    }

    interface OnResetTaskCompleted {

        fun onResetCompleted()
    }

   /* interface OnDueOverdueCalculationTaskCompleted {

        fun onDueOverdueTaskCompleted(infoModel: ArogyasakhiInfoModel)


        fun onTaskFailed()
    }
*/
    fun checkUpdate()
    fun restoreData()
    fun logout()
    fun downloadForms()
    fun restoreRegistrations(pageNumber: Int)
    fun resetDataMemberValues()
    fun restoreVisits(pageNumber: Int)
    fun chnageLanguage(context: Context, language: String)
}