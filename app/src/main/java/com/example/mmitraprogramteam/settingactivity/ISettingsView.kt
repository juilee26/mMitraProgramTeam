package com.example.mmitraprogramteam.settingactivity

import com.example.mmitraprogramteam.utility.IMvpView

interface ISettingsView  : IMvpView {
    fun showProgressBar(label :String)
    fun hideProgressBar()
    fun showDialog(title : String,message : String)
    fun updateAvailable(url : String)
    fun showApkDownloadProgress()
     fun updateApkDownloadProgress(progress: Int)
    fun dissmissApkDownloadProgress()
    fun showSnackBar(message : String)
}