package botapi.models

import com.google.gson.annotations.SerializedName as Name

enum class ParseMode {
    Markdown,
    MarkdownV2,
    HTML
}

data class BaseResponse<T>(
    val result: T? = null,
    val ok: Boolean,
    @Name("error_code") val errorCode: Int? = null,
    @Name("description") val errorDescription: String? = null,
    val parameters: ResponseParameters? = null,
)

data class ResponseParameters(
    @Name("migrate_to_chat_id") val migrateToChatId: Int? = null,
    @Name("retry_after") val retryAfter: Int? = null,
)

data class User(
    @Name("id") val id: Long,
    @Name("is_bot") val isBot: Boolean,
    @Name("first_name") val firstName: String,
    @Name("last_name") val lastName: String? = null,
    @Name("username") val username: String? = null,
    @Name("language_code") val languageCode: String? = null,
    @Name("is_premium") val isPremium: Boolean? = null,
    @Name("added_to_attachment_menu") val addedToAttachmentMenu: Boolean? = null,
    @Name("can_join_groups") val canJoinGroups: Boolean? = null,
    @Name("can_read_all_group_messages") val canReadAllGroupMessages: Boolean? = null,
    @Name("supports_inline_queries") val supportsInlineQueries: Boolean? = null,
)

data class Chat(
    @Name("id") val id: Long,
    @Name("type") val type: String,
    @Name("title") val title: String? = null,
    @Name("username") val username: String? = null,
    @Name("first_name") val firstName: String? = null,
    @Name("last_name") val lastName: String? = null,
    @Name("is_forum") val isForum: Boolean? = null,
    @Name("photo") val photo: ChatPhoto? = null,
    @Name("active_usernames") val activeUsernames: List<String>? = null,
    @Name("emoji_status_custom_emoji_id") val emojiStatusCustomEmojiId: String? = null,
    @Name("bio") val bio: String? = null,
    @Name("has_private_forwards") val hasPrivateForwards: Boolean? = null,
    @Name("has_restricted_voice_and_video_messages") val hasRestrictedVoiceAndVideoMessages: Boolean? = null,
    @Name("join_to_send_messages") val joinToSendMessages: Boolean? = null,
    @Name("join_by_request") val joinByRequest: Boolean? = null,
    @Name("description") val description: String? = null,
    @Name("invite_link") val inviteLink: String? = null,
    @Name("pinned_message") val pinnedMessage: Message? = null,
    @Name("permissions") val permissions: ChatPermissions? = null,
    @Name("slow_mode_delay") val slowModeDelay: Int? = null,
    @Name("message_auto_delete_time") val messageAutoDeleteTime: Int? = null,
    @Name("has_aggressive_anti_spam_enabled") val hasAggressiveAntiSpamEnabled: Boolean? = null,
    @Name("has_hidden_members") val hasHiddenMembers: Boolean? = null,
    @Name("has_protected_content") val hasProtectedContent: Boolean? = null,
    @Name("sticker_set_name") val stickerSetName: String? = null,
    @Name("can_set_sticker_set") val canSetStickerSet: Boolean? = null,
    @Name("linked_chat_id") val linkedChatId: Int? = null,
    @Name("location") val location: ChatLocation? = null,
)

interface Sendresponse

data class Message(
    @Name("message_id") val messageId: Long,
    @Name("message_thread_id") val messageThreadId: Long? = null,
    @Name("from") val from: User? = null,
    @Name("sender_chat") val senderChat: Chat? = null,
    @Name("date") val date: Long,
    @Name("chat") val chat: Chat,
    @Name("forward_from") val forwardFrom: User? = null,
    @Name("forward_from_chat") val forwardFromChat: Chat? = null,
    @Name("forward_from_message_id") val forwardFromMessageId: Int? = null,
    @Name("forward_signature") val forwardSignature: String? = null,
    @Name("forward_sender_name") val forwardSenderName: String? = null,
    @Name("forward_date") val forwardDate: Int? = null,
    @Name("is_topic_message") val isTopicMessage: Boolean? = null,
    @Name("is_automatic_forward") val isAutomaticForward: Boolean? = null,
    @Name("reply_to_message") val replyToMessage: Message? = null,
    @Name("via_bot") val viaBot: User? = null,
    @Name("edit_date") val editDate: Int? = null,
    @Name("has_protected_content") val hasProtectedContent: Boolean? = null,
    @Name("media_group_id") val mediaGroupId: String? = null,
    @Name("author_signature") val authorSignature: String? = null,
    @Name("text") val text: String? = null,
    @Name("entities") val entities: List<MessageEntity>? = null,
    @Name("animation") val animation: Animation? = null,
    @Name("audio") val audio: Audio? = null,
    @Name("document") val document: Document? = null,
    @Name("photo") val photo: List<PhotoSize>? = null,
    @Name("sticker") val sticker: Sticker? = null,
    @Name("video") val video: Video? = null,
    @Name("video_note") val videoNote: VideoNote? = null,
    @Name("voice") val voice: Voice? = null,
    @Name("caption") val caption: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("has_media_spoiler") val hasMediaSpoiler: Boolean? = null,
    @Name("contact") val contact: Contact? = null,
    @Name("dice") val dice: Dice? = null,
    @Name("game") val game: Game? = null,
    @Name("poll") val poll: Poll? = null,
    @Name("venue") val venue: Venue? = null,
    @Name("location") val location: Location? = null,
    @Name("new_chat_members") val newChatMembers: List<User>? = null,
    @Name("left_chat_member") val leftChatMember: User? = null,
    @Name("new_chat_title") val newChatTitle: String? = null,
    @Name("new_chat_photo") val newChatPhoto: List<PhotoSize>? = null,
    @Name("delete_chat_photo") val deleteChatPhoto: Boolean? = null,
    @Name("group_chat_created") val groupChatCreated: Boolean? = null,
    @Name("supergroup_chat_created") val supergroupChatCreated: Boolean? = null,
    @Name("channel_chat_created") val channelChatCreated: Boolean? = null,
    @Name("message_auto_delete_timer_changed") val messageAutoDeleteTimerChanged: MessageAutoDeleteTimerChanged? = null,
    @Name("migrate_to_chat_id") val migrateToChatId: Int? = null,
    @Name("migrate_from_chat_id") val migrateFromChatId: Int? = null,
    @Name("pinned_message") val pinnedMessage: Message? = null,
    @Name("invoice") val invoice: Invoice? = null,
    @Name("successful_payment") val successfulPayment: SuccessfulPayment? = null,
    @Name("user_shared") val userShared: UserShared? = null,
    @Name("chat_shared") val chatShared: ChatShared? = null,
    @Name("connected_website") val connectedWebsite: String? = null,
    @Name("write_access_allowed") val writeAccessAllowed: WriteAccessAllowed? = null,
    @Name("passport_data") val passportData: PassportData? = null,
    @Name("proximity_alert_triggered") val proximityAlertTriggered: ProximityAlertTriggered? = null,
    @Name("forum_topic_created") val forumTopicCreated: ForumTopicCreated? = null,
    @Name("forum_topic_edited") val forumTopicEdited: ForumTopicEdited? = null,
    @Name("forum_topic_closed") val forumTopicClosed: ForumTopicClosed? = null,
    @Name("forum_topic_reopened") val forumTopicReopened: ForumTopicReopened? = null,
    @Name("general_forum_topic_hidden") val generalForumTopicHidden: GeneralForumTopicHidden? = null,
    @Name("general_forum_topic_unhidden") val generalForumTopicUnhidden: GeneralForumTopicUnhidden? = null,
    @Name("video_chat_scheduled") val videoChatScheduled: VideoChatScheduled? = null,
    @Name("video_chat_started") val videoChatStarted: VideoChatStarted? = null,
    @Name("video_chat_ended") val videoChatEnded: VideoChatEnded? = null,
    @Name("video_chat_participants_invited") val videoChatParticipantsInvited: VideoChatParticipantsInvited? = null,
    @Name("web_app_data") val webAppData: WebAppData? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
) : Sendresponse

