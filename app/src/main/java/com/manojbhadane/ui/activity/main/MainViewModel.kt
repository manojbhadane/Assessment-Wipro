package com.manojbhadane.ui.activity.main

import androidx.lifecycle.MutableLiveData
import com.manojbhadane.app.App
import com.manojbhadane.app.Constant
import com.manojbhadane.base.BaseViewModel
import com.manojbhadane.model.response.country.CountryInfoModel
import com.manojbhadane.model.response.country.Rows
import com.manojbhadane.network.NetworkManager
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * ViewModel for mainActivity
 */
class MainViewModel : BaseViewModel() {

    // init variables
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mCountryInfoModelMutableData = MutableLiveData<CountryInfoModel>()

    /**
     * fun to get data and should be observable
     */
    fun getCountryData(): MutableLiveData<CountryInfoModel> {
        if (NetworkManager.isConnectingToInternet(App.sInstance)) {
            mErrorInternet.postValue(false)
            mLoading.postValue(true)
            mApiInterface.getInfo(Constant.URL_API)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<CountryInfoModel> {

                        override fun onComplete() {
                            mLoading.postValue(false)
                        }

                        override fun onSubscribe(d: Disposable) {
                            mCompositeDisposable.add(d)
                        }

                        override fun onNext(t: CountryInfoModel) {
                            mCountryInfoModelMutableData.postValue(t)
                        }

                        override fun onError(e: Throwable) {
                            mLoading.postValue(false)
                            mError.postValue(e.message)
                            e.printStackTrace()
                        }
                    })
        } else {
            mErrorInternet.postValue(true)
        }
        return mCountryInfoModelMutableData
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
    }

}