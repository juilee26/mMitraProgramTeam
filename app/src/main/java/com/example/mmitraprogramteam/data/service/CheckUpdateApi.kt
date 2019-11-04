package com.example.mmitraprogramteam.data.service

import com.example.mmitraprogramteam.data.model.UpdateModel
import retrofit2.Call
import retrofit2.http.GET

interface CheckUpdateApi {
   // @GET(RELEASE)
   fun getUpdateData(): Call<UpdateModel>
}

