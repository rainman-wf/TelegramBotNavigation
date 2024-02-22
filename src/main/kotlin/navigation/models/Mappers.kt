package navigation.models

import botapi.models.*

internal fun Update.toResponse(): NavResponse {
    return when (val result =
        message ?: editedMessage?: callbackQuery ?: inlineQuery ?: chosenInlineResult ?: channelPost ?: error("unhandled update type")) {

        is Message -> result.toResponse()

        is CallbackQuery -> NavResponse(
            user = result.from,
            data = result.data ?: error("data is null"),
            callbackId = result.id
        )

        is InlineQuery -> NavResponse(
            user = result.from,
            data = result.query,
            listQuery = result.id
        )

        is ChosenInlineResult -> NavResponse(
            user = result.from,
            data = result.query,
            listItem = result.resultId
        )


        else -> error("unhandled update type")
    }
}

internal fun Message.toResponse() =
    NavResponse(
        user = from ?: User(senderChat!!.id, false, senderChat.title?: "Title"),
        data = text ?: "",
        messageId = messageId,
        entities = entities
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