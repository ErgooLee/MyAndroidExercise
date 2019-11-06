package com.example.test.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.test.R
import com.example.test.utils.dp

class MaterialTextView(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val TEXT_SIZE = 12.dp
    private val TEXT_MARGIN = 8.dp
    private val HORIZONTAL_OFFSET = 1.dp
    private val VERTICAL_OFFSET = 23.dp
    private val EXTRA_VERTICAL_OFFSET = 5.dp
    private var floatLabelShown = false
    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    var useFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(
                        paddingLeft,
                        (paddingTop + TEXT_SIZE + TEXT_MARGIN).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                } else {
                    setPadding(
                        paddingLeft,
                        (paddingTop - TEXT_SIZE - TEXT_MARGIN).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                }
            }

        }

    private val animator by lazy {
        ObjectAnimator.ofFloat(
            this,
            "floatingLabelFraction",
            0f,
            1f
        )
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = TEXT_SIZE
    }


    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.MaterialTextView
        )
        useFloatingLabel =
            typedArray.getBoolean(R.styleable.MaterialTextView_useFloatingLabel, false)
        typedArray.recycle()

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.alpha = (floatingLabelFraction * 0xff).toInt()
        val currentVerticalValue =
            VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction)
        canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentVerticalValue, paint)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (useFloatingLabel) {
            if (text.isNullOrEmpty() && floatLabelShown) {
                floatLabelShown = false
                animator.reverse()
            } else if (!floatLabelShown && !text.isNullOrEmpty()) {
                floatLabelShown = true
                animator.start()
            }
        }
    }
}