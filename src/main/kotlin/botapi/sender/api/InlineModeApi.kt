package botapi.sender.api

import botapi.models.BaseResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InlineModeApi {
    @FormUrlEncoded
    @POST("answerInlineQuery")
    suspend fun answerInlineQuery(
        @Field("inline_query_id") inlineQueryId: String,
        @Field("results") results: String,
        @Field("cache_time") cacheTime: Int?,
        @Field("is_personal") isPersonal: Boolean?,
        @Field("next_offset") nextOffset: String?,
        @Field("button") button: String?,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("answerCallbackQuery")
    suspend fun answerCallbackQuery(
        @Field("callback_query_id") callbackQueryId: String,
        @Field("text") text: String?,
        @Field("show_alert") showAlert: Boolean?,
        @Field("url") url: String?,
        @Field("cache_time") cacheTime: Int?,
    ): Response<BaseResponse<Boolean>>
}