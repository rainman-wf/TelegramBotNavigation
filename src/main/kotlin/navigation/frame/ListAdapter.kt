package navigation.frame

import botapi.models.InlineQueryResult

interface ListAdapter<T> {
    fun map(data: List<T>): List<InlineQueryResult>
}