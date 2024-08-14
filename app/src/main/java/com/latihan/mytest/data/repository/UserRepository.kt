package com.latihan.mytest.data.repository

import com.latihan.mytest.data.remote.ApiService
import com.latihan.mytest.data.remote.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class UserRepository(private val apiService: ApiService) {

    fun getUsers(page: Int, perPage: Int): Flow<List<DataItem>> = flow {
        try {
            val response = apiService.getUsers(page, perPage)
            emit(response.body()?.data ?: emptyList())
        } catch (e: Exception) {
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)
}
