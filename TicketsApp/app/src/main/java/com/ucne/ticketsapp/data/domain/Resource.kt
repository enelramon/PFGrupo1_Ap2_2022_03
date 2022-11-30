package com.ucne.ticketsapp.data.domain

import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import retrofit2.Response

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}



/*sealed class Resource<T>(val data: Response<ClienteDto>? = null, val message: String? = null) {
    class Success<T>(data: Response<ClienteDto>) : Resource<T>(data)*/