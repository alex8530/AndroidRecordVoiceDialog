package com.alex.recordvoicedialog

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_record_voice_bottom_sheet_dialog.*


class RecordVoiceBottomSheetFragmentDialog :

    BottomSheetDialogFragment() {
    private val TAG = "RecordVoiceBottomSheet"
    var startTime: Long = 0
    var recordListener: OnRecordListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(
            R.layout.fragment_record_voice_bottom_sheet_dialog,
            container,
            false
        )
    }

    private fun resetViews() {
        cancelLottieAnimationView.visibility = View.INVISIBLE
        sendLottieAnimationView.visibility = View.INVISIBLE
        tv_longer_than_one_min.visibility = View.INVISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        resetViews()
        recordContainer.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.d(TAG, "ACTION_DOWN")
                        if (recordListener != null) recordListener!!.onStartRecording()
                        tv_time.base = SystemClock.elapsedRealtime()
                        tv_time.start()
                        startTime = System.currentTimeMillis()
                        resetViews()
                        recordLottieAnimationView.playAnimation()
                    }
                    MotionEvent.ACTION_MOVE -> {
                        Log.d(TAG, "ACTION_MOVE")
                    }
                    MotionEvent.ACTION_UP -> {
                        Log.d(TAG, "ACTION_up")
                        val time = System.currentTimeMillis() - startTime
                        tv_time.stop()
                        recordLottieAnimationView.pauseAnimation()

                        if (isLessThanOneSecond(time)) {
                            if (recordListener != null) recordListener!!.onLessThanSecond()
                            cancelLottieAnimationView.visibility = View.VISIBLE
                            tv_longer_than_one_min.visibility = View.VISIBLE

                        } else {
                            if (recordListener != null) recordListener!!.onFinish(time)
                            cancelLottieAnimationView.visibility = View.VISIBLE
                            sendLottieAnimationView.visibility = View.VISIBLE
                        }
                    }
                }

                return true
            }
        })

        sendLottieAnimationView.setOnClickListener {
            if (recordListener != null) recordListener!!.onSend()
//            sendLottieAnimationView.playAnimation()
//            sendLottieAnimationView.addAnimatorUpdateListener {
//               Log.d(TAG, it.animatedValue.toString()  )
//                if (it.animatedValue as Float >=  .99){
//                    dismiss()
//                }
//            }
            dismiss()

        }
        cancelLottieAnimationView.setOnClickListener {
            if (recordListener != null) recordListener!!.onCancel()
            dismiss()
        }
    }

    private fun isLessThanOneSecond(time: Long): Boolean {
        return time <= 1000
    }

    private fun setTextViewLongerThanOneMinText(text:String){
        tv_longer_than_one_min.text=text
    }

}


