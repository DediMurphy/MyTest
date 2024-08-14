package com.latihan.mytest.data.repository

import com.latihan.mytest.data.remote.ApiService
import com.latihan.mytest.data.remote.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Repository untuk mengelola pengambilan data pengguna dari ApiService.
 * @param apiService Instance ApiService untuk berkomunikasi dengan API.
 */
class UserRepository(private val apiService: ApiService) {

    /**
     * Mengambil daftar pengguna dari API dengan menggunakan coroutines dan Flow.
     * @param page Halaman data yang ingin diambil.
     * @param perPage Jumlah item per halaman.
     * @return Flow yang mengalirkan daftar pengguna. Jika terjadi kesalahan atau API tidak berhasil,
     *         akan mengalirkan daftar kosong.
     */
    fun getUsers(page: Int, perPage: Int): Flow<List<DataItem>> = flow {
        try {
            // Mengirim permintaan ke API untuk mendapatkan data pengguna
            val response = apiService.getUsers(page, perPage)
            // Mengirimkan data pengguna ke aliran, atau daftar kosong jika data tidak ada
            emit(response.body()?.data ?: emptyList())
        } catch (e: Exception) {
            // Mengirimkan daftar kosong jika terjadi kesalahan
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO) // Menetapkan dispatcher IO untuk operasi jaringan
}

