package com.calculatorapp.emi.calculator.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.calculatorapp.emi.calculator.activity.DetailsScreenActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.data.PieData
import com.calculatorapp.emi.calculator.R
import java.util.ArrayList

class ChartFragment : Fragment() {

    private var view1: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.fragment_chart, container, false)
        initDefine()
        setPieChartData()
        return view1
    }

   private lateinit var pieChart: PieChart
   private lateinit var txtPercentagePrincipal: TextView
   private lateinit var txtPercentageIntrest: TextView
   private lateinit var txtPrincipalValue: TextView
   private lateinit var txtIntrestValue: TextView
   private lateinit var txtChartPKey: TextView
    fun initDefine() {
        txtPercentagePrincipal = view1!!.findViewById<View?>(R.id.txtPercentagePrincipal) as TextView
        txtPercentageIntrest = view1!!.findViewById<View?>(R.id.txtPercentageIntrest) as TextView
        txtPrincipalValue = view1!!.findViewById<View?>(R.id.txtPrincipalValue) as TextView
        txtIntrestValue = view1!!.findViewById<View?>(R.id.txtIntrestValue) as TextView
        txtChartPKey = view1!!.findViewById<View?>(R.id.txtChartPKey) as TextView
        pieChart = view1!!.findViewById<View?>(R.id.piechart) as PieChart
        pieChart.setUsePercentValues(true)
    }

    @SuppressLint("SetTextI18n")
    fun setPieChartData() {
        val yvalues = ArrayList<Entry?>()
        var a = 100f
        val b: Float = ((100 - DetailsScreenActivity.rate).toFloat())
        a -= b
        txtPercentagePrincipal.text = "$b%"
        txtPercentageIntrest.text = "$a%"
        txtIntrestValue.text = "₹ " + DetailsScreenActivity.intrest
        txtPrincipalValue.text = "₹ " + DetailsScreenActivity.principal
        yvalues.add(Entry(a, 0))
        yvalues.add(Entry(b, 1))
        val dataSet = PieDataSet(yvalues, "")
        dataSet.setDrawValues(false)
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        val xVals = ArrayList<String?>()
        xVals.add(requireContext().resources.getString(R.string.txtIntrestAmountHeader))
        xVals.add(requireContext().resources.getString(R.string.txtPrincipal))
        pieChart.setDescription("")
        pieChart.setDrawSliceText(true)
        val data = PieData(xVals, dataSet)
        pieChart.isDrawHoleEnabled = false
        pieChart.transparentCircleRadius = 30f
        pieChart.holeRadius = 30f
        data.setValueTextSize(13f)
        data.setValueTextColor(Color.DKGRAY)
        pieChart.data = data
    }
}