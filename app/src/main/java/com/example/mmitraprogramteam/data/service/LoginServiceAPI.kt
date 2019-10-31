package tech.inscripts.ins_armman.mMitra.data.service

import com.example.mmitraprogramteam.data.Url.Companion.AUTHENTICATE
import com.example.mmitraprogramteam.data.model.UserDetails
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface LoginServiceAPI {
    @Headers("Content-Type: application/json")
    @POST(AUTHENTICATE)
    fun getAuthentication(@Body userDetails: UserDetails): Call<ResponseBody>
}