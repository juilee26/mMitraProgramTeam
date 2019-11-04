package com.example.mmitraprogramteam.data.model.syncing

import com.example.mmitraprogramteam.data.database.DatabaseContract
import com.example.mmitraprogramteam.data.model.UserDetails
import com.google.gson.annotations.SerializedName

class UpdateImageModel : UserDetails() {
    @SerializedName(DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID)
    private var uniqueId: String? = null
    @SerializedName(DatabaseContract.RegistrationTable.COLUMN_IMAGE)
    private var image: String? = null

    fun setUniqueId(uniqueId: String) {
        this.uniqueId = uniqueId
    }

    fun setImage(image: String) {
        this.image = image
    }

    override fun toString(): String {
        return "UpdateImageModel(uniqueId=$uniqueId, image=$image)"
    }

}