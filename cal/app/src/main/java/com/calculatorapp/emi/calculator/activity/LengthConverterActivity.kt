package com.calculatorapp.emi.calculator.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*

import com.calculatorapp.emi.calculator.R
import com.calculatorapp.emi.calculator.utils.*
import java.text.DecimalFormat

class LengthConverterActivity : BaseActivity(), View.OnClickListener {
   private lateinit var btnSetting: ImageView
   private lateinit var e1: EditText
   private lateinit var e2: EditText
   private lateinit var s1: Spinner
   private lateinit var s2: Spinner
   private var count1 = 0
   private lateinit var ca: ConvertingUnits.Length
   private var llAdViewFacebook : LinearLayout?=null
   private var llAdView : RelativeLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_length_converter)
        supportActionBar!!.hide()
        context = this
        initDefine()
        initAction()
        Utils.loadBannerAd(llAdView!!,llAdViewFacebook!!,this)
    }

    fun initDefine() {
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        btnSetting = findViewById(R.id.btnSetting)
        e1 = findViewById<View?>(R.id.item1) as EditText
        e2 = findViewById<View?>(R.id.item2) as EditText
        s1 = findViewById<View?>(R.id.spinner1) as Spinner
        s2 = findViewById<View?>(R.id.spinner2) as Spinner
        ca = ConvertingUnits.Length()
    }

    fun initAction() {
        btnSetting.setOnClickListener(this)
    }

    @SuppressLint("WrongConstant", "SetTextI18n")
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.num0 -> e1.setText(e1.text.toString() + "0")
            R.id.num1 -> e1.setText(e1.text.toString() + "1")
            R.id.num2 -> e1.setText(e1.text.toString() + "2")
            R.id.num3 -> e1.setText(e1.text.toString() + "3")
            R.id.num4 -> e1.setText(e1.text.toString() + "4")
            R.id.num5 -> e1.setText(e1.text.toString() + "5")
            R.id.num6 -> e1.setText(e1.text.toString() + "6")
            R.id.num7 -> e1.setText(e1.text.toString() + "7")
            R.id.num8 -> e1.setText(e1.text.toString() + "8")
            R.id.num9 -> e1.setText(e1.text.toString() + "9")
            R.id.dot -> if (count1 == 0) {
                e1.setText(e1.text.toString() + ".")
                count1++
            }
            R.id.clear -> {
                e1.setText("")
                e2.setText("")
                count1 = 0
            }
            R.id.backSpace -> if (e1.length() != 0) {
                val text = e1.text.toString()
                if (text.endsWith(".")) count1 = 0
                val newText = text.substring(0, text.length - 1)
                e1.setText(newText)
            }
            R.id.equal -> {
                val item1 = s1.selectedItemPosition
                val item2 = s2.selectedItemPosition
                val value1 = e1.text.toString().toDouble()
                val result = evaluate(item1, item2, value1)
                try {
                    e2.setText(DecimalFormat("##.###").format(result))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            R.id.btnSetting -> fullView.openDrawer(Gravity.START)
        }
    }

    private fun evaluate(item1: Int, item2: Int, value: Double): Double {
        var temp = 0.0
        return if (item1 == item2) value else {
            when (item1) {
                0 -> temp = ca.NanoToMeter(value)
                1 -> temp = ca.milliToMeter(value)
                2 -> temp = ca.CentiToMeter(value)
                3 -> temp = value
                4 -> temp = ca.KiloToMeter(value)
                5 -> temp = ca.InchToMeter(value)
                6 -> temp = ca.FootToMeter(value)
                7 -> temp = ca.YardToMeter(value)
                8 -> temp = ca.MileToMeter(value)
            }
            when (item2) {
                0 -> temp = ca.MeterToNano(temp)
                1 -> temp = ca.meterToMilli(temp)
                2 -> temp = ca.MeterToCenti(temp)
                4 -> temp = ca.MeterToKilo(temp)
                5 -> temp = ca.MeterToInch(temp)
                6 -> temp = ca.MeterToFoot(temp)
                7 -> temp = ca.MeterToYard(temp)
                8 -> temp = ca.MeterToMile(temp)
            }
            temp
        }
    }
}