package com.example.test.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children

class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val childrenBounds = mutableListOf<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        var lineWidthUsed = 0
        var heightUsed = 0

        var lineHeight = 0
        var maxWidth = 0
        children.forEachIndexed { index, child ->
            measureChildWithMargins(
                child,
                widthMeasureSpec,
                0,
                heightMeasureSpec,
                heightUsed
            )

            if (widthSpecMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.measuredWidth > widthSpecSize) {
                heightUsed += lineHeight
                lineWidthUsed = 0
                lineHeight = 0

                measureChildWithMargins(
                    child,
                    widthMeasureSpec,
                    0,
                    heightMeasureSpec,
                    heightUsed
                )

            }

            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            val childBounds = childrenBounds[index]
            childBounds.set(
                lineWidthUsed,
                heightUsed,
                lineWidthUsed + child.measuredWidth,
                heightUsed + child.measuredHeight
            )
            lineWidthUsed += child.measuredWidth
            lineHeight = maxOf(lineHeight, child.measuredHeight)
            maxWidth = maxOf(lineWidthUsed, maxWidth)

        }

        setMeasuredDimension(maxWidth, heightUsed + lineHeight)

    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        children.forEachIndexed { index, child ->
            val rect = childrenBounds[index]
            child.layout(rect.left, rect.top, rect.right, rect.bottom)
        }
    }


//            val layoutParams = child.layoutParams
//            var childWidthSpecMode = 0
//            var childWidthSpecSize = 0
//            when (layoutParams.width) {
//                LayoutParams.MATCH_PARENT -> {
//                    when (widthSpecMode) {
//                        MeasureSpec.EXACTLY, MeasureSpec.AT_MOST -> {
//                            childWidthSpecMode = MeasureSpec.EXACTLY
//                            childWidthSpecSize = widthSpecSize - widthUsed
//                        }
//                        MeasureSpec.UNSPECIFIED -> {
//                            childWidthSpecMode = MeasureSpec.UNSPECIFIED
//                            childWidthSpecSize = 0
//                        }
//                    }
//                }
//                LayoutParams.WRAP_CONTENT -> {
//                    when (widthSpecMode) {
//                        MeasureSpec.EXACTLY, MeasureSpec.AT_MOST -> {
//                            childWidthSpecMode = MeasureSpec.AT_MOST
//                            childWidthSpecSize = widthSpecSize - widthUsed
//                        }
//                        MeasureSpec.UNSPECIFIED -> {
//                            childWidthSpecMode = MeasureSpec.UNSPECIFIED
//                            childWidthSpecSize = 0
//                        }
//                    }
//                }
//                else -> {
//                    childWidthSpecMode = MeasureSpec.EXACTLY
//                    childWidthSpecSize = layoutParams.width
//                }
//            }
//            child.measure(MeasureSpec.makeMeasureSpec(childWidthSpecMode, childWidthSpecSize),)

}