package com.example.mmitraprogramteam.home

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.MotionEventCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.completeforms.CompleteFormActivity
import com.example.mmitraprogramteam.forms.EnrollmentQuestions
import com.example.mmitraprogramteam.otpmodule.OTPActivity
import com.example.mmitraprogramteam.settingactivity.Settings
import com.example.mmitraprogramteam.settingactivity.SettingsActivity
import com.example.mmitraprogramteam.settingactivity.SettingsPresentor
import com.example.mmitraprogramteam.userprofile.UserProfileActivity
import com.example.mmitraprogramteam.utility.Utility
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_homeactivity.*
import kotlinx.android.synthetic.main.activity_main.btnReg
import kotlinx.android.synthetic.main.activity_main.btnReports
import kotlinx.android.synthetic.main.activity_main.btnWomen
import kotlinx.android.synthetic.main.activity_main.indicator
import kotlinx.android.synthetic.main.activity_main.viewPager
import kotlinx.android.synthetic.main.activity_main_buttons.*
import java.util.*

class MainActivity : AppCompatActivity(), IMainActivity,NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,View.OnTouchListener{



    var image_Array : IntArray = intArrayOf(R.drawable.slide01,R.drawable.slide02,R.drawable.slide03)
    //internal lateinit var viewPager : ViewPager
    var mPresenter: MainActivityPresentor?=null
    var utility : Utility = Utility()
    var mSyncDrawable: LayerDrawable? = null
    var mProgressDialog: AlertDialog? = null
    var settingsPresentor : SettingsPresentor?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homeactivity)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(R.string.home)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_navigation_icon)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        /*mPresenter?.getLoginDetail(userDetails)
        val name = userDetails.get(0)     */
        nav_view.setNavigationItemSelectedListener(this)
        var navigationView : NavigationView = findViewById(R.id.nav_view)
        navigationView?.setNavigationItemSelectedListener(this)

        var menu : Menu =navigationView.menu
        var version : MenuItem = menu.findItem(R.id.version)
        version.title = utility.getAppVersionName(this)
        var header : View = navigationView!!.getHeaderView(0)
        var textUserName : TextView = header.findViewById(R.id.navUserName)
        textUserName.text="USER"
        var drawerLayout : DrawerLayout = findViewById(R.id.drawer_layout)
        var mDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name)
        mDrawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE)
        drawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()


        viewPager.setAdapter(SliderAdapter(this, image_Array))
        indicator.setupWithViewPager(viewPager, true)

        val timer = Timer()
        timer.scheduleAtFixedRate(SliderTimer(), 4000, 6000)
        btnReg.setOnClickListener(this)
        btnWomen.setOnClickListener(this)
        btnReports.setOnClickListener(this)
        btnLanguage.setOnClickListener(this)
        btnUpdateForms.setOnClickListener(this)


    } //end of onCreate

    override fun getContext(): Context {
        return this    }

    override fun onClick(v: View?) {
        when (v?.getId()) {
            R.id.btnReg -> startActivity(Intent(this, OTPActivity::class.java))

            R.id.btnWomen -> startActivity(Intent(this, CompleteFormActivity::class.java))

            R.id.btnReports -> Toast.makeText(applicationContext,"Reports",Toast.LENGTH_SHORT).show()

            R.id.btnUserProfile -> startActivity(Intent(this, UserProfileActivity::class.java))
            R.id.btnLanguage -> Toast.makeText(applicationContext,"Language",Toast.LENGTH_SHORT).show()
            R.id.btnUpdateForms -> Toast.makeText(applicationContext,"Update Forms",Toast.LENGTH_SHORT).show()
        }    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action){
            MotionEventCompat.ACTION_POINTER_DOWN ->{
                v?.background?.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP)
            v?.invalidate()
            }
            MotionEventCompat.ACTION_POINTER_UP ->{
                v?.background?.clearColorFilter()
                v?.invalidate()
            }
        }
