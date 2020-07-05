package com.alex.recordvoicedialog

interface OnRecordListener {
    fun onStartRecording()
    fun onCancel()
    fun onFinish(recordTime: Long)
    fun onLessThanSecond()
    fun onSend()
}