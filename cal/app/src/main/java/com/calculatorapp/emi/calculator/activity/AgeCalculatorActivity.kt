package com.calculatorapp.emi.calculator.activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.calculatorapp.emi.calculator.R
import com.calculatorapp.emi.calculator.utils.DifferenceIn
import com.calculatorapp.emi.calculator.utils.DifferenceIn.*
import java.time.*
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.roundToLong

class AgeCalculatorActivity : BaseActivity(), View.OnClickListener {
    private var btnSubmit: Button? = null
    private var btnClear: Button? = null
    private var btnSetting: ImageView? = null
    private var llAgeResult: LinearLayout? = null
    private lateinit var datePicker: DatePicker
    private val minage = 1
    private var mNow = Calendar.getInstance()
    private lateinit var mDates: IntArray
    private var resDay: Long = 0
    private var resMonth: Long = 0
    private var resYear: Long = 0
    private var temp: Long = 0
    private var calculatedDay: Long = 0
    private var temp2: Long = 0

    private var currentYear: TextView? = null
    private var currentMonth: TextView? = null
    private var currentDays: TextView? = null

    private var remainingMonth: TextView? = null
    private var remainingDays: TextView? = null

    private var totalY: TextView? = null
    private var totalM: TextView? = null
    private var totalW: TextView? = null
    private var totalD: TextView? = null
    private var totalHr: TextView? = null
    private var totalMints: TextView? = null
    private var totalSec: TextView? = null
    private var llAdViewFacebook: LinearLayout? = null
    private var llAdView: RelativeLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_calculator)
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

    fun initDefine() {
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        llAgeResult = findViewById(R.id.llAgeResult)
        btnSetting = findViewById(R.id.btnSetting)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnClear = findViewById(R.id.btnClear)
        datePicker = findViewById(R.id.datePicker)



        currentYear = findViewById(R.id.ageYears)
        currentMonth = findViewById(R.id.ageMonths)
        currentDays = findViewById(R.id.ageDays)

        remainingMonth = findViewById(R.id.nextBDMonth)
        remainingDays = findViewById(R.id.nextDay)

        totalY = findViewById(R.id.totalYear)
        totalM = findViewById(R.id.totalMonth)
        totalW = findViewById(R.id.totalWeek)
        totalD = findViewById(R.id.totalDay)
        totalHr = findViewById(R.id.totalHour)
        totalMints = findViewById(R.id.totalMinute)
        totalSec = findViewById(R.id.totalSecond)
//        val minage = 1
        val minage = 0
        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.YEAR, -minage)
