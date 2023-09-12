package botapi.sender.api

import botapi.common.CHAT_ID
import botapi.common.DISABLE_NOTIFICATION
import botapi.common.MESSAGE_ID
import botapi.common.USER_ID
import botapi.models.BaseResponse
import botapi.models.Chat
import botapi.models.ChatInviteLink
import botapi.models.ChatMember
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface GroupsActionsApi {
    @FormUrlEncoded
    @POST("banChatMember")
    suspend fun banChatMember(
        @Field(CHAT_ID) chatId: Any,
        @Field(USER_ID) userId: Long,
        @Field("until_date") untilDate: Long?,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("unbanChatMember")
    suspend fun unbanChatMember(
        @Field(CHAT_ID) chatId: Any,
        @Field(USER_ID) userId: Long,
        @Field("only_if_banned") onlyIfBanned: Boolean?,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("restrictChatMember")
    suspend fun restrictChatMember(
        @Field(CHAT_ID) chatId: Any,
        @Field(USER_ID) userId: Long,
        @Field("permissions") permissions: String,
        @Field("until_date") untilDate: Long?,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("promoteChatMember")
    suspend fun promoteChatMember(
        @Field(CHAT_ID) chatId: Any,
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
    @POST("setChatAdministratorCustomTitle")
    suspend fun setChatAdministratorCustomTitle(
        @Field(CHAT_ID) chatId: Any,
        @Field(USER_ID) userId: Long,
        @Field("custom_title") customTitle: String,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("banChatSenderChat")
    suspend fun banChatSenderChat(
        @Field(CHAT_ID) chatId: Any,
        @Field("sender_chat_id") senderChatId: Long,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("unbanChatSenderChat")
    suspend fun unbanChatSenderChat(
        @Field(CHAT_ID) chatId: Any,
        @Field("sender_chat_id") senderChatId: Long,
    ): Response<BaseResponse<Boolean>>


    @FormUrlEncoded
    @POST("setChatPermissions")
    suspend fun setChatPermissions(
        @Field(CHAT_ID) chatId: Any,
        @Field("permissions") permissions: String,
        @Field("use_independent_chat_permissions") useIndependentChatPermissions: Boolean?
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("exportChatInviteLink")
    suspend fun exportChatInviteLink(
        @Field(CHAT_ID) chatId: Any,
    ): Response<BaseResponse<String>>

    @FormUrlEncoded
    @POST("createChatInviteLink")
    suspend fun createChatInviteLink(
        @Field(CHAT_ID) chatId: Any,
        @Field("name") name: String?,
        @Field("expire_date") expireDate: Long?,
        @Field("member_limit") memberLimit: Int?,
        @Field("creates_join_request") createsJoinRequest: Boolean?,
    ): Response<BaseResponse<ChatInviteLink>>


    @FormUrlEncoded
    @POST("editChatInviteLink")
    suspend fun editChatInviteLink(
        @Field(CHAT_ID) chatId: Any,
        @Field("invite_link") inviteLink: String,
        @Field("name") name: String?,
        @Field("expire_date") expireDate: Long?,
        @Field("member_limit") memberLimit: Int?,
        @Field("creates_join_request") createsJoinRequest: Boolean?,
    ): Response<BaseResponse<ChatInviteLink>>

    @FormUrlEncoded
    @POST("revokeChatInviteLink")
    suspend fun revokeChatInviteLink(
        @Field(CHAT_ID) chatId: Any,
        @Field("invite_link") inviteLink: String,
    ): Response<BaseResponse<ChatInviteLink>>

    @FormUrlEncoded
    @POST("approveChatJoinRequest")
    suspend fun approveChatJoinRequest(
        @Field(CHAT_ID) chatId: Any,
        @Field(USER_ID) userId: Long
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("declineChatJoinRequest")
    suspend fun declineChatJoinRequest(
        @Field(CHAT_ID) chatId: Any,
        @Field(USER_ID) userId: Long
    ): Response<BaseResponse<Boolean>>


    @Multipart
    @POST("setChatPhoto")
    suspend fun setChatPhoto(
        @Part(CHAT_ID) chatId: Any,
        @Part("photo") photo: MultipartBody.Part,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("deleteChatPhoto")
    suspend fun deleteChatPhoto(
        @Field(CHAT_ID) chatId: Any,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("setChatTitle")
    suspend fun setChatTitle(
        @Field(CHAT_ID) chatId: Any,
        @Field("title") title: String,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("setChatDescription")
    suspend fun setChatDescription(
        @Field(CHAT_ID) chatId: Any,
        @Field("description") description: String,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("pinChatMessage")
    suspend fun pinChatMessage(
        @Field(CHAT_ID) chatId: Any,
        @Field(MESSAGE_ID) messageId: Long,
        @Field(DISABLE_NOTIFICATION) disableNotification: Boolean?
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("unpinChatMessage")
    suspend fun unpinChatMessage(
        @Field(CHAT_ID) chatId: Any,
        @Field(MESSAGE_ID) messageId: Long,
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("unpinAllChatMessages")
    suspend fun unpinAllChatMessages(
        @Field(CHAT_ID) chatId: Any
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("leaveChat")
    suspend fun leaveChat(
        @Field(CHAT_ID) chatId: Any
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("getChat")
    suspend fun getChat(
        @Field(CHAT_ID) chatId: Any
    ): Response<BaseResponse<Chat>>

    @FormUrlEncoded
    @POST("getChatAdministrators")
    suspend fun getChatAdministrators(
        @Field(CHAT_ID) chatId: Any
    ): Response<BaseResponse<List<ChatMember>>>

    @FormUrlEncoded
    @POST("getChatMemberCount")
    suspend fun getChatMemberCount(
        @Field(CHAT_ID) chatId: Any
    ): Response<BaseResponse<Int>>

    @FormUrlEncoded
    @POST("getChatMember")
    suspend fun getChatMember(
        @Field(CHAT_ID) chatId: Any,
        @Field(USER_ID) userId: Long
    ): Response<BaseResponse<ChatMember>>

    @FormUrlEncoded
    @POST("setChatStickerSet")
    suspend fun setChatStickerSet(
        @Field(CHAT_ID) chatId: Any,
        @Field("sticker_set_name") stickerSetName: String
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("deleteChatStickerSet")
    suspend fun deleteChatStickerSet(
        @Field(CHAT_ID) chatId: Any
    ): Response<BaseResponse<Boolean>>
}