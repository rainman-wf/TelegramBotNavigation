package navv2.entities

import botapi.models.Update
import botapi.models.User

data class UserAction(
    val from: User,
    val data: String?,
    val messageId: Long?,
    val listQuery: ListQuery?,
    val listItem: String?,
    val popupQuery: String?
) {
    companion object {
        fun from(update: Update, user: User): UserAction {
            return UserAction(
                from = user,
                data = update.message?.text
                    ?: update.editedMessage?.text
                    ?: update.inlineQuery?.query
                    ?: update.chosenInlineResult?.query
                    ?: update.callbackQuery?.data,
                messageId = update.message?.messageId ?: update.editedMessage?.messageId,
                listQuery = update.inlineQuery?.let { ListQuery(it.id, it.offset.toIntOrZero()) },
                listItem = update.chosenInlineResult?.resultId,
                popupQuery = update.callbackQuery?.id
            )
        }

        private fun String.toIntOrZero() : Int {
            return try {
                this.toInt()
            } catch (_: Exception) {
                0
            }
        }

    }


    class ListQuery(
        val id: String,
        val offset: Int
    )
}