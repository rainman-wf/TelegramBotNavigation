package navigation.models

class NavResponse(
    val userId: UserId,
    val username: String? = null,
    val firstName: String,
    val data: String,
    val messageId: Long? = null,
    val callbackId: String? = null,
    val listQuery: String? = null,
    val listItem: String? = null
)

