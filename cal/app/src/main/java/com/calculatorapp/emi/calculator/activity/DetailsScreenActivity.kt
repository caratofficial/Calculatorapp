package com.calculatorapp.emi.calculator.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.calculatorapp.emi.calculator.fragment.MoreDetailsFragment
import com.calculatorapp.emi.calculator.fragment.ChartFragment
import com.google.android.material.tabs.TabLayout
import com.calculatorapp.emi.calculator.R
import java.util.ArrayList

class DetailsScreenActivity : AppCompatActivity(), View.OnClickListener {
    var llAdViewFacebook : LinearLayout?=null
    var llAdView : RelativeLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_screen)
        supportActionBar!!.hide()
        getAppContent()
        initDefine()
        initAction()
        com.calculatorapp.emi.calculator.utils.Utils.loadBannerAd(llAdView!!,llAdViewFacebook!!,this)
    }

    private fun getAppContent() {
        val intent = intent
        principal = intent.getIntExtra("Principal", 0).toDouble()
//        rate = intent.getIntExtra("RateOfIntrest", 0).toDouble()
        rate = intent.getDoubleExtra("RateOfIntrest", 0.0)
        month = intent.getIntExtra("Month", 0).toDouble()
        emi = intent.getIntExtra("EMI", 0).toDouble()
        intrest = intent.getIntExtra("Intrest", 0).toDouble()
    }

    var tabLayout: TabLayout? = null
    lateinit var viewPager: ViewPager
    var btnBackDetails: ImageButton? = null
    fun initDefine() {
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        btnBackDetails = findViewById<View?>(R.id.btnBackDetails) as ImageButton
        viewPager = findViewById<View?>(R.id.viewpager) as ViewPager
        setupViewPager(viewPager)
        tabLayout = findViewById<View?>(R.id.tablayout) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.setOnTabSelectedListener(onTabSelectedListener(viewPager))
    }

    fun initAction() {
        btnBackDetails!!.setOnClickListener(this)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MoreDetailsFragment(), "SUMMARY")
        adapter.addFragment(ChartFragment(), "CHART")
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        onTabSelectedListener(viewPager)
    }

    private fun onTabSelectedListener(viewPager: ViewPager?): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager!!.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        }
    }

    override fun onClick(v: View?) {
        if (v === btnBackDetails) {
            finish()
        }
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager?) :
        FragmentPagerAdapter(manager!!) {
        private val mFragmentList: MutableList<Fragment?> = ArrayList()
        private val mFragmentTitleList: MutableList<String?> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]!!
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment?, title: String?) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList.get(position)
        }
    }

    companion object {
        var principal = 0.0
        var rate = 0.0
        var month = 0.0
        var emi = 0.0
        var intrest = 0.0
    }
}