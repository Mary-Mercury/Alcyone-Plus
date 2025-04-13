package com.mary.alcyoneplus.Data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface repository {

    fun getNews(): Flow<ApiResult<List<NewsDto>>>

    fun getExampleFlowTest(): Flow<ApiResult<List<TableTestDto>>>

    fun getSchedule2111(): Flow<ApiResult<List<TableTestDto>>>

    fun getSchedule2111YEXP(): Flow<ApiResult<List<ScheduleDtoEXP>>>
}

class RepositoryImpl @Inject constructor(
    private val source: NetworkRequests
) : repository {

    private var inMemoryCache: List<ScheduleDtoEXP>? = null

    override fun getNews(): Flow<ApiResult<List<NewsDto>>> {
        return source.getNews()
    }

    override fun getExampleFlowTest(): Flow<ApiResult<List<TableTestDto>>> {
        return source.getExampleFlowTest()
    }

    override fun getSchedule2111(): Flow<ApiResult<List<TableTestDto>>> {
        return source.getSchedule2111()
    }

    override fun getSchedule2111YEXP(): Flow<ApiResult<List<ScheduleDtoEXP>>>  = flow {
        emit(ApiResult.Loading)

        inMemoryCache?.let {
            emit(ApiResult.Success(it))
            return@flow
        }

        source.getSchedule2111YEXP().collect { result ->
            when (result) {
                is ApiResult.Success -> {
                    inMemoryCache = result.data
                    emit(result)
                }
                is ApiResult.Error -> emit(result)
                is ApiResult.Loading -> emit(result)
            }
        }
    }.flowOn(Dispatchers.IO)
    //        return source.getSchedule2111YEXP()
}

