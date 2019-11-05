package com.example.mmitraprogramteam.settingactivity

import android.content.Context
import com.example.mmitraprogramteam.data.model.RequestFormModel
import com.example.mmitraprogramteam.data.model.UpdateModel
import com.example.mmitraprogramteam.data.model.restoreData.BeneficiariesList
import com.example.mmitraprogramteam.data.model.restoreData.RestoreDataRequest
import com.example.mmitraprogramteam.data.model.restoreData.RestoreRegistration
import com.example.mmitraprogramteam.data.model.restoreData.RestoreVisits
import com.example.mmitraprogramteam.data.model.syncing.BeneficiaryDetails
import org.json.JSONObject
import java.util.ArrayList

interface ISettingsInteractor {
     fun changeLocale(context: Context, language: String)
     fun downloadForms(requestFormModel: RequestFormModel, onFormDownloadFinished: OnFormDownloadFinished)
     fun fetchLoginDetails(id: Int)
     fun fetchFormJsonHash(id: Int)
     fun saveFormData(formJsonObject: JSONObject)
     //fun downloadHelpManual(helpModel: RequestHelpModel, downloadFinished: onHelpManualDownloadFinished)
     //fun saveHelpManualData(formJsonObject: JSONObject)
     fun checkReleaseUpdate(onCheckUpdateFinished: onCheckUpdateFinished)
     fun downloadAndSaveApk(apkLink: String)
     fun getHash(type: String): String
     fun downloadRegistrationData(request: RestoreDataRequest, downloadFinished: OnRegistrationsDownloadFinished)

     fun downloadVisitsData(request: RestoreDataRequest, downloadFinished: OnVisitsDownloadFinished)

    /*
    fun downloadReferralData(request: RestoreDataRequest, downloadFinished: OnReferralDownloadFinished)
    fun downloadGrowthMonitoringData(request: RestoreDataRequest, downloadFinished: OnGrowthMonitoringFinished)
    */

    fun saveDownloadedData(listRegistrations: ArrayList<BeneficiaryDetails>, listVisits: ArrayList<BeneficiariesList>)
    fun addOrUpdateFormHash(item: String, hash: String)
    fun deleteLoginDetails()

    interface OnFormDownloadFinished {
        fun onSuccessFormDownloading(jsonObject: JSONObject, hash: String)
        fun onFailure(message: String)
    }

    interface onCheckUpdateFinished {
        fun onUpdateCheckSuccess(updateModel: UpdateModel)
        fun onFailure(message: String)
    }

    /*interface onHelpManualDownloadFinished {
        fun onSuccessDownloadedHelpManual(jsonObject: JSONObject, hash: String)
        fun onFailure(message: String)
    }*/

    interface OnRegistrationsDownloadFinished {
        fun onSuccessRegistrationsDownloading(registration: RestoreRegistration)
        fun onFailure(message: String)
    }

    interface OnVisitsDownloadFinished {
        fun onSuccessVisitsDownloading(visits: RestoreVisits)
        fun onFailure(message: String)
    }


   /* interface OnGrowthMonitoringFinished {
        fun onSuccessGrowthMonitoringDownloading(growthMonitoring: RestoreGrowthMonitoring)
        fun onFailure(message: String)
    }*/
}