package com.calculatorapp.emi.calculator.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.calculatorapp.emi.calculator.interfaces.ConfirmDialogCallBack

object CommonUtilities {
    fun showToast(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
    fun confirmationDialog(
        content: Context,
        confirmCallBack: ConfirmDialogCallBack,
        strTitle: String,
        strMsg: String
    ): Boolean {

        val builder1 = AlertDialog.Builder(content)
        builder1.setTitle(strTitle)
        builder1.setMessage(strMsg)
        builder1.setCancelable(true)

        builder1.setPositiveButton("Yes") { dialog, _ ->
            dialog.cancel()
            confirmCallBack.Okay()
        }

        builder1.setNegativeButton("No") { dialog, _ ->
            dialog.cancel()
            confirmCallBack.cancel()
        }

        val alert11 = builder1.create()
        alert11.show()

        return false
    }
}