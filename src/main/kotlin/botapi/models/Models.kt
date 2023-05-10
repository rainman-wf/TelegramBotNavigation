package botapi.models

import com.google.gson.annotations.SerializedName as Name

enum class ParseMode {
    Markdown,
    MarkdownV2,
    HTML
}

data class BaseResponse<T>(
    val result: T?,
    val ok: Boolean,
    @Name("error_code") val errorCode: Int? = null,
    @Name("description") val errorDescription: String? = null,
    val parameters: ResponseParameters? = null,
)

data class User(
    @Name("id") val id: Long,
    @Name("is_bot") val isBot: Boolean,
    @Name("first_name") val firstName: String,
    @Name("last_name") val lastName: String?,
    @Name("username") val username: String?,
    @Name("language_code") val languageCode: String?,
    @Name("is_premium") val isPremium: Boolean?,
    @Name("added_to_attachment_menu") val addedToAttachmentMenu: Boolean?,
    @Name("can_join_groups") val canJoinGroups: Boolean?,
    @Name("can_read_all_group_messages") val canReadAllGroupMessages: Boolean?,
    @Name("supports_inline_queries") val supportsInlineQueries: Boolean?,
)

data class Chat(
    @Name("id") val id: Long,
    @Name("type") val type: String,
    @Name("title") val title: String?,
    @Name("username") val username: String?,
    @Name("first_name") val firstName: String?,
    @Name("last_name") val lastName: String?,
    @Name("is_forum") val isForum: Boolean?,
    @Name("photo") val photo: ChatPhoto?,
    @Name("active_usernames") val activeUsernames: List<String>?,
    @Name("emoji_status_custom_emoji_id") val emojiStatusCustomEmojiId: String?,
    @Name("bio") val bio: String?,
    @Name("has_private_forwards") val hasPrivateForwards: Boolean?,
    @Name("has_restricted_voice_and_video_messages") val hasRestrictedVoiceAndVideoMessages: Boolean?,
    @Name("join_to_send_messages") val joinToSendMessages: Boolean?,
    @Name("join_by_request") val joinByRequest: Boolean?,
    @Name("description") val description: String?,
    @Name("invite_link") val inviteLink: String?,
    @Name("pinned_message") val pinnedMessage: Message?,
    @Name("permissions") val permissions: ChatPermissions?,
    @Name("slow_mode_delay") val slowModeDelay: Int?,
    @Name("message_auto_delete_time") val messageAutoDeleteTime: Int?,
    @Name("has_aggressive_anti_spam_enabled") val hasAggressiveAntiSpamEnabled: Boolean?,
    @Name("has_hidden_members") val hasHiddenMembers: Boolean?,
    @Name("has_protected_content") val hasProtectedContent: Boolean?,
    @Name("sticker_set_name") val stickerSetName: String?,
    @Name("can_set_sticker_set") val canSetStickerSet: Boolean?,
    @Name("linked_chat_id") val linkedChatId: Int?,
    @Name("location") val location: ChatLocation?,
)

