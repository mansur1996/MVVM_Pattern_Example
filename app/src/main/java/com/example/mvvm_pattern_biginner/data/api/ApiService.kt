package com.example.mvvm_pattern_biginner.data.api

import com.example.mvvm_pattern_biginner.data.database.entity.User
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("users")
    suspend fun getUsers() : Response<List<User>>

}