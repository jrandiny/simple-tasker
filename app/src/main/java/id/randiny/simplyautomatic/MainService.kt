package id.randiny.simplyautomatic

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import id.randiny.simplyautomatic.data.MainDatabase
import id.randiny.simplyautomatic.data.Routine
import id.randiny.simplyautomatic.data.RoutineDAO
import id.randiny.simplyautomatic.module.Module
import id.randiny.simplyautomatic.module.ModuleFactory

class MainService : Service() {
    private lateinit var mainDatabase: MainDatabase
    private lateinit var routineDAO: RoutineDAO

    private val condition: MutableList<Module> = mutableListOf()
    private val action: MutableList<Module> = mutableListOf()

    private val routineObserver = Observer<List<Routine>> {
        cleanupService()
        condition.clear()
        action.clear()
        Log.d(LOG_TAG, "Get ${it.size} new routines")
        for (routine in it) {
            condition.add(ModuleFactory.createModule(routine, true, this))
            action.add(ModuleFactory.createModule(routine, false, this))
        }
        prepareService()
    }

    companion object {
        private var INSTANCE: MainService? = null
        fun isInstanceCreated(): Boolean {
            return INSTANCE != null
        }

        private const val SERVICE_NOTIFICATION_ID = 1
        private const val SERVICE_CHANNEL_ID = "Service"
        private const val SERVICE_CHANNEL_NAME = "Service Notification"
        private const val LOG_TAG = "My/Service"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                SERVICE_CHANNEL_ID,
                SERVICE_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_NONE
            )
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

        mainDatabase = MainDatabase.getDatabase(applicationContext)
        routineDAO = mainDatabase.routineDAO()

        routineDAO.getAllActive().observeForever(routineObserver)

        startForeground(SERVICE_NOTIFICATION_ID, notification)
        Log.d(LOG_TAG, "Service start")
        INSTANCE = this

        return START_STICKY
    }

    override fun onDestroy() {
        INSTANCE = null
        routineDAO.getAllActive().removeObserver(routineObserver)
        Log.d(LOG_TAG, "Service destroyed")
    }

    private fun cleanupService() {
        Log.d(LOG_TAG, "Cleaning up module")
        for (module in condition) {
            module.destroyListener()
        }
    }

    private fun prepareService() {
        Log.d(LOG_TAG, "Preparing ${condition.size} modules")
        for (i in 0 until condition.size) {
            condition[i].setupListener(action[i])
        }
    }
}