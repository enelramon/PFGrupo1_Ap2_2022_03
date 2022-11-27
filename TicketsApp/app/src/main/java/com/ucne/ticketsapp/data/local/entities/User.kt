package com.ucne.ticketsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val password: String,
    val adminMode: Boolean
)
