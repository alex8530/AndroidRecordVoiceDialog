package com.alex.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alex.recordvoicedialog.OnRecordListener
import com.alex.recordvoicedialog.RecordVoiceBottomSheetFragmentDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_showDailog.setOnClickListener {
            val r = RecordVoiceBottomSheetFragmentDialog()
            r.isCancelable=false
            r.show(supportFragmentManager, "tag!")
            r.recordListener= (object :OnRecordListener{
                override fun onCancel() {
                    Log.d(TAG, "onCancel")
                }

                override fun onFinish(recordTime: Long) {
                    Log.d(TAG, "onFinish : " + recordTime)
                }
                override fun onLessThanSecond() {
                    Log.d(TAG, "onLessThanSecond")
                }

                override fun onSend(elapsedTime:Long) {
                    Log.d(TAG, "onSend")
                }

                override fun dismissDialog() {
                    Log.d(TAG, "dismissDialog")
                }

                override fun onStartRecording() {
                    Log.d(TAG, "onStartRecording")
                }
            })
        }

    }
}

