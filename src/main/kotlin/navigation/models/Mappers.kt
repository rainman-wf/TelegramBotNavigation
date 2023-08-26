package navigation.models

import botapi.models.*

internal fun Update.toResponse(): NavResponse {
    return when (val result =
        message ?: callbackQuery ?: inlineQuery ?: chosenInlineResult ?: channelPost ?: error("unhandled update type")) {

        is Message -> result.toResponse()

        is CallbackQuery -> NavResponse(
            userId = result.from.id,
            username = result.from.username,
            firstName = result.from.firstName,
            data = result.data ?: error("data is null"),
            callbackId = result.id
        )

        is InlineQuery -> NavResponse(
            userId = result.from.id,
            username = result.from.username,
            firstName = result.from.firstName,
            data = result.query,
            listQuery = result.id
        )

        is ChosenInlineResult -> NavResponse(
            userId = result.from.id,
            username = result.from.username,
            firstName = result.from.firstName,
            data = result.query,
            listItem = result.resultId
        )

        else -> error("unhandled update type")
    }
}

internal fun Message.toResponse() =
    NavResponse(
        userId = from?.id ?: senderChat!!.id,
        username = from?.username ?: senderChat!!.username,
        firstName = from?.firstName ?: senderChat!!.title!!,
        data = text ?: "",
        messageId = messageId
    )

internal fun String.toMarkdown(): String {
    return this
        .replace(">", "\\>")
        .replace("(", "\\(")
        .replace(")", "\\)")
        .replace("#", "\\#")
        .replace(".", "\\.")
        .replace("!", "\\!")
        .replace("-", "\\-")
        .replace("~(", "\\(")
        .replace("~)", "\\)")
        .replace("~[", "\\[")
        .replace("~]", "\\]")
        .replace("~_", "\\_")
        .replace("|", "\\|")
        .replace("=", "\\=")
        .replace("+", "\\+")
}