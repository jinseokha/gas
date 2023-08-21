package com.devseok.gas.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.devseok.gas.data.model.DetailById

/**
 * @author Ha Jin Seok
 * @created 2023-08-21
 * @desc
 */
@Dao
interface DetailOILRepository {

    @Query("SELECT * FROM detailOIL")
    fun findByAll(): List<DetailOIL>

    @Query("SELECT * FROM detailOIL where UNI_ID=:uni_id")
    fun findByDetail(uni_id: String): DetailOIL

    @Insert
    fun detailOILInsert(detailById: DetailOIL)

    @Delete
    fun detailOILDelete(detailById: DetailOIL)
}