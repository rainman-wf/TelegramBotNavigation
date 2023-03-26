package navigation.frame

import navigation.args.ArgsContainer
import navigation.models.DataList
import navigation.models.UserId

abstract class ListFrame(private val userId: UserId, args: ArgsContainer? = null) : Frame(userId, args) {

    suspend fun <T> list(list: List<T>, queryId: String, block: DataList<T>.() -> Unit) {
        val builder = DataList(list, queryId)
        block(builder)
        builder.execute(list)
    }
}