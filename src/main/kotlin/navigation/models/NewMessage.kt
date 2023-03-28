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

    fun <T>grid(list: List<T>, columns: Int, adapter: GridAdapter<T>) {
        val buttons = adapter.map(list)
        val grouped = buttons.groupBy(columns).map { it.toList() }
        _buttons.addAll(grouped)
    }

    private fun <T> Collection<T>.groupBy(quantity: Int): Collection<Collection<T>> {
        return withIndex()
            .groupBy { it.index / quantity }
            .map { it.value.map { index -> index.value } }
    }
}

