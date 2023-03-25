package com.calculatorapp.emi.calculator.interfaces

interface CallbackListener {
    fun onSuccess()
    fun onCancel()
    fun onRetry()
}