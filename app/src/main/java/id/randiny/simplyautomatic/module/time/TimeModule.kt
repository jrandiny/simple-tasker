package id.randiny.simplyautomatic.module.time

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import ca.antonious.materialdaypicker.MaterialDayPicker
import id.randiny.simplyautomatic.module.Module
import java.text.SimpleDateFormat
import java.util.*


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
        const val PARAM_ALARM_IS_ONESHOT = "param_type"
        const val PARAM_DAYS = "param_day"
        const val PARAM_DATE = "param_date"
        const val PARAM_TIME = "param_time"

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

        if (param.get(PARAM_ALARM_IS_ONESHOT) == "1") {
            Log.d(LOG_TAG, "Is oneshot")

            val dateText = param.get(PARAM_DATE)
            val timeText = param.get(PARAM_TIME)

            if (dateText != null && timeText != null) {
                val formatter = SimpleDateFormat("dd-MM-yyyyHH:mm", Locale.getDefault())
                val datetime = formatter.parse("$dateText$timeText")

                if (datetime != null) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        datetime.time,
                        alarmIntent
                    )
                } else {
                    Log.e(LOG_TAG, "Invalid datetime parsing")
                }
            } else {
                Log.e(LOG_TAG, "Invalid param")
            }
        } else {
            Log.d(LOG_TAG, "Is repeating")
            val dayText = param.get(PARAM_DAYS)
            val timeText = param.get(PARAM_TIME)

            if (dayText != null && timeText != null) {
                val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                val datetime = formatter.parse("$timeText")

                if (datetime != null) {
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = System.currentTimeMillis()

                    Log.d(LOG_TAG, "Set hour ${datetime.hours}:${datetime.minutes}")
                    calendar.set(Calendar.HOUR_OF_DAY, datetime.hours)
                    calendar.set(Calendar.MINUTE, datetime.minutes)

                    var repeatMillis: Long = 0
                    if (dayText != "none") {
                        Log.d(LOG_TAG, "Repeat weekly")
                        val dayOfWeek = MaterialDayPicker.Weekday.values()
                            .indexOf(MaterialDayPicker.Weekday.valueOf(dayText))
                        Log.d(LOG_TAG, "Day of week $dayOfWeek")
                        repeatMillis = AlarmManager.INTERVAL_DAY * 7
                        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek + 1)
                    } else {
                        Log.d(LOG_TAG, "Repeat daily")
                        repeatMillis = AlarmManager.INTERVAL_DAY
                    }


                    if (calendar.timeInMillis < System.currentTimeMillis()) {
                        calendar.add(Calendar.MILLISECOND, repeatMillis.toInt())
                    }

                    val startMillis = calendar.timeInMillis
                    Log.d(LOG_TAG, "Repeating start at $startMillis with interval $repeatMillis")
                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        startMillis,
                        repeatMillis,
                        alarmIntent
                    )
                } else {
                    Log.e(LOG_TAG, "Invalid datetime parsing")
                }

            } else {
                Log.e(LOG_TAG, "Invalid param")
            }
        }


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