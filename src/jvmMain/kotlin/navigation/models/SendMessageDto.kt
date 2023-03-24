package navigation.models

import com.pengrad.telegrambot.request.AnswerCallbackQuery
import com.pengrad.telegrambot.request.AnswerInlineQuery
import com.pengrad.telegrambot.request.DeleteMessage
import com.pengrad.telegrambot.request.EditMessageText
import com.pengrad.telegrambot.request.ForwardMessage
import com.pengrad.telegrambot.request.SendMessage


sealed class RequestType<T>(open val request: T)

data class SendMessageDto(override val request: SendMessage) : RequestType<SendMessage>(request)

data class EditMessageDto(override val request: EditMessageText) : RequestType<EditMessageText>(request)

data class ForwardMessageDto(override val request: ForwardMessage) : RequestType<ForwardMessage>(request)

data class DeleteMessageDto(override val request: DeleteMessage) : RequestType<DeleteMessage>(request)

data class AnswerCallbackQueryDto(override val request: AnswerCallbackQuery) : RequestType<AnswerCallbackQuery>(request)

data class AnswerInlineQueryDto(override val request: AnswerInlineQuery) : RequestType<AnswerInlineQuery>(request)

fun String.toMarkdown(): String {
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