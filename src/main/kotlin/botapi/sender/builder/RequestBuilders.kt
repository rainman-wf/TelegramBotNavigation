package botapi.sender.builder

import botapi.models.*
import com.google.gson.Gson

internal val gson = Gson()

class SendMessage : MarkupIncludable {
    var messageThreadId: Long? = null
    var parseMode: ParseMode? = null
    var entities: List<MessageEntity>? = null
    var disableWebPagePreview: Boolean? = null
    var disableNotification: Boolean? = null
    var protectContent: Boolean? = null
    var replyToMessageId: Long? = null
    var allowSendingWithoutReply: Boolean? = null
    var replyMarkup: ReplyMarkup? = null
}


class SendPhoto : MarkupIncludable {
    var messageThreadId: Long? = null
    var caption: String? = null
    var parseMode: ParseMode? = null
    var captionEntities: List<MessageEntity>? = null
    var hasSpoiler: Boolean? = null
    var disableNotification: Boolean? = null
    var protectContent: Boolean? = null
    var replyToMessageId: Long? = null
    var allowSendingWithoutReply: Boolean? = null
    var replyMarkup: ReplyMarkup? = null
}

class SendDocument : MarkupIncludable {
    var messageThreadId: Long? = null
    var thumbnail: Any? = null
    var caption: String? = null
    var parseMode: ParseMode? = null
    var captionEntities: List<MessageEntity>? = null
    var disableContentTypeDetection: Boolean? = null
    var disableNotification: Boolean? = null
    var protectContent: Boolean? = null
    var replyToMessageId: Long? = null
    var allowSendingWithoutReply: Boolean? = null
    var replyMarkup: ReplyMarkup? = null
}

class EditMessageReplyMarkupBuilder : MarkupIncludable {
    var replyMarkup: InlineKeyboardMarkup? = null
}

class AnswerInlineQuery {
    val results: MutableList<InlineQueryResult> = mutableListOf()
    var cacheTime: Int? = null
    var isPersonal: Boolean? = null
    var nextOffset: String? = null
    var button: InlineQueryResultButton? = null

    fun MutableList<InlineQueryResult>.build(builder: InlineQueryResultsBuilder.() -> Unit) {
        val _builder = InlineQueryResultsBuilder()
        builder(_builder)
        this@AnswerInlineQuery.results.addAll(_builder.build())
    }
}

class AnswerCallbackQuery {
    var text: String? = null
    var showAlert: Boolean? = null
    var url: String? = null
    var cacheTime: Int? = null
}

class EditMessageText {
    var parseMode: ParseMode? = null
    var entities: List<MessageEntity>? = null
    var disableWebPagePreview: Boolean? = null
    var replyMarkup: ReplyMarkup? = null
}

class CopyMessage {
    var messageThreadId: Long? = null
    var caption: String? = null
    var parseMode: ParseMode? = null
    var captionEntities: List<MessageEntity>? = null
    var disableNotification: Boolean? = null
    var protectContent: Boolean? = null
    var replyToMessageId: Long? = null
    var allowSendingWithoutReply: Boolean? = null
    var replyMarkup: ReplyMarkup? = null
}

class ForwardMessage {
    var messageThreadId: Long? = null
    var disableNotification: Boolean? = null
    var protectContent: Boolean? = null
}

class PromoteChatMember {
    var isAnonymous: Boolean? = null
    var canChangeInfo: Boolean? = null
    var canPostMessages: Boolean? = null
    var canEditMessages: Boolean? = null
    var canDeleteMessages: Boolean? = null
    var canInviteUsers: Boolean? = null
    var canRestrictMembers: Boolean? = null
    var canPinMessages: Boolean? = null
    var canPromoteMembers: Boolean? = null
}