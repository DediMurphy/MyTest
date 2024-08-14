package com.latihan.mytest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latihan.mytest.data.remote.DataItem
import com.latihan.mytest.data.repository.UserRepository
import kotlinx.coroutines.launch

/**
 * ViewModel untuk mengelola data pengguna dan menangani logika bisnis terkait pengguna.
 * @param userRepository Instance UserRepository untuk mengambil data pengguna dari API.
 */

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    // LiveData untuk daftar pengguna yang akan diperbarui dan diamati oleh UI
    private val _users = MutableLiveData<List<DataItem>>(emptyList())
    val users: LiveData<List<DataItem>> get() = _users

    // LiveData untuk pesan kesalahan yang mungkin terjadi selama pengambilan data
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    /**
     * Mengambil data pengguna dari repository dan memperbarui LiveData dengan hasilnya.
     * @param page Halaman data yang ingin diambil.
     * @param perPage Jumlah item per halaman.
     */
    fun fetchUsers(page: Int, perPage: Int) {
        // Melakukan operasi asinkron di scope ViewModel
        viewModelScope.launch {
            try {
                // Mengumpulkan data pengguna dari repository
                userRepository.getUsers(page, perPage).collect { result ->
                    _users.value = result // Memperbarui LiveData dengan hasil pengguna
                }
            } catch (e: Exception) {
                // Menangani kesalahan jika terjadi selama pengambilan data
                _error.value = e.message
            }
        }
    }
}
