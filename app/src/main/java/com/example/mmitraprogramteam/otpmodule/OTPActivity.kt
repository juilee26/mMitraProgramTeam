package com.example.mmitraprogramteam.otpmodule
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.mmitraprogramteam.data.volley.SingletonRequestQueue
import com.example.mmitraprogramteam.home.MainActivity
import kotlinx.android.synthetic.main.activity_opt_screen.*
import org.json.JSONObject
import java.util.HashMap
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.inputmethod.InputMethodManager
import com.example.mmitraprogramteam.R
import com.example.mmitraprogramteam.data.database.DBHelper
import com.example.mmitraprogramteam.forms.EnrollmentQuestions


class OTPActivity() : AppCompatActivity(),IOTPView,View.OnClickListener{

    var mobile_number: String =""
    var otp_gen_number:String=""
    var otp_submited:String=""
    var val1 :String=""
    var val2 :String=""
    var val3 :String=""
    var val4 :String=""
    var min =0
    var sec =0
    val TIMER_TIME_MILLS : Long =300000
    var mTimeLeftInMills = TIMER_TIME_MILLS
    lateinit var anim : Animation
    var username = ""
    var password = "'"
    var dbHelper = DBHelper(this)
    internal lateinit var presentor : OTPPresenter
    var userDetail :ArrayList<String>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opt_screen)

        presentor = OTPPresenter()
        presentor.attachView(this)

        digit1.addTextChangedListener(GenericTextWatcher(digit1))
        digit2.addTextChangedListener(GenericTextWatcher(digit2))
        digit3.addTextChangedListener(GenericTextWatcher(digit3))
        digit4.addTextChangedListener(GenericTextWatcher(digit4))

        btnSendOtp.setOnClickListener(this)
        btnNext.setOnClickListener(this)

        //declare animation
        anim = AnimationUtils.loadAnimation(this, R.anim.fade_in_effect)

        //get userDetails from db
        fetchUserDetails()



    }

    override fun fetchUserDetails() {
       /* userDetail = presentor.getUserDetails()
        username = userDetail!![0]
        password= userDetail!![1]
   */ }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.btnSendOtp->{
               validateMobileNo()
                hideKeyboard()
                object :CountDownTimer(mTimeLeftInMills,1000){
                    override fun onFinish() {
                        btnSendOtp.text ="RESEND OTP"
                        txtTimerValue.visibility = View.GONE
                    }

                    override fun onTick(millisUntilFinished: Long) {
                        mTimeLeftInMills= millisUntilFinished
                        min = (mTimeLeftInMills.toInt()/(1000*60))%60
                        sec =  mTimeLeftInMills.toInt()/1000%60
                        var time = String.format("%02d:%02d",min,sec)
                        var timer  = resources.getString(R.string.otp_timer_val)+time
                        txtTimerValue.text = timer


                    }
                }.start()

            }

            R.id.btnNext ->{
                val intent = Intent(this@OTPActivity, EnrollmentQuestions::class.java)
                startActivity(intent)
            }
        }

    }


    fun networkCallForOTP() {

        mobile_number = edit_mobile_number.text.toString()
        otp_gen_number = ((Math.random() * 9000).toInt() + 1000).toString()
        Log.d("value", mobile_number + " :: " + otp_gen_number)
        println(mobile_number + " :: " + otp_gen_number)

        val URL = "http://testmmitraapi.000webhostapp.com/mMitra_API/otp"
        var params = HashMap<String, String>()
        params["mobile_no"] = mobile_number
        params["otp"] = otp_gen_number
        params["password"] = "1234"
        params["email"] = "sunny@gmail.com"
        val request_json = JsonObjectRequest(URL, JSONObject(params as Map<*, *>),
            Response.Listener { response ->
                Toast.makeText(this@OTPActivity, "" + response.toString(), Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
               error.printStackTrace()
                Log.d("Error", error.toString())
                Toast.makeText(this@OTPActivity, "" + error.toString(), Toast.LENGTH_SHORT).show()
            })

        val queue = SingletonRequestQueue.getInstance(applicationContext).requestQueue
        queue.add(request_json)
    }

    /**
     * Generic class is used for OTP edittext to perform next on entering pin
     */
    inner class GenericTextWatcher(private val view: View) : TextWatcher {

        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            when (view.id) {
                R.id.digit1 -> if (text.length == 1)
                    digit2.requestFocus()
                R.id.digit2 -> if (text.length == 1)
                    digit3.requestFocus()
                else if (text.isEmpty())
                    digit1.requestFocus()
                R.id.digit3 -> if (text.length == 1)
                    digit4.requestFocus()
                else if (text.isEmpty())
                    digit2.requestFocus()
                R.id.digit4 -> if (text.isEmpty())
                    digit3.requestFocus()
                else{
                    if(text.length==1){
                        hideKeyboard()
                    }
                    val1=digit1.text.toString()
                    val2=digit2.text.toString()
                    val3=digit3.text.toString()
                    val4=digit4.text.toString()
                    otp_submited = val1+val2+val3+val4
                    if (otp_gen_number.equals(otp_submited, ignoreCase = true)) {
                        txtSuccessfulmsg.visibility = View.VISIBLE
                        txtNextInfo.visibility = View.VISIBLE
                        btnNext.visibility =View.VISIBLE
                        successfulImg.visibility=View.VISIBLE
                        txtTimerValue.visibility=View.GONE
                        //set animation
                        txtSuccessfulmsg.startAnimation(anim)
                        txtNextInfo.startAnimation(anim)
                        btnNext.startAnimation(anim)

                    } else {
                        btnSendOtp.setText("Resend OTP")
                        digit1.text.clear()
                        digit2.text.clear()
                        digit3.text.clear()
                        digit4.text.clear()
                        Toast.makeText(
                            this@OTPActivity,
                            "OTP is Not Matched",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
            // TODO Auto-generated method stub
        }

        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
        }

    }

    fun hideKeyboard() {
        val inputManager:InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
    }

    override fun validateMobileNo() {
        edit_mobile_number.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                inputMobileNo.setErrorTextColor(ColorStateList.valueOf(Color.GRAY))
            }
        }

        mobile_number = edit_mobile_number.text.toString()
        if(mobile_number.isEmpty() || mobile_number.length!=10){
            inputMobileNo.error = getString(R.string.mobile_no_error)
            txtTimerValue.visibility=View.GONE
        }
        else if(mobile_number.length==10){
            inputMobileNo.setErrorTextColor(ColorStateList.valueOf(Color.GRAY))
            hideKeyboard()
            networkCallForOTP()
            txtTimerValue.visibility= View.VISIBLE
        }
    }

    override fun getContext(): Context {
return this
    }

    override fun openNextActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAuthenticationFailedError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle(this.getString(R.string.exit_otp_title))
            .setMessage(this.getString(R.string.exit_otp_message))
            .setIcon(R.mipmap.ic_exitalert)
            .setPositiveButton(R.string.yes) { dialog, which ->
                try {
                    val intent = Intent(this@OTPActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton(R.string.no) { dialog, which ->

            }.show()


    }
}