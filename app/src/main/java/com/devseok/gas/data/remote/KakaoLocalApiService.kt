package com.devseok.gas.data.remote

import com.devseok.gas.data.model.TmCoordinatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * @author Ha Jin Seok
 * @created 2023-07-25
 * @desc
 */
interface KakaoLocalApiService {

    @Headers("Authorization: KakaoAK 52da2fce7633743188e69958b6641e78") // API_KEY 전달
    @GET("v2/local/geo/transcoord.json?output_coord=KTM")
    suspend fun getTmCoordinates(
        @Query("x") longitude: Double,
        @Query("y") latitude: Double
    ): Response<TmCoordinatesResponse>
}