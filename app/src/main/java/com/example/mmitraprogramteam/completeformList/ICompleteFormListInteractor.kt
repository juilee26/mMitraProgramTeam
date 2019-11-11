package com.example.mmitraprogramteam.completeformList
import android.database.Cursor

interface ICompleteFormListInteractor {
     fun getCompleteFormList(unique_mother_id : String): Cursor
     fun checkFormPresent() : Int
}