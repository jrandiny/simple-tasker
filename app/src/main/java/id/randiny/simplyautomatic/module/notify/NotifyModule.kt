package id.randiny.simplyautomatic.module.notify

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.module.Module

class NotifyModule(routineId: Int, context: Context) :
    Module(routineId, context) {

    private lateinit var context: Context

    companion object {
        private const val NOTIF_CHANNEL_ID = "notif"
        private const val NOTIF_CHANNEL_NAME = "Notification module"
    }

    override fun init(context: Context) {
    }

    override fun action() {
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
            .setContentTitle("test")
            .setContentText("test")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
    }
}