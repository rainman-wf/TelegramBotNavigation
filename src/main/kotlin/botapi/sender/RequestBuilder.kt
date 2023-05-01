package botapi.sender

import botapi.models.InlineKeyboardMarkup
import botapi.models.InlineQueryResultArticle

interface RequestBuilder {

    fun keyboard(builder: InlineKeyboardBuilder.() -> Unit): InlineKeyboardMarkup {
        val _builder = InlineKeyboardBuilder()
        builder(_builder)
        return InlineKeyboardMarkup(_builder.rows)
    }
}
