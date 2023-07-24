package com.devseok.gas.util

/**
 * @author Ha Jin Seok
 * @created 2023-07-24
 * @desc
 */
object Utils {

    var latitude : Double = 0.0
    var longitude : Double = 0.0

    fun getLatitude(lat : Double) : Double {
        return 320000 + (lat - 126) * 3000
    }

    fun getLongitude(lot : Double) : Double {
        return 550000 + (lot - 38) * 3700
    }
}