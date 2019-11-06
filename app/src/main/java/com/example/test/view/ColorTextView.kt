package com.example.test.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.test.utils.dp
import java.util.*

class ColorTextView(context: Context?, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    private val colors =
        listOf(Color.BLACK, Color.CYAN, Color.BLUE, Color.YELLOW, Color.RED, Color.GRAY)

    private val textSizes = intArrayOf(16, 22, 28)
    private val cornerRadius = 4.dp
    private val xPadding = 16.dp.toInt()
    private val yPadding = 8.dp.toInt()
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var rectF: RectF
    private val texts = listOf("北京", "上海", "天津", "重庆", "广州", "武汉")
    private val random = Random()

    init {
        setTextColor(Color.WHITE)
        textSize = textSizes[random.nextInt(textSizes.size)].toFloat()
        paint.color = colors[random.nextInt(colors.size)]
        setPadding(xPadding, yPadding, xPadding, yPadding)
        text = texts[random.nextInt(texts.size)]
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        rectF = RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())
    }


    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            rectF,
            cornerRadius, cornerRadius,
            paint
        )
        super.onDraw(canvas)
    }
}