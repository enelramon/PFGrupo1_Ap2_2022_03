package com.ucne.ticketsapp.util

data class UserConfig(
    val theme: Int = 2,
    val colorIndex: Int = 0
)

fun getUserConfig(Id: Int): UserConfig {
    val userConfig: UserConfig = when (Id) {
        1 -> {
            UserConfig(0, 0)
        }
        2 -> {
            UserConfig(0, 1)
        }
        3 -> {
            UserConfig(0, 2)
        }
        4 -> {
            UserConfig(1, 0)
        }
        5 -> {
            UserConfig(1, 1)
        }
        6 -> {
            UserConfig(1, 2)
        }
        7 -> {
            UserConfig(2, 0)
        }
        8 -> {
            UserConfig(2, 1)
        }
        else -> {
            UserConfig(2, 2)
        }
    }
    return userConfig
}