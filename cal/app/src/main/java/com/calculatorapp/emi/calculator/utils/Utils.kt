package com.calculatorapp.emi.calculator.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.calculatorapp.emi.calculator.interfaces.CallbackListener


object Utils {

    fun setPref(context: Context?, key: String?, value: String?) {
        val editor =
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value)
        editor.apply()
    }

    fun getPref(context: Context?, key: String?, value: String?): String? {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, value)
    }


    fun setPref(context: Context?, key: String?, value: Int?) {
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(
            key,
            value!!
        )
        editor.apply()
    }

    fun getPref(context: Context?, key: String?, value: Int?): Int {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, value!!)
    }

    fun loadBannerAd(llAdView: RelativeLayout, llAdViewFacebook: LinearLayout, context: Context){
        if (getPref(context, CommonConstants.AD_TYPE_FB_GOOGLE, "")
                .equals(CommonConstants.AD_GOOGLE) &&
            getPref(context, CommonConstants.STATUS_ENABLE_DISABLE, "")
                .equals(CommonConstants.ENABLE)
        ) {
            CommonConstantAd.loadBannerGoogleAd(context, llAdView)
            llAdViewFacebook.visibility = View.GONE
            llAdView.visibility = View.VISIBLE
        } else if (getPref(context, CommonConstants.AD_TYPE_FB_GOOGLE, "")
                .equals(CommonConstants.AD_FACEBOOK)
            &&
            getPref(context, CommonConstants.STATUS_ENABLE_DISABLE, "")
                .equals(CommonConstants.ENABLE)
        ) {
            llAdViewFacebook.visibility = View.VISIBLE
            llAdView.visibility = View.GONE
            CommonConstantAd.loadFacebookBannerAd(context, llAdViewFacebook)
        } else {
            llAdView.visibility = View.GONE
            llAdViewFacebook.visibility = View.GONE
        }
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }


    fun openInternetDialog(
        context: Context,
        callbackListener: CallbackListener,
        isSplash: Boolean?
    ) {
        if (!isNetworkConnected(context)) {
            val builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("No internet Connection")
            builder.setCancelable(false)
            builder.setMessage("Please turn on internet connection to continue")
            builder.setNegativeButton(
                "Retry"
            ) { dialog, _ ->
                if (!isSplash!!) {
                    openInternetDialog(context, callbackListener, false)
                }
                dialog.dismiss()
                callbackListener.onRetry()
            }
            builder.setPositiveButton(
                "Close"
            ) { dialog, which ->
                dialog.dismiss()
                val homeIntent = Intent(Intent.ACTION_MAIN)
                homeIntent.addCategory(Intent.CATEGORY_HOME)
                homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                context.startActivity(homeIntent)
                (context as Activity).finishAffinity()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }


}