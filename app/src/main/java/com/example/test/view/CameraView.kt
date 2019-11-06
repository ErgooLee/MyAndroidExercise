package com.example.test.view

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.example.test.R
import com.example.test.utils.dp

class CameraView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 30.dp
    }
    private val padding = 20.dp
    private val avatarWidth = 100.dp.toInt()
    private val bitmap = getAvatar(avatarWidth)
    private val camera = Camera()

    var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        camera.setLocation(
            0f, 0f,
            -6 * Resources.getSystem().displayMetrics.density
        )

    }

//    private val clipped = Path().apply {
//        addOval(
//            RectF(padding, padding, padding + avatarWidth, padding + avatarWidth),
//            Path.Direction.CCW
//        )
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


//        canvas.clipRect(padding, padding, padding + avatarWidth / 2, padding + avatarWidth / 2)
//        canvas.clipPath(clipped)
//        canvas.rotate
//        canvas.scale
//        canvas.translate()
//        canvas.skew()

//        //上半部分
//        canvas.save()
//        canvas.translate(padding + avatarWidth / 2, padding + avatarWidth / 2)
//        canvas.clipRect(
//            -avatarWidth / 2,
//            -avatarWidth / 2,
//            avatarWidth / 2,
//            0
//        )
//        canvas.translate(-padding - avatarWidth / 2, -padding - avatarWidth / 2)
//        canvas.drawBitmap(bitmap, padding, padding, paint)
//        canvas.restore()
//
//
//        //下半部分
//        canvas.translate(padding + avatarWidth / 2, padding + avatarWidth / 2)
//        camera.applyToCanvas(canvas)
//        canvas.clipRect(
//            -avatarWidth / 2,
//            0,
//            avatarWidth / 2,
//            avatarWidth / 2
//        )
//        canvas.translate(-padding - avatarWidth / 2, -padding - avatarWidth / 2)
//        canvas.drawBitmap(bitmap, padding, padding, paint)


        //上半部分
        canvas.withSave {
            canvas.translate(padding + avatarWidth / 2, padding + avatarWidth / 2)
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(topFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(
                -avatarWidth,
                -avatarWidth,
                avatarWidth,
                0
            )
            canvas.rotate(flipRotation)
            canvas.translate(-padding - avatarWidth / 2, -padding - avatarWidth / 2)
            canvas.drawBitmap(bitmap, padding, padding, paint)
        }

        //下半部分
        canvas.withSave {
            canvas.translate(padding + avatarWidth / 2, padding + avatarWidth / 2)
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(bottomFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(
                -avatarWidth,
                0,
                avatarWidth,
                avatarWidth
            )
            canvas.rotate(flipRotation)
            canvas.translate(-padding - avatarWidth / 2, -padding - avatarWidth / 2)
            canvas.drawBitmap(bitmap, padding, padding, paint)
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