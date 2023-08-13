package com.devseok.gas.repository

import com.devseok.gas.data.local.AreaDBDao
import com.devseok.gas.data.model.AreaCode
import com.devseok.gas.data.model.AreaDBCode
import com.devseok.gas.data.model.AroundAll
import com.devseok.gas.data.model.AvgAllPrice
import com.devseok.gas.data.model.AvgLastWeek
import com.devseok.gas.data.model.AvgSidoPrice
import com.devseok.gas.data.model.DetailById
import com.devseok.gas.data.model.LowTop10
import com.devseok.gas.data.model.SearchByName
import com.devseok.gas.data.model.UreaPrice
import com.devseok.gas.data.remote.GasApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-03-27
 * @desc
 */
@Singleton
class GasRepository @Inject constructor(
    private val gasApi: GasApi,
    private val areaDBDao: AreaDBDao
) {

    // 1. 전국 주유소 평균가격
    suspend fun getAvgAllPrice() : Response<AvgAllPrice> {
        return gasApi.getAvgAllPrice()
    }

    // 2. 시도별 주유소 평균가격
    suspend fun getAvgSidoPrice(sido: String) : Response<AvgSidoPrice> {
        return gasApi.getAvgSidoPrice(sido = sido)
    }

    // 7. 최근 1주의 주간 평균유가(전국/시도별)
    suspend fun getAvgLastWeek(sido: String) : Response<AvgLastWeek> {
        return gasApi.getAvgLastWeek(sido = sido)
    }

    // 8. 지역별 최저가 주유소 (TOP 20)
    suspend fun getLowTop10(prodcd: String, area: String) : Response<LowTop10> {
        return gasApi.getLowTop10(prodcd = prodcd, area = area)
    }

    // 반경내 주유소
    suspend fun getAroundAll(x: String, y: String, radius: String, prodcd: String, sort: String) : Response<AroundAll> {
        return gasApi.getAroundAll(x = x, y = y, radius = radius, prodcd = prodcd, sort = sort)
    }

    suspend fun getDetailById(id: String) : Response<DetailById> {
        return gasApi.getDetailById(id = id)
    }

    // 11. 상호로 주유소 검색
    suspend fun getSearchByName(osnm: String) : Response<SearchByName> {
        return gasApi.getSearchByName(osnm = osnm)
    }

    // 18. 요소수 주유소 판매가격(지역별)
    suspend fun getUreaPrice(area: String) : Response<UreaPrice> {
        return gasApi.getUreaPrice(area = area)
    }

    // 19. 지역코드
    suspend fun getAreaCode() : Response<AreaCode> {
        return gasApi.getAreaCode()
    }

    fun getAllAreaCode() = areaDBDao.getAreaCode()

    suspend fun insertAreaCode(areaCode: AreaDBCode) = areaDBDao.insert(areaCode)

    suspend fun deleteAreaCode(areaCode: AreaDBCode) = areaDBDao.delete(areaCode)
    suspend fun deleteAllAreaCode() = areaDBDao.deleteAllAreacode()



}