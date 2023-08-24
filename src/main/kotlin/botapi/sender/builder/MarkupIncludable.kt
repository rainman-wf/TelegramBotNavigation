package botapi.sender.builder

import botapi.models.InlineKeyboardMarkup
import botapi.models.ReplyKeyboardMarkup

interface MarkupIncludable {

    fun inlineKeyboardMarkup(builder: InlineKeyboardBuilder.() -> Unit) : InlineKeyboardMarkup {
        val _builder = InlineKeyboardBuilder()
        builder(_builder)
        return _builder.build()
    }

    fun replyKeyboardMarkup(builder: ReplyKeyboardBuilder.() -> Unit) : ReplyKeyboardMarkup {
        val _builder = ReplyKeyboardBuilder()
        builder(_builder)
        return _builder.build()
    }
}


