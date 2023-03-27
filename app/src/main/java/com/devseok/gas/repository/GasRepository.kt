package com.devseok.gas.repository

import com.devseok.gas.data.remote.GasApi
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
    private val gasApi: GasApi
) {

}