package id.randiny.simplyautomatic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import id.randiny.simplyautomatic.data.ModuleConfig
import id.randiny.simplyautomatic.data.RoutineViewModel
import id.randiny.simplyautomatic.data.RoutineViewModelFactory
import id.randiny.simplyautomatic.module.ModuleType


class CreateActivity : AppCompatActivity() {

    private lateinit var routineViewModel: RoutineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        routineViewModel =
            ViewModelProvider(this, RoutineViewModelFactory(this)).get(RoutineViewModel::class.java)

        findViewById<MaterialButton>(R.id.create_btn).setOnClickListener {
            val sampleParam = mutableMapOf<String, Any>()
            sampleParam.put("sample1", 123)
            sampleParam.put("sample2", "asd")
            val trigger = ModuleConfig(ModuleType.TIME, sampleParam)
            val action = ModuleConfig(ModuleType.API, sampleParam)
            routineViewModel.addItem(trigger, action)
            finish()
        }
    }

}
