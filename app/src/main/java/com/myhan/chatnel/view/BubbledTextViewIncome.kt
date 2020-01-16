package com.myhan.chatnel.view

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.widget.TextView

class BubbledTextViewIncome @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    private val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    init {
        setPadding(20.dp, 10.dp, 20.dp, 10.dp)
    }

    override fun onDraw(canvas: Canvas?) {
        val paint = Paint().apply { color = Color.parseColor("#ffa500") }

        val rect = Rect(0, 0, width, height)
        val rectF = RectF(rect)

        canvas?.drawRoundRect(rectF, 32f, 32f, paint)
        super.onDraw(canvas)
    }
}