package com.manojbhadane.ui.activity.main

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.manojbhadane.R
import com.manojbhadane.adapter.ConuntryAdapter
import com.manojbhadane.base.BaseActivity
import com.manojbhadane.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var mCountryAdapter: ConuntryAdapter

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun init(dataBinding: ActivityMainBinding, viewModel: MainViewModel) {

        dataBinding.laySwipeRefresh.setOnRefreshListener {
            viewModel.getCountryData()
            dataBinding.laySwipeRefresh.isRefreshing = false
        }

        /**
         * Observe data model
         */
        viewModel.getCountryData().observe(this, Observer {
            supportActionBar!!.title = it.title
            mCountryAdapter = ConuntryAdapter(this, it.rows)
            dataBinding.recyclerview.adapter = mCountryAdapter
        })

        /**
         * Observe mLoading state
         */
        viewModel.onLoading().observe(this, Observer {
            dataBinding.prgbar.visibility = if (it) View.VISIBLE else View.INVISIBLE
        })

        /**
         * Observe mError state
         */
        viewModel.onError().observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        })

        /**
         * Observer No Internet state
         */
        viewModel.onErrorNoInternet().observe(this, Observer {
            dataBinding.txtError.visibility = if (it) View.VISIBLE else View.GONE
            dataBinding.prgbar.visibility = View.GONE
        })
    }
}