data class MessageId(
    @Name("message_id") val messageId: Long,
) : Sendresponse

data class MessageEntity(
    @Name("type") val type: String,
    @Name("offset") val offset: Int,
    @Name("length") val length: Int,
    @Name("url") val url: String? = null,
    @Name("user") val user: User? = null,
    @Name("language") val language: String? = null,
    @Name("custom_emoji_id") val customEmojiId: String? = null,
)

data class PhotoSize(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("width") val width: Int,
    @Name("height") val height: Int,
    @Name("file_size") val fileSize: Int? = null,
)

data class Animation(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("width") val width: Int,
    @Name("height") val height: Int,
    @Name("duration") val duration: Int,
    @Name("thumb") val thumb: PhotoSize? = null,
    @Name("file_name") val fileName: String? = null,
    @Name("mime_type") val mimeType: String? = null,
    @Name("file_size") val fileSize: Int? = null,
)

data class Audio(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("duration") val duration: Int,
    @Name("performer") val performer: String? = null,
    @Name("title") val title: String? = null,
    @Name("file_name") val fileName: String? = null,
    @Name("mime_type") val mimeType: String? = null,
    @Name("file_size") val fileSize: Int? = null,
    @Name("thumb") val thumb: PhotoSize? = null,
)

data class Document(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("thumb") val thumb: PhotoSize? = null,
    @Name("file_name") val fileName: String? = null,
    @Name("mime_type") val mimeType: String? = null,
    @Name("file_size") val fileSize: Int? = null,
)

data class Video(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("width") val width: Int,
    @Name("height") val height: Int,
    @Name("duration") val duration: Int,
    @Name("thumb") val thumb: PhotoSize? = null,
    @Name("file_name") val fileName: String? = null,
    @Name("mime_type") val mimeType: String? = null,
    @Name("file_size") val fileSize: Int? = null,
)

data class VideoNote(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("length") val length: Int,
    @Name("duration") val duration: Int,
    @Name("thumb") val thumb: PhotoSize? = null,
    @Name("file_size") val fileSize: Int? = null,
)

data class Voice(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("duration") val duration: Int,
    @Name("mime_type") val mimeType: String? = null,
    @Name("file_size") val fileSize: Int? = null,
)

data class Contact(
    @Name("phone_number") val phoneNumber: String,
    @Name("first_name") val firstName: String,
    @Name("last_name") val lastName: String? = null,
    @Name("user_id") val userId: Long? = null,
    @Name("vcard") val vcard: String? = null,
)

data class Dice(
    @Name("emoji") val emoji: String,
    @Name("value") val value: Int,
)

data class PollOption(
    @Name("text") val text: String,
    @Name("voter_count") val voterCount: Int,
)

data class PollAnswer(
    @Name("poll_id") val pollId: String,
    @Name("user") val user: User,
    @Name("option_ids") val optionIds: List<Int>,
)

data class Poll(
    @Name("id") val id: String,
    @Name("question") val question: String,
    @Name("options") val options: List<PollOption>,
    @Name("total_voter_count") val totalVoterCount: Int,
    @Name("is_closed") val isClosed: Boolean,
    @Name("is_anonymous") val isAnonymous: Boolean,
    @Name("type") val type: String,
    @Name("allows_multiple_answers") val allowsMultipleAnswers: Boolean,
    @Name("correct_option_id") val correctOptionId: Int? = null,
    @Name("explanation") val explanation: String? = null,
    @Name("explanation_entities") val explanationEntities: List<MessageEntity>? = null,
    @Name("open_period") val openPeriod: Int? = null,
    @Name("close_date") val closeDate: Int? = null,
)

