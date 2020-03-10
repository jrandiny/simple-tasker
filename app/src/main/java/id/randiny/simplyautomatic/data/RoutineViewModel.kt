package id.randiny.simplyautomatic.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RoutineViewModel(context: Context) : ViewModel() {
    private val mainDatabase = MainDatabase.getDatabase(context)
    private val routineDAO = mainDatabase.routineDAO()

    val activeList: LiveData<List<Routine>> = routineDAO.getAllActive()
    val inactiveList: LiveData<List<Routine>> = routineDAO.getAllInactive()

    fun addItem(trigger: ModuleConfig, action: ModuleConfig) {
        val routine = Routine(0,true,trigger.type, trigger.param, action.type, action.param)
        Log.d("My/RoutineViewModel","Inserting entry")
        viewModelScope.launch {
            routineDAO.insert(routine)
        }
    }

}