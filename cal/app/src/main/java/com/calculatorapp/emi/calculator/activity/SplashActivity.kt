package com.calculatorapp.emi.calculator.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

import com.calculatorapp.emi.calculator.R
import com.calculatorapp.emi.calculator.interfaces.AdsCallback
import com.calculatorapp.emi.calculator.interfaces.CallbackListener
import com.calculatorapp.emi.calculator.utils.CommonConstantAd
import com.calculatorapp.emi.calculator.utils.CommonConstants
import com.calculatorapp.emi.calculator.utils.Utils

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), AdsCallback , CallbackListener {

    private var isLoaded = false
    private val handler = Handler(Looper.myLooper()!!)
    private val myRunnable = Runnable {
        if (Utils.isNetworkConnected(this@SplashActivity)) {
            if (!isLoaded) {
                startNextActivity(0)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            window.requestFeature(Window.FEATURE_NO_TITLE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        setContentView(R.layout.activity_splash)
        callApi()
    }

    private fun callApi() {
        if (Utils.isNetworkConnected(this)) {
            successCall()
        } else {
            Utils.openInternetDialog(this, this, true)
        }
        handler.postDelayed(myRunnable, 10000)
    }

    private fun startNextActivity(time: Long) {
        try {
            Thread {
                kotlin.run {
                    synchronized(this) {
                        Thread.sleep(time)
                        runOnUiThread {
//                            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                            val mainIntent = Intent(this@SplashActivity, BasicCalculatorActivity::class.java)
                            startActivity(mainIntent)
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                            finish()
                        }
                    }
                }
            }.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onSuccess() {

    }

    override fun onCancel() {

    }

    override fun onRetry() {
        callApi()
    }


    private fun successCall() {
        if (Utils.getPref(this, CommonConstants.SPLASH_SCREEN_COUNT, 1) == 1) {
            Utils.setPref(this, CommonConstants.SPLASH_SCREEN_COUNT, 2)
            startNextActivity(1000)
        } else {
            checkAd()
        }
    }

    private fun checkAd() {
        if (Utils.getPref(this, CommonConstants.STATUS_ENABLE_DISABLE, "")
                .equals(CommonConstants.ENABLE)
        ) {
            if (Utils.getPref(this, CommonConstants.AD_TYPE_FB_GOOGLE, "")
                    .equals(CommonConstants.AD_GOOGLE)
            ) {
                CommonConstantAd.googlebeforloadAd(this)
            } else if (Utils.getPref(this, CommonConstants.AD_TYPE_FB_GOOGLE, "")
                    .equals(CommonConstants.AD_FACEBOOK)
            ) {
                CommonConstantAd.facebookbeforeloadFullAd(this)
            }
            if (Utils.getPref(this, CommonConstants.STATUS_ENABLE_DISABLE, "")
                    .equals(CommonConstants.ENABLE)
            ) {
                Handler(Looper.myLooper()!!).postDelayed({
                    when {
                        Utils.getPref(
                            this@SplashActivity,
                            CommonConstants.AD_TYPE_FB_GOOGLE,
                            ""
                        ).equals(CommonConstants.AD_GOOGLE) -> {
                            CommonConstantAd.showInterstitialAdsGoogle(
                                this@SplashActivity,
                                this@SplashActivity
                            )
                        }
                        Utils.getPref(
                            this@SplashActivity,
                            CommonConstants.AD_TYPE_FB_GOOGLE,
                            ""
                        ).equals(CommonConstants.AD_FACEBOOK) -> {
                            CommonConstantAd.showInterstitialAdsFacebook(this@SplashActivity)
                        }
                        else -> {
                            startNextActivity(0)
                        }
                    }
                }, 3000)
                Utils.setPref(this, CommonConstants.SPLASH_SCREEN_COUNT, 1)
            } else {
                startNextActivity(0)
            }
        } else {
            Utils.setPref(this, CommonConstants.SPLASH_SCREEN_COUNT, 1)
            startNextActivity(1000)
        }
    }

    override fun adLoadingFailed() {
        startNextActivity(0)
    }

    override fun adClose() {
        startNextActivity(0)
    }

    override fun startNextScreen() {
        startNextActivity(0)
    }

    override fun onLoaded() {
        isLoaded = true
    }


    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(myRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(myRunnable)
    }
}