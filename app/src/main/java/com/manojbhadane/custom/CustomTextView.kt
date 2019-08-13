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
        val lTypeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        val lCustomFont = lTypeArray.getInt(R.styleable.CustomTextView_fontName, 0)

        try {
            typeface = when (lCustomFont) {
                1 -> App.sTypefaceRegular!!
                2 -> App.sTypefaceBold!!
                else -> App.sTypefaceRegular!!
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        lTypeArray.recycle()
    }
}