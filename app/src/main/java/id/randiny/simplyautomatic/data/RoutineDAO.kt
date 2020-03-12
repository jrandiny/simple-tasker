package id.randiny.simplyautomatic.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoutineDAO {
    @Query("SELECT * FROM Routine WHERE active = 1")
    fun getAllActive(): LiveData<List<Routine>>

    @Query("SELECT * FROM Routine WHERE active = 0")
    fun getAllInactive(): LiveData<List<Routine>>

    @Query("SELECT * FROM Routine WHERE id=:id")
    fun getById(id: Int): LiveData<Routine>

    @Query("UPDATE Routine SET active = NOT active WHERE id = :id")
    suspend fun toggleActivation(id: Int)

    @Query("DELETE FROM Routine WHERE id= :id")
    suspend fun delete(id: Int)

    @Update
    suspend fun update(routine: Routine)

    @Insert
    suspend fun insert(routine: Routine)
}