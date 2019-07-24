package com.manojbhadane.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.innoplexus.patientapp.network.RetrofitInstance
import com.manojbhadane.network.ApiInterface

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