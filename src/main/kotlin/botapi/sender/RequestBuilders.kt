package botapi.sender

import botapi.models.*
import com.google.gson.Gson

internal val gson = Gson()

data class SendMessageBuilder(
    var messageThreadId: Long? = null,
    var parseMode: ParseMode? = null,
    var entities: List<MessageEntity>? = null,
    var disableWebPagePreview: Boolean? = null,
    var disableNotification: Boolean? = null,
    var protectContent: Boolean? = null,
    var replyToMessageId: Long? = null,
    var allowSendingWithoutReply: Boolean? = null,
    var replyMarkup: ReplyMarkup? = null
) : RequestBuilder

data class SendPhotoBuilder(
    var messageThreadId: Long? = null,
    var caption: String? = null,
    var parseMode: ParseMode? = null,
    var captionEntities: List<MessageEntity>? = null,
    var hasSpoiler: Boolean? = null,
    var disableNotification: Boolean? = null,
    var protectContent: Boolean? = null,
    var replyToMessageId: Long? = null,
    var allowSendingWithoutReply: Boolean? = null,
    var replyMarkup: ReplyMarkup? = null
) : RequestBuilder


data class SendDocumentBuilder(
    var messageThreadId: Long? = null,
    var thumbnail: Any? = null,
    var caption: String? = null,
    var parseMode: ParseMode? = null,
    var captionEntities: List<MessageEntity>? = null,
    var disableContentTypeDetection: Boolean? = null,
    var disableNotification: Boolean? = null,
    var protectContent: Boolean? = null,
    var replyToMessageId: Long? = null,
    var allowSendingWithoutReply: Boolean? = null,
    var replyMarkup: ReplyMarkup? = null
)

data class EditMessageReplyMarkupBuilder(
    var replyMarkup: InlineKeyboardMarkup? = null
) : RequestBuilder

data class AnswerInlineQueryBuilder(
    val results: MutableList<InlineQueryResult> = mutableListOf(),
    var cacheTime: Int? = null,
    var isPersonal: Boolean? = null,
    var nextOffset: String? = null,
    var button: InlineQueryResultButton? = null,
) {

    fun MutableList<InlineQueryResult>.build (builder: InlineQueryResultsBuilder.() -> Unit) {
        val _builder = InlineQueryResultsBuilder()
        builder(_builder)
        this@AnswerInlineQueryBuilder.results.addAll(_builder.results)
    }

}

data class AnswerCallbackQueryBuilder(
    var showAlert: Boolean? = null,
    var url: String? = null,
    var cacheTime: Int? = null,
)

data class EditMessageTextBuilder(
    var parseMode: ParseMode? = null,
    var entities: List<MessageEntity>? = null,
    var disableWebPagePreview: Boolean? = null,
    var replyMarkup: ReplyMarkup? = null
) : RequestBuilder

data class CopyMessageBuilder(
   var messageThreadId: Long? = null,
   var caption: String? = null,
   var parseMode: ParseMode? = null,
   var captionEntities: List<MessageEntity>? = null,
   var disableNotification: Boolean? = null,
   var protectContent: Boolean? = null,
   var replyToMessageId: Long? = null,
   var allowSendingWithoutReply: Boolean? = null,
   var replyMarkup: ReplyMarkup? = null
) : RequestBuilder