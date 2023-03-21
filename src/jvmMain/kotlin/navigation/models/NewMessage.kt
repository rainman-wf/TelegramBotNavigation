package navigation.models

data class NewMessage(override val userId: UserId, val messageId: Int? = null) : Request {
    lateinit var text: String
    private val _buttons = mutableListOf(listOf<Button>())
    val buttons: List<List<Button>> = _buttons

    fun addRow(vararg button: Button) {
        _buttons.add(button.toList())
    }

}

