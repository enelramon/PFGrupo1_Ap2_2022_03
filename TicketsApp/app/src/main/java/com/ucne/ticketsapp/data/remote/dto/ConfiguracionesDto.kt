package com.ucne.ticketsapp.data.remote.dto

data class ConfiguracionesDto(
    val configuracionId : Int,
    val theme : Int,
    val colorSchemeIndex : Int,
    val recordarEnDispositivo: Boolean
)
