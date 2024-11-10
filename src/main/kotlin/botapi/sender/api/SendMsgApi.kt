package botapi.sender.api

import botapi.*
import botapi.common.*
import botapi.common.ALLOW_SENDING_WITHOUT_REPLY
import botapi.common.AUDIO
import botapi.common.BUSINESS_CONNECTION_ID
import botapi.common.CAPTION
import botapi.common.CAPTION_ENTITIES
import botapi.common.CHAT_ID
import botapi.common.DISABLE_CONTENT_TYPE_DETECTION
import botapi.common.DISABLE_NOTIFICATION
import botapi.common.DISABLE_WEB_PAGE_PREVIEW
import botapi.common.DOCUMENT
import botapi.common.DURATION
import botapi.common.ENTITIES
import botapi.common.FROM_CHAT_ID
import botapi.common.HAS_SPOILER
import botapi.common.MESSAGE_ID
import botapi.common.MESSAGE_THREAD_ID
import botapi.common.PARSE_MODE
import botapi.common.PERFORMER
import botapi.common.PHOTO
import botapi.common.PROTECT_CONTENT
import botapi.common.REPLY_MARKUP
import botapi.common.REPLY_TO_MESSAGE_ID
import botapi.common.TEXT
import botapi.common.THUMBNAIL
import botapi.common.TITLE
import botapi.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

internal interface SendMsgApi {

