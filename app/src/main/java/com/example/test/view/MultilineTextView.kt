package com.example.test.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import com.example.test.R
import com.example.test.utils.dp

class MultilineTextView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {

    //lorem ipsum
    private val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Justo eget magna fermentum iaculis eu non diam phasellus vestibulum. Nulla pharetra diam sit amet nisl suscipit. Diam maecenas ultricies mi eget mauris pharetra. Commodo quis imperdiet massa tincidunt nunc pulvinar sapien et. Tempus urna et pharetra pharetra. Vulputate eu scelerisque felis imperdiet proin fermentum. Et netus et malesuada fames ac turpis egestas maecenas. Arcu non sodales neque sodales. Sit amet tellus cras adipiscing enim. Aliquam faucibus purus in massa tempor nec."

    private val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    private lateinit var staticLayout :StaticLayout
    private val fontMetrics = Paint.FontMetrics()
    private val measureWidth = floatArrayOf(0f)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        staticLayout = StaticLayout(
            text, paint, width,
            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        staticLayout.draw(canvas)
        canvas.drawBitmap(getAvatar(100.dp.toInt()), width.toFloat() - 100.dp, 70.dp, paint)
        paint.getFontMetrics(fontMetrics)

        var index = 0
        var count = 0
        var top = -fontMetrics.top
        while (index < text.length) {
            var maxWidth = width.toFloat()
            if (top + fontMetrics.bottom >= 70.dp && top + fontMetrics.top <= 170.dp) {
                maxWidth = width.toFloat() - 100.dp
            }
            count = paint.breakText(text, index, text.length, true, maxWidth, measureWidth)
            canvas.drawText(text, index, index + count, 0f, top, paint)
            top += paint.fontSpacing
            index += count
        }
    }

    fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.bussiness_man_avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.bussiness_man_avatar, options)

    }
}