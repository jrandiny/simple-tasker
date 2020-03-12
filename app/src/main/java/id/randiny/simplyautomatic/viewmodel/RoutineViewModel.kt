package id.randiny.simplyautomatic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.randiny.simplyautomatic.data.ModuleConfig

class RoutineViewModel : ViewModel() {
    private val condition: MutableLiveData<ModuleConfig> = MutableLiveData()
    private val action: MutableLiveData<ModuleConfig> = MutableLiveData()

    val validConfig: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        var conditionStatus = false
        var actionStatus = false

        fun update() {
            this.postValue(conditionStatus && actionStatus)
        }

        addSource(condition) {
            conditionStatus = it != null
            update()
        }
        addSource(action) {
            actionStatus = it != null
            update()
        }
    }

    fun getCondition(): LiveData<ModuleConfig> {
        return condition
    }

    fun setCondition(data: ModuleConfig) {
        condition.postValue(data)
    }

    fun getAction(): LiveData<ModuleConfig> {
        return action
    }

    fun setAction(data: ModuleConfig) {
        action.postValue(data)
    }

}