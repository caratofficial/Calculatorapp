package com.calculatorapp.emi.calculator.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*

import com.calculatorapp.emi.calculator.R
import java.text.DecimalFormat

class PercentageCalculatorActivity : BaseActivity(), View.OnClickListener {
   private var btnSetting: ImageView? = null
   private var txtResult: TextView? = null
   private var edtPercentage: EditText? = null
   private var edtNumber: EditText? = null
   private var btnCalc: Button? = null
   private var llAdViewFacebook :LinearLayout?=null
   private var llAdView :RelativeLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_percentage_calculator)
        supportActionBar!!.hide()
        context = this
        initDefine()
        initAction()
        com.calculatorapp.emi.calculator.utils.Utils.loadBannerAd(llAdView!!,llAdViewFacebook!!,this)
    }

    fun initDefine() {
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        btnSetting = findViewById(R.id.btnSetting)
        txtResult = findViewById(R.id.txtResult)
        edtPercentage = findViewById(R.id.edtPercentage)
        edtNumber = findViewById(R.id.edtNumber)
        btnCalc = findViewById(R.id.btnCalc)
    }

    fun initAction() {
        btnSetting!!.setOnClickListener(this)
        btnCalc!!.setOnClickListener(this)
    }

    @SuppressLint("WrongConstant")
    override fun onClick(view: View?) {
        if (view === btnSetting) {
            fullView.openDrawer(Gravity.START)
        } else if (view === btnCalc) {
            calculateResult()
        }
    }

    private fun calculateResult() {
        if (edtPercentage!!.text.toString().isNotEmpty()
            && edtNumber!!.text.toString().isNotEmpty()
        ) {
            if (edtPercentage!!.text.toString().toFloat() > 0.0
                && edtNumber!!.text.toString().toFloat() > 0.0
            ) {
                val percentage = edtPercentage!!.text.toString().toFloat()
                val dec = percentage / 100
                val total = dec * edtNumber!!.text.toString().toFloat()
                txtResult!!.text = total.toString()
                txtResult!!.text = DecimalFormat("##.##").format(total)
            } else {
                Toast.makeText(this, "Please enter correct value", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Please enter correct value", Toast.LENGTH_LONG).show()
        }
    }
}