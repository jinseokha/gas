package com.devseok.gas.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devseok.gas.data.model.*
import com.devseok.gas.repository.GasRepository
import com.devseok.gas.util.NetworkUtil.Companion.hasInternetConnection
import com.devseok.gas.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-03-27
 * @desc
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gasRepository: GasRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val avgAllPrice: MutableLiveData<Resource<AvgAllPrice>> = MutableLiveData()
    val areaCode: MutableLiveData<Resource<AreaCode>> = MutableLiveData()

    var avgAllPriceResponse: AvgAllPrice? = null
    var searchByNameResponse: SearchByName? = null

    init {
        getAvgAllPrice() // 1. 전국 주유소 평균가격

        /** TODO : getAreaDBCode() 가 존재하면 getAreaCode() 실행 X */
        getAreaCode() // 19. 지역코드
    }

    fun getAreaDBCode() = gasRepository.getAllAreaCode()

    /** 전국 주유소 평균가격 */
    private fun getAvgAllPrice() = viewModelScope.launch {
        safeAvgAllPrice()
    }

    /** 지역코드 */
    private fun getAreaCode() = viewModelScope.launch {
        safeAreaCode()
    }

    private suspend fun safeAreaCode() {
        try {
            if (hasInternetConnection(context)) {
                val response = gasRepository.getAreaCode()
                // TODO : 내부 데이터 저장용 (RoomDB)
                Log.d("testtest", "" + response.body()!!.result.oIL)

                viewModelScope.launch {
                    var areaDBCode = AreaDBCode(1, response.body()!!.result.oIL)
                    gasRepository.insertAreaCode(areaDBCode)
                }

            }
        } catch (e: Exception) {
            when (e) {
                is IOException -> areaCode.postValue(Resource.Error("Network Failure"))
                else -> areaCode.postValue(Resource.Error("Conversion Error"))
            }
        }
    }



    private suspend fun safeAvgAllPrice() {
        avgAllPrice.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(context)) {
                val response = gasRepository.getAvgAllPrice()
                avgAllPrice.postValue(handlerAvgAllPriceResponse(response))
            } else {
                avgAllPrice.postValue(Resource.Error("No Internet Connection"))
            }
        } catch (e: Exception) {
            when (e) {
                is IOException -> avgAllPrice.postValue(Resource.Error("Network Failure"))
                else -> avgAllPrice.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handlerAvgAllPriceResponse(response: Response<AvgAllPrice>) : Resource<AvgAllPrice> {
        if (response.isSuccessful) {
            response.body()?.let { resultReponse ->
                if (avgAllPriceResponse == null)
                    avgAllPriceResponse = resultReponse

                return Resource.Success(avgAllPriceResponse ?: resultReponse)
            }
        }

        return Resource.Error(response.message())
    }

    private fun handlerSearchByNameResponse(response: Response<SearchByName>) : Resource<SearchByName> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (searchByNameResponse == null)
                    searchByNameResponse = resultResponse

                return Resource.Success(searchByNameResponse ?: resultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}