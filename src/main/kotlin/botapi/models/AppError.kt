package botapi.models


sealed class AppError(message: String?) : Exception(message)

class ApiError(code: Int, message: String?) : AppError("code $code : \"$message\"")
class NullBodyError : AppError("Response body is null")
class UnexpectedError : AppError("Unexpected error")