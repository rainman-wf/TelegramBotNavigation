package navigation.frame

import navigation.models.DataList
import navigation.models.UserId

abstract class ListFrame(private val userId: UserId) : Frame(userId) {

    override suspend fun back() {
        controller.backFromList(userId)
    }

    suspend fun <T> list(list: List<T>, queryId: String, block: (DataList<T>) -> Unit) {
        val builder = DataList(list, queryId)
        block(builder)
        builder.execute(list)
    }
}