package botapi.sender

import botapi.Bot
import botapi.common.toJson
import botapi.sender.builder.*
import botapi.sender.builder.gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

suspend fun Bot.sendMessage(chatId: Long, text: String, builder: SendMessage.() -> Unit = {}) = apiRequest {

    val _builder = SendMessage()

    builder(_builder)

    api.requestSenderApi.sendMessage(
        chatId = chatId,
        text = text,
        messageThreadId = _builder.messageThreadId,
        parseMode = _builder.parseMode?.name,
        entities = gson.toJson(_builder.entities),
        disableWebPagePreview = _builder.disableWebPagePreview,
        disableNotification = _builder.disableNotification,
        protectContent = _builder.protectContent,
        replyToMessageId = _builder.replyToMessageId,
        allowSendingWithoutReply = _builder.allowSendingWithoutReply,
        replyMarkup = _builder.replyMarkup?.let { gson.toJson(_builder.replyMarkup) }
    )
}

suspend fun Bot.sendDocument(
    chatId: Long,
    document: File,
    builder: SendDocument.() -> Unit = {}
) = apiRequest {

    val _builder = SendDocument()

    builder(_builder)

    val _document = MultipartBody.Part.createFormData(
        "document", document.name, document.asRequestBody(
            "multipart/form-data".toMediaType()
        )
    )

    api.requestSenderApi.sendDocument(
        chatId = chatId,
        document = _document,
        messageThreadId = _builder.messageThreadId,
        thumbnail = _builder.thumbnail?.let { (it as String).toRequestBody() },
        caption = _builder.caption?.toRequestBody(),
        parseMode = _builder.parseMode?.name?.toRequestBody(),
        captionEntities = _builder.captionEntities?.toJson()?.toRequestBody(),
        disableContentTypeDetection = _builder.disableContentTypeDetection,
        disableNotification = _builder.disableNotification,
        protectContent = _builder.protectContent,
        replyToMessageId = _builder.replyToMessageId,
        allowSendingWithoutReply = _builder.allowSendingWithoutReply,
        replyMarkup = _builder.replyMarkup?.toJson()?.toRequestBody(),
    )
}

suspend fun Bot.sendDocument(
    chatId: Long,
    document: String,
    builder: SendDocument.() -> Unit = {}
) = apiRequest {

    val _builder = SendDocument()

    builder(_builder)

    api.requestSenderApi.sendDocument(
        chatId = chatId,
        document = document,
        messageThreadId = _builder.messageThreadId,
        thumbnail = _builder.thumbnail?.let { it as String },
        caption = _builder.caption,
        parseMode = _builder.parseMode?.name,
        captionEntities = _builder.captionEntities?.toJson(),
        disableContentTypeDetection = _builder.disableContentTypeDetection,
        disableNotification = _builder.disableNotification,
        protectContent = _builder.protectContent,
        replyToMessageId = _builder.replyToMessageId,
        allowSendingWithoutReply = _builder.allowSendingWithoutReply,
        replyMarkup = _builder.replyMarkup?.toJson(),
    )
}

suspend fun Bot.sendPhoto(chatId: Long, photo: File, builder: SendPhoto.() -> Unit = {}) = apiRequest {

    val _builder = SendPhoto()

    val _photo = MultipartBody.Part.createFormData(
        "photo", photo.name, photo.asRequestBody(
            "multipart/form-data".toMediaType()
        )
    )

    builder(_builder)

    api.requestSenderApi.sendPhoto(
        chatId = chatId,
        photo = _photo,
        messageThreadId = _builder.messageThreadId,
        caption = _builder.caption?.toRequestBody(),
        parseMode = _builder.parseMode?.name?.toRequestBody(),
        captionEntities = _builder.captionEntities.toJson()?.toRequestBody(),
        hasSpoiler = _builder.hasSpoiler,
        disableNotification = _builder.disableNotification,
        protectContent = _builder.protectContent,
        replyToMessageId = _builder.replyToMessageId,
        allowSendingWithoutReply = _builder.allowSendingWithoutReply,
        replyMarkup = _builder.replyMarkup?.toJson()?.toRequestBody()
    )
}

