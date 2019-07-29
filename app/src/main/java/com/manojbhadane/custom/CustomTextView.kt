package com.manojbhadane.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.manojbhadane.R
import com.manojbhadane.app.App

/**
 * Custom textview to add additional custom features
 */
class CustomTextView : AppCompatTextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {

        // do customisation if required in future

        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        val customFont = a.getInt(R.styleable.CustomTextView_fontName, 0)

        try {
            typeface = when (customFont) {
                1 -> App.typefaceRegular!!
                2 -> App.typefaceBold!!
                else -> App.typefaceRegular!!
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        a.recycle()
    }
}