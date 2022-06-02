package com.example.mvvm_pattern_biginner.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_pattern_biginner.data.database.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(user: List<User>)

    @Query("select * from users")
    suspend fun getAllUsers() : List<User>
}