suspend fun Bot.sendPhoto(chatId: Long, photo: String, builder: SendPhoto.() -> Unit = {}) = apiRequest {

    val _builder = SendPhoto()

    builder(_builder)

    api.requestSenderApi.sendPhoto(
        chatId = chatId,
        photo = photo,
        messageThreadId = _builder.messageThreadId,
        caption = _builder.caption,
        parseMode = _builder.parseMode?.name,
        captionEntities = _builder.captionEntities.toJson(),
        hasSpoiler = _builder.hasSpoiler,
        disableNotification = _builder.disableNotification,
        protectContent = _builder.protectContent,
        replyToMessageId = _builder.replyToMessageId,
        allowSendingWithoutReply = _builder.allowSendingWithoutReply,
        replyMarkup = _builder.replyMarkup?.toJson()
    )
}

suspend fun Bot.editMessageReplyMarkup(
    chatId: Long,
    messageId: Long,
    builder: EditMessageReplyMarkupBuilder.() -> Unit = {}
) =
    apiRequest {

        val _builder = EditMessageReplyMarkupBuilder()

        builder(_builder)

        api.requestUpdateMsgApi.editMessageReplyMarkup(
            chatId = chatId,
            messageId = messageId,
            replyMarkup = _builder.replyMarkup?.toJson()
        )
    }

suspend fun Bot.deleteMessage(chatId: Long, messageId: Long) = apiRequest {
    api.requestUpdateMsgApi.deleteMessage(chatId, messageId)
}

suspend fun Bot.answerInlineQuery(
    inlineQueryId: String,
    builder: AnswerInlineQuery.() -> Unit
) = apiRequest {

    val _buildr = AnswerInlineQuery()

    builder(_buildr)

    if (_buildr.results.isEmpty()) throw IllegalArgumentException("AnswerInlineQuery results must not be empty")

    api.inlineModeApi.answerInlineQuery(
        inlineQueryId = inlineQueryId,
        results = requireNotNull(_buildr.results.toJson()),
        cacheTime = _buildr.cacheTime,
        isPersonal = _buildr.isPersonal,
        nextOffset = _buildr.nextOffset,
        button = _buildr.button?.toJson()
    )
}

suspend fun Bot.editMessageText(
    chatId: Long,
    messageId: Long,
    text: String,
    builder: EditMessageText.() -> Unit = {}
) = apiRequest {

    val _builder = EditMessageText()

    builder(_builder)

    api.requestUpdateMsgApi.editMessageText(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        text = text,
        parseMode = _builder.parseMode,
        entities = _builder.entities?.toJson(),
        disableWebPagePreview = _builder.disableWebPagePreview,
        replyMarkup = _builder.replyMarkup?.toJson(),
    )
}

suspend fun Bot.answerCallbackQuery(
    callbackQueryId: String,
    builder: AnswerCallbackQuery.() -> Unit = {}
) = apiRequest {

    val _builder = AnswerCallbackQuery()

    builder(_builder)

    api.inlineModeApi.answerCallbackQuery(
        callbackQueryId = callbackQueryId,
        text = _builder.text,
        showAlert = _builder.showAlert,
        url = _builder.url,
        cacheTime = _builder.cacheTime,
    )
}

suspend fun Bot.copyMessage(
    chatId: Any,
    fromChatId: Any,
    messageId: Long, builder: CopyMessage.() -> Unit = {}
) = apiRequest {

    val _builder = CopyMessage()

    builder(_builder)

    api.requestSenderApi.copyMessage(
        chatId = chatId,
        fromChatId = fromChatId,
        messageId = messageId,
        messageThreadId = _builder.messageThreadId,
        caption = _builder.caption,
        parseMode = _builder.parseMode?.name,
        captionEntities = _builder.captionEntities?.toJson(),
        disableNotification = _builder.disableNotification,
        protectContent = _builder.protectContent,
        replyToMessageId = _builder.replyToMessageId,
        allowSendingWithoutReply = _builder.allowSendingWithoutReply,
        replyMarkup = _builder.replyMarkup?.toJson(),
    )
}

