package com.manojbhadane.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manojbhadane.network.ApiInterface
import com.manojbhadane.network.RetrofitInstance

/**
 * Base class for viewModel
 */
open class BaseViewModel : ViewModel() {

    var mApiInterface: ApiInterface = RetrofitInstance.getService(ApiInterface::class.java)

    var mError = MutableLiveData<String>()

    var mErrorInternet = MutableLiveData<Boolean>()

    var mLoading = MutableLiveData<Boolean>()

    fun onError(): MutableLiveData<String> {
        return mError
    }

    fun onLoading(): MutableLiveData<Boolean> {
        return mLoading
    }

    fun onErrorNoInternet(): MutableLiveData<Boolean> {
        return mErrorInternet
    }

}