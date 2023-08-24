package botapi.sender.builder

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

    fun <T> grid(list: List<T>, columns: Int, adapter: GridAdapter<T>) {
        val buttons = adapter.map(list)
        val grouped = buttons.groupBy(columns).map { it.toList() }
        rows.addAll(grouped)
    }

    private fun <T> Collection<T>.groupBy(quantity: Int): Collection<Collection<T>> {
        return withIndex()
            .groupBy { it.index / quantity }
            .map { it.value.map { index -> index.value } }
    }

    inner class RowBuilder {

        private val buttons: MutableList<InlineKeyboardButton> = mutableListOf()

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