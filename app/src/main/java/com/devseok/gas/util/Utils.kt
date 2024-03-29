package com.devseok.gas.util

import com.devseok.gas.R

/**
 * @author Ha Jin Seok
 * @created 2023-07-24
 * @desc
 */
object Utils {

    var latitude : Double = 0.0
    var longitude : Double = 0.0

    var latKATECx : Double? = 0.0
    var lonKATECy : Double? = 0.0


    fun pollConvert(brand: String) : Int {
        return when(brand) {
            "SKE", "SKG" -> R.drawable.pole_1 // SK
            "GSC" -> R.drawable.pole_2 // GS
            "HDO" -> R.drawable.pole_3 // 현대오일뱅크
            "SOL" -> R.drawable.pole_4 // S-OIL
            "RTO" -> R.drawable.pole_6 // 자영알뜰
            "RTX" -> R.drawable.pole_6 // 고속도로알뜰
            "NHO" -> R.drawable.pole_5 // 농협알뜰
            "ETC" -> R.drawable.pole_all // 자가상표
            "E1G" -> R.drawable.pole_7 // E1
            else -> R.drawable.pole_all
        }
    }

    fun pollBrandConvert(brand: String) : String {
        return when(brand) {
            "SKE", "SKG" -> "SK에너지"// SK
            "GSC" -> "GS칼텍스" // GS
            "HDO" -> "현대오일뱅크" // 현대오일뱅크
            "SOL" -> "S-OIL" // S-OIL
            "RTO" -> "자영알뜰" // 자영알뜰
            "RTX" -> "고속도로알뜰" // 고속도로알뜰
            "NHO" -> "농협알뜰" // 농협알뜰
            "ETC" -> "자가상표" // 자가상표
            "E1G" -> "E1" // E1
            else -> "null"
        }
    }
}