package com.devseok.gas.repository

import android.util.Log
import com.devseok.gas.BuildConfig
import com.devseok.gas.data.remote.KakaoLocalApiService
import com.devseok.gas.util.KAKAO_BASE_URL
import com.devseok.gas.util.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * @author Ha Jin Seok
 * @created 2023-07-25
 * @desc
 */
object KakaoRepository {
    private val kakaoLocalApiService: KakaoLocalApiService by lazy {
        Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildHttpClient())
            .build()
            .create()
    }

    private fun buildHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        // DEBUG일때만 다 보여주기
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        // NONE : 보여주지 않음
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()

    suspend fun getKATEXconvert(latitude: Double, longitude: Double) : Boolean{
        val tmCoordinates = kakaoLocalApiService
            .getTmCoordinates(longitude, latitude)
            .body()
            ?.documents
            ?.firstOrNull()

        val tmX = tmCoordinates?.x
        val tmY = tmCoordinates?.y

        Utils.latKATECx = tmX
        Utils.lonKATECy = tmY
        Log.d("test", "" + tmX + tmY)

        return true
    }
}