package com.devseok.gas.data.model


import com.google.gson.annotations.SerializedName

data class LowTop10(
    @SerializedName("RESULT")
    var rESULT: RESULT
) {
    data class RESULT(
        @SerializedName("OIL")
        var oIL: List<OIL>
    ) {
        data class OIL(
            @SerializedName("UNI_ID")
            var uNIID: String,
            @SerializedName("PRICE")
            var pRICE: Int,
            @SerializedName("POLL_DIV_CD")
            var pOLLDIVCD: String,
            @SerializedName("OS_NM")
            var oSNM: String,
            @SerializedName("VAN_ADR")
            var vANADR: String,
            @SerializedName("NEW_ADR")
            var nEWADR: String,
            @SerializedName("GIS_X_COOR")
            var gISXCOOR: Double,
            @SerializedName("GIS_Y_COOR")
            var gISYCOOR: Double
        )
    }
}