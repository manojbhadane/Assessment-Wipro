package com.manojbhadane.app

import android.app.Application
import android.graphics.Typeface
import com.manojbhadane.network.RetrofitInstance

class App : Application() {

    companion object {
        lateinit var sInstance: App
            private set

        var sTypefaceBold: Typeface? = null
        var sTypefaceRegular: Typeface? = null
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this

        sTypefaceBold = Typeface.createFromAsset(assets, "fonts/Montserrat-Bold.ttf")
        sTypefaceRegular = Typeface.createFromAsset(assets, "fonts/Montserrat-Regular.ttf")

        RetrofitInstance.init("https://dl.dropboxusercontent.com/")
    }
}