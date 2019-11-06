package com.example.mmitraprogramteam.utility

interface IBasePresenter<V> {
    fun attachView(view :V)
    fun detachView()
}