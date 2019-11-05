package com.example.mmitraprogramteam.data.service

import com.example.mmitraprogramteam.data.Url.Companion.SYNC_FORM_DATA
import com.example.mmitraprogramteam.data.model.syncing.FormDetails
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SyncFormServiceApi {
    @Headers("Content-Type: application/json")
    @POST(SYNC_FORM_DATA)
     fun syncFormDetails(@Body formDetails: FormDetails): Call<ResponseBody>
}