data class Message(
    @Name("message_id") val messageId: Long,
    @Name("message_thread_id") val messageThreadId: Long?,
    @Name("from") val from: User,
    @Name("sender_chat") val senderChat: Chat?,
    @Name("date") val date: Long,
    @Name("chat") val chat: Chat,
    @Name("forward_from") val forwardFrom: User?,
    @Name("forward_from_chat") val forwardFromChat: Chat?,
    @Name("forward_from_message_id") val forwardFromMessageId: Int?,
    @Name("forward_signature") val forwardSignature: String?,
    @Name("forward_sender_name") val forwardSenderName: String?,
    @Name("forward_date") val forwardDate: Int?,
    @Name("is_topic_message") val isTopicMessage: Boolean?,
    @Name("is_automatic_forward") val isAutomaticForward: Boolean?,
    @Name("reply_to_message") val replyToMessage: Message?,
    @Name("via_bot") val viaBot: User?,
    @Name("edit_date") val editDate: Int?,
    @Name("has_protected_content") val hasProtectedContent: Boolean?,
    @Name("media_group_id") val mediaGroupId: String?,
    @Name("author_signature") val authorSignature: String?,
    @Name("text") val text: String?,
    @Name("entities") val entities: List<MessageEntity>?,
    @Name("animation") val animation: Animation?,
    @Name("audio") val audio: Audio?,
    @Name("document") val document: Document?,
    @Name("photo") val photo: List<PhotoSize>?,
    @Name("sticker") val sticker: Sticker?,
    @Name("video") val video: Video?,
    @Name("video_note") val videoNote: VideoNote?,
    @Name("voice") val voice: Voice?,
    @Name("caption") val caption: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("has_media_spoiler") val hasMediaSpoiler: Boolean?,
    @Name("contact") val contact: Contact?,
    @Name("dice") val dice: Dice?,
    @Name("game") val game: Game?,
    @Name("poll") val poll: Poll?,
    @Name("venue") val venue: Venue?,
    @Name("location") val location: Location?,
    @Name("new_chat_members") val newChatMembers: List<User>?,
    @Name("left_chat_member") val leftChatMember: User?,
    @Name("new_chat_title") val newChatTitle: String?,
    @Name("new_chat_photo") val newChatPhoto: List<PhotoSize>?,
    @Name("delete_chat_photo") val deleteChatPhoto: Boolean?,
    @Name("group_chat_created") val groupChatCreated: Boolean?,
    @Name("supergroup_chat_created") val supergroupChatCreated: Boolean?,
    @Name("channel_chat_created") val channelChatCreated: Boolean?,
    @Name("message_auto_delete_timer_changed") val messageAutoDeleteTimerChanged: MessageAutoDeleteTimerChanged?,
    @Name("migrate_to_chat_id") val migrateToChatId: Int?,
    @Name("migrate_from_chat_id") val migrateFromChatId: Int?,
    @Name("pinned_message") val pinnedMessage: Message?,
    @Name("invoice") val invoice: Invoice?,
    @Name("successful_payment") val successfulPayment: SuccessfulPayment?,
    @Name("user_shared") val userShared: UserShared?,
    @Name("chat_shared") val chatShared: ChatShared?,
    @Name("connected_website") val connectedWebsite: String?,
    @Name("write_access_allowed") val writeAccessAllowed: WriteAccessAllowed?,
    @Name("passport_data") val passportData: PassportData?,
    @Name("proximity_alert_triggered") val proximityAlertTriggered: ProximityAlertTriggered?,
    @Name("forum_topic_created") val forumTopicCreated: ForumTopicCreated?,
    @Name("forum_topic_edited") val forumTopicEdited: ForumTopicEdited?,
    @Name("forum_topic_closed") val forumTopicClosed: ForumTopicClosed?,
    @Name("forum_topic_reopened") val forumTopicReopened: ForumTopicReopened?,
    @Name("general_forum_topic_hidden") val generalForumTopicHidden: GeneralForumTopicHidden?,
    @Name("general_forum_topic_unhidden") val generalForumTopicUnhidden: GeneralForumTopicUnhidden?,
    @Name("video_chat_scheduled") val videoChatScheduled: VideoChatScheduled?,
    @Name("video_chat_started") val videoChatStarted: VideoChatStarted?,
    @Name("video_chat_ended") val videoChatEnded: VideoChatEnded?,
    @Name("video_chat_participants_invited") val videoChatParticipantsInvited: VideoChatParticipantsInvited?,
    @Name("web_app_data") val webAppData: WebAppData?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
)

data class MessageId(
    @Name("message_id") val messageId: Long,
)

data class MessageEntity(
    @Name("type") val type: String,
    @Name("offset") val offset: Int,
    @Name("length") val length: Int,
    @Name("url") val url: String?,
    @Name("user") val user: User?,
    @Name("language") val language: String?,
    @Name("custom_emoji_id") val customEmojiId: String?,
)

data class PhotoSize(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("width") val width: Int,
    @Name("height") val height: Int,
    @Name("file_size") val fileSize: Int?,
)

data class Animation(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("width") val width: Int,
    @Name("height") val height: Int,
    @Name("duration") val duration: Int,
    @Name("thumb") val thumb: PhotoSize?,
    @Name("file_name") val fileName: String?,
    @Name("mime_type") val mimeType: String?,
    @Name("file_size") val fileSize: Int?,
)