return true

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home_activity, menu)
        val item = menu?.findItem(R.id.action_sync)
        mSyncDrawable = item?.icon as LayerDrawable
        utility.setBadgeCount(this, mSyncDrawable!!, 0)
        mPresenter?.fetchUnsentFormsCount()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item!=null){
        when (item.itemId) {
            R.id.action_settings -> startActivity(Intent(Intent(this@MainActivity, Settings::class.java)))
           // R.id.action_sync -> mPresenter?.fetchRegistrationData()
        }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_registration -> {
                startActivity(Intent(this, EnrollmentQuestions::class.java))
            }
            R.id.nav_complete -> {
          //      startActivity(Intent(this, CompleteFormActivity::class.java))
            }
            R.id.nav_updateForms -> {
                mPresenter?.downloadForms()
            }
            R.id.nav_restoreData -> {
                val builder = AlertDialog.Builder(getContext())
                builder.setTitle(R.string.restore_data)
                    // builder.setTitle("NOTICE")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage(R.string.dialog_msg_loss_data_warning)
                    //.setMessage("This Functionality in in progress..")
                    .setCancelable(false)
                    .setPositiveButton(
                        R.string.ok
                    ) { dialog, which -> mPresenter?.restoreData()
                    }
                    .setNegativeButton(
                        R.string.cancel
                    ) { dialog, which -> dialog.cancel() }
                    .show()

            }

            R.id.nav_checkUpdate -> {
                mPresenter?.checkUpdate()
            }

            R.id.nav_logout -> {
                val builder = AlertDialog.Builder(getContext())
                builder.setTitle(R.string.logout)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage(R.string.logout_message)
                    .setCancelable(false)
                    .setPositiveButton(
                        R.string.ok
                    ) { dialog, which -> mPresenter?.logout() }
                    .setNegativeButton(
                        R.string.cancel
                    ) { dialog, which -> dialog.cancel() }
                    .show()

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true    }

    override fun showSnackBar(message: String) {
        val snackbar = Snackbar
            .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)

        snackbar.show()    }

    override fun setUnsentFormsCount(count: Int) {
        if (mSyncDrawable != null) utility.setBadgeCount(this, mSyncDrawable!!, count)    }

    override fun showProgressBar(label: String) {
        var inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var dialogView = inflater.inflate(R.layout.progress_dialog_layout, null)
        var textView = dialogView.findViewById<TextView>(R.id.textView_label)
        textView.text = label
        var mAlertDialogBuilder =AlertDialog.Builder(this)
        mAlertDialogBuilder.setView(dialogView)
        mAlertDialogBuilder.setCancelable(false)
        mProgressDialog = mAlertDialogBuilder.create()
        mProgressDialog?.show()    }

    override fun hideProgressBar() {
        if (mProgressDialog != null) mProgressDialog?.dismiss()    }

    override fun showFormUpdateErrorDialog() {
        val builder = AlertDialog.Builder(getContext())
        builder.setTitle(R.string.restore_warning_text)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setMessage(R.string.update_forms_message)
            .setCancelable(false)
            .setPositiveButton(
                R.string.ok
            ) { dialog, which -> startActivity(Intent(this, SettingsActivity::class.java)) }
            .show()    }

    override fun updateAvailable(url: String) {
        android.app.AlertDialog.Builder(this)
            .setMessage(getString(R.string.dialog_update_available))
            .setPositiveButton(getString(R.string.dialog_install_text), DialogInterface.OnClickListener { dialog, which ->
                settingsPresentor!!.downloadApk(url)
            })
            .setNegativeButton(getString(R.string.cancel),
                DialogInterface.OnClickListener { dialog, which ->  })
            .show()    }



    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    private inner class SliderTimer : TimerTask() {

        override fun run() {
            this@MainActivity.runOnUiThread(Runnable {
                if (viewPager.getCurrentItem() < image_Array.size- 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1)
                } else {
                    viewPager.setCurrentItem(0)
                }
            })
        }
    }
    }
