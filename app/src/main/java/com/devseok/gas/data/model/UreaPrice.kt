package com.devseok.gas.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UreaPrice(
    @SerializedName("RESULT")
    var rESULT: RESULT
) {
    data class RESULT(
        @SerializedName("OIL")
        var oIL: List<OIL>
    ) {
        @Parcelize
        data class OIL(
            @SerializedName("UNI_ID")
            var uNIID: String,
            @SerializedName("OS_NM")
            var oSNM: String,
            @SerializedName("ADR")
            var aDR: String,
            @SerializedName("TEL")
            var tEL: String,
            @SerializedName("GIS_X_COOR")
            var gISXCOOR: String,
            @SerializedName("GIS_Y_COOR")
            var gISYCOOR: String,
            @SerializedName("STOCK_YN")
            var sTOCKYN: String,
            @SerializedName("PRICE")
            var pRICE: String,
            @SerializedName("TRADE_DT")
            var tRADEDT: String,
            @SerializedName("TRADE_TM")
            var tRADETM: String
        ): Parcelable
    }
}