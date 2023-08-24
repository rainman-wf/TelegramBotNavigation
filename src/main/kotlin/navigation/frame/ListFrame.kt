package navigation.frame

abstract class ListFrame : Frame() {

    suspend fun <T> list(list: List<T>, queryId: String, block: ListBuilder<T>.() -> Unit) {
        val builder = ListBuilder(list, queryId)
        block(builder)
        builder.execute()
    }
}