data class Location(
    @Name("longitude") val longitude: Float,
    @Name("latitude") val latitude: Float,
    @Name("horizontal_accuracy") val horizontalAccuracy: Float? = null,
    @Name("live_period") val livePeriod: Int? = null,
    @Name("heading") val heading: Int? = null,
    @Name("proximity_alert_radius") val proximityAlertRadius: Int? = null,
)

data class Venue(
    @Name("location") val location: Location,
    @Name("title") val title: String,
    @Name("address") val address: String,
    @Name("foursquare_id") val foursquareId: String? = null,
    @Name("foursquare_type") val foursquareType: String? = null,
    @Name("google_place_id") val googlePlaceId: String? = null,
    @Name("google_place_type") val googlePlaceType: String? = null,
)

data class WebAppData(
    @Name("data") val data: String,
    @Name("button_text") val buttonText: String,
)

data class ProximityAlertTriggered(
    @Name("traveler") val traveler: User,
    @Name("watcher") val watcher: User,
    @Name("distance") val distance: Int,
)

data class MessageAutoDeleteTimerChanged(
    @Name("message_auto_delete_time") val messageAutoDeleteTime: Int,
)

data class ForumTopicCreated(
    @Name("name") val name: String,
    @Name("icon_color") val iconColor: Int,
    @Name("icon_custom_emoji_id") val iconCustomEmojiId: String? = null,
)

class ForumTopicClosed

data class ForumTopicEdited(
    @Name("name") val name: String? = null,
    @Name("icon_custom_emoji_id") val iconCustomEmojiId: String? = null,
)

class ForumTopicReopened

class GeneralForumTopicHidden

class GeneralForumTopicUnhidden

data class UserShared(
    @Name("request_id") val requestId: Int,
    @Name("user_id") val userId: Long,
)

data class ChatShared(
    @Name("request_id") val requestId: Int,
    @Name("chat_id") val chatId: Int,
)

class WriteAccessAllowed

data class VideoChatScheduled(
    @Name("start_date") val startDate: Int,
)

class VideoChatStarted

data class VideoChatEnded(
    @Name("duration") val duration: Int,
)

data class VideoChatParticipantsInvited(
    @Name("users") val users: List<User>,
)

data class UserProfilePhotos(
    @Name("total_count") val totalCount: Int,
    @Name("photos") val photos: List<List<PhotoSize>>,
)

data class File(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("file_size") val fileSize: Int? = null,
    @Name("file_path") val filePath: String? = null,
)

data class WebAppInfo(
    @Name("url") val url: String,
)

interface ReplyMarkup

data class ReplyKeyboardMarkup(
    @Name("keyboard") val keyboard: List<List<KeyboardButton>>,
    @Name("is_persistent") val isPersistent: Boolean? = null,
    @Name("resize_keyboard") val resizeKeyboard: Boolean? = null,
    @Name("one_time_keyboard") val oneTimeKeyboard: Boolean? = null,
    @Name("input_field_placeholder") val inputFieldPlaceholder: String? = null,
    @Name("selective") val selective: Boolean? = null,
) : ReplyMarkup

interface Button {
    val text: String
}

data class KeyboardButton(
    @Name("text") override val text: String,
    @Name("request_user") val requestUser: KeyboardButtonRequestUser? = null,
    @Name("request_chat") val requestChat: KeyboardButtonRequestChat? = null,
    @Name("request_contact") val requestContact: Boolean? = null,
    @Name("request_location") val requestLocation: Boolean? = null,
    @Name("request_poll") val requestPoll: KeyboardButtonPollType? = null,
    @Name("web_app") val webApp: WebAppInfo? = null,
) : Button

data class KeyboardButtonRequestUser(
    @Name("request_id") val requestId: Int,
    @Name("user_is_bot") val userIsBot: Boolean? = null,
    @Name("user_is_premium") val userIsPremium: Boolean? = null,
)

data class KeyboardButtonRequestChat(
    @Name("request_id") val requestId: Int,
    @Name("chat_is_channel") val chatIsChannel: Boolean,
    @Name("chat_is_forum") val chatIsForum: Boolean? = null,
    @Name("chat_has_username") val chatHasUsername: Boolean? = null,
    @Name("chat_is_created") val chatIsCreated: Boolean? = null,
    @Name("user_administrator_rights") val userAdministratorRights: ChatAdministratorRights? = null,
    @Name("bot_administrator_rights") val botAdministratorRights: ChatAdministratorRights? = null,
    @Name("bot_is_member") val botIsMember: Boolean? = null,
)

data class KeyboardButtonPollType(
    @Name("type") val type: String? = null,
)

data class ReplyKeyboardRemove(
    @Name("remove_keyboard") val removeKeyboard: Boolean,
    @Name("selective") val selective: Boolean? = null,
) : ReplyMarkup

data class InlineKeyboardMarkup(
    @Name("inline_keyboard") val inlineKeyboard: List<List<InlineKeyboardButton>>,
) : ReplyMarkup


data class InlineKeyboardButton(
    @Name("text") override val text: String,
    @Name("url") val url: String? = null,
    @Name("callback_data") val callbackData: String? = null,
    @Name("web_app") val webApp: WebAppInfo? = null,
    @Name("login_url") val loginUrl: LoginUrl? = null,
    @Name("switch_inline_query") val switchInlineQuery: String? = null,
    @Name("switch_inline_query_current_chat") val switchInlineQueryCurrentChat: String? = null,
    @Name("callback_game") val callbackGame: CallbackGame? = null,
    @Name("pay") val pay: Boolean? = null,
) : Button

data class LoginUrl(
    @Name("url") val url: String,
    @Name("forward_text") val forwardText: String? = null,
    @Name("bot_username") val botUsername: String? = null,
    @Name("request_write_access") val requestWriteAccess: Boolean? = null,
)

