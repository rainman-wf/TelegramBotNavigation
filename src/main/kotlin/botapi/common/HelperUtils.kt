package botapi.common

import botapi.sender.builder.gson

enum class ButtonType {
    CALLBACK, URL, INLINE_QUERY, INLINE_QUERY_CURRENT_CHAT, PAY
}

internal fun Any?.toJson(): String? {
    return this?.let { gson.toJson(it) }
}
