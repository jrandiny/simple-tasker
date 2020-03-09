package id.randiny.simplyautomatic.data.moduleconfig

import androidx.lifecycle.LiveData
import androidx.room.*
import id.randiny.simplyautomatic.data.moduleconfig.ModuleConfig
import id.randiny.simplyautomatic.data.routine.Routine
import id.randiny.simplyautomatic.data.routine.RoutineAndModule

@Dao
interface RoutineDAO {
    @Transaction
    @Query("SELECT * FROM Routine WHERE active = 1")
    fun getAllActive(): LiveData<List<RoutineAndModule>>

    @Transaction
    @Query("SELECT * FROM Routine WHERE active = 0")
    fun getAllInactive(): LiveData<List<RoutineAndModule>>

    @Update
    suspend fun update(routine: Routine)

    @Insert
    suspend fun insert(routine: Routine)

    @Delete
    suspend fun delete(routine: Routine)
}