data class CallbackQuery(
    @Name("id") val id: String,
    @Name("from") val from: User,
    @Name("message") val message: Message? = null,
    @Name("inline_message_id") val inlineMessageId: String? = null,
    @Name("chat_instance") val chatInstance: String,
    @Name("data") val data: String? = null,
    @Name("game_short_name") val gameShortName: String? = null,
)

data class ForceReply(
    @Name("force_reply") val forceReply: Boolean,
    @Name("input_field_placeholder") val inputFieldPlaceholder: String? = null,
    @Name("selective") val selective: Boolean? = null,
) : ReplyMarkup

data class ChatPhoto(
    @Name("small_file_id") val smallFileId: String,
    @Name("small_file_unique_id") val smallFileUniqueId: String,
    @Name("big_file_id") val bigFileId: String,
    @Name("big_file_unique_id") val bigFileUniqueId: String,
)

data class ChatInviteLink(
    @Name("invite_link") val inviteLink: String,
    @Name("creator") val creator: User,
    @Name("creates_join_request") val createsJoinRequest: Boolean,
    @Name("is_primary") val isPrimary: Boolean,
    @Name("is_revoked") val isRevoked: Boolean,
    @Name("name") val name: String? = null,
    @Name("expire_date") val expireDate: Int? = null,
    @Name("member_limit") val memberLimit: Int? = null,
    @Name("pending_join_request_count") val pendingJoinRequestCount: Int? = null,
)

data class ChatAdministratorRights(
    @Name("is_anonymous") val isAnonymous: Boolean,
    @Name("can_manage_chat") val canManageChat: Boolean,
    @Name("can_delete_messages") val canDeleteMessages: Boolean,
    @Name("can_manage_video_chats") val canManageVideoChats: Boolean,
    @Name("can_restrict_members") val canRestrictMembers: Boolean,
    @Name("can_promote_members") val canPromoteMembers: Boolean,
    @Name("can_change_info") val canChangeInfo: Boolean,
    @Name("can_invite_users") val canInviteUsers: Boolean,
    @Name("can_post_messages") val canPostMessages: Boolean? = null,
    @Name("can_edit_messages") val canEditMessages: Boolean? = null,
    @Name("can_pin_messages") val canPinMessages: Boolean? = null,
    @Name("can_manage_topics") val canManageTopics: Boolean? = null,
)

data class ChatMember (
    @Name("user") val user: User,
    @Name("status") val status: String,
    @Name("custom_title") val customTitle: String? = null,
    @Name("is_anonymous") val isAnonymous: Boolean? = null,
    @Name("until_date") val forceReply: Int? = null,
    @Name("can_be_edited") val canBeEdited: Boolean? = null,
    @Name("can_post_messages") val canPostMessages: Boolean? = null,
    @Name("can_edit_messages") val canEditMessages: Boolean? = null,
    @Name("can_delete_messages") val canDeleteMessages: Boolean? = null,
    @Name("can_restrict_members") val canRestrictMembers: Boolean? = null,
    @Name("can_promote_members") val canPromoteMembers: Boolean? = null,
    @Name("can_change_info") val canChangeInfo: Boolean? = null,
    @Name("can_invite_users") val canInviteUsers: Boolean? = null,
    @Name("can_pin_messages") val canPinMessages: Boolean? = null,
    @Name("is_member") val isMember: Boolean? = null,
    @Name("can_send_messages") val canSendMessages: Boolean? = null,
    @Name("can_send_media_messages") val canSendMediaMessages: Boolean? = null,
    @Name("can_send_polls") val canSendPolls: Boolean? = null,
    @Name("can_send_other_messages") val canSendOtherMessages: Boolean? = null,
    @Name("can_add_web_page_previews") val canAddWebPagePreviews: Boolean? = null,
)

/*data class ChatMemberOwner(
    @Name("status") val status: String,
    @Name("user") val user: User,
    @Name("is_anonymous") val isAnonymous: Boolean? = null,
    @Name("custom_title") val customTitle: String? = null,
) : ChatMember

data class ChatMemberAdministrator(
    @Name("status") val status: String,
    @Name("user") val user: User,
    @Name("can_be_edited") val canBeEdited: Boolean? = null,
    @Name("is_anonymous") val isAnonymous: Boolean? = null,
    @Name("can_manage_chat") val canManageChat: Boolean? = null,
    @Name("can_delete_messages") val canDeleteMessages: Boolean? = null,
    @Name("can_manage_video_chats") val canManageVideoChats: Boolean? = null,
    @Name("can_restrict_members") val canRestrictMembers: Boolean? = null,
    @Name("can_promote_members") val canPromoteMembers: Boolean? = null,
    @Name("can_change_info") val canChangeInfo: Boolean? = null,
    @Name("can_invite_users") val canInviteUsers: Boolean? = null,
    @Name("can_post_messages") val canPostMessages: Boolean? = null,
    @Name("can_edit_messages") val canEditMessages: Boolean? = null,
    @Name("can_pin_messages") val canPinMessages: Boolean? = null,
    @Name("can_manage_topics") val canManageTopics: Boolean? = null,
    @Name("custom_title") val customTitle: String? = null,
) : ChatMember

data class ChatMemberMember(
    @Name("status") val status: String,
    @Name("user") val user: User,
) : ChatMember

data class ChatMemberRestricted(
    @Name("status") val status: String,
    @Name("user") val user: User,
    @Name("is_member") val isMember: Boolean,
    @Name("can_send_messages") val canSendMessages: Boolean? = null,
    @Name("can_send_audios") val canSendAudios: Boolean? = null,
    @Name("can_send_documents") val canSendDocuments: Boolean? = null,
    @Name("can_send_photos") val canSendPhotos: Boolean? = null,
    @Name("can_send_videos") val canSendVideos: Boolean? = null,
    @Name("can_send_video_notes") val canSendVideoNotes: Boolean? = null,
    @Name("can_send_voice_notes") val canSendVoiceNotes: Boolean? = null,
    @Name("can_send_polls") val canSendPolls: Boolean? = null,
    @Name("can_send_other_messages") val canSendOtherMessages: Boolean? = null,
    @Name("can_add_web_page_previews") val canAddWebPagePreviews: Boolean? = null,
    @Name("can_change_info") val canChangeInfo: Boolean? = null,
    @Name("can_invite_users") val canInviteUsers: Boolean? = null,
    @Name("can_pin_messages") val canPinMessages: Boolean? = null,
    @Name("can_manage_topics") val canManageTopics: Boolean? = null,
    @Name("until_date") val untilDate: Int,
) : ChatMember

data class ChatMemberLeft(
    @Name("status") val status: String,
    @Name("user") val user: User,
) : ChatMember

data class ChatMemberBanned(
    @Name("status") val status: String,
    @Name("user") val user: User,
    @Name("until_date") val untilDate: Int,
) : ChatMember*/

