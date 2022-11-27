package com.ucne.ticketsapp.data.local.dao

import androidx.room.*
import com.ucne.ticketsapp.data.local.entities.Configuracion

@Dao
interface ConfiguracionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(configuracion: Configuracion)

    @Query("SELECT * FROM Configuraciones")
    suspend fun getConfig(): Configuracion?
}