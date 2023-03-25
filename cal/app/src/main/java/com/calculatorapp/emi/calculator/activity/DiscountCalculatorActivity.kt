package com.calculatorapp.emi.calculator.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*

import com.calculatorapp.emi.calculator.R
import java.lang.StringBuilder

class DiscountCalculatorActivity : BaseActivity(), View.OnClickListener {
    private lateinit var btnSetting: ImageView
    private lateinit var txt0: TextView
    private lateinit var txt1: TextView
    private lateinit var txt2: TextView
    private lateinit var txt3: TextView
    private lateinit var txt4: TextView
    private lateinit var txt5: TextView
    private lateinit var txt6: TextView
    private lateinit var txt7: TextView
    private lateinit var txt8: TextView
    private lateinit var txt9: TextView
    private lateinit var txtDot: TextView
    private lateinit var txtAc: TextView
    private lateinit var txtEqual: TextView
    private lateinit var txtTotalPrice: TextView
    private lateinit var txtYouSave: TextView
    private lateinit var txtX: LinearLayout
    private lateinit var txtOriginalPrice: TextView
    private lateinit var txtDiscountPrice: TextView
    private lateinit var stringBuilderOriginal: StringBuilder
    private lateinit var stringBuilderDiscount: StringBuilder
    private var llAdViewFacebook :LinearLayout?=null
    private var llAdView : RelativeLayout?=null
    private var isSelectOriginal = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discount_caculator)
        supportActionBar!!.hide()
        context = this
        initDefine()
        initAction()
        stringBuilderOriginal = StringBuilder()
        stringBuilderDiscount = StringBuilder()
        com.calculatorapp.emi.calculator.utils.Utils.loadBannerAd(llAdView!!,llAdViewFacebook!!,this)
    }

    private fun initDefine() {
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        btnSetting = findViewById(R.id.btnSetting)
        txt0 = findViewById(R.id.txt0)
        txt1 = findViewById(R.id.txt1)
        txt2 = findViewById(R.id.txt2)
        txt3 = findViewById(R.id.txt3)
        txt4 = findViewById(R.id.txt4)
        txt4 = findViewById(R.id.txt4)
        txt5 = findViewById(R.id.txt5)
        txt6 = findViewById(R.id.txt6)
        txt7 = findViewById(R.id.txt7)
        txt8 = findViewById(R.id.txt8)
        txt9 = findViewById(R.id.txt9)
        txtDot = findViewById(R.id.txtDot)
        txtX = findViewById(R.id.txtX)
        txtAc = findViewById(R.id.txtAc)
        txtEqual = findViewById(R.id.txtEqual)
        txtOriginalPrice = findViewById(R.id.txtOriginalPrice)
        txtDiscountPrice = findViewById(R.id.txtDiscountPrice)
        txtTotalPrice = findViewById(R.id.txtTotalPrice)
        txtYouSave = findViewById(R.id.txtYouSave)
    }

    private fun initAction() {
        btnSetting.setOnClickListener(this)
        txt0.setOnClickListener(this)
        txt1.setOnClickListener(this)
        txt2.setOnClickListener(this)
        txt3.setOnClickListener(this)
        txt4.setOnClickListener(this)
        txt4.setOnClickListener(this)
        txt5.setOnClickListener(this)
        txt6.setOnClickListener(this)
        txt7.setOnClickListener(this)
        txt8.setOnClickListener(this)
        txt9.setOnClickListener(this)
        txtDot.setOnClickListener(this)
        txtX.setOnClickListener(this)
        txtAc.setOnClickListener(this)
        txtOriginalPrice.setOnClickListener(this)
        txtDiscountPrice.setOnClickListener(this)
        txtEqual.setOnClickListener(this)
    }

    @SuppressLint("WrongConstant")
    override fun onClick(view: View?) {
        when {
            view === btnSetting -> {
                fullView.openDrawer(Gravity.START)
            }
            view === txtEqual -> {
                calculateValue()
            }
            view === txt0 -> {
                setOriginalPrice(0)
            }
            view === txt1 -> {
                setOriginalPrice(1)
            }
            view === txt2 -> {
                setOriginalPrice(2)
            }
            view === txt3 -> {
                setOriginalPrice(3)
            }
            view === txt4 -> {
                setOriginalPrice(4)
            }
            view === txt5 -> {
                setOriginalPrice(5)
            }
            view === txt6 -> {
                setOriginalPrice(6)
            }
            view === txt7 -> {
                setOriginalPrice(7)
            }
            view === txt8 -> {
                setOriginalPrice(8)
            }
            view === txt9 -> {
                setOriginalPrice(9)
            }
            view === txtDot -> {
                setOriginalPrice(-1)
            }
            view === txtX -> {
                setOriginalPrice(-2)
            }
            view === txtAc -> {
                setOriginalPrice(-3)
            }
            view === txtOriginalPrice -> {
                isSelectOriginal = true
                txtOriginalPrice.setTextColor(resources.getColor(R.color.theme_color))
                txtDiscountPrice.setTextColor(resources.getColor(R.color.colorBlack))
            }
            view === txtDiscountPrice -> {
                isSelectOriginal = false
                txtOriginalPrice.setTextColor(resources.getColor(R.color.colorBlack))
                txtDiscountPrice.setTextColor(resources.getColor(R.color.theme_color))
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateValue() {
        try {
            if (txtOriginalPrice.text.toString().isEmpty() && txtDiscountPrice.text.toString().isEmpty()
                && txtOriginalPrice.text.toString() != "0" && txtDiscountPrice.text != "0"){
                Toast.makeText(this,"Please enter value",Toast.LENGTH_LONG).show()
            }else {
                val originalPrice = stringBuilderOriginal.toString().toFloat()
                val discountPrice = stringBuilderDiscount.toString().toFloat()
                val savePrice = originalPrice * discountPrice / 100
                val finalPrice = originalPrice - savePrice
                txtTotalPrice.text = finalPrice.toString()
                txtYouSave.text = savePrice.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setOriginalPrice(value: Int) {
        if (value >= 0) {
            if (isSelectOriginal) {
                stringBuilderOriginal.append(value)
                txtOriginalPrice.text = stringBuilderOriginal.toString()
            } else {
                stringBuilderDiscount.append(value)
                txtDiscountPrice.text = stringBuilderDiscount.toString()
            }
        } else if (value == -1) {
            if (isSelectOriginal) {
                stringBuilderOriginal.append(".")
                txtOriginalPrice.text = stringBuilderOriginal.toString()
            } else {
                stringBuilderDiscount.append(".")
                txtDiscountPrice.text = stringBuilderDiscount.toString()
            }
        } else if (value == -2) {
            if (isSelectOriginal) {
                if (stringBuilderOriginal.isNotEmpty()) {
                    stringBuilderOriginal.deleteCharAt(stringBuilderOriginal.length - 1)
                    txtOriginalPrice.text = stringBuilderOriginal.toString()
                } else {
                    txtOriginalPrice.text = "0"
                }
            } else {
                if (stringBuilderDiscount.isNotEmpty()) {
                    stringBuilderDiscount.deleteCharAt(stringBuilderDiscount.length - 1)
                    txtDiscountPrice.text = stringBuilderDiscount.toString()
                } else {
                    txtDiscountPrice.text = "0"
                }
            }
        } else if (value == -3) {
            isSelectOriginal = true
            txtOriginalPrice.setTextColor(resources.getColor(R.color.theme_color))
            txtDiscountPrice.setTextColor(resources.getColor(R.color.colorBlack))
            stringBuilderOriginal = StringBuilder()
            stringBuilderDiscount = StringBuilder()
            txtOriginalPrice.text = getString(R.string._0)
            txtDiscountPrice.text = getString(R.string._0)
            txtYouSave.text = getString(R.string.you_save_0)
            txtTotalPrice.text = getString(R.string._0)
        }
    }
}