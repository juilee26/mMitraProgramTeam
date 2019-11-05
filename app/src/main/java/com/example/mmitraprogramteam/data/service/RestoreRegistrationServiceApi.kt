package com.example.mmitraprogramteam.data.service

import com.example.mmitraprogramteam.data.Url.Companion.GET_REGISTRATIONS
import com.example.mmitraprogramteam.data.model.restoreData.RestoreDataRequest
import com.example.mmitraprogramteam.data.model.restoreData.RestoreRegistration
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestoreRegistrationServiceApi {
    @Headers("Content-Type: application/json")
    @POST(GET_REGISTRATIONS)
     fun restoreRegistrationData(@Body request: RestoreDataRequest): Call<RestoreRegistration>
}