data class Audio(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("duration") val duration: Int,
    @Name("performer") val performer: String?,
    @Name("title") val title: String?,
    @Name("file_name") val fileName: String?,
    @Name("mime_type") val mimeType: String?,
    @Name("file_size") val fileSize: Int?,
    @Name("thumb") val thumb: PhotoSize?,
)

data class Document(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("thumb") val thumb: PhotoSize?,
    @Name("file_name") val fileName: String?,
    @Name("mime_type") val mimeType: String?,
    @Name("file_size") val fileSize: Int?,
)

data class Video(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("width") val width: Int,
    @Name("height") val height: Int,
    @Name("duration") val duration: Int,
    @Name("thumb") val thumb: PhotoSize?,
    @Name("file_name") val fileName: String?,
    @Name("mime_type") val mimeType: String?,
    @Name("file_size") val fileSize: Int?,
)

data class VideoNote(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("length") val length: Int,
    @Name("duration") val duration: Int,
    @Name("thumb") val thumb: PhotoSize?,
    @Name("file_size") val fileSize: Int?,
)

data class Voice(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("duration") val duration: Int,
    @Name("mime_type") val mimeType: String?,
    @Name("file_size") val fileSize: Int?,
)

data class Contact(
    @Name("phone_number") val phoneNumber: String,
    @Name("first_name") val firstName: String,
    @Name("last_name") val lastName: String?,
    @Name("user_id") val userId: Int?,
    @Name("vcard") val vcard: String?,
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
    @Name("correct_option_id") val correctOptionId: Int?,
    @Name("explanation") val explanation: String?,
    @Name("explanation_entities") val explanationEntities: List<MessageEntity>?,
    @Name("open_period") val openPeriod: Int?,
    @Name("close_date") val closeDate: Int?,
)

data class Location(
    @Name("longitude") val longitude: Float,
    @Name("latitude") val latitude: Float,
    @Name("horizontal_accuracy") val horizontalAccuracy: Float?,
    @Name("live_period") val livePeriod: Int?,
    @Name("heading") val heading: Int?,
    @Name("proximity_alert_radius") val proximityAlertRadius: Int?,
)

data class Venue(
    @Name("location") val location: Location,
    @Name("title") val title: String,
    @Name("address") val address: String,
    @Name("foursquare_id") val foursquareId: String?,
    @Name("foursquare_type") val foursquareType: String?,
    @Name("google_place_id") val googlePlaceId: String?,
    @Name("google_place_type") val googlePlaceType: String?,
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
    @Name("icon_custom_emoji_id") val iconCustomEmojiId: String?,
)

class ForumTopicClosed

data class ForumTopicEdited(
    @Name("name") val name: String?,
    @Name("icon_custom_emoji_id") val iconCustomEmojiId: String?,
)

class ForumTopicReopened

class GeneralForumTopicHidden

class GeneralForumTopicUnhidden

data class UserShared(
    @Name("request_id") val requestId: Int,
    @Name("user_id") val userId: Int,
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
    @Name("file_size") val fileSize: Int?,
    @Name("file_path") val filePath: String?,
)

data class WebAppInfo(
    @Name("url") val url: String,
)

interface ReplyMarkup

data class ReplyKeyboardMarkup(
    @Name("keyboard") val keyboard: List<List<KeyboardButton>>,
    @Name("is_persistent") val isPersistent: Boolean?,
    @Name("resize_keyboard") val resizeKeyboard: Boolean?,
    @Name("one_time_keyboard") val oneTimeKeyboard: Boolean?,
    @Name("input_field_placeholder") val inputFieldPlaceholder: String?,
    @Name("selective") val selective: Boolean?,
) : ReplyMarkup

data class KeyboardButton(
    @Name("text") val text: String,
    @Name("request_user") val requestUser: KeyboardButtonRequestUser?,
    @Name("request_chat") val requestChat: KeyboardButtonRequestChat?,
    @Name("request_contact") val requestContact: Boolean?,
    @Name("request_location") val requestLocation: Boolean?,
    @Name("request_poll") val requestPoll: KeyboardButtonPollType?,
    @Name("web_app") val webApp: WebAppInfo?,
)

data class KeyboardButtonRequestUser(
    @Name("request_id") val requestId: Int,
    @Name("user_is_bot") val userIsBot: Boolean?,
    @Name("user_is_premium") val userIsPremium: Boolean?,
)

