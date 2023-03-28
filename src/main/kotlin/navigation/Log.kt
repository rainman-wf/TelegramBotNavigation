package navigation

fun Any.log(message: Any?) {
    val clsName = this::class.simpleName ?: ""
    println("LOG: $clsName :: $message")
}