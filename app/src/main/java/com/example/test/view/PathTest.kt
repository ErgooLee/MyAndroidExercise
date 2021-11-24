package com.example.test.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PathTest(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 100f
        color = Color.BLACK
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(0f, 50f, 200f, 50f, paint)
    }
}