package navigation

import botapi.models.ReplyMarkup
import navigation.frame.NavKeyboardBuilder

abstract class NavComponent {

    protected var content: String? = null
    protected var formatted: Boolean = false
    protected var keyboard: ReplyMarkup? = null
    protected var _protected: Boolean? = null

    fun keyboard(builder: NavKeyboardBuilder.() -> Unit) {
        val _builder = NavKeyboardBuilder()
        builder(_builder)
        keyboard = _builder.build()
    }

    fun content(text: () -> String) {
        content = text()
    }

    fun formatted() {
        formatted = true
    }

    fun protected() {
        _protected = true
    }

}





