package com.calculatorapp.emi.calculator.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.calculatorapp.emi.calculator.R
import com.calculatorapp.emi.calculator.interfaces.ConfirmDialogCallBack
import com.calculatorapp.emi.calculator.utils.CommonUtilities
import com.calculatorapp.emi.calculator.utils.Utils
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.roundToInt


class MainActivity : BaseActivity(), View.OnClickListener {
    var llAdViewFacebook :LinearLayout?=null
    var llAdView :RelativeLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        context = this
        initDefine()
        initAction()


       Utils.loadBannerAd(llAdView!!,llAdViewFacebook!!,this)
    }



    @SuppressLint("WrongConstant")
    override fun onBackPressed() {
        fullView.closeDrawer(Gravity.START)
        CommonUtilities.confirmationDialog(this,object : ConfirmDialogCallBack {
            override fun Okay() {
                val homeIntent = Intent(Intent.ACTION_MAIN)
                homeIntent.addCategory(Intent.CATEGORY_HOME)
                homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(homeIntent)
            }

            override fun cancel() {

            }

        },"",getString(R.string.exit_confirmation))
    }
   private lateinit var edtLoanAmount: EditText
   private lateinit var edtIntrest: EditText
   private lateinit var edtYear: EditText
   private lateinit var edtMonth: EditText
   private lateinit var edtEMIAmount: EditText
   private lateinit var btnCalculate: Button
   private lateinit var btnReset: Button
   private lateinit var btnDetails: Button
   private lateinit var txtAppName: TextView
   private lateinit var txtDetailsAmount: TextView
   private lateinit var txtDetailsPeriod: TextView
   private lateinit var txtDetailsIntrestAmount: TextView
   private lateinit var txtDetailsTotalAmount: TextView
   private lateinit var txtLoanKey: TextView
   private lateinit var txtIntrestKey: TextView
   private lateinit var txtPeriodKey: TextView
   private lateinit var txtEMIAmountKey: TextView
   private lateinit var llOutput: LinearLayout
   private lateinit var btnSetting: ImageView
    fun initDefine() {
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        btnSetting = findViewById(R.id.btnSetting)
        edtLoanAmount = findViewById(R.id.edtLoanAmount)
        edtIntrest = findViewById(R.id.edtIntrest)
        edtYear = findViewById(R.id.edtYear)
        edtMonth = findViewById(R.id.edtMonth)
        edtEMIAmount = findViewById(R.id.edtEMIAmount)
        btnCalculate = findViewById(R.id.btnCalculate)
        btnReset = findViewById(R.id.btnReset)
        btnDetails = findViewById(R.id.btnDetails)
        txtLoanKey = findViewById(R.id.txtLoanKey)
        txtIntrestKey = findViewById(R.id.txtIntrestKey)
        txtPeriodKey = findViewById(R.id.txtPeriodKey)
        txtEMIAmountKey = findViewById(R.id.txtEMIAmountKey)
        txtAppName = findViewById(R.id.txtAppName)
        txtDetailsAmount = findViewById(R.id.txtDetailsAmount)
        txtDetailsPeriod = findViewById(R.id.txtDetailsPeriod)
        txtDetailsIntrestAmount = findViewById(R.id.txtDetailsIntrestAmount)
        txtDetailsTotalAmount = findViewById(R.id.txtDetailsTotalAmount)
        llOutput = findViewById(R.id.llOutput)
    }

    fun initAction() {
        btnCalculate.setOnClickListener(this)
        btnReset.setOnClickListener(this)
        btnDetails.setOnClickListener(this)
        btnSetting.setOnClickListener(this)
    }

    @SuppressLint("WrongConstant")
    override fun onClick(v: View?) {
        if (v === btnSetting) {
            fullView.openDrawer(Gravity.START)
        } else if (v === btnCalculate) {
            checkValidation()
        } else if (v === btnReset) {
            resetEditTextValue()
        } else if (v === btnDetails) {
            try {
                if (llOutput.visibility == View.VISIBLE) {
                    val intent = Intent(this@MainActivity, DetailsScreenActivity::class.java)
                    val b = m.toFloat().roundToInt()
                    val ti = tI.roundToInt()
    //                val r = edtIntrest.text.toString().toFloat().roundToInt()
                    val r = edtIntrest.text.toString().toDouble()
                    intent.putExtra("Principal", edtLoanAmount.text.toString().toInt())
                    intent.putExtra("RateOfIntrest", r)
                    intent.putExtra("EMI", DecimalFormat("##.##").format(eMI.toDouble()))
                    intent.putExtra("Month", b)
                    intent.putExtra("Intrest", ti)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "No EMI Calculation", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun checkValidation() {
        try {
            if (TextUtils.isEmpty(edtLoanAmount.text.toString())) {
                edtLoanAmount.error = "Enter Principal Amount"
                return
            }
            if (TextUtils.isEmpty(edtIntrest.text.toString())) {
                edtIntrest.error = "Enter Rate Of Interest"
                return
            } else if (edtIntrest.text.toString().toDouble() == 0.0) {
                edtIntrest.error = "Interest Must Be > 0"
                return
            }
            if (TextUtils.isEmpty(edtMonth.text.toString()) && TextUtils.isEmpty(
                    edtYear.text.toString()
                )
            ) {
                Toast.makeText(
                    context,
                    "Please enter at least one value either month or year",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            else {
                val yearValue = edtYear.text.toString()
                val monthValue = edtMonth.text.toString()
                if (yearValue != "" && monthValue != "") {
                    if (monthValue.toInt() <= 0) {
                        if (yearValue.toInt() <= 0) {
                            edtYear.error = "Year Must Be > 0"
                            return
                        }
                    }
                    if (yearValue.toInt() <= 0) {
                        if (monthValue.toInt() <= 0) {
                            edtMonth.error = "Month Must Be > 0"
                            return
                        }
                    }
                    calculateEMI()
                } else {
                    if (yearValue == "") {
                        if (monthValue.toInt() == 0) {
                            edtMonth.error = "Month Must Be > 0"
                            return
                        } else {
                            edtYear.setText("0")
                        }
                    } else {
                        if (yearValue.toInt() == 0) {
                            if (monthValue == "") {
                                edtYear.error = "Year Must Be > 0"
                            }
                            return
                        } else {
                            if (monthValue == "") {
                                edtMonth.setText("0")
                            }
                        }
                    }
                    calculateEMI()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

   private var eMI = 0f
   private var tA = 0f
   private var tI = 0f
   private var y = 0
   private var m = 0

    private fun calculateEMI() {
        edtLoanAmount.clearFocus()
        edtLoanAmount.error = null
        edtEMIAmount.clearFocus()
        edtEMIAmount.error = null
        edtIntrest.clearFocus()
        edtIntrest.error = null
        edtYear.clearFocus()
        edtYear.error = null
        edtMonth.clearFocus()
        edtMonth.error = null
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(btnCalculate.windowToken, 0)
        val p = edtLoanAmount.text.toString().toFloat()
        val i = edtIntrest.text.toString().toFloat()
        y = if (edtYear.text.toString() != "") {
            edtYear.text.toString().toInt()
        } else {
            0
        }
        m = calMonth(y) + edtMonth.text.toString().toInt()
        val principal = calPrincipal(p)
        val rate = calInterest(i)
        val upDivider = countUpDivider(rate, m.toFloat())
        val fD = multiplyUpperValue(principal, rate, upDivider)
        val d = calDivider(upDivider)
        eMI = calEMI(fD, d)
        tA = calTotalAmountWithIntrest(eMI, m)
        tI = calTotalIntrestAmount(tA, principal)
        setOutput(eMI, m.toFloat(), tI, tA)
    }

    private fun calPrincipal(p: Float): Float {
        return p
    }

    private fun calInterest(i: Float): Float {
        return i / 12 / 100
    }

    private fun calMonth(y: Int): Int {
        return y * 12
    }

    private fun countUpDivider(Rate: Float, Months: Float): Float {
        return (1 + Rate).toDouble().pow(Months.toDouble()).toFloat()
    }

    private fun multiplyUpperValue(Principal: Float, Rate: Float, Dvdnt: Float): Float {
        return Principal * Rate * Dvdnt
    }

    private fun calDivider(Dvdnt: Float): Float {
        return Dvdnt - 1
    }


    private fun calEMI(FD: Float, D: Float?): Float {
        return FD / D!!
    }

    private fun calTotalAmountWithIntrest(emi: Float, Months: Int): Float {
        return emi * Months
    }

    private fun calTotalIntrestAmount(TA: Float, Principal: Float): Float {
        return TA - Principal
    }

    @SuppressLint("SetTextI18n")
    fun setOutput(EMI: Float, M: Float, TI: Float, TA: Float) {

        edtEMIAmount.setText("" + DecimalFormat("##.##").format(EMI.toDouble()))
        txtDetailsAmount.text = "" + DecimalFormat("##.##").format(EMI.toDouble())
        txtDetailsPeriod.text = "$M Months"
        if (EMI.toDouble() == 0.0) {
            txtDetailsIntrestAmount.text = "0"
        } else {
            txtDetailsIntrestAmount.text = "" + DecimalFormat("##.##").format(TI.toDouble())
        }
        txtDetailsTotalAmount.text = "" + DecimalFormat("##.##").format(TA.toDouble())
        llOutput.visibility = View.VISIBLE
    }

    private fun resetEditTextValue() {
        edtLoanAmount.setText("")
        edtIntrest.setText("")
        edtYear.setText("")
        edtMonth.setText("")
        edtEMIAmount.setText("")
        txtDetailsAmount.text = ""
        txtDetailsPeriod.text = ""
        txtDetailsIntrestAmount.text = ""
        txtDetailsTotalAmount.text = ""
        llOutput.visibility = View.GONE
    }

    companion object {
        fun math(f: Float): Int {
            val c = (f + 0.5f).toInt()
            val n = f + 0.5f
            return if ((n - c) % 2 == 0f) f.toInt() else c
        }
    }

}