package com.latihan.mytest.di

import com.latihan.mytest.data.remote.ApiConfig
import com.latihan.mytest.data.repository.UserRepository
import com.latihan.mytest.ui.UserViewModel

object Injection {
    // Menyediakan instance UserRepository
    fun provideUserRepository(): UserRepository {
        return UserRepository(ApiConfig.instance)
    }

    // Menyediakan instance UserViewModel
    fun provideUserViewModel(): UserViewModel {
        return UserViewModel(provideUserRepository())
    }
}