//        calendar.add(Calendar.YEAR, minage)
//        calendar.add(Calendar.MONTH,minage)
//        calendar.add(Calendar.DATE,minage)
        datePicker.maxDate = calendar.timeInMillis






    }

    fun initAction() {
        btnSubmit!!.setOnClickListener(this)
        btnClear!!.setOnClickListener(this)
        btnSetting!!.setOnClickListener(this)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    override fun onClick(v: View?) {
        when {
            v === btnSetting -> {
                fullView.openDrawer(Gravity.START)
            }
            v === btnSubmit -> {
                calculateAge()
            }
            v === btnClear -> {
                llAgeResult!!.visibility = View.GONE
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun calculateAge() {
        val dayPicked = datePicker.dayOfMonth
        val monthPicked = datePicker.month + 1
        val yearPicked = datePicker.year
        val selectedDates = intArrayOf(dayPicked, monthPicked, yearPicked)
        val currentYear = Calendar.getInstance()[Calendar.YEAR]
        /*if (currentYear - yearPicked <= minage) {
            Toast.makeText(
                this@AgeCalculatorActivity,
                "Your Age should be more than One year",
                Toast.LENGTH_SHORT
            ).show()
        } else {*/
            llAgeResult!!.visibility = View.VISIBLE
            mDates = selectedDates
            ageResult()
//        }
//        val dob = LocalDate.of(1999, Month.OCTOBER, 19)
        /*val dob = LocalDate.of(yearPicked, monthPicked, dayPicked)
        val months = monthsRemain(dob)
        Log.e("TAG","Months "+months+" Days "+ (totalDaysRemain(dob)-remainDays(months,2022+1)))*/
    }



      @RequiresApi(Build.VERSION_CODES.O)
    fun monthsRemain(dob: LocalDate): Int {
        val today: LocalDate = LocalDate.now(ZoneId.of("Asia/Kolkata"))
        val age = ChronoUnit.YEARS.between(dob, today)
        var nextBirthday = dob.plusYears(age)
        if (nextBirthday.isBefore(today)) {
            nextBirthday = dob.plusYears(age + 1)
        }
        val daysUntilNextBirthday = ChronoUnit.MONTHS.between(today, nextBirthday)
        return Math.toIntExact(daysUntilNextBirthday)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun totalDaysRemain(dob: LocalDate): Int {
        val today: LocalDate = LocalDate.now(ZoneId.of("Asia/Kolkata"))
        val age = ChronoUnit.YEARS.between(dob, today)
        var nextBirthday = dob.plusYears(age)
        if (nextBirthday.isBefore(today)) {
            nextBirthday = dob.plusYears(age + 1)
        }
        val daysUntilNextBirthday = ChronoUnit.DAYS.between(today, nextBirthday)
        return Math.toIntExact(daysUntilNextBirthday)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun remainDays(months:Int, year:Int):Int{
        var days = 0
        for (i in 1 until months+1){
            days += YearMonth.of(year, i).lengthOfMonth()
            Log.e("TAG","For loop==>> "+days+" "+i+"  "+(year+1)+"  "+year)
        }
        return days
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAgeYears(year: Int, month: Int, dayOfMonth: Int): Int {
        return Period.between(
            LocalDate.of(year, month, dayOfMonth),
            LocalDate.now()
        ).years
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAgeMonths(year: Int, month: Int, dayOfMonth: Int): Int {
        return Period.between(
            LocalDate.of(year, month, dayOfMonth),
            LocalDate.now()
        ).months
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAgeDays(year: Int, month: Int, dayOfMonth: Int): Int {
        return Period.between(
            LocalDate.of(year, month, dayOfMonth),
            LocalDate.now()
        ).days
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun ageResult() {
        calculatedDay = getDiff(DAYS)
        temp = (calculatedDay / 365)
        resYear = temp
//        currentYear!!.text = resYear.toString()
        temp = (calculatedDay % 365)
        temp /= 31
        resMonth = temp
//        currentMonth!!.text = resMonth.toString()

        temp = (calculatedDay % 365)
        temp %= 31
        resDay = temp
//        currentDays!!.text = resDay.toString()
        totalY!!.text = getDiff(YEARS).toString()
        totalM!!.text = getDiff(MONTHS).toString()
        totalW!!.text = getDiff(WEEKS).toString()
        totalD!!.text = getDiff(DAYS).toString()
        totalHr!!.text = (getDiff(DAYS) * 24).toString()
        totalMints!!.text = (getDiff(DAYS) * 24 * 60).toString()
        totalSec!!.text = (getDiff(DAYS) * 24 * 60 * 60).toString()
        val cal = Calendar.getInstance()
        cal[Calendar.DAY_OF_MONTH] = mDates[DAY_INDEX]
        cal[Calendar.MONTH] = mDates[MONTH_INDEX]
        cal[Calendar.YEAR] = mDates[YEAR_INDEX]
        val today = LocalDate.now()
        val birthday = LocalDate.of(
            mDates[YEAR_INDEX], mDates[MONTH_INDEX], mDates[DAY_INDEX]
        )
        var nextBDay = birthday.withYear(today.year)
        if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
            nextBDay = nextBDay.plusYears(1)
        }
        var p2 = ChronoUnit.DAYS.between(today, nextBDay)

        temp2 = ((p2 / 30.5).roundToLong())
        val mont = temp2
//        if (mont != 12L) remainingMonth!!.text = mont.toString()
        p2 = ((p2 % 30.5).roundToLong())
//        remainingDays!!.text = p2.toString()


        val dob = LocalDate.of(mDates[YEAR_INDEX], mDates[MONTH_INDEX], mDates[DAY_INDEX])
        val months = monthsRemain(dob)
        val days = (totalDaysRemain(dob)-remainDays(months,mDates[YEAR_INDEX]+1))
        remainingMonth!!.text = months.toString()
        remainingDays!!.text = days.toString()
        var yearsTotal = getAgeYears(mDates[YEAR_INDEX],mDates[MONTH_INDEX],mDates[DAY_INDEX])
        var monthsTotal = getAgeMonths(mDates[YEAR_INDEX],mDates[MONTH_INDEX],mDates[DAY_INDEX])
        var daysTotal = getAgeDays(mDates[YEAR_INDEX],mDates[MONTH_INDEX],mDates[DAY_INDEX])

        currentYear!!.text = yearsTotal.toString()
        currentMonth!!.text = monthsTotal.toString()
        currentDays!!.text = daysTotal.toString()
        Log.e("TAG", "Next BirthDay==>>>> Months $months Days $days  $yearsTotal  $monthsTotal  $daysTotal")
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun getDiff(duration: DifferenceIn?): Long {
        val currentMonth = mNow[Calendar.MONTH] + 1
        val currentDayOfMonth = mNow[Calendar.DAY_OF_MONTH]
        val currentYear = mNow[Calendar.YEAR]
        val startDate = LocalDate.of(
            mDates[YEAR_INDEX], mDates[MONTH_INDEX], mDates[DAY_INDEX]
        )
        val endDate = LocalDate.of(currentYear, currentMonth, currentDayOfMonth)
        var diff: Long = 0
        when (duration) {
            SECONDS -> {
                val startDateSec = LocalDateTime.of(
                    mDates[YEAR_INDEX], mDates[MONTH_INDEX],
                    mDates[DAY_INDEX], 0, 0, 0
                )
                val endDateSec = LocalDateTime.of(
                    currentYear, currentMonth,
                    currentDayOfMonth,
                    mNow[Calendar.HOUR_OF_DAY],
                    mNow[Calendar.MINUTE],
                    mNow[Calendar.SECOND]
                )
                val temp = LocalDateTime.from(startDateSec)
                diff = temp.until(endDateSec, ChronoUnit.SECONDS)
            }
            DAYS -> diff = ChronoUnit.DAYS.between(startDate, endDate)
            WEEKS -> diff = ChronoUnit.WEEKS.between(startDate, endDate)
            MONTHS -> diff = ChronoUnit.MONTHS.between(startDate, endDate)
            YEARS -> diff = ChronoUnit.YEARS.between(startDate, endDate)
            HOURS -> TODO()
            MINUTES -> TODO()
            null -> TODO()
        }
        return diff

    }


    companion object {
        private const val DAY_INDEX = 0
        private const val MONTH_INDEX = 1
        private const val YEAR_INDEX = 2
    }
}