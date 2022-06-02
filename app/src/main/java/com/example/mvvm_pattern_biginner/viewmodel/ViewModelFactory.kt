package com.example.mvvm_pattern_biginner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_pattern_biginner.data.repository.UserRepository
import com.example.mvvm_pattern_biginner.utils.NetworkHelper

class ViewModelFactory(private val userRepository: UserRepository, private val networkHelper: NetworkHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(userRepository, networkHelper) as T
        }
        throw IllegalArgumentException("Error   ")
    }
}