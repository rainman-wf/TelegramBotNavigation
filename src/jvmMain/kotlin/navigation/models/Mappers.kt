package navigation.models

import bot.models.*
import com.pengrad.telegrambot.model.request.InlineKeyboardButton
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup
import com.pengrad.telegrambot.request.AnswerInlineQuery
import com.pengrad.telegrambot.request.EditMessageText
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.BaseResponse
import com.pengrad.telegrambot.response.SendResponse

fun NewMessage.toRequest(): RequestType<*> {

    val buttons = buttons.map { row ->
        row.map { button ->
            InlineKeyboardButton(button.caption).apply {
                when (button.type) {
                    ButtonType.CALLBACK -> this.callbackData(button.data)
                    ButtonType.URL -> this.url(button.data)
                    ButtonType.LIST -> this.switchInlineQueryCurrentChat(button.data)
                }
            }
        }.toTypedArray()
    }.toTypedArray()

    return this.messageId?.let {
        EditMessageDto(EditMessageText(userId.value, it, text).replyMarkup(InlineKeyboardMarkup(*buttons)))
    } ?: SendMessageDto(SendMessage(userId.value, text).replyMarkup(InlineKeyboardMarkup(*buttons)))
}


fun DeleteMessage.toRequest(): RequestType<com.pengrad.telegrambot.request.DeleteMessage> {
    return DeleteMessageDto(com.pengrad.telegrambot.request.DeleteMessage(userId.value, messageId))
}

fun <T> DataList<T>.toRequest(adapter: DataListAdapter<T>): RequestType<AnswerInlineQuery> {
    return AnswerInlineQueryDto(
        AnswerInlineQuery(queryId, *adapter.map(this.list).toTypedArray()).cacheTime(5).isPersonal(true)
    )
}

fun Update.toResponse(): Response {
    return when (val result = message ?: callbackQuery ?: inlineQuery ?: chosenInlineResult ?: error("unhandled update type")) {
        is Message -> Response(
            userId = UserId(result.from.id),
            username = result.from.username,
            firstName = result.from.firstName,
            data = result.text ?: "",
            messageId = result.messageId
        )
        is CallbackQuery -> Response(
            userId = UserId(result.from.id),
            username = result.from.username,
            firstName = result.from.firstName,
            data = result.data ?: error("data is null"),
            callbackId = result.id
        )
        is InlineQuery -> Response(
            userId = UserId(result.from.id),
            username = result.from.username,
            firstName = result.from.firstName,
            data = result.query,
            listQuery = result.id
        )
        is ChosenInlineResult -> Response(
            userId = UserId(result.from.id),
            username = result.from.username,
            firstName = result.from.firstName,
            data = result.query,
            listItem = result.resultId
        )
        else -> error("unhandled update type")
    }
}

fun BaseResponse.toResponse() = when (this) {
    is SendResponse -> Response(
        userId = UserId(message().from().id()),
        username = message().from().username(),
        firstName = message().from().firstName(),
        data = message().text() ?: "",
        messageId = message().messageId()
    )
    else -> Response(
        userId = UserId(0),
        firstName = "stub",
        data = "deleted"
    )
}