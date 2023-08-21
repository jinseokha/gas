package com.devseok.gas.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devseok.gas.data.model.DetailById
import com.devseok.gas.repository.GasRepository
import com.devseok.gas.util.NetworkUtil
import com.devseok.gas.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @created 2023-08-11
 * @desc
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val gasRepository: GasRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val detailById: MutableLiveData<Resource<DetailById>> = MutableLiveData()

    var detailByIdResponse: DetailById? = null

    fun getDetailById(id: String) = viewModelScope.launch {
        safeDetailById(id)
    }

    private suspend fun safeDetailById(id: String) = viewModelScope.launch {
        try {
            if (NetworkUtil.hasInternetConnection(context)) {
                val response = gasRepository.getDetailById(id)
                detailById.postValue(handlerDetailByIdResponse(response))
            }
        } catch (e: Exception) {
            when (e) {
                is java.io.IOException -> detailById.postValue(Resource.Error("Network Failure"))
                else -> detailById.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handlerDetailByIdResponse(response: Response<DetailById>) : Resource<DetailById> {
        if (response.isSuccessful) {
            response.body()?.let {resultResponse ->
                detailByIdResponse = resultResponse
                return Resource.Success(detailByIdResponse ?: resultResponse)
            }
        }

        return Resource.Error(response.message())
    }

}