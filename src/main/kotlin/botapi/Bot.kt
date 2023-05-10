package botapi

import botapi.common.toJson
import botapi.models.*
import botapi.poller.Api
import botapi.poller.CoroutinePoller
import botapi.sender.*
import botapi.sender.apiRequest
import botapi.sender.gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class Bot(token: String) {

    private val baseUrl = "https://api.telegram.org/bot$token/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(75, TimeUnit.SECONDS)
        .readTimeout(75, TimeUnit.SECONDS)
        .writeTimeout(75, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    private val updatePollerApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build().create(Api::class.java)

    private val requestSenderApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build().create(SendMsgApi::class.java)

    private val requestUpdateMsgApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build().create(UpdateMsgApi::class.java)

    private val inlineModeApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build().create(InlineModeApi::class.java)

    private val sender = requestSenderApi
    private val poller: CoroutinePoller = CoroutinePoller(updatePollerApi)

    suspend fun updates(body: suspend (Update) -> Unit) = poller.poll().collect {
        body(it)
    }

    suspend fun sendMessage(chatId: Long, text: String, builder: SendMessageBuilder.() -> Unit = {}) = apiRequest {

        val _builder = SendMessageBuilder()

        builder(_builder)

        sender.sendMessage(
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

    suspend fun sendPhoto(chatId: Long, photo: File, builder: SendPhotoBuilder.() -> Unit = {}) = apiRequest {

        val _builder = SendPhotoBuilder()

        val _photo = MultipartBody.Part.createFormData(
            "photo", photo.name, photo.asRequestBody(
                "multipart/form-data".toMediaType()
            )
        )

        builder(_builder)

        sender.sendPhoto(
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

    suspend fun editMessageReplyMarkup(
        chatId: Long,
        messageId: Long,
        builder: EditMessageReplyMarkupBuilder.() -> Unit = {}
    ) =
        apiRequest {

            val _builder = EditMessageReplyMarkupBuilder()

            builder(_builder)

            requestUpdateMsgApi.editMessageReplyMarkup(
                chatId = chatId,
                messageId = messageId,
                replyMarkup = _builder.replyMarkup?.toJson()
            )
        }

    suspend fun deleteMessage(chatId: Long, messageId: Long) = apiRequest {
        requestUpdateMsgApi.deleteMessage(chatId, messageId)
    }

    suspend fun answerInlineQuery(
        inlineQueryId: String,
        builder: AnswerInlineQueryBuilder.() -> Unit
    ) = apiRequest {

        val _buildr = AnswerInlineQueryBuilder()

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

    suspend fun editMessageText(
        chatId: Long,
        messageId: Long,
        text: String,
        builder: EditMessageTextBuilder.() -> Unit = {}
    ) = apiRequest {
        val _builder = EditMessageTextBuilder()

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

    suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String,
        builder: AnswerCallbackQueryBuilder.() -> Unit = {}
    ) = apiRequest {

        val _builder = AnswerCallbackQueryBuilder()

        builder(_builder)

        inlineModeApi.answerCallbackQuery(
            callbackQueryId = callbackQueryId,
            text = text,
            showAlert = _builder.showAlert,
            url = _builder.url,
            cacheTime = _builder.cacheTime,
        )
    }

    suspend fun copyMessage(
        chatId: Any,
        fromChatId: Any,
        messageId: Long, builder: CopyMessageBuilder.() -> Unit = {}
    ) = apiRequest {

        val _builder = CopyMessageBuilder()

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

    suspend fun sendDocument(chatId: Long, document: File, builder: SendDocumentBuilder.() -> Unit = {}) = apiRequest {

        val _builder = SendDocumentBuilder()

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
            thumbnail = _builder.thumbnail?.toRequestBody(),
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
}


