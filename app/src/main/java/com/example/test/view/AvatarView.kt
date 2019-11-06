package com.example.test.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.R
import com.example.test.utils.dp

class AvatarView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val IMAGE_WIDTH = 100.dp
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var rectF: RectF
    private val mode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
//        rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
        rectF = RectF(0f, 0f, IMAGE_WIDTH, IMAGE_WIDTH)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val count = canvas.saveLayer(rectF, null)
        //destination circle
        canvas.drawOval(rectF, paint)
        paint.xfermode = mode

        //source avatar
        canvas.drawBitmap(getAvatar(IMAGE_WIDTH.toInt()), 0f, 0f, paint)
        paint.xfermode = null
        canvas.restoreToCount(count)
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