data class ChatMemberUpdated(
    @Name("chat") val chat: Chat,
    @Name("from") val from: User,
    @Name("date") val date: Int,
    @Name("old_chat_member") val oldChatMember: ChatMember,
    @Name("new_chat_member") val newChatMember: ChatMember,
    @Name("invite_link") val inviteLink: ChatInviteLink? = null,
)

data class ChatJoinRequest(
    @Name("chat") val chat: Chat,
    @Name("from") val from: User,
    @Name("user_chat_id") val userChatId: Int,
    @Name("date") val date: Int,
    @Name("bio") val bio: String? = null,
    @Name("invite_link") val inviteLink: ChatInviteLink? = null,
)

data class ChatPermissions(
    @Name("can_send_messages") val canSendMessages: Boolean? = null,
    @Name("can_send_audios") val canSendAudios: Boolean? = null,
    @Name("can_send_documents") val canSendDocuments: Boolean? = null,
    @Name("can_send_photos") val canSendPhotos: Boolean? = null,
    @Name("can_send_videos") val canSendVideos: Boolean? = null,
    @Name("can_send_video_notes") val canSendVideoNotes: Boolean? = null,
    @Name("can_send_voice_notes") val canSendVoiceNotes: Boolean? = null,
    @Name("can_send_polls") val canSendPolls: Boolean? = null,
    @Name("can_send_other_messages") val canSendOtherMessages: Boolean? = null,
    @Name("can_add_web_page_previews") val canAddWebPagePreviews: Boolean? = null,
    @Name("can_change_info") val canChangeInfo: Boolean? = null,
    @Name("can_invite_users") val canInviteUsers: Boolean? = null,
    @Name("can_pin_messages") val canPinMessages: Boolean? = null,
    @Name("can_manage_topics") val canManageTopics: Boolean? = null,
)

data class ChatLocation(
    @Name("location") val location: Location,
    @Name("address") val address: String,
)

data class ForumTopic(
    @Name("message_thread_id") val messageThreadId: Int,
    @Name("name") val name: String,
    @Name("icon_color") val iconColor: Int,
    @Name("icon_custom_emoji_id") val iconCustomEmojiId: String? = null,
)

data class BotCommand(
    @Name("command") val command: String,
    @Name("description") val description: String,
)

sealed interface BotCommandScope

data class BotCommandScopeDefault(
    @Name("type") val type: String,
) : BotCommandScope

data class BotCommandScopeAllPrivateChats(
    @Name("type") val type: String,
) : BotCommandScope

data class BotCommandScopeAllGroupChats(

    @Name("type") val type: String,
) : BotCommandScope

data class BotCommandScopeAllChatAdministrators(
    @Name("type") val type: String,
) : BotCommandScope

data class BotCommandScopeChat(
    @Name("type") val type: String,
    @Name("chat_id") val chatId: Int,
) : BotCommandScope

data class BotCommandScopeChatAdministrators(
    @Name("type") val type: String,
    @Name("chat_id") val chatId: String,
) : BotCommandScope

data class BotCommandScopeChatMember(
    @Name("type") val type: String,
    @Name("chat_id") val chatId: String,
    @Name("user_id") val userId: Long,
) : BotCommandScope

sealed interface MenuButton

data class MenuButtonCommands(
    @Name("type") val type: String,
) : MenuButton

data class MenuButtonWebApp(
    @Name("type") val type: String,
    @Name("text") val text: String,
    @Name("web_app") val webApp: WebAppInfo,
) : MenuButton

data class MenuButtonDefault(
    @Name("type") val type: String,
) : MenuButton



sealed interface InputMedia

class InputFile

data class InputMediaPhoto(
    @Name("type") val type: String,
    @Name("media") val media: String,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: String? = null,
    @Name("has_spoiler") val hasSpoiler: Boolean? = null,
) : InputMedia

data class InputMediaVideo(
    @Name("type") val type: String,
    @Name("media") val media: String,
    @Name("thumbnail") val thumbnail: Any? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: String? = null,
    @Name("width") val width: Int? = null,
    @Name("height") val height: Int? = null,
    @Name("duration") val duration: Int? = null,
    @Name("supports_streaming") val supportsStreaming: Boolean? = null,
    @Name("has_spoiler") val hasSpoiler: Boolean? = null,
) : InputMedia

data class InputMediaAnimation(
    @Name("type") val type: String,
    @Name("media") val media: String,
    @Name("thumbnail") val thumbnail: Any? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: String? = null,
    @Name("width") val width: Int? = null,
    @Name("height") val height: Int? = null,
    @Name("duration") val duration: Int? = null,
    @Name("has_spoiler") val hasSpoiler: Boolean? = null,
) : InputMedia

data class InputMediaAudio(
    @Name("type") val type: String,
    @Name("media") val media: String,
    @Name("thumbnail") val thumbnail: Any? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: String? = null,
    @Name("duration") val duration: Int? = null,
    @Name("performer") val performer: String? = null,
    @Name("title") val title: String? = null,
) : InputMedia

