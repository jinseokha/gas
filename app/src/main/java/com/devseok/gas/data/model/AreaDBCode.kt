package com.devseok.gas.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-05-29
 * @desc
 */
@Entity(tableName = "table_areacode")
data class AreaDBCode(
    @PrimaryKey
    var id: Int? = null,
    var oIL: List<AreaCode.RESULT.OIL>
)