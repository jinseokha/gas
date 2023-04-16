package com.devseok.gas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.devseok.gas.data.model.AreaCode
import com.devseok.gas.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-04-16
 * @desc
 */

@Database(entities = [AreaCode::class], version = 2)
abstract class AreaCodeDatabase: RoomDatabase() {

    abstract fun getAreaCodeDao(): AreaCodeDao

    class Callback @Inject constructor(
        private val database: Provider<AreaCodeDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}