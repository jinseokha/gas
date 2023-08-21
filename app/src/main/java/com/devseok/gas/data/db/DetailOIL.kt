package com.devseok.gas.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Ha Jin Seok
 * @created 2023-08-21
 * @desc
 */

@Entity
class DetailOIL(
    @ColumnInfo(name = "CAR_WASH_YN")
    val CAR_WASH_YN: String?,
    @ColumnInfo(name = "CVS_YN")
    val CVS_YN: String?,
    @ColumnInfo(name = "GIS_X_COOR")
    val GIS_X_COOR: Double?,
    @ColumnInfo(name = "GIS_Y_COOR")
    val GIS_Y_COOR: Double?,
    @ColumnInfo(name = "GPOLL_DIV_CO")
    val GPOLL_DIV_CO: String?,
    @ColumnInfo(name = "KPETRO_YN")
    val KPETRO_YN: String?,
    @ColumnInfo(name = "LPG_YN")
    val LPG_YN: String?,
    @ColumnInfo(name = "MAINT_YN")
    val MAINT_YN: String?,
    @ColumnInfo(name = "NEW_ADR")
    val NEW_ADR: String?,
    @ColumnInfo(name = "OS_NM")
    val OS_NM: String?,
    @ColumnInfo(name = "POLL_DIV_CO")
    val POLL_DIV_CO: String?,
    @ColumnInfo(name = "SIGUNCD")
    val SIGUNCD: String?,
    @ColumnInfo(name = "TEL")
    val TEL: String?,
    @ColumnInfo(name = "UNI_ID")
    var UNI_ID: String?,
    @ColumnInfo(name = "VAN_ADR")
    val VAN_ADR: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "key")
    var key: Int = 0
}