package com.manojbhadane.adapter

import android.content.Context
import com.manojbhadane.R
import com.manojbhadane.app.App
import com.manojbhadane.base.GenericAdapter
import com.manojbhadane.databinding.ListitemAboutBinding
import com.manojbhadane.model.response.country.Rows
import com.squareup.picasso.Picasso

class AboutAdapter(context: Context, arrayList: ArrayList<Rows>) : GenericAdapter<Rows, ListitemAboutBinding>(context, arrayList) {

    override val layoutResId: Int
        get() = R.layout.listitem_about

    override fun onBindData(model: Rows, position: Int, dataBinding: ListitemAboutBinding) {
        dataBinding.model = model

        // lazy image loading
        Picasso.get().load(model.imageHref)
                .placeholder(App.instance.getDrawable(android.R.drawable.ic_menu_report_image))
                .into(dataBinding.image)
    }

    override fun onItemClick(model: Rows, position: Int) {
    }
}