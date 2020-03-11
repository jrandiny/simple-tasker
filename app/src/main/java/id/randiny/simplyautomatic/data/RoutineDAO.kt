package id.randiny.simplyautomatic.data

import androidx.lifecycle.LiveData
import androidx.room.*

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