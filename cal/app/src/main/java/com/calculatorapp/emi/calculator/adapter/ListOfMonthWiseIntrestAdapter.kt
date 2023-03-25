package com.calculatorapp.emi.calculator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.calculatorapp.emi.calculator.adapter.ListOfMonthWiseIntrestAdapter.Viewholder
import androidx.recyclerview.widget.RecyclerView
import com.calculatorapp.emi.calculator.R
import java.util.ArrayList
import java.util.HashMap
import kotlin.math.abs

class ListOfMonthWiseIntrestAdapter(
    var context: Context?,
    private var arrItem: ArrayList<HashMap<String?, String?>?>?
) : RecyclerView.Adapter<Viewholder>() {
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_of_intrest_amount, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item: HashMap<String?, String?>? = arrItem!![position]
        holder.txtMonthNo!!.text = item!!["NoOfMonth"]
        var p = item["Principal"]!!.toFloat()
        var i = item["Intrest"]!!.toFloat()
        var b = item["Balance"]!!.toFloat()
        p = abs(p)
        i = abs(i)
        b = abs(b)
        holder.txtPrincipal!!.text = String.format("%.0f", p)
        holder.txtIntrestAmount!!.text = String.format("%.0f", i)
        holder.txtBalance!!.text = String.format("%.0f", b)
        if (position == arrItem!!.size - 1) {
            holder.view!!.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return arrItem!!.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtMonthNo: TextView? = itemView.findViewById(R.id.txtNoMonth)
        var txtPrincipal: TextView? = itemView.findViewById(R.id.txtPrincipal)
        var txtIntrestAmount: TextView? = itemView.findViewById(R.id.txtIntrestAmount)
        var txtBalance: TextView? = itemView.findViewById(R.id.txtBalance)
        var view: View? = itemView.findViewById(R.id.viewLine)

    }

}