package navigation.models

import botapi.models.MessageEntity

class NavResponse(
    val userId: Long,
    val username: String? = null,
    val firstName: String,
    val data: String,
    val messageId: Long? = null,
    val callbackId: String? = null,
    val listQuery: String? = null,
    val listItem: String? = null,
    val entities: List<MessageEntity>? = null
)

