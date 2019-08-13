package com.manojbhadane.custom

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manojbhadane.R

/**
 * Custom recyclerview to add additional custom features
 */
class CustomRecyclerView : RecyclerView {

    private val mVertical: Int = 1

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val lTypeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRecyclerView)
        val lOrientation = lTypeArray.getInt(R.styleable.CustomRecyclerView_listOrientation, mVertical)
        val lItemDecorator = lTypeArray.getBoolean(R.styleable.CustomRecyclerView_addItemDecorator, true)

        if (lItemDecorator)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        if (lOrientation == mVertical) {
            this.layoutManager = LinearLayoutManager(context, VERTICAL, false)
        } else {
            this.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        }

        lTypeArray.recycle()
    }


}