package com.devseok.gas.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.devseok.gas.repository.GasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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



    /*init {
        getEvents()
    }
    fun getEvents() = viewModelScope.launch {
        safeEventsCall()
    }

    private suspend fun safeEventsCall() {
        events.postValue(Resource.Loading())

        try {
            if (hasInternetConnection(context)) {
                val response = loaringRepository.getEvents()
                events.postValue(handleEventsResponse(response))
            } else {
                events.postValue(Resource.Error("No Internet Connection"))
            }
        } catch (ex: Exception) {
            when(ex){
                is IOException -> events.postValue(Resource.Error("Network Failure"))
                else -> events.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleEventsResponse(response: Response<Events>): Resource<Events> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (eventsResponse == null)
                    eventsResponse = resultResponse

                return Resource.Success(eventsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }*/
}