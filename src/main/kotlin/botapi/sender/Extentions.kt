package botapi.sender

import botapi.Bot
import botapi.common.toJson
import botapi.sender.builder.SendMessage
import botapi.sender.builder.gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

suspend fun Bot.sendMessage(chatId: Long, text: String, builder: SendMessage.() -> Unit = {}) = apiRequest {

    val _builder = SendMessage()

    builder(_builder)

    requestSenderApi.sendMessage(
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
    builder: botapi.sender.builder.SendDocument.() -> Unit = {}
) = apiRequest {

    val _builder = botapi.sender.builder.SendDocument()

    builder(_builder)

    val _document = MultipartBody.Part.createFormData(
        "document", document.name, document.asRequestBody(
            "multipart/form-data".toMediaType()
        )
    )

    requestSenderApi.sendDocument(
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
    builder: botapi.sender.builder.SendDocument.() -> Unit = {}
) = apiRequest {

    val _builder = botapi.sender.builder.SendDocument()

    builder(_builder)

    requestSenderApi.sendDocument(
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

suspend fun Bot.sendPhoto(chatId: Long, photo: File, builder: botapi.sender.builder.SendPhoto.() -> Unit = {}) = apiRequest {

    val _builder = botapi.sender.builder.SendPhoto()

    val _photo = MultipartBody.Part.createFormData(
        "photo", photo.name, photo.asRequestBody(
            "multipart/form-data".toMediaType()
        )
    )

    builder(_builder)

    requestSenderApi.sendPhoto(
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

suspend fun Bot.sendPhoto(chatId: Long, photo: String, builder: botapi.sender.builder.SendPhoto.() -> Unit = {}) = apiRequest {

    val _builder = botapi.sender.builder.SendPhoto()

    builder(_builder)

    requestSenderApi.sendPhoto(
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
    builder: botapi.sender.builder.EditMessageReplyMarkupBuilder.() -> Unit = {}
) =
    apiRequest {

        val _builder = botapi.sender.builder.EditMessageReplyMarkupBuilder()

        builder(_builder)

        requestUpdateMsgApi.editMessageReplyMarkup(
            chatId = chatId,
            messageId = messageId,
            replyMarkup = _builder.replyMarkup?.toJson()
        )
    }

suspend fun Bot.deleteMessage(chatId: Long, messageId: Long) = apiRequest {
    requestUpdateMsgApi.deleteMessage(chatId, messageId)
}

suspend fun Bot.answerInlineQuery(
    inlineQueryId: String,
    builder: botapi.sender.builder.AnswerInlineQuery.() -> Unit
) = apiRequest {

    val _buildr = botapi.sender.builder.AnswerInlineQuery()

    builder(_buildr)

    if (_buildr.results.isEmpty()) throw IllegalArgumentException("AnswerInlineQuery results must not be empty")

    inlineModeApi.answerInlineQuery(
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
    builder: botapi.sender.builder.EditMessageText.() -> Unit = {}
) = apiRequest {

    val _builder = botapi.sender.builder.EditMessageText()

    builder(_builder)

    requestUpdateMsgApi.editMessageText(
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
    builder: botapi.sender.builder.AnswerCallbackQuery.() -> Unit = {}
) = apiRequest {

    val _builder = botapi.sender.builder.AnswerCallbackQuery()

    builder(_builder)

    inlineModeApi.answerCallbackQuery(
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
    messageId: Long, builder: botapi.sender.builder.CopyMessage.() -> Unit = {}
) = apiRequest {

    val _builder = botapi.sender.builder.CopyMessage()

    builder(_builder)

    requestSenderApi.copyMessage(
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
    requestSenderApi.getUserProfilePhotos(userId, offset, limit)
}

suspend fun Bot.getFile(fileId: String) = apiRequest {
    requestSenderApi.getFile(fileId)
}

suspend fun Bot.forwardMessage(
    fromChatId: Long,
    chatId: Long,
    messageId: Long,
    protectContent: Boolean? = null,
    messageThreadId: Long? = null,
    disableNotification: Boolean? = null,
) = apiRequest {
    requestSenderApi.forwardMessage(
        fromChatId = fromChatId,
        chatId = chatId,
        messageId = messageId,
        protectContent = protectContent,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
    )
}