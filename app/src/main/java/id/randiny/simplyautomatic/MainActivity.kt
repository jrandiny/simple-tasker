package id.randiny.simplyautomatic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import id.randiny.simplyautomatic.data.ModuleConfig
import id.randiny.simplyautomatic.data.RoutineViewModel
import id.randiny.simplyautomatic.data.RoutineViewModelFactory
import id.randiny.simplyautomatic.module.ModuleType
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

        // Setup VM
        val routineViewModel =
            ViewModelProvider(this, RoutineViewModelFactory(this)).get(RoutineViewModel::class.java)


        // Setup FAB
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener {
            val sampleParam = mutableMapOf<String, Any>()
            sampleParam.put("sample1", 123)
            sampleParam.put("sample2", "asd")
            val trigger = ModuleConfig(ModuleType.TIME, sampleParam)
            val action = ModuleConfig(ModuleType.API, sampleParam)
            routineViewModel.addItem(trigger, action)
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
    }
}