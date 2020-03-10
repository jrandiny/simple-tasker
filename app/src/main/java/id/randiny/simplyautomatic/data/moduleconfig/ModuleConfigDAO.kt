package id.randiny.simplyautomatic.data.moduleconfig

import androidx.lifecycle.LiveData
import androidx.room.*
import id.randiny.simplyautomatic.data.moduleconfig.ModuleConfig

@Dao
interface ModuleConfigDAO {
    @Query("SELECT * FROM ModuleConfig WHERE id = :id")
    fun getById(id: Int): LiveData<ModuleConfig>

    @Update
    suspend fun update(moduleConfig: ModuleConfig)

    @Insert
    suspend fun insert(moduleConfig: ModuleConfig)

    @Delete
    suspend fun delete(moduleConfig: ModuleConfig)
}