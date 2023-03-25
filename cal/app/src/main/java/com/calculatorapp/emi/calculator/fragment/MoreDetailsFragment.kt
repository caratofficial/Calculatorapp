package com.calculatorapp.emi.calculator.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.widget.NestedScrollView

import com.calculatorapp.emi.calculator.utils.EMI
import androidx.fragment.app.Fragment
import com.calculatorapp.emi.calculator.activity.DetailsScreenActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calculatorapp.emi.calculator.adapter.ListOfMonthWiseIntrestAdapter
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.calculatorapp.emi.calculator.R
import com.calculatorapp.emi.calculator.utils.CommonUtilities
import com.calculatorapp.emi.calculator.utils.CommonConstants
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.ArrayList
import java.util.HashMap

class MoreDetailsFragment : Fragment() {
   private var view1: View? = null
   private var imgPDF: ImageView? = null
   private var mainScrollView: NestedScrollView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.fragment_more_details, container, false)
        initDefine()
        return view1
    }

    private lateinit var listOfIntrestAmountMonthly: RecyclerView
    private lateinit var listOfMonthWiseIntrestAdapter: ListOfMonthWiseIntrestAdapter
    private var arrItem: ArrayList<HashMap<String?, String?>?>? = ArrayList()
    fun initDefine() {
        imgPDF = view1!!.findViewById<View?>(R.id.imgPDF) as ImageView
        mainScrollView = view1!!.findViewById<View?>(R.id.mainScrollView) as NestedScrollView
        arrItem = EMI.calcEmiAllMonths(
            DetailsScreenActivity.principal,
            DetailsScreenActivity.rate,
            DetailsScreenActivity.month
        )
        listOfIntrestAmountMonthly =
            view1!!.findViewById<View?>(R.id.listOfIntrestAmountMonthly) as RecyclerView
        listOfIntrestAmountMonthly.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        listOfIntrestAmountMonthly.isNestedScrollingEnabled = false
        listOfIntrestAmountMonthly.setHasFixedSize(true)
        listOfMonthWiseIntrestAdapter = ListOfMonthWiseIntrestAdapter(context, arrItem)
        listOfIntrestAmountMonthly.adapter = listOfMonthWiseIntrestAdapter
        imgPDF!!.setOnClickListener {
            if (hasAllPermission(context)) {
                GenerateAndSharePdf().execute()
            } else {
                Dexter.withContext(context)
                    .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    .withListener(object : MultiplePermissionsListener {
                        override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport?) {
                            if (multiplePermissionsReport!!.areAllPermissionsGranted()) {
                                GenerateAndSharePdf().execute()
                            } else {
                                Log.e(
                                    "Tag",
                                    "All necessary permission is not granted by user.Please do that first"
                                )
                            }
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            list: MutableList<PermissionRequest?>?,
                            permissionToken: PermissionToken?
                        ) {
                        }
                    }).check()
            }
        }
    }

    private fun hasAllPermission(context: Context?): Boolean {
        return if (context == null) {
            false
        } else ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
    }

    fun generateBitmapForEmiDetail(): Bitmap? {
        var returnedBitmap: Bitmap? = null
        try {
            val totalHeight = mainScrollView!!.getChildAt(0).height
            val totalWidth = mainScrollView!!.getChildAt(0).width
            returnedBitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(returnedBitmap)
            val bgDrawable = mainScrollView!!.background
            if (bgDrawable != null) {
                bgDrawable.draw(canvas)
            } else {
                canvas.drawColor(resources.getColor(R.color.colorWhite))
            }
            mainScrollView!!.draw(canvas)
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        }
        return returnedBitmap
    }

    @SuppressLint("StaticFieldLeak")
    inner class GenerateAndSharePdf : AsyncTask<Void?, Void?, Void?>() {
        var dialog: ProgressDialog? = ProgressDialog(context)
        override fun onPreExecute() {
            dialog!!.setTitle("EMI Statement")
            dialog!!.setMessage("Please wait")
            dialog!!.show()
        }

        override fun doInBackground(vararg voids: Void?): Void? {
            val bitmap = generateBitmapForEmiDetail()
            bitmap?.let { generatePdf(it) }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            try {

                if (dialog!!.isShowing) {
                    dialog!!.dismiss()
                }
                try {
                    val file = File((requireActivity()).filesDir.toString())
                    val filePath = File(file.absolutePath + File.separator + CommonConstants.PDFName.plus(CommonConstants.ExtPDF))
                    val uri = FileProvider.getUriForFile(context!!, "${requireActivity().packageName}.provider", filePath)
                    if (filePath.exists() && filePath.isFile) {
                        try {
                            try {
                                var intent: Intent?
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    intent = Intent(Intent.ACTION_VIEW)
                                    intent.data = uri
                                    intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                                } else {
                                    intent = Intent(Intent.ACTION_VIEW)
                                    intent.setDataAndType(Uri.parse(filePath.toString()), "application/pdf")
                                    intent = Intent.createChooser(intent, "Open File")
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }

                                startActivity(intent)
                                CommonUtilities.showToast(context,filePath.absolutePath)
                            } catch (e: ActivityNotFoundException) {
                                Toast.makeText(context, "No Application available to view pdf", Toast.LENGTH_LONG).show()
                            }


                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } else {
                        CommonUtilities.showToast(context, CommonConstants.MsgSomethingWrong)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun generatePdf(bitmap: Bitmap) {
        var filePath: File? = null

        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val file = File(requireActivity().filesDir.toString())
            if (!file.exists()) {
                file.mkdirs()
            }
            filePath = File(file.absolutePath + File.separator + CommonConstants.PDFName.plus(CommonConstants.ExtPDF))
        }
        try {
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

            val document = PdfDocument()

            val totalHeight = (bitmap.height / 2000).toFloat()
            var totalPages = totalHeight.toInt()
            if (totalHeight > totalPages) {
                totalPages++
            }

            val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 0).create()
            val page = document.startPage(pageInfo)
            val canvas = page.canvas
            val paint = Paint()
            paint.color = Color.parseColor("#ffffff")
            canvas.drawPaint(paint)
            paint.color = Color.BLUE
            canvas.drawBitmap(bitmap, 0f, 0f, null)

            document.finishPage(page)
            try {
                document.writeTo(FileOutputStream(filePath))
            } catch (e: Exception) {
                e.printStackTrace()
                CommonUtilities.showToast(requireContext(), CommonConstants.MsgSomethingWrong)
            }
            document.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}