package com.example.test.event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class EventView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(event)
    }

}