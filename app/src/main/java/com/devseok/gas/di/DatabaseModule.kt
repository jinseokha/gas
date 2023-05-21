package com.devseok.gas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-03-27
 * @desc
 */

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /*@Provides
    @Singleton
    fun provideDatabase(application: Application, callback: AreaCodeDatabase.Callback) : AreaCodeDatabase {
        return Room.databaseBuilder(application, AreaCodeDatabase::class.java, "areaCode_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideAreaCodeDao(db: AreaCodeDatabase): AreaCodeDao {
        return db.getAreaCodeDao()
    }*/
}