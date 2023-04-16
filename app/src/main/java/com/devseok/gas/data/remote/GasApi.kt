package com.devseok.gas.data.remote

import com.devseok.gas.data.model.*
import com.devseok.gas.util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-03-27
 * @desc
 */
interface GasApi {
    // avgAllPrice.do

    // 1. 전국 주유소 평균가격
    @GET("avgAllPrice.do")
    suspend fun getAvgAllPrice(
        @Query("code") apiKey: String = API_KEY,
        @Query("out") out: String = "json"
    ): Response<AvgAllPrice>

    // 2. 시도별 주유소 평균가격
    @GET("avgSidoPrice.do")
    suspend fun getAvgSidoPrice(
        @Query("code") apiKey: String = API_KEY,
        @Query("out") out: String = "json",
        @Query("sido") sido: String
    ): Response<AvgSidoPrice>

    // 7. 최근 1주의 주간 평균유가(전국/시도별)
    @GET("avgLastWeek.do")
    suspend fun getAvgLastWeek(
        @Query("code") apiKey: String = API_KEY,
        @Query("out") out: String = "json",
        @Query("sido") sido: String
    ): Response<AvgLastWeek>

    // 8. 지역별 최저가 주유소 (TOP 20)
    @GET("lowTop10.do")
    suspend fun getLowTop10(
        @Query("code") apiKey: String = API_KEY,
        @Query("out") out: String = "json",
        @Query("prodcd") prodcd: String = "B027",
        @Query("area") area: String
    ): Response<LowTop10>

    // TODO : 지도 테스트하면서 Model 추가 필요
    // 9. 반경내 주유소
    /*@GET("aroundAll.do")
    suspend fun getAroundAll(
        @Query("code") apiKey: String = API_KEY,
        @Query("out") out: String = "json",
        @Query("x") x: String,
        @Query("y") y: String,
        @Query("radius") radius: String,
        @Query("prodcd") prodcd: String = "B027",
        @Query("sort") sort: String
    )*/

    // 11. 상호로 주유소 검색
    @GET("searchByName.do")
    suspend fun getSearchByName(
        @Query("code") apiKey: String = API_KEY,
        @Query("out") out: String = "json",
        @Query("osnm") osnm: String
    ): Response<SearchByName>

    // 18. 요소수 주유소 판매가격(지역별)
    @GET("ureaPrice.do")
    suspend fun getUreaPrice(
        @Query("code") apiKey: String = API_KEY,
        @Query("out") out: String = "json",
        @Query("area") area: String
    ): Response<UreaPrice>

    // 19. 지역코드
    @GET("areaCode.do")
    suspend fun getAreaCode(
        @Query("code") apiKey: String = API_KEY,
        @Query("out") out: String = "json"
    ): Response<AreaCode>
}