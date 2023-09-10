package com.chichi289.week3.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chichi289.week3.data.db.dao.UserDao
import com.chichi289.week3.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao():UserDao

}