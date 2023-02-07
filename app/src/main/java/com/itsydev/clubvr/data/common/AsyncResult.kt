package com.itsydev.clubvr.data.common

sealed class AsyncResult<out T>(open val data: T?) {
    data class Success<out T>(override val data: T?) : AsyncResult<T>(data)
    data class Error<out T>(val error: String, override val data: T?) : AsyncResult<T>(data)
    data class Loading<out T>(override val data: T?) : AsyncResult<T>(data)

    fun isSuccess() = this is Success<T>

    fun isError() = this is Error<T>

    fun isLoading() = this is Loading<T>

}