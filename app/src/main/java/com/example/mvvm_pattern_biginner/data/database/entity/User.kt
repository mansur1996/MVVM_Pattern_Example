package com.example.mvvm_pattern_biginner.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey (autoGenerate = true)
    val id : Int,
    var name : String,
    var username : String,
    var email : String,
    var phone : String,
)