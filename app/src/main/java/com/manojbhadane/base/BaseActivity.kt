package com.manojbhadane.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import java.lang.Class as Class1

abstract class BaseActivity<B : ViewDataBinding, M : ViewModel> : AppCompatActivity() {

    private lateinit var mDataBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutResId())
        val viewModel = ViewModelProviders.of(this).get(getViewModel())
        init(mDataBinding, viewModel)
    }

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    protected abstract fun getViewModel(): Class1<M>

    protected abstract fun init(dataBinding: B, viewModel: M)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}