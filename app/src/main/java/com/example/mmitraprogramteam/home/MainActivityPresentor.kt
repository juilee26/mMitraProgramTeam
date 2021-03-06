package com.example.mmitraprogramteam.home

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.database.Cursor
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.data.database.DatabaseContract
import com.example.mmitraprogramteam.data.model.RequestFormModel
import com.example.mmitraprogramteam.data.model.SyncRegistrationDetails
import com.example.mmitraprogramteam.data.model.UpdateModel
import com.example.mmitraprogramteam.data.model.restoreData.BeneficiariesList
import com.example.mmitraprogramteam.data.model.restoreData.RestoreDataRequest
import com.example.mmitraprogramteam.data.model.restoreData.RestoreRegistration
import com.example.mmitraprogramteam.data.model.restoreData.RestoreVisits
import com.example.mmitraprogramteam.data.model.syncing.BeneficiaryDetails
import com.example.mmitraprogramteam.data.model.syncing.FormDetails
import com.example.mmitraprogramteam.data.model.syncing.QuestionAnswer
import com.example.mmitraprogramteam.login.Login
import com.example.mmitraprogramteam.settingactivity.ISettingsInteractor
import com.example.mmitraprogramteam.settingactivity.SettingsPresentor
import com.example.mmitraprogramteam.utility.Constants.*
import com.example.mmitraprogramteam.utility.Utility
import org.json.JSONException
import org.json.JSONObject


