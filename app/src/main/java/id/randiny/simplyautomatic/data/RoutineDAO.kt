package id.randiny.simplyautomatic.data.moduleconfig

import androidx.lifecycle.LiveData
import androidx.room.*
import id.randiny.simplyautomatic.data.Routine

@Dao
interface RoutineDAO {
    @Query("SELECT * FROM Routine WHERE active = 1")
    fun getAllActive(): LiveData<List<Routine>>

    @Query("SELECT * FROM Routine WHERE active = 0")
    fun getAllInactive(): LiveData<List<Routine>>

    @Update
    suspend fun update(routine: Routine)

    @Insert
    suspend fun insert(routine: Routine)

    @Delete
    suspend fun delete(routine: Routine)
}