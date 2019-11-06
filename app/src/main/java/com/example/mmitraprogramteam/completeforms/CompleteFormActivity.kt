package tech.inscripts.ins_armman.mMitra.completeforms

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.data.model.completeFilledForm
import kotlinx.android.synthetic.main.activity_woman_list.*

class CompleteFormActivity : AppCompatActivity(),ICompleteFormView,CompleteFormsAdapter.ClickListener{
     var completedPresenter: CompleteFormPresenter?=null
     var mcompleteFormAdapter: CompleteFormsAdapter?=null
     var mProgressBar: ProgressBar?=null
     var emptyLayout: RelativeLayout?=null
    private var mRecyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_woman_list)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle("Complete Forms")
        setSupportActionBar(toolbar)
        mProgressBar = findViewById(R.id.child_list_progress_bar)
        emptyLayout=findViewById(R.id.empty_layout)
        mRecyclerView=findViewById(R.id.recycler_view)

        val layoutManager = LinearLayoutManager(this@CompleteFormActivity)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        mRecyclerView?.addItemDecoration(itemDecoration)
        mRecyclerView?.setLayoutManager(layoutManager)

        completedPresenter = CompleteFormPresenter()
        completedPresenter?.attachView(this) }

    override fun setAdapter(mWomenList: List<completeFilledForm>) {
        mProgressBar?.visibility = View.GONE
        if(mWomenList==null || mWomenList.size < 1){
            empty_list_layout.visibility=View.VISIBLE
            return
        }
        if(mWomenList!=null){
            mcompleteFormAdapter = CompleteFormsAdapter(getContext(),mWomenList)
            mRecyclerView?.setAdapter(mcompleteFormAdapter)
            mcompleteFormAdapter?.setOnClickListener(this)
        }
        else{
            mcompleteFormAdapter?.swapDataList(mWomenList)
            mcompleteFormAdapter?.notifyDataSetChanged()
        }
    }

    override fun getContext(): Context {
    return this
    }

    override fun itemClicked(v: View, position: Int) {
    }

    override fun onResume() {
        super.onResume()
        completedPresenter?.getListCompleteForm()
    }

    override fun onDestroy() {
        super.onDestroy()
        completedPresenter?.attachView(this)

    }
}