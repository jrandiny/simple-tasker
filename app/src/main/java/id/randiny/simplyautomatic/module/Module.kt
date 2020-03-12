package id.randiny.simplyautomatic.module

import android.content.Context
import androidx.lifecycle.LiveData
import id.randiny.simplyautomatic.data.MainDatabase
import id.randiny.simplyautomatic.data.Routine

abstract class Module(
    private val routineId: Int,
    private val context: Context
) {

    private val mainDatabase =
        MainDatabase.getDatabase(
            context
        )
    private val routine: LiveData<Routine> = mainDatabase.routineDAO().getById(routineId)

    abstract fun init(context: Context)

    open fun poll(): Boolean {
        return false
    }

    open fun action() {}

    open fun setupListener(action: Module) {}

    open fun destroyListener() {}
}