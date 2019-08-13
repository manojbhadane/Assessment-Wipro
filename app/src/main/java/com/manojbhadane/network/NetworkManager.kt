package com.manojbhadane.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkManager {

    /**
     * Returns
     * TRUE     - if internet available
     * FALSE    - if internet unavailable
     */
    fun isConnectingToInternet(context: Context): Boolean {
        val lConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val lActiveNetwork: NetworkInfo? = lConnectivityManager.activeNetworkInfo
        return lActiveNetwork?.isConnected == true
    }
}
