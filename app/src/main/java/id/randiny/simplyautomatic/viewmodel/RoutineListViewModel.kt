package id.randiny.simplyautomatic.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.randiny.simplyautomatic.data.MainDatabase
import id.randiny.simplyautomatic.data.ModuleConfig
import id.randiny.simplyautomatic.data.Routine
import kotlinx.coroutines.launch

class RoutineListViewModel(context: Context) : ViewModel() {
    private val mainDatabase =
        MainDatabase.getDatabase(
            context
        )
    private val routineDAO = mainDatabase.routineDAO()

    val activeList: LiveData<List<Routine>> = routineDAO.getAllActive()
    val inactiveList: LiveData<List<Routine>> = routineDAO.getAllInactive()

    fun addItem(name: String, trigger: ModuleConfig, action: ModuleConfig) {
        val routine = Routine(
            0,
            true,
            name,
            trigger.name,
            trigger.type,
            trigger.param,
            action.name,
            action.type,
            action.param
        )
        Log.d("My/RoutineViewModel", "Inserting entry")
        viewModelScope.launch {
            routineDAO.insert(routine)
        }
    }

    fun toggleActivation(id: Int) {
        Log.d("My/RoutineViewModel", "Toggle entry $id")
        viewModelScope.launch {
            routineDAO.toggleActivation(id)
        }
    }

    fun deleteRoutine(id: Int) {
        Log.d("My/RoutineViewModel", "Delete entry $id")
        viewModelScope.launch {
            routineDAO.delete(id)
        }
    }

}