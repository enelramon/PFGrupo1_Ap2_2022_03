package com.ucne.ticketsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Configuraciones")
data class Configuracion(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tema: Int,
    val colorSchemeIndex: Int,
    val rememberMe: Boolean
)