data class InputMediaDocument(
    @Name("type") val type: String,
    @Name("media") val media: String,
    @Name("thumbnail") val thumbnail: Any? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: String? = null,
    @Name("disable_content_type_detection") val disableContentTypeDetection: Boolean? = null
): InputMedia

data class Sticker(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("type") val type: String,
    @Name("width") val width: Int,
    @Name("height") val height: Int,
    @Name("is_animated") val isAnimated: Boolean,
    @Name("is_video") val isVideo: Boolean,
    @Name("thumb") val thumb: PhotoSize? = null,
    @Name("emoji") val emoji: String? = null,
    @Name("set_name") val setName: String? = null,
    @Name("premium_animation") val premiumAnimation: File? = null,
    @Name("mask_position") val maskPosition: MaskPosition? = null,
    @Name("custom_emoji_id") val customEmojiId: String? = null,
    @Name("file_size") val fileSize: Int? = null,
)

data class StickerSet(
    @Name("name") val name: String,
    @Name("title") val title: String,
    @Name("sticker_type") val stickerType: String,
    @Name("is_animated") val isAnimated: Boolean,
    @Name("is_video") val isVideo: Boolean,
    @Name("stickers") val stickers: List<Sticker>,
    @Name("thumb") val thumb: PhotoSize? = null,
)

data class MaskPosition(
    @Name("point") val point: String,
    @Name("x_shift") val xShift: Float,
    @Name("y_shift") val yShift: Float,
    @Name("scale") val scale: Float,
    @Name("label") val label: String,
    @Name("amount") val amount: Int,
)

data class LabeledPrice(
    @Name("lable") val label: String,
    @Name("amount") val amount: Int
)

data class Invoice(
    @Name("title") val title: String,
    @Name("description") val description: String,
    @Name("start_parameter") val startParameter: String,
    @Name("currency") val currency: String,
    @Name("total_amount") val totalAmount: Int,
)

data class ShippingAddress(
    @Name("country_code") val countryCode: String,
    @Name("state") val state: String,
    @Name("city") val city: String,
    @Name("street_line1") val streetLine1: String,
    @Name("street_line2") val streetLine2: String,
    @Name("post_code") val postCode: String,
)

data class OrderInfo(
    @Name("name") val name: String? = null,
    @Name("phone_number") val phoneNumber: String? = null,
    @Name("email") val email: String? = null,
    @Name("shipping_address") val shippingAddress: ShippingAddress? = null,
)

data class ShippingOption(
    @Name("id") val id: String,
    @Name("title") val title: String,
    @Name("prices") val prices: List<LabeledPrice>,
)

data class SuccessfulPayment(
    @Name("currency") val currency: String,
    @Name("total_amount") val totalAmount: Int,
    @Name("invoice_payload") val invoicePayload: String,
    @Name("shipping_option_id") val shippingOptionId: String? = null,
    @Name("order_info") val orderInfo: OrderInfo? = null,
    @Name("telegram_payment_charge_id") val telegramPaymentChargeId: String,
    @Name("provider_payment_charge_id") val providerPaymentChargeId: String,
)

data class ShippingQuery(
    @Name("id") val id: String,
    @Name("from") val from: User,
    @Name("invoice_payload") val invoicePayload: String,
    @Name("shipping_address") val shippingAddress: ShippingAddress,
)

data class PreCheckoutQuery(
    @Name("id") val id: String,
    @Name("from") val from: User,
    @Name("currency") val currency: String,
    @Name("total_amount") val totalAmount: Int,
    @Name("invoice_payload") val invoicePayload: String,
    @Name("shipping_option_id") val shippingOptionId: String? = null,
    @Name("order_info") val orderInfo: OrderInfo? = null,
)

data class PassportData(
    @Name("data") val data: List<EncryptedPassportElement>,
    @Name("credentials") val credentials: EncryptedCredentials,
)

data class PassportFile(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("file_size") val fileSize: Int,
    @Name("file_date") val fileDate: Int,
)

data class EncryptedPassportElement(
    @Name("type") val type: String,
    @Name("data") val data: String? = null,
    @Name("phone_number") val phoneNumber: String? = null,
    @Name("email") val email: String? = null,
    @Name("files") val files: List<PassportFile>? = null,
    @Name("front_side") val frontSide: PassportFile? = null,
    @Name("reverse_side") val reverseSide: PassportFile? = null,
    @Name("selfie") val selfie: PassportFile? = null,
    @Name("translation") val translation: List<PassportFile>? = null,
    @Name("hash") val hash: String,
)

data class EncryptedCredentials(
    @Name("data") val data: String,
    @Name("hash") val hash: String,
    @Name("secret") val secret: String,
)

sealed interface PassportElementError

data class PassportElementErrorDataField(
    @Name("source") val source: String,
    @Name("type") val type: String,
    @Name("field_name") val fieldName: String,
    @Name("data_hash") val dataHash: String,
    @Name("message") val message: String,
) : PassportElementError

data class PassportElementErrorFrontSide(
    @Name("source") val source: String,
    @Name("type") val type: String,
    @Name("file_hash") val fileHash: String,
    @Name("message") val message: String,
) : PassportElementError

data class PassportElementErrorReverseSide(
    @Name("source") val source: String,
    @Name("type") val type: String,
    @Name("file_hash") val fileHash: String,
    @Name("message") val message: String,
) : PassportElementError

data class PassportElementErrorSelfie(
    @Name("source") val source: String,
    @Name("type") val type: String,
    @Name("file_hash") val fileHash: String,
    @Name("message") val message: String,
) : PassportElementError

data class PassportElementErrorFile(
    @Name("source") val source: String,
    @Name("type") val type: String,
    @Name("file_hash") val fileHash: String,
    @Name("message") val message: String,
) : PassportElementError

data class PassportElementErrorFiles(
    @Name("source") val source: String,
    @Name("type") val type: String,
    @Name("file_hashes") val fileHashes: List<String>,
    @Name("message") val message: String,
) : PassportElementError

