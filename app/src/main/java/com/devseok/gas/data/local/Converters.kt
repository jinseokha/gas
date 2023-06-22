package com.devseok.gas.data.local

import androidx.room.TypeConverter
import com.devseok.gas.data.model.AreaCode
import com.google.gson.Gson

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-05-29
 * @desc
 */
class Converters {

    @TypeConverter
    fun fromOIL(oil: List<AreaCode.RESULT.OIL>?)
            = Gson().toJson(oil)

    @TypeConverter
    fun toOIL(oil: String)
            = Gson().fromJson(oil, Array<AreaCode.RESULT.OIL>::class.java).toList()

}