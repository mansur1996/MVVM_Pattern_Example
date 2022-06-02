package com.example.mvvm_pattern_biginner.ui.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_pattern_biginner.data.api.ApiClient
import com.example.mvvm_pattern_biginner.data.repository.UserRepository
import com.example.mvvm_pattern_biginner.databinding.ActivityMainBinding
import com.example.mvvm_pattern_biginner.data.database.AppDatabase
import com.example.mvvm_pattern_biginner.ui.adapter.UserAdapter
import com.example.mvvm_pattern_biginner.viewmodel.UserViewModel
import com.example.mvvm_pattern_biginner.viewmodel.ViewModelFactory
import com.example.mvvm_pattern_biginner.utils.NetworkHelper
import com.example.mvvm_pattern_biginner.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var context: Context
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter()
        binding.rv.adapter = userAdapter
        context = this
        val userRepository = UserRepository(ApiClient.apiService, AppDatabase.getInstance(context))
        val networkHelper = NetworkHelper(context)
        userViewModel = ViewModelProvider(context as MainActivity, ViewModelFactory(userRepository, networkHelper))[UserViewModel::class.java]
        initViews()
    }

    private fun initViews() {
        binding.progressBar.visibility = View.VISIBLE

        userViewModel.getUsers().observe(context as MainActivity, Observer {
            when(it.status){
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rv.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    userAdapter.submitList(it.data)
                    binding.progressBar.visibility = View.GONE
                    binding.rv.visibility = View.VISIBLE
                }
            }
        })

    }
}