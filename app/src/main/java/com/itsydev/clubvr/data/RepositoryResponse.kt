package com.itsydev.clubvr.data

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface RepositoryResponse<out ResultType> {

    suspend fun flow(): Flow<AsyncResult<ResultType>>

    suspend fun valueAsync(): Deferred<AsyncResult<ResultType>>

}