data class PassportElementErrorTranslationFile(
    @Name("source") val source: String,
    @Name("type") val type: String,
    @Name("file_hash") val fileHash: String,
    @Name("message") val message: String,
) : PassportElementError

data class PassportElementErrorTranslationFiles(
    @Name("source") val source: String,
    @Name("type") val type: String,
    @Name("file_hashes") val fileHashes: List<String>,
    @Name("message") val message: String,
) : PassportElementError

data class PassportElementErrorUnspecified(
    @Name("source") val source: String,
    @Name("type") val type: String,
    @Name("element_hash") val elementHash: String,
    @Name("message") val message: String,
    @Name("title") val title: String,
    @Name("description") val description: String,
    @Name("photo") val photo: List<PhotoSize>,
    @Name("text") val text: String? = null,
    @Name("text_entities") val textEntities: List<MessageEntity>? = null,
    @Name("animation") val animation: Animation? = null,
) : PassportElementError

data class Game(
    @Name("title") val title: String,
    @Name("description") val description: String,
    @Name("photo") val photo: List<PhotoSize>,
    @Name("text") val text: String? = null,
    @Name("text_entities") val text_entities: List<MessageEntity>? = null,
    @Name("animation") val animation: Animation? = null,
)

class CallbackGame

data class GameHighScore(
    @Name("position") val position: Int,
    @Name("user") val user: User,
    @Name("score") val score: Int,
)

data class InlineQuery(
    @Name("id") val id: String,
    @Name("from") val from: User,
    @Name("query") val query: String,
    @Name("offset") val offset: String,
    @Name("chat_type") val chatType: String? = null,
    @Name("location") val location: Location? = null,
)

sealed interface InlineQueryResult

data class InlineQueryResultButton(
    @Name("text") val text: String,
    @Name("web_app") val webApp: WebAppInfo? = null,
    @Name("start_parameter") val startParameter: String? = null
)

data class InlineQueryResultArticle(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("title") val title: String,
    @Name("input_message_content") val inputMessageContent: InputMessageContent,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("url") val url: String? = null,
    @Name("hide_url") val hideUrl: Boolean? = null,
    @Name("description") val description: String? = null,
    @Name("thumb_url") val thumbUrl: String? = null,
    @Name("thumb_width") val thumbWidth: Int? = null,
    @Name("thumb_height") val thumbHeight: Int? = null,
) : InlineQueryResult

