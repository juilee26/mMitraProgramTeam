package com.example.mmitraprogramteam.completeformList
import android.content.Context
import com.example.mmitraprogramteam.data.model.CompleteFormQnA
import java.util.ArrayList

interface ICompleteFormListView {
     fun getContext(): Context
     fun getData(formDetails: ArrayList<CompleteFormQnA>)
     fun checkFormPresent()
}