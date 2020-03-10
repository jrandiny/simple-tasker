package id.randiny.simplyautomatic

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService

class MainService:Service() {
    private val SERVICE_NOTIFICATION_ID = 1
    private val SERVICE_CHANNEL_ID = "Service"
    private val SERVICE_CHANNEL_NAME = "Service Notification"


    companion object{
        private var INSTANCE: MainService? = null
        fun isInstanceCreated():Boolean {
            return INSTANCE != null;
        }
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(SERVICE_CHANNEL_ID, SERVICE_CHANNEL_NAME,NotificationManager.IMPORTANCE_NONE)
            channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(this, SERVICE_CHANNEL_ID)
            .setContentTitle(getText(R.string.app_name))
            .setContentText(getText(R.string.notification_text))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .build()

        startForeground(SERVICE_NOTIFICATION_ID, notification)
        Toast.makeText(this, "service start", Toast.LENGTH_LONG).show()
        INSTANCE = this
        return START_STICKY
    }

    override fun onDestroy() {
        INSTANCE = null
        Toast.makeText(this, "service done", Toast.LENGTH_LONG).show()
    }
}