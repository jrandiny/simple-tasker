package id.randiny.simplyautomatic.module.wifi

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import id.randiny.simplyautomatic.module.Module

class WifiModule(
    identifier: Int,
    private val param: Map<String, String>,
    private val context: Context
) :
    Module(identifier, param, context) {

    companion object {
        private const val LOG_TAG = "My/WifiModule"

        const val PARAM_TOGGLE = "toggle_to"
    }

    private lateinit var wifiManager: WifiManager

    override fun action() {
        Log.d(LOG_TAG, "Triggering wifi action with param $param")

        wifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            @Suppress("DEPRECATION")
            wifiManager.isWifiEnabled = !wifiManager.isWifiEnabled
        } else {
            Log.e(LOG_TAG, "API not supported")
        }
    }
}