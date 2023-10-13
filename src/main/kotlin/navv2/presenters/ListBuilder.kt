package navv2.presenters

import botapi.models.InlineQueryResultArticle
import botapi.models.InputTextMessageContent
import botapi.sender.answerInlineQuery
import botapi.sender.builder.AnswerInlineQuery
import navv2.abstractions.ListAdapter
import navv2.entities.ContextManager

class ListBuilder<T>(
    private val list: List<T>,
    private val queryId: String,
) {

    lateinit var adapter: ListAdapter<T>

    suspend fun execute() {
        ContextManager.bot.answerInlineQuery(queryId) {
            results.addAll(
                adapter.bind(list).map {
                    InlineQueryResultArticle(
                        type = "article",
                        id = it.id.toString(),
                        title = it.title,
                        inputMessageContent = InputTextMessageContent("Loading..."),
                        thumbUrl = it.thumbnailUrl,
                        description = it.description
                    )
                }
            )
            cacheTime = 1
            isPersonal = true
        }
    }
}