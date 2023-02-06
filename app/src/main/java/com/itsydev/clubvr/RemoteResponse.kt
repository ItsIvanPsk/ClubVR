package com.itsydev.clubvr

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal inline fun <T> remoteResponse(crossinline block: suspend () -> T): RepositoryResponse<T> {
    return object : RemoteResponse<T>() {

        override suspend fun requestRemoteCall(): T = block()

    }.build()
}

abstract class RemoteResponse<ResultType> {

    private var flow: Flow<AsyncResult<ResultType>>? = null
    private var deferredValue: Deferred<AsyncResult<ResultType>>? = null

    private val response = object : RepositoryResponse<ResultType> {
        override suspend fun flow() = executeFlow()
        override suspend fun valueAsync() = executeAsync()
    }

    fun build(): RepositoryResponse<ResultType> {
        return response
    }

    private suspend fun executeFlow(): Flow<AsyncResult<ResultType>> {
        return flow ?: flow {
            AsyncResult.Loading(null)
            executeBase { emit(it) }
        }.apply { flow = this }
    }

    private suspend fun executeAsync(): Deferred<AsyncResult<ResultType>> {
        return deferredValue ?: coroutineScope {
            async {
                executeBase { }
            }
        }.apply { deferredValue = this }
    }

    private suspend fun executeBase(send: suspend (AsyncResult<ResultType>) -> Unit): AsyncResult<ResultType> {
        val finalValue: AsyncResult<ResultType> = try {
            AsyncResult.Loading(null)
            val networkResponse = requestRemoteCall()
            delay(2000)
            AsyncResult.Success(networkResponse)
        } catch (e: Exception) {
            AsyncResult.Error("Ha petau", null)
        }
        send(finalValue)
        return finalValue
    }


    protected abstract suspend fun requestRemoteCall(): ResultType
}