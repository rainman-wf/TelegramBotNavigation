package botapi.sender.builder

import botapi.models.*

class ReplyKeyboardBuilder {

    private val rows: MutableList<List<KeyboardButton>> = mutableListOf()

    fun build() = ReplyKeyboardMarkup(rows)

    fun row(rowBuilder: RowBuilder.() -> Unit) {
        val builder = RowBuilder()
        rowBuilder(builder)
        rows.add(builder.build())
    }

    inner class RowBuilder {

        private val buttons: MutableList<KeyboardButton> = mutableListOf()

        fun build () = buttons

        inner class KeyboardButtonBuilder(
            val requestUser: KeyboardButtonRequestUser? = null,
            val requestChat: KeyboardButtonRequestChat? = null,
            val requestContact: Boolean? = null,
            val requestLocation: Boolean? = null,
            val requestPoll: KeyboardButtonPollType? = null,
            val webApp: WebAppInfo? = null,
        )

        fun button(text: String, builder: KeyboardButtonBuilder.() -> Unit) {

            val _builder = KeyboardButtonBuilder()

            builder(_builder)

            buttons.add(
                KeyboardButton(
                    text = text,
                    requestUser = _builder.requestUser,
                    requestChat = _builder.requestChat,
                    requestContact = _builder.requestContact,
                    requestLocation = _builder.requestLocation,
                    requestPoll = _builder.requestPoll,
                    webApp = _builder.webApp,
                )
            )
        }
    }
}