package navigation.models

data class NewMessage(override val toUserId: UserId, val messageId: Int? = null) : Request {
    lateinit var text: String
    private val _buttons = mutableListOf(listOf<Button>())
    var formatted: Boolean = false
    val buttons: List<List<Button>> = _buttons

    fun addRow(vararg button: Button) {
        _buttons.add(button.toList())
    }

    fun formatted() {
        formatted = true
    }
}

