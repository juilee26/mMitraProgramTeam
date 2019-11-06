package com.example.mmitraprogramteam.data.model

import com.example.mmitraprogramteam.data.model.syncing.BeneficiaryDetails
import com.example.mmitraprogramteam.utility.Constants.BENEFICIARIES
import com.google.gson.annotations.SerializedName

class SyncRegistrationDetails : UserDetails() {
    @SerializedName(BENEFICIARIES)
    private var regData : ArrayList<BeneficiaryDetails>?=null
    fun setRegData(regData : ArrayList<BeneficiaryDetails>){
        this.regData=regData
    }

    override fun toString(): String {
        return "SyncRegistrationDetails(regData=$regData)"
    }

}