class MainActivityPresentor : IMainActivityPresentor<IMainActivity>,IMainActivityInteractor.OnDataSync,
    IMainActivityInteractor.OnFormSync, ISettingsInteractor.OnFormDownloadFinished,ISettingsInteractor.onCheckUpdateFinished,
    ISettingsInteractor.OnRegistrationsDownloadFinished,ISettingsInteractor.OnVisitsDownloadFinished {


    private val FETCH_USER_DATA = 101
    private val FETCH_REGISTRATION_DATA = 102
    private val FETCH_FORMS_DATA = 103
    private val FETCH_UNSENT_FORM_COUNT = 104

var mRequest: RestoreDataRequest?=null
    var pageCounter =0
   var settingsPresenter : SettingsPresentor?=null
    private var mIMainActivityView: IMainActivity? = null
     var mInteractor: MainActivityInteractor? = null
    private var mContext: Context? = null
    private var mEmail: String = ""
    private var mPassword:String = ""
    private var mUserId:String = ""
    private var mImei: String =""
    private val listRegistrations = ArrayList<BeneficiaryDetails>()
    private var totalPagesCalculated: Boolean = false
    private val listVisits = ArrayList<BeneficiariesList>()
    private var totalPages: Int = 0


    var utility= Utility()
    private val mOnQueryFinished = object : IMainActivityPresentor.OnQueryFinished {
        override fun onSuccess(cursor: Cursor, id: Int) {
            when (id) {
                FETCH_USER_DATA -> if (cursor.moveToFirst()) {
                    mEmail = cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_USERNAME))
                    mPassword = cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_PASSWORD))
                    mUserId=cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_USER_ID))
                    mImei =utility.getDeviceImeiNumber(mContext!!)
                   /* mUserId="1"
                    mEmail="test_user1"
                    mPassword="test_user1"
                    mImei="869432026925037"*/
                    //val arogyasakhiName = cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_NAME))
                  //  mIHomeActivityView?.setArogyasakhiName(arogyasakhiName)
                }

            FETCH_UNSENT_FORM_COUNT -> if (cursor.moveToFirst())
                    mIMainActivityView?.setUnsentFormsCount(cursor.getInt(0))

                FETCH_REGISTRATION_DATA -> if (cursor.count > 0) {
                    onFetchedRegistrationData(cursor)
                } else
                    syncUnsentForms()
            }
        }

        override fun onSuccess() {

        }

        override fun onFailure() {

        }
    }


    override fun fetchRegistrationData() {
    mIMainActivityView?.showProgressBar(mContext?.getString(R.string.uploading_forms)!!)

        ResetFailureTask(object : IMainActivityPresentor.OnResetTaskCompleted {
            override fun onResetCompleted() {
                mInteractor?.fetchRegistrationDetails(FETCH_REGISTRATION_DATA)
            }
        }, mInteractor)
            .execute()
    }

    override fun onFetchedRegistrationData(cursor: Cursor) {
        var regDetails = SyncRegistrationDetails()
        regDetails.setusername(mEmail)
        regDetails.setpassword(mPassword)
       regDetails.setImei(mImei)

        val regData = ArrayList<BeneficiaryDetails>()
        while (cursor.moveToNext()) {
            val details = BeneficiaryDetails()

            val uniqueId = cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID))
            details.setUniqueId(uniqueId)
            details.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_NAME)))
            details.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_ADDRESS)))
            details.setMobNo(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_MOBILE_NO)))
            details.setLmp(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_LMP_DATE)))
            details.setDob(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_AGE)))
            details.setEducation(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_EDUCATION)))
            details.setCreatedOn(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_CREATED_ON)))
            regData.add(details)
        }
        if (regData.size == 0)
            return

        regDetails.setRegData(regData)

        mInteractor?.sendRegistrationBasicDetails(regDetails, this)
    }

    override fun fetchUnsentFormsCount() {
    mInteractor?.fetchUnsentFormsCount(FETCH_UNSENT_FORM_COUNT)
    }

    override fun syncUnsentForms() {
        var cursor = mInteractor?.checkUnsentForms()
        if (cursor!!.moveToFirst()) {

            var details = FormDetails()
            var answerList = ArrayList<QuestionAnswer>()

            details.setusername(mEmail)
            details.setpassword(mPassword)
            details.setImei(mImei)
            var uniqueId = cursor?.getString(cursor.getColumnIndex(DatabaseContract.FilledFormStatusTable.COLUMN_UNIQUE_ID))
            details.setUniqueId(uniqueId)
            var formId = cursor.getString(cursor.getColumnIndex(DatabaseContract.FilledFormStatusTable.COLUMN_FORM_ID))
            details.setFormId(formId)

            var cursorForm = mInteractor?.fetchFormData(cursor.getString(cursor.getColumnIndex(DatabaseContract.FilledFormStatusTable.COLUMN_ID)))
            while (cursorForm?.moveToNext()!!) {
                var answer = QuestionAnswer()
                answer.setKeyword(cursorForm.getString(cursorForm.getColumnIndex(DatabaseContract.QuestionAnswerTable.COLUMN_QUESTION_KEYWORD)))
                answer.setAnswer(cursorForm.getString(cursorForm.getColumnIndex(DatabaseContract.QuestionAnswerTable.COLUMN_ANSWER_KEYWORD)))
                answer.setCreatedOn(cursorForm.getString(cursorForm.getColumnIndex(DatabaseContract.QuestionAnswerTable.COLUMN_CREATED_ON)))
                answerList.add(answer)
            }

            details.setData(answerList)

            mInteractor?.sendForms(details, this)
        }
        else
        {
            mIMainActivityView?.hideProgressBar()
            fetchUnsentFormsCount()
        }
    }
    private fun markImproperVisitToSync(uniqueId: String, formId: String, errorMsg: String) {
        mInteractor?.updateFormFailureStatus(uniqueId, formId, errorMsg)
        syncUnsentForms()
    }

    private fun markImproperRegistrationToSync(uniqueId: String, errorMsg: String) {
        mInteractor?.updateRegistrationFailureStatus(uniqueId, errorMsg)
        mInteractor?.fetchRegistrationDetails(FETCH_REGISTRATION_DATA)
    }
    override fun attachView(view: IMainActivity) {
        mIMainActivityView = view
        mContext = mIMainActivityView?.getContext()
        mInteractor = MainActivityInteractor(mContext!!, mOnQueryFinished)
        mInteractor?.fetchLoginDetails(FETCH_USER_DATA)
    }


    override fun detachView() {
    }

    override fun onSuccessFullySyncRegData(jsonObjectResponse: JSONObject) {
        if (!jsonObjectResponse.optBoolean(STATUS) && (jsonObjectResponse.optString(RESPONSE) == AUTHENTICATION_FAILED ||
                    jsonObjectResponse.optString(RESPONSE) == INVALID_IMEI)
        )
            onFailure(mContext?.getString(R.string.authentication_error_msg)!!)
        else {
            try {
                val jsonArrayResponse = jsonObjectResponse.getJSONArray(DATA)
                mInteractor?.updateRegistrationSyncStatus(jsonArrayResponse)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            mInteractor?.fetchRegistrationDetails(FETCH_REGISTRATION_DATA)
        }
    }

    override fun onSuccessfullySyncForm(jsonObject: JSONObject) {
        if (jsonObject.optBoolean(STATUS)) {
            mInteractor?.updateFormSyncStatus(jsonObject.optString(UNIQUE_ID), jsonObject.optString(FORM_ID))
            syncUnsentForms()
        } else {
            if (jsonObject.optString(RESPONSE) == AUTHENTICATION_FAILED || jsonObject.optString(RESPONSE) == INVALID_IMEI)
                onFailure(mContext?.getString(R.string.authentication_error_msg)!!)
            else {
                mInteractor?.updateFormFailureStatus(
                    jsonObject.optString(UNIQUE_ID),
                    jsonObject.optString(FORM_ID),
                    jsonObject.optString(RESPONSE, INVALID_DATA)
                )
                syncUnsentForms()
            }

        }
    }

    override fun onFailure(message: String) {
        mIMainActivityView?.hideProgressBar()
        mIMainActivityView?.showSnackBar(message)
        fetchUnsentFormsCount()
    }

    override fun getLoginDetail(userDetails : ArrayList<String>) {
        var cur : Cursor= mInteractor?.getLoginDetails()!!
        if (cur != null && cur.moveToFirst())
            do {
                var fullName = cur.getString(cur.getColumnIndex("name"))
                var username = cur.getString(cur.getColumnIndex("username"))
                userDetails.add(fullName)
                userDetails.add(username)
            } while (cur.moveToNext())
    }

    override fun resetDataMemberValues(){
        mRequest = RestoreDataRequest()
        mRequest?.email= mEmail!!
        mRequest?.password= mPassword!!
        mRequest?.setImei(utility.getDeviceImeiNumber(mIMainActivityView!!.getContext()))
        //mRequest.setImei("869432026925037")
        mRequest?.setLimit(FORM_DOWNLOAD_LIMIT)

        pageCounter=1
        totalPages=0
        totalPagesCalculated=false
        listRegistrations.clear()
        listVisits.clear()

    }
    override fun restoreRegistrations(pageNumber: Int) {
        mRequest?.setPageNumber(pageNumber)
        mInteractor?.downloadRegistrationData(mRequest!!, this)
    }

    override fun checkUpdate() {
        var a= mIMainActivityView?.getContext()
        if(utility.hasInternetConnectivity(a)){
            mIMainActivityView?.showProgressBar(a!!.getString(R.string.looking_for_update))
            mInteractor?.checkReleaseUpdate(this)
        }
        else mIMainActivityView?.showSnackBar(a!!.getString(R.string.no_internet_connection))
    }





    override fun downloadForms() {
        var a= mIMainActivityView?.getContext()
        val b= utility.hasInternetConnectivity(a)
        if(b){
            mIMainActivityView?.showProgressBar(a?.getString(R.string.downloading_data)!!)
            var details = RequestFormModel()
            details.setusername("deepalishelke")
            details.setpassword("deepalishelke")
            details.setImei("865596031245799")
            details.setShowdata("true")
            /*details.setpassword(mPassword)
            details.setusername(mEmail)
            details.setImei(utility.getDeviceImeiNumber(a!!))
            details.setHash(mInteractor!!.getHash(HASH_ITEM_FORM))
            */
             mInteractor?.downloadForms(details, this)
        }else{
            mIMainActivityView?.showSnackBar(a?.getString(R.string.no_internet_connection)!!)
        }
    }
    override fun onSuccessFormDownloading(jsonObject: JSONObject, hash: String) {
        mIMainActivityView?.hideProgressBar()

        mInteractor?.saveFormData(jsonObject)

        mIMainActivityView?.hideProgressBar()
        mIMainActivityView?.showSnackBar(mIMainActivityView?.getContext()?.getString(R.string.Toast_msg_for_formsavesuccessfully)!!)
    }

    override fun logout() {
        mInteractor?.deleteLoginDetails()
        val intent = Intent(mIMainActivityView?.getContext(), Login::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        mIMainActivityView?.getContext()?.startActivity(intent)
    }
    override fun restoreData() {
        var a= mIMainActivityView?.getContext()
        if (utility.hasInternetConnectivity(a)) {
            mIMainActivityView?.showProgressBar(a!!.getString(R.string.downloading_data))
            resetDataMemberValues()
            restoreRegistrations(pageCounter)
        } else
            mIMainActivityView?.showSnackBar(a!!.getString(R.string.no_internet_connection))
    }
    override fun onSuccessRegistrationsDownloading(registration: RestoreRegistration) {
        if(registration.getTotal()>0){
           // listRegistrations.addAll(registration.getRegistrationData()!!)
            var a=registration.getRegistrationData()
            if (a != null) {
                listRegistrations.addAll(a)
            }
            if(!totalPagesCalculated){
                totalPagesCalculated= true
                //totalPages = Math.ceil(registration.getTotal() as Double / FORM_DOWNLOAD_LIMIT as Double).toInt()
                totalPages = Math.ceil((registration.getTotal()).toDouble() / (FORM_DOWNLOAD_LIMIT).toDouble()).toInt()
            }
        }
        if(pageCounter<totalPages){
            restoreRegistrations(++pageCounter)
        }else{
            pageCounter=1
            totalPages=0
            totalPagesCalculated=false
            restoreVisits(pageCounter)
        }
    }

    override fun restoreVisits(pageNumber: Int) {
        mRequest?.setPageNumber(pageNumber)
        mInteractor?.downloadVisitsData(mRequest!!,this)
    }

    override fun onSuccessVisitsDownloading(visits: RestoreVisits) {
        if(visits.getTotal()>0){
            //visits.getBeneficiariesLists()?.let { listVisits.addAll(it) }
            var a=visits.getBeneficiariesLists()
            if (a != null) {
                listVisits.addAll(a)
            }
            if(!totalPagesCalculated){
                totalPagesCalculated =true
                totalPages=Math.ceil((visits.getTotal()).toDouble() / (FORM_DOWNLOAD_LIMIT).toDouble() ).toInt()
            }
        }
        if (pageCounter < totalPages)  {
            restoreVisits(++pageCounter)
        }
        else {
            mIMainActivityView?.hideProgressBar()
            mInteractor?.saveDownloadedData(listRegistrations, listVisits)
            mIMainActivityView?.showSnackBar(mIMainActivityView?.getContext()!!.getString(R.string.msg_restore_successful))
        }
    }

    override fun onUpdateCheckSuccess(updateModel: UpdateModel) {
        mIMainActivityView?.hideProgressBar()
        if(!updateModel.getStatus()){
            mIMainActivityView?.showSnackBar(mIMainActivityView!!.getContext().getString(R.string.dialog_app_updated_text))
            return
        }
        val context: Context= mIMainActivityView?.getContext()!!
        try{
            val pInfo : PackageInfo = context.packageManager.getPackageInfo(context.packageName,0)
            var versionCode = pInfo.versionCode
            var value = updateModel.getData()!!.versionCode
            val newVersionCode = (value).toInt()

            if(newVersionCode>versionCode){
                mIMainActivityView!!.updateAvailable(updateModel!!.getData()!!.link)
            }
            else{
                mIMainActivityView?.showSnackBar(mIMainActivityView!!.getContext().getString(R.string.dialog_app_updated_text))
            }
        }catch(e : PackageManager.NameNotFoundException){
            e.printStackTrace()
        }
    }
    override fun chnageLanguage(context: Context, language: String) {
        mInteractor?.changeLocale(context,language)
    }
}