package com.chichi289.week3.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.chichi289.week3.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    fun insertAll(vararg users: User)

    @Query("select * from user")
    fun getAllUsers(): Flow<List<User>>

    @Delete
    fun deleteUser(user: User)
}