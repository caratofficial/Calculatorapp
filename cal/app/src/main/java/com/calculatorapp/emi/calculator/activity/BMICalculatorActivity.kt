package com.calculatorapp.emi.calculator.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.calculatorapp.emi.calculator.R
import java.math.BigDecimal
import java.math.RoundingMode

class BMICalculatorActivity : BaseActivity(), View.OnClickListener {
    var weightEDTX: EditText? = null
    var heightEDTX: EditText? = null
    lateinit var calculateBtn: Button
    lateinit var btnSetting: ImageView
    var llAdViewFacebook :LinearLayout?=null
    var llAdView : RelativeLayout?=null
    var container: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)
        supportActionBar!!.hide()
        context = this
        initDefine()
        initAction()
        com.calculatorapp.emi.calculator.utils.Utils.loadBannerAd(llAdView!!,llAdViewFacebook!!,this)
    }

    private fun initAction() {
        calculateBtn.setOnClickListener(this)
        btnSetting.setOnClickListener(this)
    }

    private fun initDefine() {
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        container = findViewById(R.id.container)
        weightEDTX = findViewById(R.id.weightEDTX)
        heightEDTX = findViewById(R.id.heightEDTX)
        calculateBtn = findViewById(R.id.calculateBtn)
        btnSetting = findViewById(R.id.btnSetting)
    }

    @SuppressLint("WrongConstant")
    override fun onClick(view: View?) {
        if (view === btnSetting) {
            fullView.openDrawer(Gravity.START)
        } else if (view === calculateBtn) {
            calculateResult()
        }
    }

    private fun calculateResult() {
        if (!heightEDTX!!.text.toString().isEmpty() && !weightEDTX!!.text.toString()
                .isEmpty()
        ) {
            val weight = weightEDTX!!.text.toString().toDouble()
            val height = heightEDTX!!.text.toString().toDouble() / 100
            val heightMain = heightEDTX!!.text.toString().toInt()
            val weightMain = weightEDTX!!.text.toString().toInt()
            if (weightMain in 20..997 && heightMain >= 20 && heightMain <= 400) {
                val intent = Intent(this@BMICalculatorActivity, BMIResultActivity::class.java)
                intent.putExtra("bmi", calculateBMI(weight, height).toString())
                startActivity(intent)
            } else {
                showErrorSnack("Incorrect Values")
            }
        } else {
            showErrorSnack("A filed is missing")
        }
    }

    private fun showErrorSnack(errorMsg: String?) {
        val snackbar = Snackbar.make(container!!, "error : $errorMsg", Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundResource(R.color.colorRed)
        snackbar.show()
    }

    private fun calculateBMI(weight: Double, height: Double): BigDecimal? {
        return BigDecimal(weight / (height * height)).setScale(2, RoundingMode.HALF_EVEN)
    }
}