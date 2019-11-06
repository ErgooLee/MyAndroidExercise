package com.example.test.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import com.example.test.utils.dp

class DrawableView(context: Context?, attrs: AttributeSet?) : View(context, attrs){
    val drawable = ColorDrawable(Color.RED)

    //drawable 定义绘制规则

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //必须设置边界
        drawable.setBounds(10.dp.toInt(), 10.dp.toInt(), width, height)
        drawable.draw(canvas)

    }
}