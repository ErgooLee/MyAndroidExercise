package com.example.test.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.utils.dp
import kotlin.math.cos
import kotlin.math.sin

class PieView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val RADIUS = 70.dp

    lateinit var rectF: RectF

    val colors = listOf(Color.RED, Color.YELLOW, Color.BLUE, Color.CYAN)
    val angles = listOf(40, 50, 120, 150)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3.dp
        style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF = RectF(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var startAngle = 0f
        val index = 3
        for ((i, value) in colors.withIndex()) {
            if (i == index) {
                canvas.save()
                val angle = Math.toRadians(startAngle.toDouble() + angles[index] / 2)
                canvas.translate(5.dp * cos(angle).toFloat(), 5.dp * sin(angle).toFloat())
            }
            paint.color = value
            canvas.drawArc(
                rectF,
                startAngle,
                angles[i].toFloat(),
                true,
                paint
            )
            startAngle += angles[i].toFloat()
            if (i == index) {
                canvas.restore()
            }
        }
    }
}