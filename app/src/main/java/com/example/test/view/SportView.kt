package com.example.test.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.utils.dp

class SportView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val bgColor = Color.parseColor("#90A4AE")
    private val fgColor = Color.parseColor("#FF4081")
    private val ringWidth = 8.dp
    private val radius = 70.dp

    //sp 和屏幕密度和系统基准都相关
    //typeface 微软雅黑
    //font 粗体黑体斜体 相当于小版本
//    ---top
//    ---ascent
//    ---baseline
//    ---descent
//    ---bottom
//    ---leading
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 50.dp
        isFakeBoldText = true
        //横向居中
        textAlign = Paint.Align.CENTER
    }
    private lateinit var rectF: RectF
    private val textBounds: Rect = Rect()
    private val fontMetrics = Paint.FontMetrics()

    private var textTag = 0
    private var text = "aaaa"

    init {
        setOnClickListener {
            text = when (textTag) {
                0 -> {
                    textTag++
                    "bbbb"
                }
                1 -> {
                    textTag++
                    "gggg"
                }
                2 -> {
                    textTag++
                    "abfg"
                }
                3 -> {
                    textTag++
                    "迷你"
                }
                else -> {
                    textTag = 0
                    "aaaa"
                }
            }
            invalidate()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF = RectF(
            width / 2f - radius,
            height / 2f - radius,
            width / 2f + radius,
            height / 2f + radius
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style = Paint.Style.STROKE
        paint.color = bgColor
        paint.strokeWidth = ringWidth
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)

        paint.color = fgColor
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            rectF,
            -90f, 225f, false, paint
        )

        paint.style = Paint.Style.FILL
        paint.getTextBounds(text, 0, text.length, textBounds)
        paint.getFontMetrics(fontMetrics)

//        val halfTextHeight = (textBounds.bottom + textBounds.top) / 2f
        val halfTextHeight = (fontMetrics.bottom + fontMetrics.top) / 2f
        canvas.drawText(text, width / 2f, height / 2f - halfTextHeight, paint)
        paint.color = Color.GREEN
        paint.strokeWidth = 1f
        canvas.drawLine(
            0f, height / 2f - halfTextHeight,
            width.toFloat(), height / 2f - halfTextHeight,
            paint
        )
        canvas.drawLine(
            0f, height / 2f + halfTextHeight,
            width.toFloat(), height / 2f + halfTextHeight,
            paint
        )

//        val halfTextHeight2 = (fontMetrics.ascent + fontMetrics.descent) / 2f
//        paint.color = Color.BLUE
//        canvas.drawLine(0f, height / 2f - halfTextHeight2,
//            width.toFloat(), height / 2f - halfTextHeight2,
//            paint)
//        canvas.drawLine(
//            0f, height / 2f + halfTextHeight2,
//            width.toFloat(), height / 2f + halfTextHeight2,
//            paint)
        paint.textAlign = Paint.Align.LEFT
        paint.getFontMetrics(fontMetrics)
        paint.getTextBounds("abab", 0, "abab".length, textBounds)
        canvas.drawText("abab", 0f - textBounds.left, -fontMetrics.top, paint)


        paint.textAlign = Paint.Align.LEFT
        paint.textSize = 10.dp
        paint.getFontMetrics(fontMetrics)
        canvas.drawText("abab", 0f - textBounds.left, -fontMetrics.top, paint)

    }


}