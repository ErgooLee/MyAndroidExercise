package com.example.test.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.test.utils.dp
import kotlin.math.min


class CircleView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 2.dp
    }
    var radius = 44.dp.toInt()
        set(value) {
            field = value
            invalidate()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = resolveSize(radius * 2, widthMeasureSpec)
        val height = resolveSize(radius * 2, heightMeasureSpec)
        setMeasuredDimension(width, height)


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width / 2f, height / 2f, radius.toFloat(), paint)
    }

}