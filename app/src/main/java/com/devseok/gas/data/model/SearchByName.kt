package com.devseok.gas.data.model


import com.google.gson.annotations.SerializedName

data class SearchByName(
    @SerializedName("RESULT")
    var rESULT: RESULT
) : java.io.Serializable {
    data class RESULT(
        @SerializedName("OIL")
        var oIL: List<OIL>
    ) {
        data class OIL(
            @SerializedName("UNI_ID")
            var uNIID: String,
            @SerializedName("POLL_DIV_CD")
            var pOLLDIVCD: String,
            @SerializedName("GPOLL_DIV_CD")
            var gPOLLDIVCD: String,
            @SerializedName("OS_NM")
            var oSNM: String,
            @SerializedName("VAN_ADR")
            var vANADR: String,
            @SerializedName("NEW_ADR")
            var nEWADR: String,
            @SerializedName("SIGUNCD")
            var sIGUNCD: String,
            @SerializedName("LPG_YN")
            var lPGYN: String,
            @SerializedName("GIS_X_COOR")
            var gISXCOOR: Double,
            @SerializedName("GIS_Y_COOR")
            var gISYCOOR: Double
        )
    }
}