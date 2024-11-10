package simple

import botapi.Bot
import botapi.models.*
import botapi.sender.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import myId
import token

suspend fun main() {

    val bot = Bot(token)
    bot.setLoggingLevel(Bot.LoggingLevel.BODY)

    bot.setFullUpdates()

    bot.updates {
        Gson().toJson(message).also { println(it) }

    }


//    bot.sendMessage(myId, "Din-R Islamov Серёга Сурганов Ляйсан Сафиуллина Аида") {
//        entities = listOf(
//            MessageEntity(
//                "text_mention", 0, 13,
//                user = User(
//                    id = myId,
//                    isBot = false,
//                    firstName = "Din-R",
//                    lastName = "Islamov",
//                    username = "honored_elk",
//                    languageCode = "ru",
//                    isPremium = false,
//                    addedToAttachmentMenu = false,
//                    canJoinGroups = null,
//                    canReadAllGroupMessages = null,
//                    supportsInlineQueries = null
//                )
//
//            ),
//            MessageEntity(
//                "text_mention", 14, 15,
//                user = User(
//                    id = 1329119330,
//                    isBot = false,
//                    firstName = "Серёга",
//                    lastName = "Сурганов",
//                    username = null,
//                    languageCode = "ru",
//                    isPremium = false,
//                    addedToAttachmentMenu = false,
//                    canJoinGroups = null,
//                    canReadAllGroupMessages = null,
//                    supportsInlineQueries = null
//                )
//            ),
//            MessageEntity(
//                "text_mention", 30, 17,
//                user = User(
//                    id = 474302801,
//                    isBot = false,
//                    firstName = "Ляйсан",
//                    lastName = "Сафиуллина",
//                    username = "Lakshmi_Lis",
//                    languageCode = "",
//                    isPremium = true,
//                    addedToAttachmentMenu = false,
//                    canJoinGroups = null,
//                    canReadAllGroupMessages = null,
//                    supportsInlineQueries = null
//                )
//            ),
//            MessageEntity(
//                "text_mention", 48, 4,
//                user = User(
//                    id = 1779008295,
//                    isBot = false,
//                    firstName = "Аида",
//                    lastName = null,
//                    username = "sagdvaida",
//                    languageCode = "",
//                    isPremium = true,
//                    addedToAttachmentMenu = false,
//                    canJoinGroups = null,
//                    canReadAllGroupMessages = null,
//                    supportsInlineQueries = null
//                )
//            )
//        )
//    }
}




