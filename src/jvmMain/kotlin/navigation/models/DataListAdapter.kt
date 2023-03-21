package navigation.models

import com.pengrad.telegrambot.model.request.InlineQueryResultArticle

interface DataListAdapter<T> {
    fun map(data: List<T>): List<InlineQueryResultArticle>
}