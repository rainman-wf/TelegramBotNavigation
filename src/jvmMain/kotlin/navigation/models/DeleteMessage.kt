package navigation.models

data class DeleteMessage(override val toUserId: UserId, val messageId: Int) : Request