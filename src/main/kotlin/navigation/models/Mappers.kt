package navigation.models

import botapi.models.*

internal fun Update.toResponse(): NavResponse {
    return when (val result =
        message ?: callbackQuery ?: inlineQuery ?: chosenInlineResult ?: error("unhandled update type")) {
        is Message -> result.toResponse()
        is CallbackQuery -> NavResponse(
            userId = UserId(result.from.id),
            username = result.from.username,
            firstName = result.from.firstName,
            data = result.data ?: error("data is null"),
            callbackId = result.id
        )

        is InlineQuery -> NavResponse(
            userId = UserId(result.from.id),
            username = result.from.username,
            firstName = result.from.firstName,
            data = result.query,
            listQuery = result.id
        )

        is ChosenInlineResult -> NavResponse(
            userId = UserId(result.from.id),
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
        userId = UserId(from.id),
        username = from.username,
        firstName = from.firstName,
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
        .replace("~\\(", "(")
        .replace("~\\)", ")")
        .replace("|", "\\|")
        .replace("=", "\\=")
        .replace("+", "\\+")
}