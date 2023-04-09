package com.devseok.gas.data.model


import com.google.gson.annotations.SerializedName

data class AvgAllPrice(
    @SerializedName("RESULT")
    var rESULT: RESULT
) {
    data class RESULT(
        @SerializedName("OIL")
        var oIL: List<OIL>
    ) {
        data class OIL(
            @SerializedName("TRADE_DT")
            var tRADEDT: String,
            @SerializedName("PRODCD")
            var pRODCD: String,
            @SerializedName("PRODNM")
            var pRODNM: String,
            @SerializedName("PRICE")
            var pRICE: String,
            @SerializedName("DIFF")
            var dIFF: String
        )
    }
}