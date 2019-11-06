package com.example.mmitraprogramteam.data.service

import com.example.mmitraprogramteam.data.Url.Companion.SYNC_UPDATE_PHOTO_DATA
import com.example.mmitraprogramteam.data.model.syncing.UpdateImageModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers


import retrofit2.http.POST

interface SyncUpdatePhotoServiceApi {
    @Headers("Content-Type: application/json")
    @POST(SYNC_UPDATE_PHOTO_DATA)
    fun SyncUpdatePhotoDetails(@Body updateImageModel: UpdateImageModel): Call<ResponseBody>
}