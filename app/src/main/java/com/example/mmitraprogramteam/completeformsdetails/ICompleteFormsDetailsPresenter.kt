package com.example.mmitraprogramteam.completeformsdetails

import com.example.mmitraprogramteam.utility.IBasePresenter


interface ICompleteFormsDetailsPresenter<V> : IBasePresenter<V> {
    abstract fun displayFIlledForm(unique_id: String, form_id: Int)


}