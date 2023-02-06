package com.itsydev.clubvr

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface RepositoryResponse<out ResultType> {

    suspend fun flow(): Flow<AsyncResult<ResultType>>

    suspend fun valueAsync(): Deferred<AsyncResult<ResultType>>

}