package tech.inscripts.ins_armman.mMitra.completeformList

import com.example.mmitraprogramteam.utility.IBasePresenter

interface ICompleteFormListPresenter<V> : IBasePresenter<V> {
     fun getCompleteFormList(unique_mother_id: String)
     fun checkFormPresent() : Int?
}