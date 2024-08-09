package com.devtics.intenert_conected

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity

class Network {
    companion object {
        fun isConnected(activity:AppCompatActivity):Boolean {
            val conManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val network = conManager.activeNetwork ?: return false
                val capabilities = conManager.getNetworkCapabilities(network) ?: return false
                return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            } else {
                val networkInfo = conManager.activeNetworkInfo
                return networkInfo != null && networkInfo.isConnected
            }
        }
    }
}