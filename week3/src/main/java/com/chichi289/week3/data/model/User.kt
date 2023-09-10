package com.chichi289.week3.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int = 0,
    @ColumnInfo(name = "user_id")
    val userId: Long,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "email")
    val email: String
){

    // Due to https://stackoverflow.com/a/73048506
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        // Compare all relevant properties for equality
        if (primaryKey != other.primaryKey) return false
        if (userId != other.userId) return false
        if (userName != other.userName) return false
        if (fullName != other.fullName) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = primaryKey
        result = 31 * result + userId.hashCode()
        result = 31 * result + userName.hashCode()
        result = 31 * result + fullName.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }
}
