package com.ucne.ticketsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.ticketsapp.data.local.dao.ConfiguracionDao
import com.ucne.ticketsapp.data.local.dao.UserDao
import com.ucne.ticketsapp.data.local.entities.Configuracion
import com.ucne.ticketsapp.data.local.entities.User

@Database(
    entities = [Configuracion::class, User::class],
    exportSchema = false,
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val configDao: ConfiguracionDao
}