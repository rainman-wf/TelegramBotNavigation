package navigation.models


data class Response(
    val userId: UserId,
    val username: String? = null,
    val firstName: String,
    val data: String,
    val messageId: Int? = null,
    val callbackId: String? = null,
    val listQuery: String? = null,
    val listItem: String? = null
)

