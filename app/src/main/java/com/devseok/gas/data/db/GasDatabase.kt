package com.devseok.gas.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter

/**
 * @author Ha Jin Seok
 * @created 2023-08-21
 * @desc
 */

@Database(entities = [DetailOIL::class], version = 3, exportSchema = false)
abstract class GasDatabase : RoomDatabase() {

    abstract fun detailOILRepository() : DetailOILRepository

    companion object {

        @Volatile
        private var INSTANCE: GasDatabase? = null

        fun getDatabase(
            context: Context
        ): GasDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GasDatabase::class.java,
                    "db-gas"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}