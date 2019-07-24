package com.manojbhadane.adapter

import android.content.Context
import android.view.View
import com.manojbhadane.R
import com.manojbhadane.base.GenericAdapter
import com.manojbhadane.databinding.ListitemAboutBinding
import com.manojbhadane.model.response.country.Rows
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AboutAdapter(context: Context, arrayList: ArrayList<Rows>) : GenericAdapter<Rows, ListitemAboutBinding>(context, arrayList) {

    override val layoutResId: Int
        get() = R.layout.listitem_about

    override fun onBindData(model: Rows, position: Int, dataBinding: ListitemAboutBinding) {
        dataBinding.txtTitle.text = model.title
        dataBinding.txtDescription.text = model.description

        Picasso.get().load(model.imageHref).into(dataBinding.image, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                dataBinding.image.visibility = View.GONE
            }
        })
    }

    override fun onItemClick(model: Rows, position: Int) {

    }
}