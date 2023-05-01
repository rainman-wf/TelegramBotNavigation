package botapi.sender

import botapi.common.ButtonType
import botapi.models.CallbackGame
import botapi.models.InlineKeyboardButton
import botapi.models.LoginUrl
import botapi.models.WebAppInfo

data class InlineKeyboardBuilder(
    val rows: MutableList<List<InlineKeyboardButton>> = mutableListOf()
) {
    fun addRow(rowBuilder: RowBuilder.() -> Unit) {
        val builder = RowBuilder()
        rowBuilder(builder)
        rows.add(builder.buttons)
    }

    inner class RowBuilder(
        internal val buttons: MutableList<InlineKeyboardButton> = mutableListOf()
    ) {

        private inner class InlineButtonBuilder(
            var url: String? = null,
            var callbackData: String? = null,
            var webApp: WebAppInfo? = null,
            var loginUrl: LoginUrl? = null,
            var switchInlineQuery: String? = null,
            var switchInlineQueryCurrentChat: String? = null,
            var callbackGame: CallbackGame? = null,
            var pay: Boolean? = null,
        )

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
                ButtonType.INLINE_QUERY_CURRENT_CHAT -> _builder.switchInlineQuery = data
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