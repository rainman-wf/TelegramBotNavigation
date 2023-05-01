package botapi.sender

import botapi.models.BaseResponse
import retrofit2.Response

internal suspend fun <T> apiRequest(body: suspend () -> Response<BaseResponse<T>>): BaseResponse<T> {
    val response = try {
        body()
    } catch (e: Exception) {
        return BaseResponse(null, false, 1001, e.message)
    }
    return response.body() ?: BaseResponse(null, false, 1002, "Response body is null")
}