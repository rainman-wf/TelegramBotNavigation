package navigation.models

data class DeleteMessage(override val userId: UserId, val messageId: Int) : Request