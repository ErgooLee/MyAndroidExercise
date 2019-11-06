package com.example.test.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.utils.dp
import kotlin.math.cos
import kotlin.math.sin

class DashboardView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val RADIUS = 70.dp
    val OPEN_ANGLE = 120
    val DASH_WIDTH = 2.dp
    val DASH_HEIGHT = 10.dp
    val DASH_NUM = 20
    val MARK_LENGTH = 50.dp

    private val path = Path()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3.dp
        style = Paint.Style.STROKE

    }

    private val dash = Path().apply {
        addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CW)
    }

    private lateinit var pathDashEffect: PathDashPathEffect

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.addArc(
            RectF(
                width / 2f - RADIUS,
                height / 2f - RADIUS,
                width / 2f + RADIUS,
                height / 2f + RADIUS
            ), 90 + OPEN_ANGLE / 2f,
            360 - OPEN_ANGLE.toFloat()
        )
        val pathMeasure = PathMeasure(path, false)
        val phase = (pathMeasure.length - DASH_WIDTH) / DASH_NUM
        pathDashEffect = PathDashPathEffect(
            dash,
            phase, 0f,
            PathDashPathEffect.Style.ROTATE
        )


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
        paint.pathEffect = pathDashEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null
        val angle =
            Math.toRadians((90 + OPEN_ANGLE / 2f + (360 - OPEN_ANGLE.toFloat()) / 20 * 8).toDouble()).toFloat()
        canvas.drawLine(
            width / 2f, height / 2f,
            width / 2f + MARK_LENGTH * cos(angle),
            height / 2f + MARK_LENGTH * sin(angle),
            paint
        )


    }
}