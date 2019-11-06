package tech.inscripts.ins_armman.mMitra.completeformsdetails

import com.example.mmitraprogramteam.utility.IBasePresenter


interface ICompleteFormsDetailsPresenter<V> : IBasePresenter<V> {
    abstract fun displayFIlledForm(unique_id: String, form_id: Int)


}