data class KeyboardButtonRequestChat(
    @Name("request_id") val requestId: Int,
    @Name("chat_is_channel") val chatIsChannel: Boolean,
    @Name("chat_is_forum") val chatIsForum: Boolean?,
    @Name("chat_has_username") val chatHasUsername: Boolean?,
    @Name("chat_is_created") val chatIsCreated: Boolean?,
    @Name("user_administrator_rights") val userAdministratorRights: ChatAdministratorRights?,
    @Name("bot_administrator_rights") val botAdministratorRights: ChatAdministratorRights?,
    @Name("bot_is_member") val botIsMember: Boolean?,
)

data class KeyboardButtonPollType(
    @Name("type") val type: String?,
)

data class ReplyKeyboardRemove(
    @Name("remove_keyboard") val removeKeyboard: Boolean,
    @Name("selective") val selective: Boolean?,
) : ReplyMarkup

data class InlineKeyboardMarkup(
    @Name("inline_keyboard") val inlineKeyboard: List<List<InlineKeyboardButton>>,
) : ReplyMarkup

data class InlineKeyboardButton(
    @Name("text") val text: String,
    @Name("url") val url: String?,
    @Name("callback_data") val callbackData: String?,
    @Name("web_app") val webApp: WebAppInfo?,
    @Name("login_url") val loginUrl: LoginUrl?,
    @Name("switch_inline_query") val switchInlineQuery: String?,
    @Name("switch_inline_query_current_chat") val switchInlineQueryCurrentChat: String?,
    @Name("callback_game") val callbackGame: CallbackGame?,
    @Name("pay") val pay: Boolean?,
)

data class LoginUrl(
    @Name("url") val url: String,
    @Name("forward_text") val forwardText: String?,
    @Name("bot_username") val botUsername: String?,
    @Name("request_write_access") val requestWriteAccess: Boolean?,
)

data class CallbackQuery(
    @Name("id") val id: String,
    @Name("from") val from: User,
    @Name("message") val message: Message?,
    @Name("inline_message_id") val inlineMessageId: String?,
    @Name("chat_instance") val chatInstance: String,
    @Name("data") val data: String?,
    @Name("game_short_name") val gameShortName: String?,
)

data class ForceReply(
    @Name("force_reply") val forceReply: Boolean,
    @Name("input_field_placeholder") val inputFieldPlaceholder: String?,
    @Name("selective") val selective: Boolean?,
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
    @Name("name") val name: String?,
    @Name("expire_date") val expireDate: Int?,
    @Name("member_limit") val memberLimit: Int?,
    @Name("pending_join_request_count") val pendingJoinRequestCount: Int?,
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
    @Name("can_post_messages") val canPostMessages: Boolean?,
    @Name("can_edit_messages") val canEditMessages: Boolean?,
    @Name("can_pin_messages") val canPinMessages: Boolean?,
    @Name("can_manage_topics") val canManageTopics: Boolean?,
)

sealed interface ChatMember

data class ChatMemberOwner(
    @Name("status") val status: String,
    @Name("user") val user: User,
    @Name("is_anonymous") val isAnonymous: Boolean,
    @Name("custom_title") val customTitle: String?,
) : ChatMember

data class ChatMemberAdministrator(
    @Name("status") val status: String,
    @Name("user") val user: User,
    @Name("can_be_edited") val canBeEdited: Boolean,
    @Name("is_anonymous") val isAnonymous: Boolean,
    @Name("can_manage_chat") val canManageChat: Boolean,
    @Name("can_delete_messages") val canDeleteMessages: Boolean,
    @Name("can_manage_video_chats") val canManageVideoChats: Boolean,
    @Name("can_restrict_members") val canRestrictMembers: Boolean,
    @Name("can_promote_members") val canPromoteMembers: Boolean,
    @Name("can_change_info") val canChangeInfo: Boolean,
    @Name("can_invite_users") val canInviteUsers: Boolean,
    @Name("can_post_messages") val canPostMessages: Boolean?,
    @Name("can_edit_messages") val canEditMessages: Boolean?,
    @Name("can_pin_messages") val canPinMessages: Boolean?,
    @Name("can_manage_topics") val canManageTopics: Boolean?,
    @Name("custom_title") val customTitle: String?,
) : ChatMember

