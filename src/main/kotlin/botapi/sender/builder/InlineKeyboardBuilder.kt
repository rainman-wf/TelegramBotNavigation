package botapi.sender.builder

import botapi.common.ButtonType
import botapi.models.*
import navigation.frame.GridAdapter

class InlineKeyboardBuilder {

    private val rows: MutableList<List<InlineKeyboardButton>> = mutableListOf()

    fun build() = InlineKeyboardMarkup(rows)

    fun row(rowBuilder: RowBuilder.() -> Unit) {
        val builder = RowBuilder()
        rowBuilder(builder)
        rows.add(builder.build())
    }

    fun checkBox(text: String, data: String, checked: Boolean) = button(if (checked) "✅ $text" else text, data)

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


        rows.add(
            listOf(
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
        )

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


    fun <T> grid(list: List<T>, columns: Int = 2, mapper: InlineButtonBuilder.(T) -> InlineKeyboardButton) {
        val _builder = InlineButtonBuilder()
        rows.addAll(
            list
                .map { mapper(_builder, it) }
                .groupBy(columns)
                .map { it.toList() }
        )
    }

    private fun <T> Collection<T>.groupBy(quantity: Int): Collection<Collection<T>> {
        return withIndex()
            .groupBy { it.index / quantity }
            .map { it.value.map { index -> index.value } }
    }

    inner class RowBuilder {

        private val buttons: MutableList<InlineKeyboardButton> = mutableListOf()

        fun build() = buttons

        fun button(text: String, builder: InlineButtonBuilder.() -> Unit = {}) {

            val _builder = InlineButtonBuilder()

            builder(_builder)

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