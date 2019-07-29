package com.manojbhadane.app

import android.app.Application
import android.graphics.Typeface
import com.manojbhadane.network.RetrofitInstance

class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        var typefaceBold: Typeface? = null
        var typefaceRegular: Typeface? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        typefaceBold = Typeface.createFromAsset(assets, "fonts/Montserrat-Bold.ttf")
        typefaceRegular = Typeface.createFromAsset(assets, "fonts/Montserrat-Regular.ttf")

        RetrofitInstance.init("https://dl.dropboxusercontent.com/")
    }
}