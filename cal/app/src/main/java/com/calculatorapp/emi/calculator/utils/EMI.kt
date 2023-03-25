package com.calculatorapp.emi.calculator.utils


import java.util.ArrayList
import java.util.HashMap
import kotlin.math.pow


object EMI {
    private fun calcEmi(p: Double, r: Double, n: Double): Double {
        val R = r / 12 / 100
        return p * R * (1 + R).pow(n) / ((1 + R).pow(n) - 1)
    }

    fun calcEmiAllMonths(p: Double, r: Double, n: Double): ArrayList<HashMap<String?, String?>?> {
        val R = r / 12 / 100
        var P = p
        val e: Double = calcEmi(P, r, n)
        var intPerMonth: Double
        val item = ArrayList<HashMap<String?, String?>?>()
        var hashMapItem: HashMap<String?, String?>
        var i = 1
        while (i <= n) {
            hashMapItem = HashMap()
            intPerMonth = P * R
            P -= (e - intPerMonth)
            hashMapItem["NoOfMonth"] = "" + i
            hashMapItem["Principal"] = "" + (e - intPerMonth)
            hashMapItem["Intrest"] = "" + intPerMonth
            hashMapItem["Balance"] = "" + P
            item.add(hashMapItem)
            i++
        }
        return item
    }
}