package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.local.AppDataBase
import com.ucne.ticketsapp.data.local.entities.Configuracion
import javax.inject.Inject

class LocalConfiguracionRepository @Inject constructor(
    private val db: AppDataBase
) {
    suspend fun insert(configuracion: Configuracion) =
        db.configDao.insert(configuracion = configuracion)

    suspend fun getConfig() = db.configDao.getConfig()
}