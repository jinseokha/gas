package com.devseok.gas.data.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class AreaCode(
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