package com.manojbhadane.ui.activity.main

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.manojbhadane.R
import com.manojbhadane.adapter.AboutAdapter
import com.manojbhadane.base.BaseActivity
import com.manojbhadane.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var mAdapter: AboutAdapter

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun init(dataBinding: ActivityMainBinding, viewModel: MainViewModel) {

        dataBinding.laySwipeRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                viewModel.getCountryData()
                dataBinding.laySwipeRefresh.isRefreshing = false
            }
        })

        /**
         * Observe data model
         */
        viewModel.getCountryData().observe(this, Observer {
            supportActionBar!!.title = it.title
            mAdapter = AboutAdapter(this, it.rows)
            dataBinding.recyclerview.adapter = mAdapter
        })

        /**
         * Observe loading state
         */
        viewModel.onLoading().observe(this, Observer {
            dataBinding.prgbar.visibility = if (it) View.VISIBLE else View.INVISIBLE
        })

        /**
         * Observe error state
         */
        viewModel.onError().observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        })
    }
}
