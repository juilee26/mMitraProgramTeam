package com.example.mmitraprogramteam.data.service

import com.example.mmitraprogramteam.data.Url.Companion.DOWNLOAD_FORMS
import com.example.mmitraprogramteam.data.model.RequestFormModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FormDownloadServiceAPI {
    @Headers("Content-Type: application/json")

    @POST(DOWNLOAD_FORMS)
    fun downloadFormJson(@Body requestFormModel: RequestFormModel) : Call<ResponseBody>
}