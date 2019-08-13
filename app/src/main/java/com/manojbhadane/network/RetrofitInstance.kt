package com.manojbhadane.network

import com.manojbhadane.app.App
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Singleton mRetrofit class
 */
object RetrofitInstance {

    private var BASE_URL: String? = null
    private var mRetrofit: Retrofit? = null

    private const val mCacheSize = 10 * 1024 * 1024 // 10 MB

    /**
     * init mRetrofit sInstance
     */
    fun init(baseURL: String) {
        BASE_URL = baseURL
        getInstance()
    }

    /**
     * get mRetrofit sInstance
     */
    private fun getInstance(): Retrofit {
        if (mRetrofit == null) {

            val lCache = Cache(App.sInstance.cacheDir, mCacheSize.toLong())

            val lInterceptor = HttpLoggingInterceptor()
            lInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val lClient = OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(lInterceptor)
                    .cache(lCache)
                    .build()

            mRetrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL!!)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(lClient)
                    .build()
        }
        return mRetrofit!!
    }

    /**
     * init api services
     */
    fun <T> getService(service: Class<T>): T {
        return getInstance().create(service)
    }
}