package com.devseok.gas.data.model


import com.google.gson.annotations.SerializedName

data class AvgLastWeek(
    @SerializedName("RESULT")
    var rESULT: RESULT
) {
    data class RESULT(
        @SerializedName("OIL")
        var oIL: List<OIL>
    ) {
        data class OIL(
            @SerializedName("WEEK")
            var wEEK: String,
            @SerializedName("STA_DT")
            var sTADT: String,
            @SerializedName("END_DT")
            var eNDDT: String,
            @SerializedName("AREA_CD")
            var aREACD: String,
            @SerializedName("PRODCD")
            var pRODCD: String,
            @SerializedName("PRICE")
            var pRICE: Double
        )
    }
}