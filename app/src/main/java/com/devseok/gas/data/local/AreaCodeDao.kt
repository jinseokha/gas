package com.devseok.gas.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devseok.gas.data.model.AreaCode

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-04-16
 * @desc
 */
@Dao
interface AreaCodeDao {

    @Query("SELECT * FROM areaCode_table")
    fun getAreaCode() : LiveData<List<AreaCode>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(areaCode: AreaCode) : Long

    @Query("DELETE FROM areaCode_table")
    suspend fun deleteAllAreaCode()

}