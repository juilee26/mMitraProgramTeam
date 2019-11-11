package com.example.mmitraprogramteam.home

import com.example.mmitraprogramteam.utility.IMvpView

interface IMainActivity : IMvpView {
       fun showSnackBar(message: String)
       fun setUnsentFormsCount(count: Int)
       fun showProgressBar(label: String)
       fun hideProgressBar()
       fun showFormUpdateErrorDialog()
       fun updateAvailable(url : String)
}