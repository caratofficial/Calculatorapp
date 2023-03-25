package com.calculatorapp.emi.calculator.activity

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.messaging.FirebaseMessaging
import com.calculatorapp.emi.calculator.R
import com.calculatorapp.emi.calculator.interfaces.ConfirmDialogCallBack
import com.calculatorapp.emi.calculator.utils.CommonUtilities
import java.lang.Exception
import java.util.ArrayList
import kotlin.math.roundToInt

open class BaseActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    lateinit var context: Context
    lateinit var fullView: DrawerLayout
    lateinit var llBase: LinearLayout
    override fun setContentView(layoutResID: Int) {
        fullView = layoutInflater.inflate(R.layout.activity_base, null) as DrawerLayout
        val activityContainer = fullView.findViewById<View?>(R.id.activity_content) as FrameLayout
        layoutInflater.inflate(layoutResID, activityContainer, true)
        super.setContentView(fullView)
        context = this
        initdefineBase()


        try {
            subScribeToFirebaseTopic()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun subScribeToFirebaseTopic() {
        try {
            FirebaseMessaging.getInstance().subscribeToTopic("emi_cal_topic")
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("subScribeFirebaseTopic", ": Fail")
                    } else {
                        Log.e("subScribeFirebaseTopic", ": Success")
                    }
                }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    lateinit var arrDrawerItem: ArrayList<String>
    lateinit var arrDrawerImg: ArrayList<Int>
    private fun initdefineBase() {
        llBase = findViewById<View?>(R.id.llBase) as LinearLayout
        leftDrawerListView = findViewById<View?>(R.id.left_drawer_list_view) as ListView
        arrDrawerItem = ArrayList()
        arrDrawerItem.add("Emi Calculator")
        arrDrawerItem.add("Age Calculator")
        arrDrawerItem.add("BMI Calculator")
        arrDrawerItem.add("Basic Calculator")
        arrDrawerItem.add("Advance Calculator")
        arrDrawerItem.add("Discount Calculator")
        arrDrawerItem.add("Percentage Calculator")
        arrDrawerItem.add("Length Convert")
        arrDrawerItem.add("Line")
        arrDrawerItem.add("Contact Us")
        arrDrawerItem.add("Rate Us")
        arrDrawerItem.add("Share App")
        arrDrawerItem.add("Exit")


        arrDrawerImg = ArrayList()
        arrDrawerImg.add(R.drawable.ic_menu_emi_calculate)
        arrDrawerImg.add(R.drawable.ic_menu_age_cal)
        arrDrawerImg.add(R.drawable.bmi)
        arrDrawerImg.add(R.drawable.ic_menu_calculate)
        arrDrawerImg.add(R.drawable.ic_menu_scientific_calculator)
        arrDrawerImg.add(R.drawable.ic_menu_discount_cal)
        arrDrawerImg.add(R.drawable.ic_menu_percentage_cal)
        arrDrawerImg.add(R.drawable.ic_menu_length)
        arrDrawerImg.add(R.drawable.baseline_contact_support_white_24)
        arrDrawerImg.add(R.drawable.baseline_star_white_24)
        arrDrawerImg.add(R.drawable.baseline_share_white_24)
        arrDrawerImg.add(R.drawable.ic_baseline_exit_to_app_24)
        setListViewAdapter()
        leftDrawerListView.onItemClickListener = this
        llBase.post {
            val width = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                300f,
                resources.displayMetrics
            )
            val params = llBase.layoutParams
            params.width = width.roundToInt()
            llBase.layoutParams = params
        }
    }

    lateinit var leftDrawerListView: ListView
    lateinit var menuAdapter: MenuAdapter
    private fun setListViewAdapter() {
        menuAdapter = MenuAdapter()
        leftDrawerListView.adapter = menuAdapter
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        fullView.closeDrawer(GravityCompat.START)
        when (arrDrawerItem.get(position)) {
            "Emi Calculator" -> {
                val intent1 = Intent(applicationContext, MainActivity::class.java)
                intent1.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent1)
            }
            "Age Calculator" -> {
                val intent = Intent(applicationContext, AgeCalculatorActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            "BMI Calculator" -> {
                val intentBMI = Intent(applicationContext, BMICalculatorActivity::class.java)
                intentBMI.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intentBMI)
            }
            "Advance Calculator" -> {
                val intentAdvance =
                    Intent(applicationContext, AdvanceCalculatorActivity::class.java)
                intentAdvance.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intentAdvance)
            }
            "Basic Calculator" -> {
                val intentBasic = Intent(
                    applicationContext, BasicCalculatorActivity::class.java
                )
                intentBasic.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intentBasic)
            }
            "Discount Calculator" -> {
                val intentDiscount = Intent(
                    applicationContext, DiscountCalculatorActivity::class.java
                )
                intentDiscount.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intentDiscount)
            }
            "Percentage Calculator" -> {
                val intentPercentage = Intent(
                    applicationContext, PercentageCalculatorActivity::class.java
                )
                intentPercentage.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intentPercentage)
            }
            "Length Convert" -> {
                val intentLength = Intent(
                    applicationContext, LengthConverterActivity::class.java
                )
                intentLength.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intentLength)
            }
            "Contact Us" -> contactUs()
            "Rate Us" -> RateUs()
            "Share App" -> shareAppLink()
            "Exit" -> CommonUtilities.confirmationDialog(this, object : ConfirmDialogCallBack {
                override fun Okay() {
                    val homeIntent = Intent(Intent.ACTION_MAIN)
                    homeIntent.addCategory(Intent.CATEGORY_HOME)
                    homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(homeIntent)
                }

                override fun cancel() {

                }

            }, "", getString(R.string.exit_confirmation))
        }
    }

    private fun contactUs() {
        try {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "plain/text"
            emailIntent.putExtra(
                Intent.EXTRA_EMAIL,
                arrayOf<String?>("Enter your email")
            )
            emailIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                resources.getString(R.string.app_name) + " - Android"
            )
            emailIntent.putExtra(Intent.EXTRA_TEXT, "")
            emailIntent.putExtra(Intent.EXTRA_STREAM, "")
            emailIntent.setPackage("com.google.android.gm")
            startActivity(emailIntent)
        } catch (e: Resources.NotFoundException) {
            val sendIntentIfGmailFail = Intent(Intent.ACTION_SEND)
            sendIntentIfGmailFail.type = "*/*"
            sendIntentIfGmailFail.putExtra(Intent.EXTRA_EMAIL, "Enter your email")
            sendIntentIfGmailFail.putExtra(
                Intent.EXTRA_SUBJECT,
                resources.getString(R.string.app_name) + " - Android"
            )
            if (sendIntentIfGmailFail.resolveActivity(packageManager) != null) {
                startActivity(sendIntentIfGmailFail)
            }
        }
    }


    fun shareAppLink() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        val link = "https://play.google.com/store/apps/details?id=$packageName"
        shareIntent.putExtra(Intent.EXTRA_TEXT, link)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.app_name))
        shareIntent.type = "text/plain"
        startActivity(Intent.createChooser(shareIntent, resources.getString(R.string.app_name)))
    }

    fun RateUs() {
        val appPackageName = packageName
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store")))
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }

    inner class MenuAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return arrDrawerItem.size
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var convertView = convertView
            convertView = LayoutInflater.from(context).inflate(R.layout.row_of_drawer_item, null)
            val imgItem = convertView.findViewById<View?>(R.id.imgItem) as ImageView
            val txtItem = convertView.findViewById<View?>(R.id.txtItem) as TextView
            val viewLine = convertView.findViewById(R.id.viewLine) as View
            val llMain = convertView.findViewById<View?>(R.id.llMain) as LinearLayout
            if (arrDrawerItem[position] == "Line") {
            viewLine.visibility = View.VISIBLE
            llMain.visibility = View.GONE
            } else {
            viewLine.visibility = View.GONE
            llMain.visibility = View.VISIBLE
            }
            imgItem.setImageResource(arrDrawerImg.get(position))
            txtItem.text = arrDrawerItem.get(position)
            return convertView
        }
    }
}