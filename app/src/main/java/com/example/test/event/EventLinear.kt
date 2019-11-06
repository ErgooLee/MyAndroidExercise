package com.example.test.event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

class EventLinear(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val ret = super.dispatchTouchEvent(ev)
        return ret
    }
}