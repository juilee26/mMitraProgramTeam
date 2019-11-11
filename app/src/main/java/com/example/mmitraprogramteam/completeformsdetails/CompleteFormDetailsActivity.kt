package com.example.mmitraprogramteam.completeformsdetails

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.data.database.DBHelper
import com.example.mmitraprogramteam.data.model.CompleteFormQnA
import java.util.ArrayList

class CompleteFormDetailsActivity : AppCompatActivity(), ICompleteFormsDetailsView {
    internal lateinit var unique_id: String
    internal var form_id: Int = 0
    internal lateinit var recyclerView: RecyclerView
    internal lateinit var adapter: DetailsAdapter
    internal lateinit var db: DBHelper
    internal lateinit var presentor: CompleteFormsDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_forms_details)
        presentor = CompleteFormsDetailsPresenter()
        presentor.attachView(this)
        db= DBHelper(this)
        val toolbar = findViewById<Toolbar>(R.id.include2)
        toolbar.setTitle("Form Details")
        setSupportActionBar(toolbar)
        recyclerView= findViewById(R.id.recyclerView)
        var linearLayoutManager = LinearLayoutManager(this@CompleteFormDetailsActivity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        unique_id = intent.getStringExtra("id")
        form_id = intent.getIntExtra("form_id", 0)
        presentor.displayFIlledForm(unique_id, form_id)
    }

    override fun getContext(): Context {
        return this
    }

    override fun getFormdetails(formDetails: ArrayList<CompleteFormQnA>) {
        adapter = DetailsAdapter(this,form_id,formDetails )
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presentor.detachView()
    }
}