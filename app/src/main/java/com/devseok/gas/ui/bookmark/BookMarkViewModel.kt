package com.devseok.gas.ui.bookmark

import android.content.Context
import androidx.lifecycle.ViewModel
import com.devseok.gas.repository.GasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-07-23
 * @desc
 */
@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val gasRepository: GasRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

}