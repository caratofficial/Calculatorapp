package com.calculatorapp.emi.calculator.activity

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi

import com.calculatorapp.emi.calculator.R
import com.calculatorapp.emi.calculator.interfaces.CallbackListener
import com.calculatorapp.emi.calculator.utils.CommonConstants
import com.calculatorapp.emi.calculator.utils.Utils
import java.lang.Exception
import java.text.DecimalFormat
import javax.script.ScriptEngineManager


class BasicCalculatorActivity : BaseActivity(), View.OnClickListener, CallbackListener {
    private var button0: Button? = null
    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null
    private var button6: Button? = null
    private var button7: Button? = null
    private var button8: Button? = null
    private var button9: Button? = null
    private var btnEqual: Button? = null
    private var buttonAdd: Button? = null
    private var buttonSub: Button? = null
    private var buttonDivision: Button? = null
    private var buttonMul: Button? = null
    private var button10: Button? = null
    private var btnPercentage: Button? = null
    private var buttonC: Button? = null
    private var bitactroEditText: TextView? = null
    private var resultEditText: TextView? = null
    private var clear: ImageView? = null
    private var btnSetting: ImageView? = null
    private var str: String? = ""
    private var llAdViewFacebook: LinearLayout? = null
    private var llAdView: RelativeLayout? = null
    private var scrollEdt1: HorizontalScrollView? = null
    private var scrollEdt2: HorizontalScrollView? = null
    private var lastValue: String? = ""
    private var lastOperator: String? = ""

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_basic_calculato)

        successCall()
        btnPercentage = findViewById(R.id.btnPercentage)
        btnSetting = findViewById(R.id.btnSetting)
        llAdView = findViewById(R.id.llAdView)
        scrollEdt1 = findViewById(R.id.scrollEdt1)
        scrollEdt2 = findViewById(R.id.scrollEdt2)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        btnSetting!!.setOnClickListener(this)
        btnEqual = findViewById<View?>(R.id.btnEqual) as Button
        button0 = findViewById<View?>(R.id.button0) as Button
        button1 = findViewById<View?>(R.id.button1) as Button
        button2 = findViewById<View?>(R.id.button2) as Button
        button3 = findViewById<View?>(R.id.button3) as Button
        button4 = findViewById<View?>(R.id.button4) as Button
        button5 = findViewById<View?>(R.id.button5) as Button
        button6 = findViewById<View?>(R.id.button6) as Button
        button7 = findViewById<View?>(R.id.button7) as Button
        button8 = findViewById<View?>(R.id.button8) as Button
        button9 = findViewById<View?>(R.id.button9) as Button
        button10 = findViewById<View?>(R.id.button10) as Button
        buttonAdd = findViewById<View?>(R.id.buttonAdd) as Button
        buttonSub = findViewById<View?>(R.id.buttonSub) as Button
        buttonMul = findViewById<View?>(R.id.buttonMul) as Button
        buttonDivision = findViewById<View?>(R.id.buttonDivision) as Button
        buttonC = findViewById<View?>(R.id.buttonC) as Button
        clear = findViewById<View?>(R.id.cut) as ImageView
        bitactroEditText = findViewById<View?>(R.id.edt1) as TextView
        resultEditText = findViewById<View?>(R.id.edt2) as TextView
        Utils.loadBannerAd(llAdView!!, llAdViewFacebook!!, this)

        button1!!.setOnClickListener {
            try {
                scrollToEnd()
                str += '1'
                startStoreLastValue("1")
                val temp = str
                bitactroEditText!!.text = str
                str = handleOperatorSymbols(str)
                val ans = calculate(str)
                Log.e("TAG", "onCreate::::btn 1=>0  $ans")
                /*val b1 = BigDecimal(ans).stripTrailingZeros()
                    ans = b1.toString()
                    Log.e("TAG", "onCreate::::btn 1=>00  $ans")
                    val d = ans.toDouble()
                    val format = DecimalFormat("##")
                    ans = format.format(d)
                    Log.e("TAG", "onCreate::::btn 1=>000  $ans")*/
                resultEditText!!.text = DecimalFormat("##.##").format(ans.toDouble())
                str = temp

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        button2!!.setOnClickListener {
            try {
                scrollToEnd()
                str += "2"
                startStoreLastValue("2")
                val temp = str
                bitactroEditText!!.text = str
                str = handleOperatorSymbols(str)
                val ans = calculate(str)
                /*val b1 = BigDecimal(ans).stripTrailingZeros()
                    ans = b1.toString()
                    val d = ans.toDouble()
                    val format = DecimalFormat("0.######")
                    ans = format.format(d)*/
                resultEditText!!.text = DecimalFormat("##.##").format(ans.toDouble())
                str = temp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        button3!!.setOnClickListener {
            try {
                scrollToEnd()
                str += "3"
                startStoreLastValue("3")
                val temp = str
                bitactroEditText!!.text = str
                str = handleOperatorSymbols(str)
                var ans = calculate(str)
                /* val b1 = BigDecimal(ans).stripTrailingZeros()
                      ans = b1.toString()
                      val d = ans.toDouble()
                      val format = DecimalFormat("0.#######")
                      ans = format.format(d)*/
                resultEditText!!.text = DecimalFormat("##.##").format(ans.toDouble())
                str = temp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        button4!!.setOnClickListener {
            try {
                scrollToEnd()
                str += "4"
                startStoreLastValue("4")
                val temp = str
                bitactroEditText!!.text = str
                str = handleOperatorSymbols(str)
                var ans = calculate(str)
                /*val b1 = BigDecimal(ans).stripTrailingZeros()
                    ans = b1.toString()
                    val d = ans.toDouble()
                    val format = DecimalFormat("0.######")
                    ans = format.format(d)*/
                resultEditText!!.text = DecimalFormat("##.##").format(ans.toDouble())
                str = temp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        button5!!.setOnClickListener {
            try {
                scrollToEnd()
                str += '5'
                val temp = str
                startStoreLastValue("5")
                bitactroEditText!!.text = str
                str = handleOperatorSymbols(str)
                var ans = calculate(str)
                /*val b1 = BigDecimal(ans).stripTrailingZeros()
                    ans = b1.toString()
                    val d = ans.toDouble()
                    val format = DecimalFormat("0.######")
                    ans = format.format(d)*/
                resultEditText!!.text = DecimalFormat("##.##").format(ans.toDouble())
                str = temp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        button6!!.setOnClickListener {
            try {
                scrollToEnd()
                str += "6"
                startStoreLastValue("6")
                val temp = str
                bitactroEditText!!.text = str
                str = handleOperatorSymbols(str)
                var ans = calculate(str)
                /*val b1 = BigDecimal(ans).stripTrailingZeros()
                    ans = b1.toString()
                    val d = ans.toDouble()
                    val format = DecimalFormat("0.######")
                    ans = format.format(d)*/
                resultEditText!!.text = DecimalFormat("##.##").format(ans.toDouble())
                str = temp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        button7!!.setOnClickListener {
            try {
                scrollToEnd()
                str += "7"
                startStoreLastValue("7")
                val temp = str
                bitactroEditText!!.text = str
                str = handleOperatorSymbols(str)
                var ans = calculate(str)
                /*val b1 = BigDecimal(ans).stripTrailingZeros()
                    ans = b1.toString()
                    val d = ans.toDouble()
                    val format = DecimalFormat("0.######")
                    ans = format.format(d)*/
                resultEditText!!.text = DecimalFormat("##.##").format(ans.toDouble())
                str = temp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        button8!!.setOnClickListener {
            try {
                scrollToEnd()
                str += "8"
                startStoreLastValue("8")
                val temp = str
                bitactroEditText!!.text = str
                str = handleOperatorSymbols(str)
                var ans = calculate(str)
                /*val b1 = BigDecimal(ans).stripTrailingZeros()
                    ans = b1.toString()
                    val d = ans.toDouble()
                    val format = DecimalFormat("0.######")
                    ans = format.format(d)*/
                resultEditText!!.text = DecimalFormat("##.##").format(ans.toDouble())
                str = temp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        button9!!.setOnClickListener {
            try {
                scrollToEnd()
                str += "9"
                startStoreLastValue("9")
                val temp = str
                bitactroEditText!!.text = str
                str = handleOperatorSymbols(str)
                var ans = calculate(str)
                /*val b1 = BigDecimal(ans).stripTrailingZeros()
                    ans = b1.toString()
                    val d = ans.toDouble()
                    val format = DecimalFormat("0.######")
                    ans = format.format(d)*/
                resultEditText!!.text = DecimalFormat("##.##").format(ans.toDouble())
                str = temp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        buttonAdd!!.setOnClickListener {
            try {
                lastOperator = ""
                lastOperator = "+"
                scrollToEnd()
                lastValue = ""
                if (str!!.isEmpty()) {
                    bitactroEditText!!.text = "Bad Expression"
                } else if (validCheck(str)) {
                    bitactroEditText!!.text = "$str+"
                    str = "$str+"
                } else {
                    if (str!!.isNotEmpty()) {
                        str = str!!.substring(0, str!!.length - 1)
                        str = "$str+"
                        bitactroEditText!!.text = str
                    } else bitactroEditText!!.text = "Bad Expression"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        button0!!.setOnClickListener {
            try {
                scrollToEnd()
                str += '0'
                startStoreLastValue("0")
                val temp = str
                bitactroEditText!!.text = str
                str = handleOperatorSymbols(str) //handling divide symbol
                when {
                    str!!.length < 2 -> {
                    }
                    str!![str!!.length - 2] == '/' -> {
                    }
                    else -> {
                        str = handleOperatorSymbols(str)
                        val ans = calculate(str)
                        resultEditText!!.text = DecimalFormat("##.##").format(ans.toDouble())
                    }
                }
                str = temp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        buttonSub!!.setOnClickListener {
            try {
                lastOperator = ""
                lastOperator = "-"
                scrollToEnd()
                lastValue = ""
                if (str!!.isEmpty()) {
                    bitactroEditText!!.text = "$str-"
                    str += '-'
                } else if (str!!.length > 1 && str!![str!!.length - 1] == '-') {
                } else if (validCheck(str)) {
                    bitactroEditText!!.text = bitactroEditText!!.text.toString() + "-"
                    str = "$str-"
                } else {
                    val len = bitactroEditText!!.text.length
                    if (len > 0) {
                        if (str!!.length > 1 && str!![str!!.length - 1] == '+') str =
                            str!!.substring(0, str!!.length - 1)
                        str = "$str-"
                        bitactroEditText!!.text = str
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        buttonMul!!.setOnClickListener {
            try {
                lastOperator = ""
                lastOperator = "x"
                scrollToEnd()
                lastValue = ""
                if (str!!.isEmpty()) {
                    bitactroEditText!!.text = "Bad Expression"
                } else if (validCheck(str)) {
                    bitactroEditText!!.text = bitactroEditText!!.text.toString() + "x"
                    str += 'x'
                } else {
                    val len = bitactroEditText!!.text.length
                    if (len > 0) {
                        str = str!!.substring(0, str!!.length - 1)
                        str += 'x'
                        bitactroEditText!!.text = str
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        buttonDivision!!.setOnClickListener {
            try {
                lastOperator = ""
                lastOperator = "÷"
                scrollToEnd()
                lastValue = ""
                if (str!!.isEmpty()) {
                    bitactroEditText!!.text = "Bad Expression"
                } else if (validCheck(str)) {
                    bitactroEditText!!.text = bitactroEditText!!.text.toString() + "÷"
                    str = "$str÷"
                } else {
                    val len = bitactroEditText!!.text.length
                    if (len > 0) {
                        str = str!!.substring(0, str!!.length - 1)
                        str = "$str÷"
                        bitactroEditText!!.text = str
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        buttonC!!.setOnClickListener {
            try {
                scrollToEnd()
                lastValue = ""
                lastOperator = ""
                str = ""
                bitactroEditText!!.text = str
                resultEditText!!.text = str
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        button10!!.setOnClickListener {
            try {
                scrollToEnd()
                if (str!!.isNotEmpty()) if (validCheck(str)) {
                    str = "$str."
                    startStoreLastValue(".")
                }
                bitactroEditText!!.text = str
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        clear!!.setOnClickListener {
            try {
                scrollToEnd()
                lastValue = ""
                if (str!!.isNotEmpty()) str = str!!.substring(0, str!!.length - 1)
                bitactroEditText!!.text = str
                var ans: String? = ""
                if (str!!.isNotEmpty()) {
                    val temp = str
                    str = handleOperatorSymbols(str)
                    ans = calculate(str)
                    str = temp
                }
//                resultEditText!!.text = ans
                resultEditText!!.text = DecimalFormat("##.##").format(ans!!.toDouble())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        btnEqual!!.setOnClickListener {
            try {
                if (str != "") {
                    bitactroEditText!!.text =
                        DecimalFormat("##").format(resultEditText!!.text.toString().toDouble())
                    lastValue = bitactroEditText!!.text.toString()
                    str = DecimalFormat("##").format(resultEditText!!.text.toString().toDouble())
                    resultEditText!!.text = ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        btnPercentage!!.setOnClickListener {
            try {
                scrollToEnd()
                if (lastOperator == "÷" || lastOperator == "x") {
                    val result = DecimalFormat("##.##").format(lastValue!!.toDouble() / 100)
                    val replaceWord = "$lastOperator$lastValue"
                    if (bitactroEditText!!.text!!.contains(replaceWord)) {
                        val str = str!!.replace(replaceWord, "$lastOperator${result.toString()}")
                        Log.e(
                            "TAG",
                            "onCreate::::btnPercentage==>before IFF $result  $replaceWord $str"
                        )
                        bitactroEditText!!.text = str
                        this.str = str
                        val handle = handleOperatorSymbols(str)
                        val answer = calculate(handle)
//                        resultEditText!!.text = answer
                        resultEditText!!.text = DecimalFormat("##.##").format(answer.toDouble())
                        Log.e(
                            "TAG",
                            "onCreate::::btnPercentage==>after IFF $result  $replaceWord $str"
                        )
                    }

                } else if (lastOperator == "-" || lastOperator == "+") {
                    val replaceWord = "$lastOperator$lastValue"
                    val finalValue: Double = if (lastOperator == "-") {
                        resultEditText!!.text.toString().toDouble() + lastValue!!.toDouble()
                    } else {
                        resultEditText!!.text.toString().toDouble() - lastValue!!.toDouble()
                    }

                    var resultPercentageValue: Float = if (lastOperator == "-") {
                        finalValue.toFloat() * lastValue!!.toFloat() / 100
                    } else {
                        finalValue.toFloat() * lastValue!!.toFloat() / 100
                    }
                    resultPercentageValue =
                        DecimalFormat("##.##").format(resultPercentageValue).toFloat()
                    if (bitactroEditText!!.text!!.contains(replaceWord)) {
                        val str = str!!.replace(
                            replaceWord,
                            "$lastOperator${resultPercentageValue.toString()}"
                        )
                        Log.e(
                            "TAG",
                            "onCreate::::btnPercentage==>before ELSE $resultPercentageValue  $replaceWord $str"
                        )
                        bitactroEditText!!.text = str
                        this.str = str
                        val handle = handleOperatorSymbols(str)
                        val answer = calculate(handle)
//                        resultEditText!!.text = answer
                        resultEditText!!.text = DecimalFormat("##.##").format(answer.toDouble())
                        Log.e(
                            "TAG",
                            "onCreate::::btnPercentage==>after ELSE $resultPercentageValue  $replaceWord $str"
                        )
                    }
                }
                Log.e("TAG", "onCreate::::lastValue:: $lastValue  $lastOperator  $str")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun startStoreLastValue(value: String) {
        lastValue += value
    }


    private fun successCall() {
        if (Utils.isNetworkConnected(this)) {
            if (CommonConstants.ENABLE_DISABLE == CommonConstants.ENABLE) {
                Utils.setPref(
                    this,
                    CommonConstants.AD_TYPE_FB_GOOGLE,
                    CommonConstants.AD_TYPE_FACEBOOK_GOOGLE
                )
                Utils.setPref(
                    this,
                    CommonConstants.FB_BANNER,
                    CommonConstants.FB_BANNER_ID
                )
                Utils.setPref(
                    this,
                    CommonConstants.FB_INTERSTITIAL,
                    CommonConstants.FB_INTERSTITIAL_ID
                )
                Utils.setPref(
                    this,
                    CommonConstants.GOOGLE_BANNER,
                    CommonConstants.GOOGLE_BANNER_ID
                )
                Utils.setPref(
                    this,
                    CommonConstants.GOOGLE_INTERSTITIAL,
                    CommonConstants.GOOGLE_INTERSTITIAL_ID
                )
                Utils.setPref(
                    this,
                    CommonConstants.STATUS_ENABLE_DISABLE,
                    CommonConstants.ENABLE_DISABLE
                )
                setAppAdId()
            } else {
                Utils.setPref(
                    this,
                    CommonConstants.STATUS_ENABLE_DISABLE,
                    CommonConstants.ENABLE_DISABLE
                )
            }
        } else {
            Utils.openInternetDialog(this, this, true)
        }
    }


    private fun setAppAdId() {
        try {
            val applicationInfo =
                packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            val bundle = applicationInfo.metaData
            val beforeChangeId = bundle.getString("com.google.android.gms.ads.APPLICATION_ID")
            Log.e("TAG", "setAppAdId:BeforeChange:::::  $beforeChangeId")
            applicationInfo.metaData.putString(
                "com.google.android.gms.ads.APPLICATION_ID",
                CommonConstants.GOOGLE_ADMOB_APP_ID
            )
            val afterChangeId = bundle.getString("com.google.android.gms.ads.APPLICATION_ID")
            Log.e("TAG", "setAppAdId:AfterChange::::  $afterChangeId")
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    private fun validCheck(S: String?): Boolean {
        var S = S
        val temp = S
        S = handleOperatorSymbols(S)
        if (S == null) {
            str = temp
            return false
        } else if (S[S.length - 1] == '.') {
            str = temp
            return false
        } else if (S[S.length - 1] == '/' || S[S.length - 1] == '*' || S[S.length - 1] == '+' || S[S.length - 1] == '-') {
            str = temp
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun calculate(S: String?): String {
        var S = S
        var res = ""
        var len = S!!.length - 1
        var count = 0
        while (len >= 0) {
            if (S!![len] == '+' || S[len] == '/' || S[len] == '*' || S[len] == '*') {
                if (S[len] == '/') {
                    if (S[S.length - 1] != '/') {
                        val st = S.substring(len + 1, S.length)
                        val d = st.toDouble()
                        if (d == 0.0) {
                            S = S.substring(0, len)
                        }
                    }
                }
                break
            }
            count++
            len--
        }
        val c = S!![S.length - 1]
        val exp: String = if (c == '+' || c == '-' || c == '*' || c == '/') {
            S.substring(0, S.length - 1)
        } else S
        val engine = ScriptEngineManager().getEngineByName("rhino")
        var result: Any? = null
        try {
            result = engine.eval(exp)
            res = result.toString()
        } catch (e: Exception) {
            res = "Error"
        }

        return res
    }

    private fun scrollToEnd() {

        scrollEdt1!!.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
        scrollEdt2!!.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
    }

    private fun handleOperatorSymbols(S: String?): String {
        var res = ""
        for (i in S!!.indices) {
            when {
                S[i] == 'x' -> {
                    res += "*"
                }
                S[i] == '÷' -> {
                    res += "/"
                }
                else -> {
                    res += S[i]
                }
            }
        }
        return res
    }

    @SuppressLint("WrongConstant")
    override fun onClick(view: View?) {
        if (view === btnSetting) {
            fullView.openDrawer(Gravity.START)
        }
    }


    override fun onSuccess() {

    }

    override fun onCancel() {
    }

    override fun onRetry() {
    }
}