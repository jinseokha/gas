package com.devseok.gas.util

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

/**
 * @author Ha Jin Seok
 * @created 2023-07-25
 * @desc
 */
object NaverKatec {

    fun getKatecCoordinates(lat: Double, long: Double): String {
        CoroutineScope(Dispatchers.IO).launch {
            val apiKey = "ty5ylox80c"
            val url =
                "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=$lat,$long&sourcecrs=epsg:4326&output=json&orders=addr"
            val request = Request.Builder()
                .url(url)
                .header("X-NCP-APIGW-API-KEY-ID", apiKey)
                .build()

            val client = OkHttpClient()
            val response = runBlocking { client.newCall(request).execute() }
            val responseData = response.body?.string()
            response.close()

            val jsonObject = JSONObject(responseData)
            Log.d("test", "$jsonObject")
        }

        /*val addresses = jsonObject.getJSONArray("results").getJSONObject(0)
            .getJSONArray("region").getJSONObject("area1").getString("alias")*/
        return "test"
    }
}