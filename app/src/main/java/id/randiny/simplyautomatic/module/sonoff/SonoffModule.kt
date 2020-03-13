package id.randiny.simplyautomatic.module.sonoff

import android.content.Context
import android.os.Build
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import id.randiny.simplyautomatic.module.Module

class SonoffModule(
    identifier: Int,
    private val param: Map<String, String>,
    context: Context
) :
    Module(identifier, param, context) {

    companion object {
        private const val LOG_TAG = "My/SonoffModule"

        const val PARAM_TOGGLE = "toggle_to"
        const val PARAM_USERNAME = "username"
        const val PARAM_PASSWORD = "passsword"
        const val PARAM_IPADDRESS = "address"
    }

    private val queue = Volley.newRequestQueue(context)

    override fun action() {
        Log.d(LOG_TAG, "Triggering sonoff module with param $param")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val onOff = param.get(PARAM_TOGGLE)
            val username = param.get(PARAM_USERNAME)
            val password = param.get(PARAM_PASSWORD)
            val ipAddress = param.get(PARAM_IPADDRESS)

            if (onOff != null && username != null && password != null && ipAddress != null) {
                val toggleState = onOff == "1"
                val cmnd: String = if (toggleState) {
                    "Power ON"
                } else {
                    "Power OFF"
                }

                val stringRequest = object : StringRequest(Method.GET, ipAddress,
                    Response.Listener {
                        Log.d(LOG_TAG, it)
                    },
                    Response.ErrorListener {
                        Log.e(LOG_TAG, it.toString())
                    }) {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["user"] = username.toString()
                        params["password"] = password.toString()
                        params["cmnd"] = cmnd
                        return params
                    }
                }
                queue.add(stringRequest)
            }

        } else {
            Log.e(LOG_TAG, "API not supported")
        }
    }
}