suspend fun Bot.getUserProfilePhotos(userId: Long, offset: Int? = null, limit: Int? = null) = apiRequest {
    api.getter.getUserProfilePhotos(userId, offset, limit)
}

suspend fun Bot.getFile(fileId: String) = apiRequest {
    api.getter.getFile(fileId)
}

suspend fun Bot.forwardMessage(
    fromChatId: Long,
    chatId: Long,
    messageId: Long,
    builder: ForwardMessage.() -> Unit = {}
) = apiRequest {

    val _builder = ForwardMessage()

    builder(_builder)

    api.requestSenderApi.forwardMessage(
        fromChatId = fromChatId,
        chatId = chatId,
        messageId = messageId,
        protectContent = _builder.protectContent,
        messageThreadId = _builder.messageThreadId,
        disableNotification = _builder.disableNotification,
    )
}

suspend fun Bot.pinChatMessage(
    chatId: Long,
    messageId: Long,
    disableNotification: Boolean? = null
) = apiRequest {

    api.requestSenderApi.pinChatMessage(chatId, messageId, disableNotification)

}

suspend fun Bot.pinChatMessage(
    chatId: String,
    messageId: Long,
    disableNotification: Boolean? = null
) = apiRequest {

    api.requestSenderApi.pinChatMessage(chatId, messageId, disableNotification)
}

suspend fun Bot.banChatMember(
    chatId: Long,
    userId: Long,
    untilDate: Long? = null,
) = apiRequest {
    api.groupsActions.banChatMember(chatId, userId, untilDate)
}

suspend fun Bot.unbanChatMember(
    chatId: Long,
    userId: Long,
    onlyIfBanned: Boolean? = null,
) = apiRequest {
    api.groupsActions.unbanChatMember(chatId, userId, onlyIfBanned)
}

suspend fun Bot.restrictChatMember(
    chatId: Long,
    userId: Long,
    permissions: String,
    untilDate: Long? = null,
) = apiRequest {
    api.groupsActions.restrictChatMember(chatId, userId, permissions, untilDate)
}

suspend fun Bot.promoteChatMember(chatId: Long, userId: Long, builder: PromoteChatMember.() -> Unit = {}) = apiRequest {

    val _builder = PromoteChatMember()

    builder(_builder)

    api.groupsActions.promoteChatMember(
        chatId = chatId,
        userId = userId,
        isAnonymous = _builder.isAnonymous,
        canChangeInfo = _builder.canChangeInfo,
        canPostMessages = _builder.canPostMessages,
        canEditMessages = _builder.canEditMessages,
        canDeleteMessages = _builder.canDeleteMessages,
        canInviteUsers = _builder.canInviteUsers,
        canRestrictMembers = _builder.canRestrictMembers,
        canPinMessages = _builder.canPinMessages,
        canPromoteMembers = _builder.canPromoteMembers
    )
}

suspend fun Bot.setChatPermissions(chatId: Long, permissions: String) = apiRequest {
    api.groupsActions.setChatPermissions(chatId, permissions)
}

suspend fun Bot.exportChatInviteLink(chatId: Long) = apiRequest {
    api.groupsActions.exportChatInviteLink(chatId)
}

suspend fun Bot.setChatPhoto(chatId: Long, photo: File) = apiRequest {

    val _photo = MultipartBody.Part.createFormData(
        "photo", photo.name, photo.asRequestBody("multipart/form-data".toMediaType())
    )

    api.groupsActions.setChatPhoto(chatId, _photo)
}

suspend fun Bot.deleteChatPhoto(chatId: Long) = apiRequest {
    api.groupsActions.deleteChatPhoto(chatId)
}

suspend fun Bot.setChatTitle(chatId: Long, title: String) = apiRequest {
    api.groupsActions.setChatTitle(chatId, title)
}

suspend fun Bot.setChatDescription(chatId: Long, description: String) = apiRequest {
    api.groupsActions.setChatDescription(chatId, description)
}