data class InlineQueryResultPhoto(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("photo_url") val photoUrl: String,
    @Name("thumb_url") val thumbUrl: String,
    @Name("photo_width") val photoWidth: Int? = null,
    @Name("photo_height") val photoHeight: Int? = null,
    @Name("title") val title: String? = null,
    @Name("description") val description: String? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultGif(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("gif_url") val gifUrl: String,
    @Name("gif_width") val gifWidth: Int? = null,
    @Name("gif_height") val gifHeight: Int? = null,
    @Name("gif_duration") val gifDuration: Int? = null,
    @Name("thumb_url") val thumbUrl: String,
    @Name("thumb_mime_type") val thumbMimeType: String? = null,
    @Name("title") val title: String? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultMpeg4Gif(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("mpeg4_url") val mpeg4Url: String,
    @Name("mpeg4_width") val mpeg4Width: Int? = null,
    @Name("mpeg4_height") val mpeg4Height: Int? = null,
    @Name("mpeg4_duration") val mpeg4Duration: Int? = null,
    @Name("thumb_url") val thumbUrl: String,
    @Name("thumb_mime_type") val thumbMimeType: String? = null,
    @Name("title") val title: String? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultVideo(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("video_url") val videoUrl: String,
    @Name("mime_type") val mimeType: String,
    @Name("thumb_url") val thumbUrl: String,
    @Name("title") val title: String,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("video_width") val videoWidth: Int? = null,
    @Name("video_height") val videoHeight: Int? = null,
    @Name("video_duration") val videoDuration: Int? = null,
    @Name("description") val description: String? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultAudio(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("audio_url") val audioUrl: String,
    @Name("title") val title: String,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("performer") val performer: String? = null,
    @Name("audio_duration") val audioDuration: Int? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultVoice(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("voice_url") val voiceUrl: String,
    @Name("title") val title: String,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("voice_duration") val voiceDuration: Int? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultDocument(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("title") val title: String,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("document_url") val documentUrl: String,
    @Name("mime_type") val mimeType: String,
    @Name("description") val description: String? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @Name("thumb_url") val thumbUrl: String? = null,
    @Name("thumb_width") val thumbWidth: Int? = null,
    @Name("thumb_height") val thumbHeight: Int? = null,
) : InlineQueryResult

data class InlineQueryResultLocation(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("latitude") val latitude: Float,
    @Name("longitude") val longitude: Float,
    @Name("title") val title: String,
    @Name("horizontal_accuracy") val horizontalAccuracy: Float? = null,
    @Name("live_period") val livePeriod: Int? = null,
    @Name("heading") val heading: Int? = null,
    @Name("proximity_alert_radius") val proximityAlertRadius: Int? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @Name("thumb_url") val thumbUrl: String? = null,
    @Name("thumb_width") val thumbWidth: Int? = null,
    @Name("thumb_height") val thumbHeight: Int? = null,
) : InlineQueryResult

data class InlineQueryResultVenue(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("latitude") val latitude: Float,
    @Name("longitude") val longitude: Float,
    @Name("title") val title: String,
    @Name("address") val address: String,
    @Name("foursquare_id") val foursquareId: String? = null,
    @Name("foursquare_type") val foursquareType: String? = null,
    @Name("google_place_id") val googlePlaceId: String? = null,
    @Name("google_place_type") val googlePlaceType: String? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @Name("thumb_url") val thumbUrl: String? = null,
    @Name("thumb_width") val thumbWidth: Int? = null,
    @Name("thumb_height") val thumbHeight: Int? = null,
) : InlineQueryResult

data class InlineQueryResultContact(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("phone_number") val phoneNumber: String,
    @Name("first_name") val firstName: String,
    @Name("last_name") val lastName: String? = null,
    @Name("vcard") val vcard: String? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @Name("thumb_url") val thumbUrl: String? = null,
    @Name("thumb_width") val thumbWidth: Int? = null,
    @Name("thumb_height") val thumbHeight: Int? = null,
) : InlineQueryResult

data class InlineQueryResultGame(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("game_short_name") val gameShortName: String,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
) : InlineQueryResult

data class InlineQueryResultCachedPhoto(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("photo_file_id") val photoFileId: String,
    @Name("title") val title: String? = null,
    @Name("description") val description: String? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultCachedGif(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("gif_file_id") val gifFileId: String,
    @Name("title") val title: String? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultCachedMpeg4Gif(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("mpeg4_file_id") val mpeg4FileId: String,
    @Name("title") val title: String? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultCachedSticker(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("sticker_file_id") val stickerFileId: String,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultCachedDocument(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("title") val title: String,
    @Name("document_file_id") val documentFileId: String,
    @Name("description") val description: String? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultCachedVideo(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("video_file_id") val videoFileId: String,
    @Name("title") val title: String,
    @Name("description") val description: String? = null,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultCachedVoice(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("voice_file_id") val voiceFileId: String,
    @Name("title") val title: String,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

data class InlineQueryResultCachedAudio(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("audio_file_id") val audioFileId: String,
    @Name("caption") val caption: String? = null,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @Name("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult

sealed interface InputMessageContent

data class InputTextMessageContent(
    @Name("message_text") val messageText: String,
    @Name("parse_mode") val parseMode: String? = null,
    @Name("entities") val entities: List<MessageEntity>? = null,
    @Name("disable_web_page_preview") val disableWebPagePreview: Boolean? = null,
) : InputMessageContent

data class InputLocationMessageContent(
    @Name("latitude") val latitude: Float,
    @Name("longitude") val longitude: Float,
    @Name("horizontal_accuracy") val horizontalAccuracy: Float? = null,
    @Name("live_period") val livePeriod: Int? = null,
    @Name("heading") val heading: Int? = null,
    @Name("proximity_alert_radius") val proximityAlertRadius: Int? = null,
) : InputMessageContent

data class InputVenueMessageContent(
    @Name("latitude") val latitude: Float,
    @Name("longitude") val longitude: Float,
    @Name("title") val title: String,
    @Name("address") val address: String,
    @Name("foursquare_id") val foursquareId: String? = null,
    @Name("foursquare_type") val foursquareType: String? = null,
    @Name("google_place_id") val googlePlaceId: String? = null,
    @Name("google_place_type") val googlePlaceType: String? = null,
) : InputMessageContent

data class InputContactMessageContent(
    @Name("phone_number") val phoneNumber: String,
    @Name("first_name") val firstName: String,
    @Name("last_name") val lastName: String? = null,
    @Name("vcard") val vcard: String? = null,
) : InputMessageContent

data class InputInvoiceMessageContent(
    @Name("title") val title: String,
    @Name("description") val description: String,
    @Name("payload") val payload: String,
    @Name("provider_token") val providerToken: String,
    @Name("currency") val currency: String,
    @Name("prices") val prices: List<LabeledPrice>,
    @Name("max_tip_amount") val maxTipAmount: Int? = null,
    @Name("suggested_tip_amounts") val suggestedTipAmounts: List<Int>? = null,
    @Name("provider_data") val providerData: String? = null,
    @Name("photo_url") val photoUrl: String? = null,
    @Name("photo_size") val photoSize: Int? = null,
    @Name("photo_width") val photoWidth: Int? = null,
    @Name("photo_height") val photoHeight: Int? = null,
    @Name("need_name") val needName: Boolean? = null,
    @Name("need_phone_number") val needPhoneNumber: Boolean? = null,
    @Name("need_email") val needEmail: Boolean? = null,
    @Name("need_shipping_address") val needShippingAddress: Boolean? = null,
    @Name("send_phone_number_to_provider") val sendPhoneNumberToProvider: Boolean? = null,
    @Name("send_email_to_provider") val sendEmailToProvider: Boolean? = null,
    @Name("is_flexible") val isFlexible: Boolean? = null,
) : InputMessageContent

data class ChosenInlineResult(
    @Name("result_id") val resultId: String,
    @Name("from") val from: User,
    @Name("location") val location: Location? = null,
    @Name("inline_message_id") val inlineMessageId: String? = null,
    @Name("query") val query: String,
)

data class SentWebAppMessage(
    @Name("inline_message_id") val inlineMessageId: String? = null
)


data class Update(
    @Name("update_id") val updateId: Int,
    @Name("message") val message: Message? = null,
    @Name("edited_message") val editedMessage: Message? = null,
    @Name("channel_post") val channelPost: Message? = null,
    @Name("edited_channel_post") val editedChannelPost: Message? = null,
    @Name("inline_query") val inlineQuery: InlineQuery? = null,
    @Name("chosen_inline_result") val chosenInlineResult: ChosenInlineResult? = null,
    @Name("callback_query") val callbackQuery: CallbackQuery? = null,
    @Name("shipping_query") val shippingQuery: ShippingQuery? = null,
    @Name("pre_checkout_query") val preCheckoutQuery: PreCheckoutQuery? = null,
    @Name("poll") val poll: Poll? = null,
    @Name("poll_answer") val pollAnswer: PollAnswer? = null,
    @Name("my_chat_member") val myChatMember: ChatMemberUpdated? = null,
    @Name("chat_member") val chatMember: ChatMemberUpdated? = null,
    @Name("chat_join_request") val chatJoinRequest: ChatJoinRequest? = null
)


