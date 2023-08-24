package botapi.models

import com.google.gson.annotations.SerializedName

data class ErrorBody(
    val ok: Boolean,
    @SerializedName("error_code") val errorCode: Int? = null,
    val description: String? = null,
    val parameters: ResponseParameters? = null,
)