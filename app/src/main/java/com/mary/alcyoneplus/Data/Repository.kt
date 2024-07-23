package com.mary.alcyoneplus.Data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface repository {

    fun getNews(): Flow<ApiResult<List<NewsDto>>>

    fun getExampleFlowTest(): Flow<ApiResult<List<TableTestDto>>>
}

class RepositoryImpl @Inject constructor(
    private val source: NetworkRequests
) : repository {

    override fun getNews(): Flow<ApiResult<List<NewsDto>>> {
        return source.getNews()
    }

    override fun getExampleFlowTest(): Flow<ApiResult<List<TableTestDto>>> {
        return source.getExampleFlowTest()
    }
}

