package com.devseok.gas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.devseok.gas.data.model.AreaCode
import com.devseok.gas.data.model.AreaDBCode
import com.devseok.gas.di.ApplicationScope
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-05-29
 * @desc
 */

@Database(entities = [AreaDBCode::class], version = 2)
@TypeConverters(Converters::class)
abstract class AreaCodeDatabase : RoomDatabase() {

    abstract fun getAreaDBCodeDao(): AreaDBDao

    class Callback @Inject constructor(
        private val database: Provider<AreaCodeDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}