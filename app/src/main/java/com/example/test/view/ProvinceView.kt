package com.example.test.view

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.test.utils.dp

private val provinces = listOf<String>(
    "河北省",
    "山西省",
    "内蒙古自治区",
    "黑龙江省",
    "吉林省",
    "辽宁省",
    "陕西省",
    "甘肃省",
    "青海省",
    "新疆维吾尔自治区",
    "宁夏回族自治区",
    "山东省",
    "河南省",
    "江苏省",
    "浙江省",
    "安徽省",
    "江西省",
    "福建省",
    "台湾省",
    "湖北省",
    "湖南省",
    "广东省",
    "广西壮族自治区",
    "海南省",
    "四川省",
    "云南省",
    "贵州省",
    "西藏自治区",
    "北京市",
    "上海市",
    "天津市",
    "重庆市",
    "香港特别行政区",
    "澳门特别行政区"
)


class ProvinceView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 20.dp
        textAlign = Paint.Align.CENTER
    }


    var province = "河北省"
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(province, width / 2f, height / 2f, paint)
    }

    class ProvinceEvaluator : TypeEvaluator<String> {
        override fun evaluate(fraction: Float, startValue: String?, endValue: String?): String {
            val startIndex = provinces.indexOf(startValue)
            val endIndex = provinces.indexOf(endValue)
            val currentIndex = (startIndex + (endIndex - startIndex) * fraction).toInt()
            return provinces[currentIndex]
        }
    }
}