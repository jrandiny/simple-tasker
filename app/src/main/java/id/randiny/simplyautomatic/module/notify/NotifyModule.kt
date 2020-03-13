package id.randiny.simplyautomatic.module.notify

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.module.Module

class NotifyModule(
    private val identifier: Int,
    private val param: Map<String, String>,
    private val context: Context
) :
    Module(identifier, param, context) {

    companion object {
        private const val LOG_TAG = "My/NotifyModule"
        private const val NOTIF_CHANNEL_ID = "notification"
        private const val NOTIF_CHANNEL_NAME = "Notification module"

        const val PARAM_NOTIFICATION_TITLE = "param_title"
        const val PARAM_NOTIFICATION_CONTENT = "param_content"
    }

    override fun action() {
        Log.d(LOG_TAG, "Preparing notification with param $param")
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIF_CHANNEL_ID,
                NOTIF_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, NOTIF_CHANNEL_ID)
            .setContentTitle(param.get(PARAM_NOTIFICATION_TITLE))
            .setContentText(param.get(PARAM_NOTIFICATION_CONTENT))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notificationId = NotificationIdGenerator.generateId(identifier)
        Log.d(LOG_TAG, "Creating notification with id $notificationId")
        notificationManager.notify(notificationId, notification)
    }
}