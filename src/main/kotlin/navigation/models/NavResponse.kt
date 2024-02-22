package navigation.models

import botapi.models.MessageEntity
import botapi.models.User

class NavResponse(
    val user: User,
    val data: String,
    val messageId: Long? = null,
    val callbackId: String? = null,
    val listQuery: String? = null,
    val listItem: String? = null,
    val entities: List<MessageEntity>? = null
)