data class ChatMemberMember(
    @Name("status") val status: String,
    @Name("user") val user: User,
) : ChatMember

data class ChatMemberRestricted(
    @Name("status") val status: String,
    @Name("user") val user: User,
    @Name("is_member") val isMember: Boolean,
    @Name("can_send_messages") val canSendMessages: Boolean,
    @Name("can_send_audios") val canSendAudios: Boolean,
    @Name("can_send_documents") val canSendDocuments: Boolean,
    @Name("can_send_photos") val canSendPhotos: Boolean,
    @Name("can_send_videos") val canSendVideos: Boolean,
    @Name("can_send_video_notes") val canSendVideoNotes: Boolean,
    @Name("can_send_voice_notes") val canSendVoiceNotes: Boolean,
    @Name("can_send_polls") val canSendPolls: Boolean,
    @Name("can_send_other_messages") val canSendOtherMessages: Boolean,
    @Name("can_add_web_page_previews") val canAddWebPagePreviews: Boolean,
    @Name("can_change_info") val canChangeInfo: Boolean,
    @Name("can_invite_users") val canInviteUsers: Boolean,
    @Name("can_pin_messages") val canPinMessages: Boolean,
    @Name("can_manage_topics") val canManageTopics: Boolean,
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
) : ChatMember

data class ChatMemberUpdated(
    @Name("chat") val chat: Chat,
    @Name("from") val from: User,
    @Name("date") val date: Int,
    @Name("old_chat_member") val oldChatMember: ChatMember,
    @Name("new_chat_member") val newChatMember: ChatMember,
    @Name("invite_link") val inviteLink: ChatInviteLink?,
)

data class ChatJoinRequest(
    @Name("chat") val chat: Chat,
    @Name("from") val from: User,
    @Name("user_chat_id") val userChatId: Int,
    @Name("date") val date: Int,
    @Name("bio") val bio: String?,
    @Name("invite_link") val inviteLink: ChatInviteLink?,
)

data class ChatPermissions(
    @Name("can_send_messages") val canSendMessages: Boolean?,
    @Name("can_send_audios") val canSendAudios: Boolean?,
    @Name("can_send_documents") val canSendDocuments: Boolean?,
    @Name("can_send_photos") val canSendPhotos: Boolean?,
    @Name("can_send_videos") val canSendVideos: Boolean?,
    @Name("can_send_video_notes") val canSendVideoNotes: Boolean?,
    @Name("can_send_voice_notes") val canSendVoiceNotes: Boolean?,
    @Name("can_send_polls") val canSendPolls: Boolean?,
    @Name("can_send_other_messages") val canSendOtherMessages: Boolean?,
    @Name("can_add_web_page_previews") val canAddWebPagePreviews: Boolean?,
    @Name("can_change_info") val canChangeInfo: Boolean?,
    @Name("can_invite_users") val canInviteUsers: Boolean?,
    @Name("can_pin_messages") val canPinMessages: Boolean?,
    @Name("can_manage_topics") val canManageTopics: Boolean?,
)

data class ChatLocation(
    @Name("location") val location: Location,
    @Name("address") val address: String,
)

