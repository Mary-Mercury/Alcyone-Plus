package com.mary.alcyoneplus.Data

import com.google.android.gms.common.api.Api
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRequests @Inject constructor(
    private val postgrest: Postgrest
) {

    fun getSchedule2111(): Flow<ApiResult<List<TableTestDto>>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                val result = postgrest["Schedule2111"]
                    .select {
                        order("id", Order.ASCENDING)
                    }.decodeList<TableTestDto>()
                emit(ApiResult.Success(result))
            } catch (e: Exception) {
                emit(ApiResult.Error(e.message))
            }
        }
    }

    fun getNews(): Flow<ApiResult<List<NewsDto>>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                val result = postgrest["NewsPosts"]
                    .select {
                        order("id", Order.DESCENDING)
                    }.decodeList<NewsDto>()
                emit(ApiResult.Success(result))
            } catch (e: Exception) {
                emit(ApiResult.Error(e.message))
            }
        }
    }

    fun getExampleFlowTest(): Flow<ApiResult<List<TableTestDto>>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                val result = postgrest["Schedule3842"]
                    .select {
                        order("id", Order.ASCENDING)
                    }.decodeList<TableTestDto>()
                emit(ApiResult.Success(result))
            } catch (e: Exception) {
                emit(ApiResult.Error(e.message))
            }
        }
    }

    fun getSchedule2111YEXP(): Flow<ApiResult<List<ScheduleDtoEXP>>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                val result = postgrest["Schedule2111Y"]
                    .select {
                        order("id", Order.ASCENDING)
                    }.decodeList<ScheduleDtoEXP>()
                emit(ApiResult.Success(result))
            } catch (e: Exception) {
                emit(ApiResult.Error(e.message))
            }
        }
    }
}


