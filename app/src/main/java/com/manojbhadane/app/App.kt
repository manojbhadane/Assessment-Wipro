package com.manojbhadane.app

import android.app.Application
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat

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

        typefaceBold = Typeface.createFromAsset(getAssets(), "Montserrat-Regular.ttf")
        typefaceRegular = Typeface.createFromAsset(getAssets(), "Montserrat-Bold.ttf")

        RetrofitInstance.init("https://dl.dropboxusercontent.com/")
    }
}