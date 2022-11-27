package com.ucne.ticketsapp.data.local.dao

import androidx.room.*
import com.ucne.ticketsapp.data.local.entities.User

@Dao

interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users")
    suspend fun getUser(): User?

    @Query("delete from Users where 1 = 1")
    suspend fun delete()
}