data class ForumTopic(
    @Name("message_thread_id") val messageThreadId: Int,
    @Name("name") val name: String,
    @Name("icon_color") val iconColor: Int,
    @Name("icon_custom_emoji_id") val iconCustomEmojiId: String?,
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
    @Name("user_id") val userId: Int,
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

data class ResponseParameters(
    @Name("migrate_to_chat_id") val migrateToChatId: Int?,
    @Name("retry_after") val retryAfter: Int?,
)

sealed interface InputMedia

class InputFile

data class InputMediaPhoto(
    @Name("type") val type: String,
    @Name("media") val media: String,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("has_spoiler") val hasSpoiler: Boolean?,
) : InputMedia

data class InputMediaVideo(
    @Name("type") val type: String,
    @Name("media") val media: String,
    @Name("thumb") val thumb: InputFile,/* or String?*/
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("width") val width: Int?,
    @Name("height") val height: Int?,
    @Name("duration") val duration: Int?,
    @Name("supports_streaming") val supportsStreaming: Boolean?,
    @Name("has_spoiler") val hasSpoiler: Boolean?,
) : InputMedia

data class InputMediaAnimation(
    @Name("type") val type: String,
    @Name("media") val media: String,
    @Name("thumb") val thumb: InputFile,/* or String?*/
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("width") val width: Int?,
    @Name("height") val height: Int?,
    @Name("duration") val duration: Int?,
    @Name("has_spoiler") val hasSpoiler: Boolean?,
) : InputMedia

data class InputMediaAudio(
    @Name("type") val type: String,
    @Name("media") val media: String,
    @Name("thumb") val thumb: InputFile,/* or String?*/
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("duration") val duration: Int?,
    @Name("performer") val performer: String?,
    @Name("title") val title: String?,
) : InputMedia

data class InputMediaDocument(
    @Name("type") val type: String,
    @Name("media") val media: String,
    @Name("thumb") val thumb: InputFile,/* or String?*/
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("disable_content_type_detection") val disableContentTypeDetection: Boolean?
)

data class Sticker(
    @Name("file_id") val fileId: String,
    @Name("file_unique_id") val fileUniqueId: String,
    @Name("type") val type: String,
    @Name("width") val width: Int,
    @Name("height") val height: Int,
    @Name("is_animated") val isAnimated: Boolean,
    @Name("is_video") val isVideo: Boolean,
    @Name("thumb") val thumb: PhotoSize?,
    @Name("emoji") val emoji: String?,
    @Name("set_name") val setName: String?,
    @Name("premium_animation") val premiumAnimation: File?,
    @Name("mask_position") val maskPosition: MaskPosition?,
    @Name("custom_emoji_id") val customEmojiId: String?,
    @Name("file_size") val fileSize: Int?,
)

data class StickerSet(
    @Name("name") val name: String,
    @Name("title") val title: String,
    @Name("sticker_type") val stickerType: String,
    @Name("is_animated") val isAnimated: Boolean,
    @Name("is_video") val isVideo: Boolean,
    @Name("stickers") val stickers: List<Sticker>,
    @Name("thumb") val thumb: PhotoSize?,
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
    @Name("name") val name: String?,
    @Name("phone_number") val phoneNumber: String?,
    @Name("email") val email: String?,
    @Name("shipping_address") val shippingAddress: ShippingAddress?,
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
    @Name("shipping_option_id") val shippingOptionId: String?,
    @Name("order_info") val orderInfo: OrderInfo?,
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
    @Name("shipping_option_id") val shippingOptionId: String?,
    @Name("order_info") val orderInfo: OrderInfo?,
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
    @Name("data") val data: String?,
    @Name("phone_number") val phoneNumber: String?,
    @Name("email") val email: String?,
    @Name("files") val files: List<PassportFile>?,
    @Name("front_side") val frontSide: PassportFile?,
    @Name("reverse_side") val reverseSide: PassportFile?,
    @Name("selfie") val selfie: PassportFile?,
    @Name("translation") val translation: List<PassportFile>?,
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
    @Name("text") val text: String?,
    @Name("text_entities") val textEntities: List<MessageEntity>?,
    @Name("animation") val animation: Animation?,
) : PassportElementError

data class Game(
    @Name("title") val title: String,
    @Name("description") val description: String,
    @Name("photo") val photo: List<PhotoSize>,
    @Name("text") val text: String?,
    @Name("text_entities") val text_entities: List<MessageEntity>?,
    @Name("animation") val animation: Animation?,
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
    @Name("chat_type") val chatType: String?,
    @Name("location") val location: Location?,
)

sealed interface InlineQueryResult

data class InlineQueryResultButton(
    @Name("text") val text: String,
    @Name("web_app") val webApp: WebAppInfo?,
    @Name("start_parameter") val startParameter: String?
)


data class InlineQueryResultArticle(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("title") val title: String,
    @Name("input_message_content") val inputMessageContent: InputMessageContent,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("url") val url: String?,
    @Name("hide_url") val hideUrl: Boolean?,
    @Name("description") val description: String?,
    @Name("thumb_url") val thumbUrl: String?,
    @Name("thumb_width") val thumbWidth: Int?,
    @Name("thumb_height") val thumbHeight: Int?,
) : InlineQueryResult

data class InlineQueryResultPhoto(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("photo_url") val photoUrl: String,
    @Name("thumb_url") val thumbUrl: String,
    @Name("photo_width") val photoWidth: Int?,
    @Name("photo_height") val photoHeight: Int?,
    @Name("title") val title: String?,
    @Name("description") val description: String?,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultGif(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("gif_url") val gifUrl: String,
    @Name("gif_width") val gifWidth: Int?,
    @Name("gif_height") val gifHeight: Int?,
    @Name("gif_duration") val gifDuration: Int?,
    @Name("thumb_url") val thumbUrl: String,
    @Name("thumb_mime_type") val thumbMimeType: String?,
    @Name("title") val title: String?,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultMpeg4Gif(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("mpeg4_url") val mpeg4Url: String,
    @Name("mpeg4_width") val mpeg4Width: Int?,
    @Name("mpeg4_height") val mpeg4Height: Int?,
    @Name("mpeg4_duration") val mpeg4Duration: Int?,
    @Name("thumb_url") val thumbUrl: String,
    @Name("thumb_mime_type") val thumbMimeType: String?,
    @Name("title") val title: String?,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultVideo(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("video_url") val videoUrl: String,
    @Name("mime_type") val mimeType: String,
    @Name("thumb_url") val thumbUrl: String,
    @Name("title") val title: String,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("video_width") val videoWidth: Int?,
    @Name("video_height") val videoHeight: Int?,
    @Name("video_duration") val videoDuration: Int?,
    @Name("description") val description: String?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultAudio(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("audio_url") val audioUrl: String,
    @Name("title") val title: String,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("performer") val performer: String?,
    @Name("audio_duration") val audioDuration: Int?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultVoice(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("voice_url") val voiceUrl: String,
    @Name("title") val title: String,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("voice_duration") val voiceDuration: Int?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultDocument(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("title") val title: String,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("document_url") val documentUrl: String,
    @Name("mime_type") val mimeType: String,
    @Name("description") val description: String?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
    @Name("thumb_url") val thumbUrl: String?,
    @Name("thumb_width") val thumbWidth: Int?,
    @Name("thumb_height") val thumbHeight: Int?,
) : InlineQueryResult

data class InlineQueryResultLocation(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("latitude") val latitude: Float,
    @Name("longitude") val longitude: Float,
    @Name("title") val title: String,
    @Name("horizontal_accuracy") val horizontalAccuracy: Float?,
    @Name("live_period") val livePeriod: Int?,
    @Name("heading") val heading: Int?,
    @Name("proximity_alert_radius") val proximityAlertRadius: Int?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
    @Name("thumb_url") val thumbUrl: String?,
    @Name("thumb_width") val thumbWidth: Int?,
    @Name("thumb_height") val thumbHeight: Int?,
) : InlineQueryResult

data class InlineQueryResultVenue(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("latitude") val latitude: Float,
    @Name("longitude") val longitude: Float,
    @Name("title") val title: String,
    @Name("address") val address: String,
    @Name("foursquare_id") val foursquareId: String?,
    @Name("foursquare_type") val foursquareType: String?,
    @Name("google_place_id") val googlePlaceId: String?,
    @Name("google_place_type") val googlePlaceType: String?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
    @Name("thumb_url") val thumbUrl: String?,
    @Name("thumb_width") val thumbWidth: Int?,
    @Name("thumb_height") val thumbHeight: Int?,
) : InlineQueryResult

data class InlineQueryResultContact(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("phone_number") val phoneNumber: String,
    @Name("first_name") val firstName: String,
    @Name("last_name") val lastName: String?,
    @Name("vcard") val vcard: String?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
    @Name("thumb_url") val thumbUrl: String?,
    @Name("thumb_width") val thumbWidth: Int?,
    @Name("thumb_height") val thumbHeight: Int?,
) : InlineQueryResult

data class InlineQueryResultGame(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("game_short_name") val gameShortName: String,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
) : InlineQueryResult

data class InlineQueryResultCachedPhoto(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("photo_file_id") val photoFileId: String,
    @Name("title") val title: String?,
    @Name("description") val description: String?,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultCachedGif(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("gif_file_id") val gifFileId: String,
    @Name("title") val title: String?,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultCachedMpeg4Gif(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("mpeg4_file_id") val mpeg4FileId: String,
    @Name("title") val title: String?,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultCachedSticker(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("sticker_file_id") val stickerFileId: String,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultCachedDocument(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("title") val title: String,
    @Name("document_file_id") val documentFileId: String,
    @Name("description") val description: String?,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultCachedVideo(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("video_file_id") val videoFileId: String,
    @Name("title") val title: String,
    @Name("description") val description: String?,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultCachedVoice(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("voice_file_id") val voiceFileId: String,
    @Name("title") val title: String,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

data class InlineQueryResultCachedAudio(
    @Name("type") val type: String,
    @Name("id") val id: String,
    @Name("audio_file_id") val audioFileId: String,
    @Name("caption") val caption: String?,
    @Name("parse_mode") val parseMode: String?,
    @Name("caption_entities") val captionEntities: List<MessageEntity>?,
    @Name("reply_markup") val replyMarkup: InlineKeyboardMarkup?,
    @Name("input_message_content") val inputMessageContent: InputMessageContent?,
) : InlineQueryResult

sealed interface InputMessageContent

data class InputTextMessageContent(
    @Name("message_text") val messageText: String,
    @Name("parse_mode") val parseMode: String?,
    @Name("entities") val entities: List<MessageEntity>?,
    @Name("disable_web_page_preview") val disableWebPagePreview: Boolean?,
) : InputMessageContent

data class InputLocationMessageContent(
    @Name("latitude") val latitude: Float,
    @Name("longitude") val longitude: Float,
    @Name("horizontal_accuracy") val horizontalAccuracy: Float?,
    @Name("live_period") val livePeriod: Int?,
    @Name("heading") val heading: Int?,
    @Name("proximity_alert_radius") val proximityAlertRadius: Int?,
) : InputMessageContent

data class InputVenueMessageContent(
    @Name("latitude") val latitude: Float,
    @Name("longitude") val longitude: Float,
    @Name("title") val title: String,
    @Name("address") val address: String,
    @Name("foursquare_id") val foursquareId: String?,
    @Name("foursquare_type") val foursquareType: String?,
    @Name("google_place_id") val googlePlaceId: String?,
    @Name("google_place_type") val googlePlaceType: String?,
) : InputMessageContent

data class InputContactMessageContent(
    @Name("phone_number") val phoneNumber: String,
    @Name("first_name") val firstName: String,
    @Name("last_name") val lastName: String?,
    @Name("vcard") val vcard: String?,
) : InputMessageContent

data class InputInvoiceMessageContent(
    @Name("title") val title: String,
    @Name("description") val description: String,
    @Name("payload") val payload: String,
    @Name("provider_token") val providerToken: String,
    @Name("currency") val currency: String,
    @Name("prices") val prices: List<LabeledPrice>,
    @Name("max_tip_amount") val maxTipAmount: Int?,
    @Name("suggested_tip_amounts") val suggestedTipAmounts: List<Int>?,
    @Name("provider_data") val providerData: String?,
    @Name("photo_url") val photoUrl: String?,
    @Name("photo_size") val photoSize: Int?,
    @Name("photo_width") val photoWidth: Int?,
    @Name("photo_height") val photoHeight: Int?,
    @Name("need_name") val needName: Boolean?,
    @Name("need_phone_number") val needPhoneNumber: Boolean?,
    @Name("need_email") val needEmail: Boolean?,
    @Name("need_shipping_address") val needShippingAddress: Boolean?,
    @Name("send_phone_number_to_provider") val sendPhoneNumberToProvider: Boolean?,
    @Name("send_email_to_provider") val sendEmailToProvider: Boolean?,
    @Name("is_flexible") val isFlexible: Boolean?,
) : InputMessageContent

data class ChosenInlineResult(
    @Name("result_id") val resultId: String,
    @Name("from") val from: User,
    @Name("location") val location: Location?,
    @Name("inline_message_id") val inlineMessageId: String?,
    @Name("query") val query: String,
)

data class SentWebAppMessage(
    @Name("inline_message_id") val inlineMessageId: String?
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
    @Name("poll_answer") val pollAnswer: PollAnswer? = null
)


