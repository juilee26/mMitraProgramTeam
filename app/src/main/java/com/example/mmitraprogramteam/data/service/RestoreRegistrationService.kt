package com.example.mmitraprogramteam.data.service

import android.content.Context
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.data.model.restoreData.RestoreDataRequest
import com.example.mmitraprogramteam.data.model.restoreData.RestoreRegistration
import com.example.mmitraprogramteam.settingactivity.ISettingsInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RestoreRegistrationService {
    private var mServiceAPI: RestoreRegistrationServiceApi?=null

    constructor(mServiceAPI: RestoreRegistrationServiceApi?) {
        this.mServiceAPI = mServiceAPI
    }


    fun downloadRegistrationData(context: Context, request: RestoreDataRequest, downloadFinished: ISettingsInteractor.OnRegistrationsDownloadFinished) {
        val call = mServiceAPI!!.restoreRegistrationData(request)
        call.enqueue(object : Callback<RestoreRegistration> {
            override fun onResponse(call: Call<RestoreRegistration>, response: Response<RestoreRegistration>) {

                if (response.code() == 200) {
                 //   downloadFinished.onSuccessRegistrationsDownloading(response.body())
                } else {
                    try {
                        downloadFinished.onFailure(response.errorBody()!!.string())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<RestoreRegistration>, t: Throwable) {
                downloadFinished.onFailure(context.getString(R.string.oops_some_thing_happened_wrong))
            }
        })
    }
}