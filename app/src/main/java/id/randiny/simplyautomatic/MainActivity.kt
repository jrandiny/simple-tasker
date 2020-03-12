package id.randiny.simplyautomatic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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
    }
}