package com.example.mmitraprogramteam.completeforms

import android.content.Context
import com.example.mmitraprogramteam.data.model.completeFilledForm

interface ICompleteFormView {
    fun setAdapter(mWomenList : List<completeFilledForm>)
    fun getContext() : Context
}