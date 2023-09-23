package com.chichi289.week5.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.chichi289.week5.data.remote.model.user.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("select * from user")
    fun getUser(): Flow<List<User>>

    @Delete
    fun deleteUser(user: User)
}