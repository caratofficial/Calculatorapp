package com.calculatorapp.emi.calculator.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

import com.calculatorapp.emi.calculator.R
import java.lang.Exception

class SettingActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportActionBar!!.hide()
        initDefine()
        initAction()
    }

   private var rltContactUs: RelativeLayout? = null
   private var rltRate: RelativeLayout? = null
   private var rltShare: RelativeLayout? = null
   private var rltMoreApp: RelativeLayout? = null
   private var btnBackSetting: ImageView? = null
    fun initDefine() {
        rltContactUs = findViewById(R.id.rltContactUs)
        rltRate = findViewById(R.id.rltRate)
        rltShare = findViewById(R.id.rltShare)
        rltMoreApp = findViewById(R.id.rltMoreApp)
        btnBackSetting = findViewById(R.id.btnBackSetting)
    }

    fun initAction() {
        rltContactUs!!.setOnClickListener(this)
        rltRate!!.setOnClickListener(this)
        rltShare!!.setOnClickListener(this)
        rltMoreApp!!.setOnClickListener(this)
        btnBackSetting!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when {
            v === rltContactUs -> {
                contactUs()
            }
            v === rltRate -> {
                val appPackageName = packageName
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                "market://details?id=$appPackageName"
                            )
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                "https://play.google.com/store/apps/details?id=$appPackageName"
                            )
                        )
                    )
                }
            }
            v === rltShare -> {
                shareAppLink()
            }
            v === rltMoreApp -> {
                val uri = Uri.parse("https://play.google.com/store/apps/developer?id=Ninety+Nine+Apps")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            v === btnBackSetting -> {
                finish()
            }
        }
    }

    private fun shareAppLink() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        val link = "https://play.google.com/store/apps/details?id=$packageName"
        shareIntent.putExtra(Intent.EXTRA_TEXT, link)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.app_name))
        shareIntent.type = "text/plain"
        startActivity(Intent.createChooser(shareIntent, resources.getString(R.string.app_name)))
    }

    private fun contactUs() {
        try {
            val sendIntentGmail = Intent(Intent.ACTION_SEND)
            sendIntentGmail.type = "plain/text"
            sendIntentGmail.setPackage("com.google.android.gm")
            sendIntentGmail.putExtra(Intent.EXTRA_EMAIL, "ninetyninedevelopers@gmail.com")
            sendIntentGmail.putExtra(
                Intent.EXTRA_SUBJECT, resources.getString(
                    R.string.app_name
                )
            )
            startActivity(sendIntentGmail)
        } catch (e: Exception) {
            val sendIntentIfGmailFail = Intent(Intent.ACTION_SEND)
            sendIntentIfGmailFail.type = "*/*"
            sendIntentIfGmailFail.putExtra(Intent.EXTRA_EMAIL, "ninetyninedevelopers@gmail.com")
            sendIntentIfGmailFail.putExtra(
                Intent.EXTRA_SUBJECT,
                resources.getString(R.string.app_name)
            )
            if (sendIntentIfGmailFail.resolveActivity(packageManager) != null) {
                startActivity(sendIntentIfGmailFail)
            }
        }
    }
}