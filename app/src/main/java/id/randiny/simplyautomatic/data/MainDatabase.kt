package id.randiny.simplyautomatic.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Routine::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class MainDatabase : RoomDatabase() {
    abstract fun routineDAO(): RoutineDAO

    companion object {
        @Volatile
        private var mainDatabase: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {
            val instance = mainDatabase

            if (instance != null) {
                return instance
            }

            synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "main_database"
                ).build()
                mainDatabase = db
                return db
            }
        }
    }
}