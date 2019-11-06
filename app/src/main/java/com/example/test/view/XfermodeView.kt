package com.example.test.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.utils.dp

class XfermodeView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val circleF = RectF(70.dp, 10.dp, 140.dp, 80.dp)
    private val rectF = RectF(40.dp, 45.dp, 110.dp, 115.dp)
    private val mode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)


    private lateinit var destBitmap: Bitmap
    private lateinit var sourceBitmap: Bitmap


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        destBitmap = createBitmap(width, height)
        sourceBitmap = createBitmap(width, height)
        val canvas = Canvas(destBitmap)
        paint.color = Color.RED
        canvas.drawOval(circleF, paint)

        canvas.setBitmap(sourceBitmap)
        paint.color = Color.BLUE
        canvas.drawRect(rectF, paint)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)
//        //destination
//        paint.color = Color.RED
//        canvas.drawOval(circleF, paint)
//        //source
//        paint.xfermode = mode
//        paint.color = Color.BLUE
//        canvas.drawRect(rectF, paint)
//        paint.xfermode = null
//        canvas.restore()


        canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)
        //destination
        canvas.drawBitmap(destBitmap, 0f, 0f, paint)
        paint.xfermode = mode

        canvas.drawBitmap(sourceBitmap, 0f, 0f, paint)
        paint.xfermode = null

        //source
        canvas.restore()
    }

    private fun createBitmap(width: Int, height: Int): Bitmap {
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    }

}

