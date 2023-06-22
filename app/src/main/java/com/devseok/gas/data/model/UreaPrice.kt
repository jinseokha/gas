package com.devseok.gas.data.model


import com.google.gson.annotations.SerializedName

data class UreaPrice(
    @SerializedName("RESULT")
    var result: RESULT
) {
    data class RESULT(
        @SerializedName("OIL")
        var oIL: List<OIL>
    ) {
        data class OIL(
            @SerializedName("AREA_CD")
            var aREACD: String,
            @SerializedName("AREA_NM")
            var aREANM: String
        )
    }
}