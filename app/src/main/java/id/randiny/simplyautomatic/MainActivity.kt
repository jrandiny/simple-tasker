package id.randiny.simplyautomatic

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import id.randiny.simplyautomatic.ui.RoutineListPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup tab
        setContentView(R.layout.activity_main)
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

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Create new action", Snackbar.LENGTH_LONG).show()
        }

        // Setup service
        if(!MainService.isInstanceCreated()){
            val intent = Intent(this, MainService::class.java)
            ContextCompat.startForegroundService(this, intent)
        }
    }
}