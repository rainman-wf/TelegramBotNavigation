package navigation.models

data class Button(
    val caption: String,
    val data: String,
    val type: ButtonType = ButtonType.CALLBACK
)