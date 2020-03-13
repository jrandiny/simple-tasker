package id.randiny.simplyautomatic.module.time

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import id.randiny.simplyautomatic.module.Module


class TimeModule(
    private val identifier: Int,
    private val param: Map<String, String>,
    private val context: Context
) :
    Module(identifier, param, context) {

    private var alarmIntent: PendingIntent? = null
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private lateinit var receiver: BroadcastReceiver

    companion object {
        const val PARAM_TIMESTATUS = "toggle_to"
        const val PARAM_DAYS = ""
        const val PARAM_DATE = ""
        const val PARAM_TIME = ""

        private const val LOG_TAG = "My/TimeModule"

        private const val ACTION_STRING = "id.randiny.simple-tasker.action.time"
        private const val IDENTIFIER_EXTRA = "extra_id"
    }

    private class AlarmReceiver(private val action: Module) : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent
        ) {
            val id = intent.getIntExtra(IDENTIFIER_EXTRA, -1)
            Log.d(LOG_TAG, "Time callback of id $id called")
            action.action()
        }
    }

    override fun setupListener(action: Module) {
        Log.d(LOG_TAG, "Init listener with $param")

        receiver = AlarmReceiver(action)

        val intent = Intent("$ACTION_STRING.$identifier")
        intent.putExtra(IDENTIFIER_EXTRA, identifier)
        alarmIntent = PendingIntent.getBroadcast(context, identifier, intent, 0)

//        alarmManager.setExactAndAllowWhileIdle(
//            AlarmManager.RTC_WAKEUP,
//            System.currentTimeMillis(),
//            alarmIntent
//        )

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            60000,
            alarmIntent
        )

        val filter = IntentFilter("$ACTION_STRING.$identifier")
        context.registerReceiver(receiver, filter)
    }

    override fun destroyListener() {
        if (alarmIntent != null) {
            alarmManager.cancel(alarmIntent)
        }
        context.unregisterReceiver(receiver)
    }

    override fun action() {
        Log.d(LOG_TAG, "Triggering time module with param $param")

    }
}