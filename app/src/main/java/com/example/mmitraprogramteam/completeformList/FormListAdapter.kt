package com.example.mmitraprogramteam.completeformList
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.completeformsdetails.CompleteFormDetailsActivity
import com.example.mmitraprogramteam.data.model.CompleteFormQnA
import com.example.mmitraprogramteam.utility.Utility
import org.json.JSONException
import org.json.JSONObject

class FormListAdapter() : RecyclerView.Adapter<FormListAdapter.ViewHolder>() {

    internal lateinit var mContext: Context
    internal lateinit var language: String
    internal lateinit var formName:String
    internal lateinit var participant_id:String
    internal var form_id: Int = 0
    var utility = Utility()

    internal lateinit var uniqueId: String
    internal var name:String? = null
    internal var pos = 0
    private var mDetails: ArrayList<CompleteFormQnA>?=null
    private var clickListener:ClickListener? = null

    constructor(mContext: Context, participant_id: String, form_id: Int, mDetails: ArrayList<CompleteFormQnA>?) : this() {
        this.mContext = mContext
        this.participant_id = participant_id
        this.form_id = form_id
        this.mDetails = mDetails
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.activity_incomplete_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDetails!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val c :CompleteFormQnA  = mDetails?.get(position) as CompleteFormQnA
        try {
            var obj = JSONObject(c?.formName)
            println("formNAme : " + obj)
            language = utility.getLanguagePreferance(mContext)!!
            if (language.isEmpty()) {
                utility.setApplicationLocale(mContext, "en")
            } else {
                utility.setApplicationLocale(mContext, language)
            }
            formName = obj.getString(this.language)
            println("formNAme : " + formName)
            formName = formName.toUpperCase()
            println("formNAme : " + formName)
            holder.txtformName.text = formName

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    fun setClickListener(clickListener : ClickListener){
        this.clickListener=clickListener
    }

     interface ClickListener{
        fun itemClicked(view : View, position : Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        internal var txtformName: TextView=itemView.findViewById(R.id.textview_name)

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View) {
            var intent = Intent(mContext, CompleteFormDetailsActivity::class.java)
            if (clickListener != null) {
                clickListener!!.itemClicked(v, position)
                val formId = mDetails?.get(position)?.getForm_id()
                uniqueId = participant_id
                intent.putExtra("unique_id", uniqueId)
                intent.putExtra("form_id", formId)
                mContext.startActivity(intent)
            }
        }
    }
}