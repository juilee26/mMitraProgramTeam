package com.example.mmitraprogramteam.data.service

import android.content.Context
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.data.model.restoreData.RestoreDataRequest
import com.example.mmitraprogramteam.data.model.restoreData.RestoreVisits
import com.example.mmitraprogramteam.settingactivity.ISettingsInteractor
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class RestoreVisitsService {
    var mServiceAPI : RestoreVisitsServiceAPI?=null

    constructor(mServiceAPI: RestoreVisitsServiceAPI?) {
        this.mServiceAPI = mServiceAPI
    }

    fun downloadVisitsData(context: Context, request: RestoreDataRequest, downloadFinished: ISettingsInteractor.OnVisitsDownloadFinished) {
      val call = mServiceAPI?.restoreRegistrationData(request)
        call!!.enqueue(object : retrofit2.Callback<RestoreVisits>{
            override fun onFailure(call: Call<RestoreVisits>?, t: Throwable?) {
              downloadFinished.onFailure(context.getString(R.string.oops_some_thing_happened_wrong))
            }

            override fun onResponse(call: Call<RestoreVisits>?, response: Response<RestoreVisits>?) {
                if (response?.code() == 200) {
                 //   downloadFinished.onSuccessVisitsDownloading(response.body())
                } else {
                    try {
                        response?.errorBody()?.string()?.let { downloadFinished.onFailure(it) }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }

        })
    }
}