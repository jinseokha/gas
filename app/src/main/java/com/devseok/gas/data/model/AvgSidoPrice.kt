package com.devseok.gas.data.model


import com.google.gson.annotations.SerializedName

data class AvgSidoPrice(
    @SerializedName("RESULT")
    var rESULT: RESULT
) {
    data class RESULT(
        @SerializedName("OIL")
        var oIL: List<OIL>
    ) {
        data class OIL(
            @SerializedName("SIDOCD")
            var sIDOCD: String,
            @SerializedName("SIDONM")
            var sIDONM: String,
            @SerializedName("PRODCD")
            var pRODCD: String,
            @SerializedName("PRICE")
            var pRICE: Double,
            @SerializedName("DIFF")
            var dIFF: Double
        )
    }
}