package com.example.mmitraprogramteam.data.model.syncing

import com.example.mmitraprogramteam.data.model.UserDetails
import com.google.gson.annotations.SerializedName

class RequestHelpModel : UserDetails() {
    @SerializedName("hash")
    private var hash: String? = null

    fun setHash(hash: String) {
        this.hash = hash
    }
}