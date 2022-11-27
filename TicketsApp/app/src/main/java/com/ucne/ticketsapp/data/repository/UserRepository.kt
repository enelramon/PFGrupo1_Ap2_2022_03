package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.local.AppDataBase
import com.ucne.ticketsapp.data.local.entities.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val db: AppDataBase
) {
    suspend fun insert(user: User) = db.userDao.insert(user)
    suspend fun getUser() = db.userDao.getUser()
    suspend fun delete() = db.userDao.delete()
}