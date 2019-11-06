package com.example.mmitraprogramteam.data.model.restoreData

import com.example.mmitraprogramteam.data.model.restoreData.BeneficiariesList
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class RestoreVisits {
    @SerializedName("total")
    private var total: Int = 0
    @SerializedName("data")
    private var beneficiariesLists: ArrayList<BeneficiariesList>? = null

    fun getTotal(): Int {
        return total
    }

    fun setTotal(total: Int) {
        this.total = total
    }

    fun getBeneficiariesLists(): ArrayList<BeneficiariesList>? {
        return beneficiariesLists
    }

    fun setBeneficiariesLists(beneficiariesLists: ArrayList<BeneficiariesList>) {
        this.beneficiariesLists = beneficiariesLists
    }

    override fun toString(): String {
        return "RestoreVisits{" +
                "total=" + total +
                ", beneficiariesLists=" + beneficiariesLists +
                '}'.toString()
    }
}