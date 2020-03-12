package id.randiny.simplyautomatic.module.sonoff

import android.content.Context
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
    }

    private val queue = Volley.newRequestQueue(context)

    override fun action() {
        Log.d(LOG_TAG, "Triggering sonoff module with param $param")

        val url = "http://10.0.2.2:3000"
        val req = StringRequest(Request.Method.GET, url, null, Response.ErrorListener {
            Log.e(LOG_TAG, it.toString())
        })

        queue.add(req)
    }
}