package botapi.sender

import botapi.models.BaseResponse
import retrofit2.Response
import java.net.SocketTimeoutException

internal suspend fun <T> apiRequest(body: suspend () -> Response<BaseResponse<T>>): BaseResponse<T> {
    val response = try {
        body()
    } catch (e: SocketTimeoutException) {
        return BaseResponse(null, false, 1001, e.message)
    }
    return response.body() ?: BaseResponse(null, false, 1002, "Response body is null")
}