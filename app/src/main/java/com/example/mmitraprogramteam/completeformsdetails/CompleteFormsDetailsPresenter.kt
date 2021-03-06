package com.example.mmitraprogramteam.completeformsdetails

import android.util.Log
import com.example.mmitraprogramteam.data.model.CompleteFormQnA
import java.util.*
import java.util.Arrays.asList
import kotlin.collections.ArrayList


class CompleteFormsDetailsPresenter : ICompleteFormsDetailsPresenter<CompleteFormDetailsActivity> {


    internal var completeFormView: ICompleteFormsDetailsView? = null
    internal var interactor: CompleteFormsDetailsInteractor?=null
    internal var formDetails = ArrayList<CompleteFormQnA>()
    internal var childFormId = ArrayList<CompleteFormQnA>()


    override fun attachView(view: CompleteFormDetailsActivity) {
        this.completeFormView = view
        this.interactor = CompleteFormsDetailsInteractor(view?.getContext()!!)
    }

    override fun detachView() {
        completeFormView = null
    }

    override fun displayFIlledForm(unique_id: String, form_id: Int) {
        val cur = interactor?.displayFormDetails(unique_id, form_id)
        var que_keyword : String=""
            if (cur != null && cur.moveToFirst()) {
                do {
                    val completeFormQnA = CompleteFormQnA()
                     var que= cur.getString(cur.getColumnIndex("question_label"))
                    if (que == null) {
                        que = cur.getString(cur.getColumnIndex("question_keyword"))
                    //to get the question label of the dependant question
                        var queLabel = interactor?.getDependantQuestion(que)
                        if(queLabel!=null){
                            que=queLabel
                        }
                        else {
                            que = cur.getString(cur.getColumnIndex("question_keyword"))
                        }
                        completeFormQnA.question = que
                    } else {
                        completeFormQnA.question = cur.getString(cur.getColumnIndex("question_label"))
                        que_keyword=cur.getString(cur.getColumnIndex("question_label"))
                    }
                    var ans: String? = cur.getString(cur.getColumnIndex("option_label"))
                    if (ans == null) {
                       /* var queType = interactor?.getQuestionType(que_keyword)
                        if (queType == "int" || queType == "text" || queType == "date")
                        {
                       */     ans = cur.getString(cur.getColumnIndex("answer_keyword"))
                        /*}
                        else {
                            ans = cur.getString(cur.getColumnIndex("answer_keyword"))
                            var ansArray: List<String> = ans.split(",").map { it.trim() }
                            var ansLabel: List<String>? = interactor?.getAnswerLabel(ansArray)
                            ans = ansLabel?.joinToString()
//                            Log.d("ANS :", ans)
                        }*/
                        completeFormQnA.answer = ans
                        Log.d("STORE_ANS :", ans)
                        }
                    else {
                        completeFormQnA.answer = cur.getString(cur.getColumnIndex("option_label"))
                    }
                    formDetails.add(completeFormQnA)
                    println("question :$que\nanswer :$ans")
                } while (cur.moveToNext())
            }

        if (!formDetails.isEmpty()) {
            completeFormView?.getFormdetails(formDetails)
        }
    }

}