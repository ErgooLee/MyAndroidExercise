package com.example.test.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.example.test.utils.dp

private val RADIUS = 30.dp

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    private lateinit var pathMeasure: PathMeasure


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        path.addCircle(width / 2f, height / 2f, RADIUS, Path.Direction.CW)
        path.addRect(
            width / 2f - RADIUS,
            height / 2f,
            width / 2f + RADIUS,
            height.toFloat(),
            Path.Direction.CCW)
        //更好用，不用判断方向
        path.fillType = Path.FillType.EVEN_ODD
        //不太好用，需要判断方向
//        path.fillType = Path.FillType.WINDING

        path.addCircle(width / 2f, height / 2f,
            RADIUS * 1.2f, Path.Direction.CW)

        pathMeasure = PathMeasure(path, false)
        println(pathMeasure.isClosed)
        println(pathMeasure.length)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.drawLine(100f, 100f, 200f, 200f, paint)
//        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)
//        canvas.drawCircle(
//            width / 2f, height / 2f,
//            Pixels.dp2px(20),
//            paint
//        )
//        canvas.drawCircle(
//            width / 2f, height / 2f,
//            RADIUS,
//            paint
//        )

        canvas.drawPath(path, paint)



    }
}