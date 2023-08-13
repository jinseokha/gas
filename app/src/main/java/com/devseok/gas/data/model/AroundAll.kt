package com.devseok.gas.data.model

import com.google.gson.annotations.SerializedName

data class AroundAll(
    @SerializedName("RESULT")
    val result: RESULT
) {
    data class RESULT(
        @SerializedName("OIL")
        val oIL: List<OIL>
    ) {
        data class OIL(
            @SerializedName("DISTANCE")
            val DISTANCE: Double,
            @SerializedName("GIS_X_COOR")
            val GIS_X_COOR: Double,
            @SerializedName("GIS_Y_COOR")
            val GIS_Y_COOR: Double,
            @SerializedName("OS_NM")
            val OS_NM: String,
            @SerializedName("POLL_DIV_CD")
            val POLL_DIV_CD: String,
            @SerializedName("PRICE")
            val PRICE: Int,
            @SerializedName("UNI_ID")
            val UNI_ID: String,
            var detailById: DetailById?
        )
    }
}