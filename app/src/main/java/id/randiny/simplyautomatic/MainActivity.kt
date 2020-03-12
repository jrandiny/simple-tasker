package id.randiny.simplyautomatic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import id.randiny.simplyautomatic.ui.RoutineListPagerAdapter

class MainActivity : AppCompatActivity() {

    private val CREATE_ROUTINE_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup tab
        setContentView(R.layout.main_activity)
        val routineListPagerAdapter =
            RoutineListPagerAdapter(
                this,
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = routineListPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // Setup FAB
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        // Setup service
        if (!MainService.isInstanceCreated()) {
            val intent = Intent(this, MainService::class.java)
            ContextCompat.startForegroundService(this, intent)
        }

//         Setup option
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.option_button, FragmentSetting())
//            .commit()

        // Alarm manager
        // Get AlarmManager instance
//        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//        // Intent part
//        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
//        intent.action = "FOO_ACTION"
//        intent.putExtra("KEY_FOO_STRING", "Medium AlarmManager Demo")
//
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//
//        // Alarm time
//        val ALARM_DELAY_IN_SECOND = 10
//        val alarmTimeAtUTC = System.currentTimeMillis() + ALARM_DELAY_IN_SECOND * 1_000L
//
//    // Set with system Alarm Service
//    // Other possible functions: setExact() / setRepeating() / setWindow(), etc
//        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTimeAtUTC, pendingIntent)
    }
}