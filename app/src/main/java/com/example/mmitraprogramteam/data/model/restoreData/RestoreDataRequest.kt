package com.example.mmitraprogramteam.data.model.restoreData

import com.example.mmitraprogramteam.data.model.UserDetails
import com.google.gson.annotations.SerializedName


class RestoreDataRequest : UserDetails() {
    @SerializedName("limit")
    private var limit: Int = 0

    @SerializedName("pg")
    private var pageNumber: Int = 0

    fun setLimit(limit: Int) {
        this.limit = limit
    }

    fun setPageNumber(pageNumber: Int) {
        this.pageNumber = pageNumber
    }
}
