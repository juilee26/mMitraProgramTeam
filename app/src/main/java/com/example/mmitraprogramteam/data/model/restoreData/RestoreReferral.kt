package com.example.mmitraprogramteam.data.model.restoreData

import com.example.mmitraprogramteam.data.model.syncing.Referral
import com.google.gson.annotations.SerializedName

class RestoreReferral {
    @SerializedName("total")
    var total:Int?=null
    @SerializedName("dataSource")
    var referralList: ArrayList<Referral>?=null

    override fun toString(): String {
        return "RestoreReferral(total=$total, referralList=$referralList)"
    }

}