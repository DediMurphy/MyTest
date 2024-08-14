package com.latihan.mytest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latihan.mytest.data.remote.DataItem
import com.latihan.mytest.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    // LiveData untuk daftar pengguna
    private val _users = MutableLiveData<List<DataItem>>(emptyList())
    val users: LiveData<List<DataItem>> get() = _users

    // LiveData untuk pesan kesalahan
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchUsers(page: Int, perPage: Int) {
        viewModelScope.launch {
            userRepository.getUsers(page, perPage).collect { result ->
                _users.value = result
            }
        }
    }
}