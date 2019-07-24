package com.manojbhadane.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manojbhadane.network.ApiInterface

/**
 * Base class for viewModel
 */
open class BaseViewModel : ViewModel() {

    var apiInterface: ApiInterface = RetrofitInstance.getService(ApiInterface::class.java)

    var error = MutableLiveData<String>()

    var loading = MutableLiveData<Boolean>()

    fun onError(): MutableLiveData<String> {
        return error
    }

    fun onLoading(): MutableLiveData<Boolean> {
        return loading
    }

}