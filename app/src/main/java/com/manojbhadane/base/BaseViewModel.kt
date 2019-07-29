package com.manojbhadane.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manojbhadane.network.ApiInterface
import com.manojbhadane.network.RetrofitInstance

/**
 * Base class for viewModel
 */
open class BaseViewModel : ViewModel() {

    var apiInterface: ApiInterface = RetrofitInstance.getService(ApiInterface::class.java)

    var error = MutableLiveData<String>()

    var errorInternet = MutableLiveData<Boolean>()

    var loading = MutableLiveData<Boolean>()

    fun onError(): MutableLiveData<String> {
        return error
    }

    fun onLoading(): MutableLiveData<Boolean> {
        return loading
    }

    fun onErrorNoInternet(): MutableLiveData<Boolean> {
        return errorInternet
    }

}