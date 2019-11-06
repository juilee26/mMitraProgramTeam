package com.example.mmitraprogramteam.data.service

import com.example.mmitraprogramteam.data.Url.Companion.GET_VISITS
import com.example.mmitraprogramteam.data.model.restoreData.RestoreDataRequest
import com.example.mmitraprogramteam.data.model.restoreData.RestoreVisits
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestoreVisitsServiceAPI {
    @Headers("Content-Type: application/json")
    @POST(GET_VISITS)
     fun restoreRegistrationData(@Body request: RestoreDataRequest): Call<RestoreVisits>
}