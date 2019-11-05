package com.example.mmitraprogramteam.data.model.syncing

import com.example.mmitraprogramteam.data.model.UserDetails
import com.example.mmitraprogramteam.utility.Constants.*
import com.example.mmitraprogramteam.utility.Constants.UNIQUE_ID
import com.google.gson.annotations.SerializedName

class FormDetails : UserDetails() {
    @SerializedName(UNIQUE_ID)
    private var uniqueId:String?=null
    @SerializedName(FORM_ID)
    private var formId:String?=null
    @SerializedName(DATA)
    private var data: ArrayList<QuestionAnswer>?=null

    fun setUniqueId(uniqueId: String) {
        this.uniqueId = uniqueId
    }
    fun getUniqueId(): String? {
        return uniqueId
    }

    fun setFormId(formId: String) {
        this.formId = formId
    }
    fun getFormId(): String? {
        return formId
    }

    fun setData(data: ArrayList<QuestionAnswer>) {
        this.data = data
    }
    fun getData(): ArrayList<QuestionAnswer>? {
        return data
    }

}