package com.example.offlinecaching.data.remote

import com.example.offlinecaching.common.Resource
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
  crossinline query: () -> Flow<ResultType>,
  crossinline fetch: suspend () -> RequestType,
  crossinline saveFetchResult: suspend (RequestType) -> Unit,
  crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    val data = query().first()

    val resource = if (shouldFetch(data)) {

        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())

            query().map { Resource.Success(it) }

        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable.localizedMessage, it) }
        }

    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(resource)
}
