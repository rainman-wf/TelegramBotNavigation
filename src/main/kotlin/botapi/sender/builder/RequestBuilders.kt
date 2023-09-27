package botapi.sender.builder

import botapi.common.*
import botapi.common.ANIMATION
import botapi.common.AUDIO
import botapi.common.DOCUMENT
import botapi.common.PHOTO
import botapi.common.VIDEO
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

class EditMessageCaption {
    var parseMode: ParseMode? = null
    var captionEntities: List<MessageEntity>? = null
    var replyMarkup: ReplyMarkup? = null
}

class EditMessageMedia {

    var replyMarkup: ReplyMarkup? = null
    lateinit var inputMedia: InputMedia

    internal inline fun <reified T: InputMedia>buildMedia(builder: InputMediaBuilder.() -> Unit) {

        val _builder = when (T::class) {
            InputMediaPhoto::class -> InputMediaPhotoBuilder()
            InputMediaAnimation::class -> InputMediaAnimationBuilder()
            InputMediaAudio::class -> InputMediaAudioBuilder()
            InputMediaVideo::class -> InputMediaVideoBuilder()
            InputMediaDocument::class  -> InputMediaDocumentBuilder ()
            else -> throw IllegalArgumentException("bla bla")
        }

        builder(_builder)

        inputMedia = when (_builder) {
            is InputMediaPhotoBuilder ->
                InputMediaPhoto(
                    type = _builder.type,
                    media = _builder.media,
                    caption = _builder.caption,
                    parseMode = _builder.parseMode?.name,
                    captionEntities = _builder.captionEntities?.toJson(),
                    hasSpoiler = _builder.hasSpoiler,
                )

            is InputMediaAnimationBuilder ->
                InputMediaAnimation(
                    type = _builder.type,
                    media = _builder.media,
                    thumbnail = _builder.thumbnail,
                    caption = _builder.caption,
                    parseMode = _builder.parseMode?.name,
                    captionEntities = _builder.captionEntities?.toJson(),
                    width = _builder.width,
                    height = _builder.height,
                    duration = _builder.duration,
                    hasSpoiler = _builder.hasSpoiler,
                )

            is InputMediaAudioBuilder -> InputMediaAudio(
                type = _builder.type,
                media = _builder.media,
                thumbnail = _builder.thumbnail,
                caption = _builder.caption,
                parseMode = _builder.parseMode?.name,
                captionEntities = _builder.captionEntities?.toJson(),
                duration = _builder.duration,
                performer = _builder.performer,
                title = _builder.title,
            )

            is InputMediaDocumentBuilder -> InputMediaDocument(
                type = _builder.type,
                media = _builder.media,
                thumbnail = _builder.thumbnail,
                caption = _builder.caption,
                parseMode = _builder.parseMode?.name,
                captionEntities = _builder.captionEntities?.toJson(),
                disableContentTypeDetection = _builder.disableContentTypeDetection,
            )

            is InputMediaVideoBuilder ->
                InputMediaVideo(
                    type = _builder.type,
                    media = _builder.media,
                    thumbnail = _builder.thumbnail,
                    caption = _builder.caption,
                    parseMode = _builder.parseMode?.name,
                    captionEntities = _builder.captionEntities?.toJson(),
                    width = _builder.width,
                    height = _builder.height,
                    duration = _builder.duration,
                    supportsStreaming = _builder.supportsStreaming,
                    hasSpoiler = _builder.hasSpoiler,
                )

        }
    }
}

sealed interface InputMediaBuilder {
    val type: String
    var media: String
    var caption: String?
    var parseMode: ParseMode?
    var captionEntities: List<MessageEntity>?
}

class InputMediaPhotoBuilder : InputMediaBuilder {
    override val type: String = PHOTO
    override lateinit var media: String
    override var caption: String? = null
    override var parseMode: ParseMode? = null
    override var captionEntities: List<MessageEntity>? = null
    var hasSpoiler: Boolean? = null
}

class InputMediaVideoBuilder : InputMediaBuilder {
    override val type: String = VIDEO
    override lateinit var media: String
    override var caption: String? = null
    override var parseMode: ParseMode? = null
    override var captionEntities: List<MessageEntity>? = null
    var thumbnail: Any? = null
    var width: Int? = null
    var height: Int? = null
    var duration: Int? = null
    var supportsStreaming: Boolean? = null
    var hasSpoiler: Boolean? = null
}

class InputMediaAnimationBuilder : InputMediaBuilder {
    override val type: String = ANIMATION
    override lateinit var media: String
    override var caption: String? = null
    override var parseMode: ParseMode? = null
    override var captionEntities: List<MessageEntity>? = null
    var thumbnail: Any? = null
    var width: Int? = null
    var height: Int? = null
    var duration: Int? = null
    var supportsStreaming: Boolean? = null
    var hasSpoiler: Boolean? = null
}

class InputMediaAudioBuilder : InputMediaBuilder {
    override val type: String = AUDIO
    override lateinit var media: String
    override var caption: String? = null
    override var parseMode: ParseMode? = null
    override var captionEntities: List<MessageEntity>? = null
    var thumbnail: Any? = null
    var duration: Int? = null
    var performer: String? = null
    var title: String? = null
}

class InputMediaDocumentBuilder : InputMediaBuilder {
    override val type: String = DOCUMENT
    override lateinit var media: String
    override var caption: String? = null
    override var parseMode: ParseMode? = null
    override var captionEntities: List<MessageEntity>? = null
    var thumbnail: Any? = null
    var disableContentTypeDetection: Boolean? = null
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

class CreateChatInviteLink {
    var name: String? = null
    var expireDate: Long? = null
    var memberLimit: Int? = null
    var createsJoinRequest: Boolean? = null
}

class EditChatInviteLink {
    var name: String? = null
    var expireDate: Long? = null
    var memberLimit: Int? = null
    var createsJoinRequest: Boolean? = null
}