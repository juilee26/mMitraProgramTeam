package com.example.mmitraprogramteam.data.model.restoreData

import com.example.mmitraprogramteam.data.model.syncing.BeneficiaryDetails
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class RestoreRegistration {
    @SerializedName("total")
    private val total: Int = 0
    @SerializedName("data")
    private var registrationData: ArrayList<BeneficiaryDetails>? = null

    fun getTotal(): Int {
        return total
    }

    fun getRegistrationData(): ArrayList<BeneficiaryDetails>? {
        return registrationData
    }
}