package com.calculatorapp.emi.calculator.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.calculatorapp.emi.calculator.R
import java.lang.NumberFormatException
import java.lang.RuntimeException
import kotlin.math.*

class AdvanceCalculatorActivity : BaseActivity(), View.OnClickListener {
    private var btnSetting: ImageView? = null
    private var b1: Button? = null
    private var b2: Button? = null
    private var b3: Button? = null
    private var b4: Button? = null
    private var b5: Button? = null
    private var b6: Button? = null
    private var b7: Button? = null
    private var b8: Button? = null
    private var b9: Button? = null
    private var b0: Button? = null
    private var bdot: Button? = null
    private var bpi: Button? = null
    private var bequal: Button? = null
    private var bplus: Button? = null
    private var bdiv: Button? = null
    private var bmul: Button? = null
    private var bminus: Button? = null
    private var blog: Button? = null
    private var bac: Button? = null
    private var bc: Button? = null
    private var bb1: Button? = null
    private var bb2: Button? = null
    private var bln: Button? = null
    private var bsin: Button? = null
    private var bcos: Button? = null
    private var btan: Button? = null
    private var binv: Button? = null
    private var bsqrt: Button? = null
    private var bsquare: Button? = null
    private var bfact: Button? = null
    private var tvsec: TextView? = null
    private var tvmain: TextView? = null
    private var pi = "3.14159265"
    private var llAdViewFacebook: LinearLayout? = null
    private var llAdView: RelativeLayout? = null
    private var scrollEdt1 : HorizontalScrollView?=null
    private var scrollEdt2 : HorizontalScrollView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advance_calculator)
        supportActionBar!!.hide()
        context = this
        initDefine()
        initAction()
        com.calculatorapp.emi.calculator.utils.Utils.loadBannerAd(
            llAdView!!,
            llAdViewFacebook!!,
            this
        )
    }

    private fun scrollToEnd(){
        scrollEdt1!!.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
        scrollEdt2!!.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
    }

    fun initDefine() {
        scrollEdt1 = findViewById(R.id.scrollEdt1)
        scrollEdt2 = findViewById(R.id.scrollEdt2)
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        btnSetting = findViewById(R.id.btnSetting)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
        b4 = findViewById(R.id.b4)
        b5 = findViewById(R.id.b5)
        b6 = findViewById(R.id.b6)
        b7 = findViewById(R.id.b7)
        b8 = findViewById(R.id.b8)
        b9 = findViewById(R.id.b9)
        b0 = findViewById(R.id.b0)
        bac = findViewById(R.id.bac)
        bc = findViewById(R.id.bc)
        bb1 = findViewById(R.id.bb1)
        bb2 = findViewById(R.id.bb2)
        bpi = findViewById(R.id.bpi)
        bdot = findViewById(R.id.bdot)
        bequal = findViewById(R.id.bequal)
        bplus = findViewById(R.id.bplus)
        bminus = findViewById(R.id.bminus)
        bmul = findViewById(R.id.bmul)
        bdiv = findViewById(R.id.bdiv)
        binv = findViewById(R.id.binv)
        bsqrt = findViewById(R.id.bsqrt)
        bsquare = findViewById(R.id.bsquare)
        bfact = findViewById(R.id.bfact)
        bln = findViewById(R.id.bln)
        bsin = findViewById(R.id.bsin)
        bcos = findViewById(R.id.bcos)
        btan = findViewById(R.id.btan)
        blog = findViewById(R.id.blog)

        tvmain = findViewById(R.id.tvmain)
        tvsec = findViewById(R.id.tvsec)
    }

    @SuppressLint("SetTextI18n")
    fun initAction() {
        btnSetting!!.setOnClickListener(this)


        b1!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "1"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        b2!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "2"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        b3!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "3"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        b4!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "4"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        b5!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "5"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        b6!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "6"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        b7!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "7"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        b8!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "8"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        b9!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "9"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        b0!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "0"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        bdot!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "."
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bac!!.setOnClickListener {
            try {
                tvmain!!.text = ""
                tvsec!!.text = ""
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bc!!.setOnClickListener {
            try {
                var `val` = tvmain!!.text.toString()
                `val` = `val`.substring(0, `val`.length - 1)
                tvmain!!.text = `val`
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bplus!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "+"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bminus!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "-"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bdiv!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "÷"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bmul!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "×"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bsqrt!!.setOnClickListener {
            try {
                val `val` = tvmain!!.text.toString()
                val r = sqrt(`val`.toDouble())
                tvmain!!.text = r.toString()
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        bb1!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "("
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bb2!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + ")"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bpi!!.setOnClickListener {
            try {
                tvsec!!.text = bpi!!.text
                tvmain!!.text = tvmain!!.text.toString() + pi
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bsin!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "sin"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bcos!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "cos"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        btan!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "tan"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binv!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "^" + "(-1)"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bfact!!.setOnClickListener {
            try {
                val `val` = tvmain!!.text.toString().toInt()
                val fact: Int = factorial(`val`)
                tvmain!!.text = fact.toString()
                tvsec!!.text = "$`val`!"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bsquare!!.setOnClickListener {
            try {
                val d = tvmain!!.text.toString().toDouble()
                val square = d * d
                tvmain!!.text = square.toString()
                tvsec!!.text = "$d²"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bln!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "ln"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        blog!!.setOnClickListener {
            try {
                tvmain!!.text = tvmain!!.text.toString() + "log"
                scrollToEnd()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bequal!!.setOnClickListener {
            try {
                val `val` = tvmain!!.text.toString()
                val replacedstr = `val`.replace('÷', '/').replace('×', '*')
                val result: Double = eval(replacedstr)
                /*tvmain!!.text = result.toString()
                tvsec!!.text = `val`*/

                tvsec!!.text = result.toString()
                tvmain!!.text = `val`
                scrollToEnd()
            } catch (n: NumberFormatException) {
                n.printStackTrace()
                Toast.makeText(this, "" + n.message, Toast.LENGTH_LONG).show()
            } catch (r: RuntimeException) {
                r.printStackTrace()
                Toast.makeText(this, "" + r.message, Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("WrongConstant")
    override fun onClick(view: View?) {
        if (view === btnSetting) {
            fullView.openDrawer(Gravity.START)
        }
    }

    private fun factorial(n: Int): Int {
        return if (n == 1 || n == 0) 1 else n * factorial(n - 1)
    }

    private fun eval(str: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) str[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }


            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.code) -> x += parseTerm()
                        eat('-'.code) -> x -= parseTerm()
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('*'.code) -> x *= parseTerm()
                        eat('/'.code) -> x /= parseTerm()
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor()
                if (eat('-'.code)) return -parseFactor()
                var x: Double
                val startPos = pos
                if (eat('('.code)) {
                    x = parseExpression()
                    eat(')'.code)
                } else if (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) {
                    while (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) nextChar()
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch >= 'a'.code && ch <= 'z'.code || ch == '.'.code) {
                    while (ch >= 'a'.code && ch <= 'z'.code || ch == '.'.code) nextChar()
                    val func = str.substring(startPos, pos)
                    x = parseFactor()
                    x =
                        when (func) {
                            "sqrt" -> sqrt(x)
                            "sin" -> sin(
                                Math.toRadians(
                                    x
                                )
                            )
                            "cos" -> cos(
                                Math.toRadians(x)
                            )
                            "tan" -> tan(Math.toRadians(x))
                            "log" -> log10(
                                x
                            )
                            "ln" -> ln(x)
                            else -> throw RuntimeException(
                                "Unknown function: $func"
                            )
                        }
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                if (eat('^'.code)) x = x.pow(parseFactor())
                return x
            }
        }.parse()
    }

}