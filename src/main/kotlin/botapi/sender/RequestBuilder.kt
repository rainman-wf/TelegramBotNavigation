package botapi.sender

import botapi.models.BaseResponse
import botapi.models.InlineKeyboardMarkup
import botapi.models.InlineQueryResultArticle
import navigation.frame.Frame

interface RequestBuilder {

    fun keyboard(builder: InlineKeyboardBuilder.() -> Unit): InlineKeyboardMarkup {
        val _builder = InlineKeyboardBuilder()
        builder(_builder)
        return InlineKeyboardMarkup(_builder.rows)
    }

    fun <T> grid(list: List<T>, columns: Int, adapter: Frame.GridAdapter<T>): InlineKeyboardMarkup {
        val buttons = adapter.map(list)
        val grouped = buttons.groupBy(columns).map { it.toList() }
        return InlineKeyboardMarkup(grouped)
    }

    private fun <T> Collection<T>.groupBy(quantity: Int): Collection<Collection<T>> {
        return withIndex()
            .groupBy { it.index / quantity }
            .map { it.value.map { index -> index.value } }
    }

}
