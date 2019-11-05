package com.example.mmitraprogramteam.data.service

import android.content.Context
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.data.model.UpdateModel
import com.example.mmitraprogramteam.data.service.CheckUpdateApi
import com.example.mmitraprogramteam.settingactivity.ISettingsInteractor
import retrofit2.Call
import retrofit2.Response

class CheckUpdateService {
   var mCheckUpdateApi : CheckUpdateApi?=null

    constructor(mCheckUpdateApi: CheckUpdateApi?) {
        this.mCheckUpdateApi = mCheckUpdateApi
    }

    fun getUpdateData(onCheckUpdateFinished: ISettingsInteractor.onCheckUpdateFinished, context: Context){

        val call : Call<UpdateModel> = mCheckUpdateApi!!.getUpdateData()
        call.enqueue(object: retrofit2.Callback<UpdateModel>{
            override fun onFailure(call: Call<UpdateModel>?, t: Throwable?) {
                onCheckUpdateFinished.onFailure(context.getString(R.string.oops_some_thing_happened_wrong))
            }

            override fun onResponse(call: Call<UpdateModel>?, response: Response<UpdateModel>?) {
                if(response!!.code() == 200){
                 //   val mobileData : UpdateModel= response.body()
            //        onCheckUpdateFinished.onUpdateCheckSuccess(mobileData)
                }
                else {
                    onCheckUpdateFinished.onFailure(context.getString(R.string.invalid_data_frm_server))
                }
            }
        })
    }
}