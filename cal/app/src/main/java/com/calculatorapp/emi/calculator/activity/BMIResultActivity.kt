package com.calculatorapp.emi.calculator.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.calculatorapp.emi.calculator.R

class BMIResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var containerL: LinearLayout
    private lateinit var bmiValueTV: TextView
    private lateinit var bmiLabelTV: TextView
    private lateinit var commentTV: TextView
    private lateinit var advice1TV: TextView
    private lateinit var advice2TV: TextView
    private lateinit var advice3TV: TextView
    private lateinit var skipResultBTN: ImageView
    private lateinit var bmiFlagImgView: ImageView
    private lateinit var advice1IMG: ImageView
    private lateinit var advice2IMG: ImageView
    private lateinit var advice3IMG: ImageView
    private lateinit var context: Context
    private var llAdViewFacebook :LinearLayout?=null
    private var llAdView : RelativeLayout?=null
    private var bmi = 0.0

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.statusBarColor = R.color.colorBlack
        setContentView(R.layout.activity_bmi_result)
        supportActionBar!!.hide()
        context = this
        initDefine()
        initAction()
        bmi = intent.getStringExtra("bmi")!!.toDouble()
        setIntentData()
        com.calculatorapp.emi.calculator.utils.Utils.loadBannerAd(llAdView!!,llAdViewFacebook!!,this)
    }

    @SuppressLint("SetTextI18n")
    private fun setIntentData() {
        if (bmi == -1.0) {
            containerL.visibility = View.GONE
        } else {
            bmiValueTV.text = bmi.toString()
            if (bmi < 18.5) {
                containerL.setBackgroundResource(R.color.colorYellow)
                bmiFlagImgView.setImageResource(R.drawable.exclamationmark)
                bmiLabelTV.text = "You have an UnderWeight!"
                commentTV.text = "Here are some advices To help you increase your weight"
                advice1IMG.setImageResource(R.drawable.nowater)
                advice1TV.text = "Don't drink water before meals"
                advice2IMG.setImageResource(R.drawable.bigmeal)
                advice2TV.text = "Use bigger plates"
                advice3TV.text = "Get quality sleep"
            } else {
                if (bmi > 25) {
                    containerL.setBackgroundResource(R.color.colorRed)
                    bmiFlagImgView.setImageResource(R.drawable.warning)
                    bmiLabelTV.text = "You have an OverWeight!"
                    commentTV.text = "Here are some advices To help you decrease your weight"
                    advice1IMG.setImageResource(R.drawable.water)
                    advice1TV.text = "Drink water a half hour before meals"
                    advice2IMG.setImageResource(R.drawable.twoeggs)
                    advice2TV.text = "Eat only two meals per day and make sure that they contains a high protein"
                    advice3IMG.setImageResource(R.drawable.nosugar)
                    advice3TV.text = "Drink coffee or tea and Avoid sugary food"
                }
            }
        }
    }

    private fun initAction() {
        skipResultBTN.setOnClickListener(this)
    }

    private fun initDefine() {
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        containerL = findViewById(R.id.containerL)
        bmiValueTV = findViewById(R.id.bmiValueTV)
        bmiLabelTV = findViewById(R.id.bmiLabelTV)
        commentTV = findViewById(R.id.commentTV)
        advice1TV = findViewById(R.id.advice1TV)
        advice2TV = findViewById(R.id.advice2TV)
        advice3TV = findViewById(R.id.advice3TV)
        skipResultBTN = findViewById(R.id.skipResultBTN)
        bmiFlagImgView = findViewById(R.id.bmiFlagImgView)
        advice1IMG = findViewById(R.id.advice1IMG)
        advice2IMG = findViewById(R.id.advice2IMG)
        advice3IMG = findViewById(R.id.advice3IMG)
    }

    override fun onClick(view: View?) {
        if (view === skipResultBTN) {
            val intent1 = Intent(applicationContext, BMICalculatorActivity::class.java)
            intent1.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent1)
            finish()
        }
    }
}