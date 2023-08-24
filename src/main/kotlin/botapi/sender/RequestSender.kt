package botapi.sender

import botapi.models.BaseResponse
import botapi.models.ErrorBody
import com.google.gson.Gson
import retrofit2.Response

internal suspend fun <T> apiRequest(body: suspend () -> Response<BaseResponse<T>>): BaseResponse<T> {
    val response = try {
        body()
    } catch (e: Exception) {
        return BaseResponse(null, false, 1001, e.message)
    }

    val errorResponse =
        response.errorBody()
            ?.let {
                Gson().fromJson(it.string(), ErrorBody::class.java)
            }?.let {
                BaseResponse<T>(null, false, it.errorCode, it.description, it.parameters)
            }

    return response.body() ?: errorResponse ?: BaseResponse(null, false, 1002, "who knows")
}