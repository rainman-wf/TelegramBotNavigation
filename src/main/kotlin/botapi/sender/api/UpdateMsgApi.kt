package botapi.sender.api

import botapi.*
import botapi.common.CAPTION
import botapi.common.CAPTION_ENTITIES
import botapi.common.CHAT_ID
import botapi.common.DISABLE_WEB_PAGE_PREVIEW
import botapi.common.ENTITIES
import botapi.common.INLINE_MESSAGE_ID
import botapi.common.MESSAGE_ID
import botapi.common.PARSE_MODE
import botapi.common.REPLY_MARKUP
import botapi.common.TEXT
import botapi.models.*
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

internal interface UpdateMsgApi {

    @FormUrlEncoded
    @POST("editMessageText")
    suspend fun editMessageText(
        @Field(CHAT_ID) chatId: Any?,
        @Field(MESSAGE_ID) messageId: Long?,
        @Field(INLINE_MESSAGE_ID) inlineMessageId: String?,
        @Field(TEXT) text: String,
        @Field(PARSE_MODE) parseMode: ParseMode?,
        @Field(ENTITIES) entities: String?,
        @Field(DISABLE_WEB_PAGE_PREVIEW) disableWebPagePreview: Boolean?,
        @Field(REPLY_MARKUP) replyMarkup: String?
    ): Response<BaseResponse<Message>>

    @FormUrlEncoded
    @POST("editMessageCaption")
    suspend fun editMessageCaption(
        @Field(CHAT_ID) chatId: Any?,
        @Field(MESSAGE_ID) messageId: Long?,
        @Field(INLINE_MESSAGE_ID) inlineMessageId: String?,
        @Field(CAPTION) caption: String,
        @Field(PARSE_MODE) parseMode: ParseMode?,
        @Field(CAPTION_ENTITIES) captionEntities: String?,
        @Field(REPLY_MARKUP) replyMarkup: String?
    ): Response<BaseResponse<Message>>

    @FormUrlEncoded
    @POST("editMessageMedia")
    suspend fun editMessageMedia(
        @Field(CHAT_ID) chatId: Any?,
        @Field(MESSAGE_ID) messageId: Long?,
        @Field(INLINE_MESSAGE_ID) inlineMessageId: String?,
        @Field("media") media: String,
        @Field(REPLY_MARKUP) replyMarkup: String?,
    ): Response<BaseResponse<Message>>

    @FormUrlEncoded
    @POST("editMessageReplyMarkup")
    suspend fun editMessageReplyMarkup(
        @Field(CHAT_ID) chatId: Any,
        @Field(MESSAGE_ID) messageId: Long,
        @Field(REPLY_MARKUP) replyMarkup: String?
    ): Response<BaseResponse<Message>>

    @FormUrlEncoded
    @POST("editMessageReplyMarkup")
    suspend fun editMessageReplyMarkup(
        @Field(INLINE_MESSAGE_ID) inlineMessageId: String,
        @Field(REPLY_MARKUP) replyMarkup: String?
    ): Response<BaseResponse<Boolean>>

    @POST("deleteMessage")
    suspend fun deleteMessage(
        @Query(CHAT_ID) chatId: Any,
        @Query(MESSAGE_ID) messageId: Long
    ): Response<BaseResponse<Boolean>>

}