package com.example.mmitraprogramteam.completeformList
import android.content.Context
import android.database.Cursor
import com.example.mmitraprogramteam.data.database.DBHelper

class CompleteFormListInteractor : ICompleteFormListInteractor {

      var mContext: Context?=null
      var dbHelper: DBHelper

    constructor(mContext: Context?) {
        this.mContext = mContext
        this.dbHelper = DBHelper(mContext)
    }


    override fun getCompleteFormList(unique_mother_id : String): Cursor {
    return dbHelper.getFormsList(unique_mother_id)
    }
    override fun checkFormPresent(): Int {
        return dbHelper.checkFormsPresent()
    }
}