package botapi.sender.api

import botapi.common.CHAT_ID
import botapi.common.USER_ID
import botapi.models.BaseResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface GroupsActionsApi {
    @FormUrlEncoded
    @POST("banChatMember")
    fun banChatMember(
        @Field(CHAT_ID) chatId: Long,
        @Field(USER_ID) userId: Long,
        @Field("until_date") untilDate: Long?,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("unbanChatMember")
    fun unbanChatMember(
        @Field(CHAT_ID) chatId: Long,
        @Field(USER_ID) userId: Long,
        @Field("only_if_banned") onlyIfBanned: Boolean?,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("restrictChatMember")
    fun restrictChatMember(
        @Field(CHAT_ID) chatId: Long,
        @Field(USER_ID) userId: Long,
        @Field("permissions") permissions: String,
        @Field("until_date") untilDate: Long?,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("promoteChatMember")
    fun promoteChatMember(
        @Field(CHAT_ID) chatId: Long,
        @Field(USER_ID) userId: Long,
        @Field("is_anonymous") isAnonymous: Boolean?,
        @Field("can_change_info") canChangeInfo: Boolean?,
        @Field("can_post_messages") canPostMessages: Boolean?,
        @Field("can_edit_messages") canEditMessages: Boolean?,
        @Field("can_delete_messages") canDeleteMessages: Boolean?,
        @Field("can_invite_users") canInviteUsers: Boolean?,
        @Field("can_restrict_members") canRestrictMembers: Boolean?,
        @Field("can_pin_messages") canPinMessages: Boolean?,
        @Field("can_promote_members") canPromoteMembers: Boolean?,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("setChatPermissions")
    fun setChatPermissions(
        @Field(CHAT_ID) chatId: Long,
        @Field("permissions") permissions: String,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("exportChatInviteLink")
    fun exportChatInviteLink(
        @Field(CHAT_ID) chatId: Long,
    ): Response<BaseResponse<String>>

    @Multipart
    @POST("setChatPhoto")
    fun setChatPhoto(
        @Part(CHAT_ID) chatId: Long,
        @Part("photo") photo: MultipartBody.Part,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("deleteChatPhoto")
    fun deleteChatPhoto(
        @Field(CHAT_ID) chatId: Long,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("setChatTitle")
    fun setChatTitle(
        @Field(CHAT_ID) chatId: Long,
        @Field("title") title: String,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("setChatDescription")
    fun setChatDescription(
        @Field(CHAT_ID) chatId: Long,
        @Field("description") description: String,
    ): Response<BaseResponse<Boolean>>

}