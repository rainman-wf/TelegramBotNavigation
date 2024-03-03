package navigation.frame

import botapi.models.InlineKeyboardButton

//interface GridAdapter<T> {
//    fun map(data: List<T>): List<InlineKeyboardButton>
//}

open class GridAdapter<T>(
    private val mapper: (T) -> InlineKeyboardButton
)  {
    operator fun invoke(p1: List<T>): List<InlineKeyboardButton> {
        return p1.map { mapper(it) }
    }
}

