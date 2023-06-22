package com.devseok.gas.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devseok.gas.data.model.AreaDBCode

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-05-29
 * @desc
 */
@Dao
interface AreaDBDao {

    @Query("SELECT * FROM table_areacode")
    fun getAreaCode(): LiveData<List<AreaDBCode>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(areaDBCode: AreaDBCode) : Long

    @Delete
    suspend fun delete(areaDBCode: AreaDBCode)

    @Query("DELETE FROM table_areacode")
    suspend fun deleteAllAreacode()
}