package navigation.frame

import botapi.common.ButtonType
import botapi.models.*

class NavKeyboardBuilder {

    private val rows: MutableList<List<InlineKeyboardButton>> = mutableListOf()

    internal fun build() = InlineKeyboardMarkup(rows)

    fun row(rowBuilder: RowBuilder.() -> Unit) {
        val builder = RowBuilder()
        rowBuilder(builder)
        rows.add(builder.build())
    }

    fun backButton(text: String = "Назад") = button(text, "back")
    fun homeButton(text: String = "Выйти") = button(text, "home")
    fun checkBox(text: String, data: String, checked: Boolean) = button(if (checked) "✅ $text" else text, data)

    fun button(
        text: String,
        data: String,
        type: ButtonType = ButtonType.CALLBACK
    ) : InlineKeyboardButton {

        val _builder = InlineButtonBuilder()

        when (type) {
            ButtonType.CALLBACK -> _builder.callbackData = data
            ButtonType.URL -> _builder.url = data
            ButtonType.INLINE_QUERY -> _builder.switchInlineQuery = data
            ButtonType.INLINE_QUERY_CURRENT_CHAT -> _builder.switchInlineQueryCurrentChat = data
            ButtonType.PAY -> _builder.pay = true
        }


        return InlineKeyboardButton(
            text = text,
            url = _builder.url,
            callbackData = _builder.callbackData,
            webApp = _builder.webApp,
            loginUrl = _builder.loginUrl,
            switchInlineQuery = _builder.switchInlineQuery,
            switchInlineQueryCurrentChat = _builder.switchInlineQueryCurrentChat,
            callbackGame = _builder.callbackGame,
            pay = _builder.pay,
        ).also { rows.add(listOf(it)) }
    }


    fun <T> grid(list: List<T>, columns: Int = 2, mapper: (T) -> InlineKeyboardButton) {
        rows.addAll(
            list
                .map { mapper(it) }
                .groupBy(columns)
                .map { it.toList() }
        )
    }

    private fun <T> Collection<T>.groupBy(quantity: Int): Collection<Collection<T>> {
        return withIndex()
            .groupBy { it.index / quantity }
            .map { it.value.map { index -> index.value } }
    }

    inner class InlineButtonBuilder(
        var url: String? = null,
        var callbackData: String? = null,
        var webApp: WebAppInfo? = null,
        var loginUrl: LoginUrl? = null,
        var switchInlineQuery: String? = null,
        var switchInlineQueryCurrentChat: String? = null,
        var callbackGame: CallbackGame? = null,
        var pay: Boolean? = null,
    )


    inner class RowBuilder {

        private val buttons: MutableList<InlineKeyboardButton> = mutableListOf()

        fun build() = buttons

        fun button(
            text: String,
            data: String,
            type: ButtonType = ButtonType.CALLBACK
        ) {

            val _builder = InlineButtonBuilder()

            when (type) {
                ButtonType.CALLBACK -> _builder.callbackData = data
                ButtonType.URL -> _builder.url = data
                ButtonType.INLINE_QUERY -> _builder.switchInlineQuery = data
                ButtonType.INLINE_QUERY_CURRENT_CHAT -> _builder.switchInlineQueryCurrentChat = data
                ButtonType.PAY -> _builder.pay = true
            }

            buttons.add(
                InlineKeyboardButton(
                    text = text,
                    url = _builder.url,
                    callbackData = _builder.callbackData,
                    webApp = _builder.webApp,
                    loginUrl = _builder.loginUrl,
                    switchInlineQuery = _builder.switchInlineQuery,
                    switchInlineQueryCurrentChat = _builder.switchInlineQueryCurrentChat,
                    callbackGame = _builder.callbackGame,
                    pay = _builder.pay,
                )
            )
        }
    }
}