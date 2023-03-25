package com.calculatorapp.emi.calculator.interfaces


interface AdsCallback {
    fun adLoadingFailed()
    fun adClose()
    fun startNextScreen()
    fun onLoaded()
}