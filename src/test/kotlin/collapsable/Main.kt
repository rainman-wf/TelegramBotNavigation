package collapsable

import v1nav.Home
import v1nav.bot
import botapi.common.ButtonType
import botapi.models.InlineQueryResult
import botapi.models.InlineQueryResultArticle
import botapi.models.InputTextMessageContent
import botapi.sender.deleteMessage
import botapi.sender.sendMessage
import navigation.Navigation
import navigation.args.NavArg
import navigation.frame.*
import navigation.init
import navigation.listen
import navigation.models.NavResponse
import kotlin.random.Random

data class App(
    val id: Long,
    val title: String,
    val body: List<String>
)

val apps = (1..10).map {
    App(it.toLong(), "Заявка $it",
        run {
            val bodies = Random.nextInt(1, 3)
            if (bodies == 1) listOf("Тело заявки #$it, сообщение №1")
            else (1..bodies).map { num ->
                "Тело заявки #$it, сообщение #$num"
            }
        }
    )
}.toMutableList()

class AppsAdapter : ListAdapter<App> {
    override fun map(data: List<App>): List<InlineQueryResult> {
        return data.map {
            InlineQueryResultArticle(
                type = "article",
                id = it.id.toString(),
                title = it.title,
                inputMessageContent = InputTextMessageContent("Loading..."),
                description = "Ho ho ho!"
            )
        }
    }
}


suspend fun main() {

    Navigation.init(bot) {
        home(::Home)
        root("/start", ::Apps)
    }

    bot.updates {
        Navigation.listen(this)
    }
}

class Apps : Frame() {
    override suspend fun show() {
        text {
            content {
                "Просмотр заявок, чтобы показать список нажми кнопку"
            }
            keyboard {
                row {
                    button("Список", "list", ButtonType.INLINE_QUERY_CURRENT_CHAT)
                }
                row {
                    button("Home", "/home")
                }
            }
        }
    }

    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse)
        navResponse.listQuery?.let {
            println(it)
            navigate(::AppList, AppList.QueryId(it))
        }
    }
}

class AppList : ListFrame() {

    class QueryId(
        val value: String
    ) : NavArg

    override suspend fun show() {

        val args = navArgs<QueryId>() ?: throw IllegalArgumentException("No args passed")

        list(apps, args.value) {
            adapter = AppsAdapter()
        }
    }

    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse)
        navResponse.listItem?.let { listId ->
            replace(::AppDetailsFrame, AppDetailsFrame.AppId(listId.toLong()))
        }

        navResponse.listQuery?.let {
            println(it)
        }
        println(navResponse.data)
    }
}

class AppDetailsFrame : Frame() {

    class AppId(
        val value: Long,
    ) : NavArg

    private val ids = mutableListOf<Long>()

    private lateinit var arg: AppId

    override suspend fun show() {

        arg = navArgs<AppId>() ?: throw IllegalArgumentException("arg is missed")

        val app = apps.find { it.id == arg.value } ?: throw NullPointerException("app not found")

        text {
            content {
                """
               ID - ${app.id}
               Title - ${app.title}
            """.trimIndent()
            }

        }

        app.body.forEach { msg ->
            bot.sendMessage(
                chatId = userId,
                text = msg
            ).result?.let { ids.add(it.messageId) }
        }

        bot.sendMessage(
            chatId = userId,
            text = "Action bar"
        ) {
            replyMarkup = inlineKeyboardMarkup {
                row {
                    button("[X] close") {
                        callbackData = "back"
                    }
                    button("Delete") {
                        callbackData = "delete"
                    }
                }
            }
        }.result?.let { ids.add(it.messageId) }
    }

    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse)
        when (navResponse.data) {
            "back" -> {
                ids.reversed().forEach {
                    bot.deleteMessage(userId, it)
                }
                ids.clear()
                back()
            }

            "delete" -> {
                ids.reversed().forEach {
                    bot.deleteMessage(userId, it)
                }
                ids.clear()

                apps.removeIf { it.id == arg.value }

                back()
            }
        }
    }
}

