package com.example.mmitraprogramteam.login
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.data.database.DBHelper
import com.example.mmitraprogramteam.data.database.DatabaseManager
import com.example.mmitraprogramteam.data.model.UserDetails
import com.example.mmitraprogramteam.utility.Constants.AUTHENTICATION_FAILED
import com.example.mmitraprogramteam.utility.Constants.AUTHENTICATION_SUCCESS
import com.example.mmitraprogramteam.utility.Utility
import org.json.JSONObject
import java.util.*

class LoginPresenter : ILoginPresenter<ILoginView>, ILoginInteractor.OnLoginFinished {

    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.CAMERA
    )
    var iLoginview: ILoginView? = null
    var util = Utility()
    var mUserDetails = UserDetails()
    var iLogInteractor = LoginInteractor()
    var utility=Utility()

    override fun attachView(ilogin: ILoginView) {
        this.iLoginview = ilogin
        var loginInter = LoginInteractor()

        if (checkPermissions()) {
            initializeDBHelper()
        }

    }

    override fun initializeDBHelper() {
        var dbHelper = DBHelper(iLoginview!!.getContext())
        DatabaseManager.initializeInstance(dbHelper)
        DatabaseManager.getInstance().openDatabase()
    }


    override fun checkPermissions(): Boolean {
        val listPermissionsNeeded = ArrayList<String>()

        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(iLoginview!!.getContext(),
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                listPermissionsNeeded.add(permission)
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            getPermissions(listPermissionsNeeded)
            return false
        } else {
            return true
        }
    }

    override fun getPermissions(listPermissionsNeeded: List<String>) {
        ActivityCompat.requestPermissions(
            iLoginview!!.getContext() as Activity,
            listPermissionsNeeded.toTypedArray(),
            1
        )
    }

    override fun validateCredentials(username: String, password: String) {
        iLoginview?.resetErrorMsg()
        if (username.isEmpty())
            iLoginview?.setUsernameError()
        else if (password.isEmpty())
            iLoginview!!.setPasswordError()
        else
            loginUser(username, password)
    }

    override fun loginUser(username: String, password: String) {
        if (util.hasInternetConnectivity(iLoginview!!.getContext())) {
            iLoginview!!.showProgressBar()
            createRequestBody(username, password)
            iLogInteractor.login(mUserDetails,this,iLoginview?.getContext()!!)
        } else {
            var title = "No internet"
            var message = "No internet connectivity. Please check your network"
            iLoginview!!.showDialog(title, message)
        }
    }

    override fun createRequestBody(username: String, password: String) {
            mUserDetails = UserDetails()
            mUserDetails.setusername(username)
            mUserDetails.setpassword(password)
         mUserDetails.setImei(utility.getDeviceImeiNumber(iLoginview?.getContext()!!))
            //mUserDetails.setShowdata("true")
    }

    override fun checkIfUserAlreadyLoggedIn() {
        try{
            if(iLogInteractor.userAlreadyLoggedIn()){
                iLoginview?.openHomeActivity()
            }
        }
        catch(e : IllegalStateException){
            checkPermissions()
            initializeDBHelper()
            checkIfUserAlreadyLoggedIn()
        }

    }

    override fun detachView() {
    }

    override fun onSuccess(jsonObject: JSONObject){
       if (jsonObject.has("status")){
               when (jsonObject.optString("status")) {
                   AUTHENTICATION_SUCCESS -> {
                       iLogInteractor.saveUserDetails(mUserDetails.email!!, mUserDetails.password!!, jsonObject)
                       iLoginview?.hideProgressBar()
                       iLoginview?.openHomeActivity()
                   }
                   AUTHENTICATION_FAILED -> {
                       iLoginview?.hideProgressBar()
                       iLoginview?.showDialog(iLoginview?.getContext()?.getString(R.string.error)!!,
                           iLoginview?.getContext()?.getString(R.string.authentication_failed)!!)
                       iLoginview?.setAuthenticationFailedError()
                   }
                   else -> {
                       iLoginview?.hideProgressBar()
                       iLoginview?.showDialog(
                           iLoginview?.getContext()?.getString(R.string.error)!!,
                           iLoginview?.getContext()?.getString(R.string.response_not_found)!!
                       )
                   }
               }
       }else{
         iLoginview?.hideProgressBar()
           iLoginview?.showDialog(iLoginview?.getContext()?.getString(R.string.error)!!, iLoginview?.getContext()!!.getString(R.string.response_not_found))
       }
    }

    override fun onFailure(message: String) {
        iLoginview?.hideProgressBar()
        iLoginview?.showDialog("Error",message)
    }
}