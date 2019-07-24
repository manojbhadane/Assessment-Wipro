package com.manojbhadane.network

import com.manojbhadane.model.response.country.CountryInfoModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET
    fun getInfo(@Url url: String): Observable<CountryInfoModel>

}