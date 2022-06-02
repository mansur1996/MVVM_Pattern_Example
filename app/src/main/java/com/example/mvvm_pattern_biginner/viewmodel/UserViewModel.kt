package com.example.mvvm_pattern_biginner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_pattern_biginner.data.repository.UserRepository
import com.example.mvvm_pattern_biginner.data.database.entity.User
import com.example.mvvm_pattern_biginner.utils.NetworkHelper
import com.example.mvvm_pattern_biginner.utils.Resource
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository, private val networkHelper: NetworkHelper) : ViewModel() {

    private val liveData = MutableLiveData<Resource<List<User>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            liveData.postValue(Resource.loading(null))
            if(networkHelper.isNetworkConnected()){
                val remoteUsers = userRepository.getRemoteUsers()
                // 200 likmi
                if(remoteUsers.isSuccessful){
                    userRepository.addUsers(remoteUsers.body() ?: emptyList())
                    liveData.postValue(Resource.success(remoteUsers.body()))
                }else{
                    liveData.postValue(Resource.error(remoteUsers.errorBody()?.string() ?: "", null))
                }
            }else{
                liveData.postValue(Resource.success(userRepository.getLocalUsers()))
            }
        }
    }

    fun getUsers() : LiveData<Resource<List<User>>> = liveData

}