    @FormUrlEncoded
    @POST("sendMessage")
    suspend fun sendMessage(
        @Field(CHAT_ID) chatId: Any,
        @Field(TEXT) text: String,
        @Field(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Field(PARSE_MODE) parseMode: String?,
        @Field(ENTITIES) entities: String?,
        @Field(DISABLE_WEB_PAGE_PREVIEW) disableWebPagePreview: Boolean?,
        @Field(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Field(PROTECT_CONTENT) protectContent: Boolean?,
        @Field(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Field(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Field(REPLY_MARKUP) replyMarkup: String?
    ): Response<BaseResponse<Message>>

    @FormUrlEncoded
    @POST("forwardMessage")
    suspend fun forwardMessage(
        @Field(CHAT_ID) chatId: Any,
        @Field(FROM_CHAT_ID) fromChatId: Any,
        @Field(MESSAGE_ID) messageId: Long,
        @Field(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Field(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Field(PROTECT_CONTENT) protectContent: Boolean?
    ): Response<BaseResponse<Message>>


    @FormUrlEncoded
    @POST("copyMessage")
    suspend fun copyMessage(
        @Field(CHAT_ID) chatId: Any,
        @Field(FROM_CHAT_ID) fromChatId: Any,
        @Field(MESSAGE_ID) messageId: Long,
        @Field(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Field(CAPTION) caption: String?,
        @Field(PARSE_MODE) parseMode: String?,
        @Field(CAPTION_ENTITIES) captionEntities: String?,
        @Field(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Field(PROTECT_CONTENT) protectContent: Boolean?,
        @Field(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Field(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Field(REPLY_MARKUP) replyMarkup: String?
    ): Response<BaseResponse<MessageId>>


    @Multipart
    @POST("sendPhoto")
    suspend fun sendPhoto(
        @Query(CHAT_ID) chatId: Any,
        @Part photo: MultipartBody.Part,
        @Part(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Part(CAPTION) caption: RequestBody?,
        @Part(PARSE_MODE) parseMode: RequestBody?,
        @Part(CAPTION_ENTITIES) captionEntities: RequestBody?,
        @Part(HAS_SPOILER) hasSpoiler: Boolean?,
        @Part(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Part(PROTECT_CONTENT) protectContent: Boolean?,
        @Part(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Part(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Part(REPLY_MARKUP) replyMarkup: RequestBody?
    ): Response<BaseResponse<Message>>


    @FormUrlEncoded
    @POST("sendPhoto")
    suspend fun sendPhoto(
        @Field(CHAT_ID) chatId: Any,
        @Field(PHOTO) photo: String,
        @Field(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Field(CAPTION) caption: String?,
        @Field(PARSE_MODE) parseMode: String?,
        @Field(CAPTION_ENTITIES) captionEntities: String?,
        @Field(HAS_SPOILER) hasSpoiler: Boolean?,
        @Field(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Field(PROTECT_CONTENT) protectContent: Boolean?,
        @Field(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Field(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Field(REPLY_MARKUP) replyMarkup: String?
    ): Response<BaseResponse<Message>>

    @Multipart
    @POST("sendAudio")
    suspend fun sendAudio(
        @Query(CHAT_ID) chatId: Any,
        @Part audio: MultipartBody.Part,
        @Part(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Part(CAPTION) caption: RequestBody?,
        @Part(PARSE_MODE) parseMode: RequestBody?,
        @Part(CAPTION_ENTITIES) captionEntities: RequestBody?,
        @Part(DURATION) duration: Int?,
        @Part(PERFORMER) performer: RequestBody?,
        @Part(TITLE) title: RequestBody?,
        @Part thumbnail: MultipartBody.Part?,
        @Part(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Part(PROTECT_CONTENT) protectContent: Boolean?,
        @Part(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Part(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Part(REPLY_MARKUP) replyMarkup: RequestBody?
    ): Response<BaseResponse<Message>>


    @Multipart
    @POST("sendAudio")
    suspend fun sendAudio(
        @Field(CHAT_ID) chatId: Any,
        @Field(AUDIO) audio: String,
        @Field(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Field(CAPTION) caption: String?,
        @Field(PARSE_MODE) parseMode: String?,
        @Field(CAPTION_ENTITIES) captionEntities: String?,
        @Field(DURATION) duration: Int?,
        @Field(PERFORMER) performer: String?,
        @Field(TITLE) title: String?,
        @Field(THUMBNAIL) thumbnail: String?,
        @Field(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Field(PROTECT_CONTENT) protectContent: Boolean?,
        @Field(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Field(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Field(REPLY_MARKUP) replyMarkup: String?
    ): Response<BaseResponse<Message>>

    @Multipart
    @POST("sendAudio")
    suspend fun sendAudio(
        @Part(CHAT_ID) chatId: Any,
        @Part audio: MultipartBody.Part,
        @Part(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Part(CAPTION) caption: RequestBody?,
        @Part(PARSE_MODE) parseMode: RequestBody?,
        @Part(CAPTION_ENTITIES) captionEntities: RequestBody?,
        @Part(DURATION) duration: Int?,
        @Part(PERFORMER) performer: RequestBody?,
        @Part(TITLE) title: RequestBody?,
        @Part(THUMBNAIL) thumbnail: RequestBody?,
        @Part(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Part(PROTECT_CONTENT) protectContent: Boolean?,
        @Part(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Part(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Part(REPLY_MARKUP) replyMarkup: RequestBody?
    ): Response<BaseResponse<Message>>

    @Multipart
    @POST("sendAudio")
    suspend fun sendAudio(
        @Query(CHAT_ID) chatId: Any,
        @Part(AUDIO) audio: RequestBody,
        @Part(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Part(CAPTION) caption: RequestBody?,
        @Part(PARSE_MODE) parseMode: RequestBody?,
        @Part(CAPTION_ENTITIES) captionEntities: RequestBody?,
        @Part(DURATION) duration: Int?,
        @Part(PERFORMER) performer: RequestBody?,
        @Part(TITLE) title: RequestBody?,
        @Part thumbnail: MultipartBody.Part?,
        @Part(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Part(PROTECT_CONTENT) protectContent: Boolean?,
        @Part(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Part(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Part(REPLY_MARKUP) replyMarkup: RequestBody?
    ): Response<BaseResponse<Message>>

    @Multipart
    @POST("sendDocument")
    suspend fun sendDocument(
        @Part(CHAT_ID) chatId: Any,
        @Part(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Part document: MultipartBody.Part,
        @Part thumbnail: MultipartBody.Part?,
        @Part(CAPTION) caption: RequestBody?,
        @Part(PARSE_MODE) parseMode: RequestBody?,
        @Part(CAPTION_ENTITIES) captionEntities: RequestBody?,
        @Part(DISABLE_CONTENT_TYPE_DETECTION) disableContentTypeDetection: Boolean?,
        @Part(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Part(PROTECT_CONTENT) protectContent: Boolean?,
        @Part(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Part(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Part(REPLY_MARKUP) replyMarkup: RequestBody?
    ): Response<BaseResponse<Message>>

    @Multipart
    @POST("sendDocument")
    suspend fun sendDocument(
        @Part(CHAT_ID) chatId: Any,
        @Part(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Part document: MultipartBody.Part,
        @Part(THUMBNAIL) thumbnail: RequestBody?,
        @Part(CAPTION) caption: RequestBody?,
        @Part(PARSE_MODE) parseMode: RequestBody?,
        @Part(CAPTION_ENTITIES) captionEntities: RequestBody?,
        @Part(DISABLE_CONTENT_TYPE_DETECTION) disableContentTypeDetection: Boolean?,
        @Part(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Part(PROTECT_CONTENT) protectContent: Boolean?,
        @Part(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Part(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Part(REPLY_MARKUP) replyMarkup: RequestBody?
    ): Response<BaseResponse<Message>>

    @Multipart
    @POST("sendDocument")
    suspend fun sendDocument(
        @Part(CHAT_ID) chatId: Any,
        @Part(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Part(DOCUMENT) document: RequestBody,
        @Part thumbnail: MultipartBody.Part?,
        @Part(CAPTION) caption: RequestBody?,
        @Part(PARSE_MODE) parseMode: RequestBody?,
        @Part(CAPTION_ENTITIES) captionEntities: RequestBody?,
        @Part(DISABLE_CONTENT_TYPE_DETECTION) disableContentTypeDetection: Boolean?,
        @Part(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Part(PROTECT_CONTENT) protectContent: Boolean?,
        @Part(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Part(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Part(REPLY_MARKUP) replyMarkup: RequestBody?
    ): Response<BaseResponse<Message>>

    @FormUrlEncoded
    @POST("sendDocument")
    suspend fun sendDocument(
        @Field(CHAT_ID) chatId: Any,
        @Field(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Field(DOCUMENT) document: String,
        @Field(THUMBNAIL) thumbnail: String?,
        @Field(CAPTION) caption: String?,
        @Field(PARSE_MODE) parseMode: String?,
        @Field(CAPTION_ENTITIES) captionEntities: String?,
        @Field(DISABLE_CONTENT_TYPE_DETECTION) disableContentTypeDetection: Boolean?,
        @Field(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Field(PROTECT_CONTENT) protectContent: Boolean?,
        @Field(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Field(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Field(REPLY_MARKUP) replyMarkup: String?
    ): Response<BaseResponse<Message>>

    @FormUrlEncoded
    @POST("sendSticker")
    suspend fun sendSticker(
        @Field(CHAT_ID) chatId: Long,
        @Field(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Field("sticker") sticker: String,
        @Field("emoji") emoji: String?,
        @Field(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Field(PROTECT_CONTENT) protectContent: Boolean?,
        @Field(REPLY_TO_MESSAGE_ID) replyToMessageId: Long?,
        @Field(ALLOW_SENDING_WITHOUT_REPLY) allowSendingWithoutReply: Boolean?,
        @Field(REPLY_MARKUP) replyMarkup: ReplyMarkup?,
    ): Response<BaseResponse<Message>>

    @FormUrlEncoded
    @POST("sendMediaGroup")
    suspend fun sendMediaGroup(
        @Field(BUSINESS_CONNECTION_ID) businessConnectionId: String?,
        @Field(CHAT_ID) chatId: Long,
        @Field(MESSAGE_THREAD_ID) messageThreadId: Long?,
        @Field("media") media: String,
        @Field(DISABLE_NOTIFICATION) disableNotification: Boolean?,
        @Field(PROTECT_CONTENT) protectContent: Boolean?,
        @Field(REPLY_PARAMETERS) replyParameters: ReplyParameters?
    )
}

