package navigation.frame

import botapi.models.InlineKeyboardButton

interface GridAdapter<T> {
    fun map(data: List<T>): List<InlineKeyboardButton>
}