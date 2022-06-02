package com.example.mvvm_pattern_biginner.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_pattern_biginner.data.database.dao.UserDao
import com.example.mvvm_pattern_biginner.data.database.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun userDao() : UserDao

    companion object{
        private var appDatabase : AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : AppDatabase{
            if (appDatabase == null){
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return appDatabase!!
        }
    }
}