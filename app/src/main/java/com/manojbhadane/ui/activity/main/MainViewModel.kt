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
        if (NetworkManager.isConnectingToInternet(App.instance)) {
            errorInternet.postValue(false)
            loading.postValue(true)
            apiInterface.getInfo(Constant.URL_API)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<CountryInfoModel> {

                        override fun onComplete() {
                            loading.postValue(false)
                        }

                        override fun onSubscribe(d: Disposable) {
                            mCompositeDisposable.add(d)
                        }

                        override fun onNext(t: CountryInfoModel) {
                            // removing object if having all null values
                            val arraylist: ArrayList<Rows> = ArrayList()
                            for (row in t.rows) {
                                if (row.title != null && row.description != null && row.imageHref != null)
                                    arraylist.add(row)
                            }
                            t.rows = arraylist
                            mCountryInfoModelMutableData.postValue(t)
                        }

                        override fun onError(e: Throwable) {
                            loading.postValue(false)
                            error.postValue(e.message)
                            e.printStackTrace()
                        }
                    })
        } else {
            errorInternet.postValue(true)
        }
        return mCountryInfoModelMutableData
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
    }

}