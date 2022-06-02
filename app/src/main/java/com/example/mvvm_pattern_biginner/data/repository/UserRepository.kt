package com.example.mvvm_pattern_biginner.data.repository

import com.example.mvvm_pattern_biginner.data.api.ApiService
import com.example.mvvm_pattern_biginner.data.database.AppDatabase
import com.example.mvvm_pattern_biginner.data.database.entity.User

class UserRepository(private val apiService: ApiService, val appDatabase: AppDatabase) {

    suspend fun getRemoteUsers() = apiService.getUsers()

    suspend fun getLocalUsers() = appDatabase.userDao().getAllUsers()

    suspend fun addUsers(list: List<User>) = appDatabase.userDao().addUsers(list)
}