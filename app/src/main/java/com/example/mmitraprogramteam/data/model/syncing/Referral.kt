package com.example.mmitraprogramteam.data.model.syncing

import com.example.mmitraprogramteam.data.database.DatabaseContract
import com.example.mmitraprogramteam.utility.Constants.*
import com.google.gson.annotations.SerializedName

class Referral {
    @SerializedName(UNIQUE_ID)
    private var uniqueId : String?=null
    @SerializedName(FORM_ID)
    private var formId:String?=null
    @SerializedName(QUESTION_KEYWORD)
    private var keyword:String?=null
    @SerializedName(ANSWER)
    private var answer:String?=null
    @SerializedName(TYPE)
    private var type:String?=null
/*    @SerializedName(DatabaseContract.ReferralTable.COLUMN_NAME_REFERRED_TO)
    private var referredTo:String?=null
    @SerializedName(DatabaseContract.ReferralTable.COLUMN_NAME_REFERRAL_DATE)
    private var referredDate:String?=null
    @SerializedName(DatabaseContract.ReferralTable.COLUMN_NAME_STATUS)
    private var referralStatus:String?=null*/

    override fun toString(): String {
        return "Referral{" +
                "uniqueId='" + uniqueId + '\''.toString() +
                ", formId='" + formId + '\''.toString() +
                ", keyword='" + keyword + '\''.toString() +
                ", answer='" + answer + '\''.toString() +
                ", type='" + type + '\''.toString() +
                /*", referredTo='" + referredTo + '\''.toString() +
                ", referredDate='" + referredDate + '\''.toString() +
                ", referrelStatus='" + referralStatus + '\''.toString() +*/
                '}'.toString();}


}