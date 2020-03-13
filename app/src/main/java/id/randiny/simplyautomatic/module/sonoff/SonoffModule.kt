package id.randiny.simplyautomatic.module.sonoff

import android.content.Context
import android.os.Build
import android.util.Log
import com.android.volley.Request
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
        const val PARAM_USERNAME = ""
        const val PARAM_PASSWORD = ""
        const val PARAM_IPADDRESS = ""
    }

    private val queue = Volley.newRequestQueue(context)

    override fun action() {
        Log.d(LOG_TAG, "Triggering sonoff module with param $param")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val onOff = param.get(PARAM_TOGGLE)
            val username = param.get(PARAM_USERNAME)
            val password = param.get(PARAM_PASSWORD)
            val ipAddress = param.get(PARAM_IPADDRESS)
            onOff?.let {
                val toggleState = onOff == "1"
                var cmnd: String
                cmnd = if (toggleState) {
                    "Power ON"
                } else {
                    "Power OFF"
                }
                val params = HashMap<String,String>()
                params["user"] = username.toString()
                params["password"] = password.toString()
                params["cmnd"] = cmnd

                val stringRequest = object : StringRequest(Request.Method.GET, ipAddress,
                    Response.Listener<String> {
                        Log.d(LOG_TAG, it)
                    },
                    Response.ErrorListener {
                        Log.e(LOG_TAG, it.toString())
                    })  {
                    override fun getParams(): MutableMap<String,String> {
                        return params
                    }
                }
                queue.add(stringRequest)
            }!!
        } else {
            Log.e(LOG_TAG, "API not supported")
        }


        val url = "http://10.0.2.2:3000"
        val req = StringRequest(Request.Method.GET, url, null, Response.ErrorListener {
            Log.e(LOG_TAG, it.toString())
        })
        queue.add(req)



    }
}