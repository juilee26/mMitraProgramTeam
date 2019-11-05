package com.example.mmitraprogramteam.data.model.restoreData

import com.example.mmitraprogramteam.data.model.syncing.QuestionAnswer
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class VisitsList {
    @SerializedName("id")
    var formId: Int = 0
    @SerializedName("created_on")
    var createdOn: String? = null
    @SerializedName("data")
    var questionAnswers: ArrayList<QuestionAnswer>? = null

    override fun toString(): String {
        return "VisitsList{" +
                "formId=" + formId +
                ", createdOn='" + createdOn + '\''.toString() +
                ", questionAnswers=" + questionAnswers +
                '}'.toString()
    }
}
