package com.example.mmitraprogramteam.data.model

import com.google.gson.annotations.SerializedName

class RequestFormModel : UserDetails() {
    @SerializedName("hash")
    private var hash : String?=null
    fun setHash(hash : String){
        this.hash=hash
    }
}