package com.devseok.gas.data.model

import com.google.gson.annotations.SerializedName

data class DetailById(
    @SerializedName("RESULT")
    val result: RESULT
) {
    data class RESULT(
        @SerializedName("OIL")
        val oIL: List<OIL>
    ) {
        data class OIL(
            @SerializedName("CAR_WASH_YN")
            val CAR_WASH_YN: String,
            @SerializedName("CVS_YN")
            val CVS_YN: String,
            @SerializedName("GIS_X_COOR")
            val GIS_X_COOR: Double,
            @SerializedName("GIS_Y_COOR")
            val GIS_Y_COOR: Double,
            @SerializedName("GPOLL_DIV_CO")
            val GPOLL_DIV_CO: String,
            @SerializedName("KPETRO_YN")
            val KPETRO_YN: String,
            @SerializedName("LPG_YN")
            val LPG_YN: String,
            @SerializedName("MAINT_YN")
            val MAINT_YN: String,
            @SerializedName("NEW_ADR")
            val NEW_ADR: String,
            @SerializedName("OIL_PRICE")
            val OIL_PRICE: List<OILPRICE>,
            @SerializedName("OS_NM")
            val OS_NM: String,
            @SerializedName("POLL_DIV_CO")
            val POLL_DIV_CO: String,
            @SerializedName("SIGUNCD")
            val SIGUNCD: String,
            @SerializedName("TEL")
            val TEL: String,
            @SerializedName("UNI_ID")
            val UNI_ID: String,
            @SerializedName("VAN_ADR")
            val VAN_ADR: String
        ) {
            data class OILPRICE(
                @SerializedName("PRICE")
                val PRICE: Int,
                @SerializedName("PRODCD")
                val PRODCD: String,
                @SerializedName("TRADE_DT")
                val TRADE_DT: String,
                @SerializedName("TRADE_TM")
                val TRADE_TM: String
            )
        }
    }
}