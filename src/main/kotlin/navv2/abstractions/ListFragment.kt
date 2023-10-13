package navv2.abstractions

import navigation.frame.ListBuilder
import navv2.entities.UserAction

abstract class ListFragment : Fragment() {

    class QueryId(
        val value: String
    ) : NavArg

    private var offset = 0
    private var filter = ""

    private val _queryId: QueryId by lazy { navArgs() }
    private var queryId = _queryId.value


    suspend fun <T> list(list: List<T>, block: ListBuilder<T>.() -> Unit) {
        val builder = ListBuilder(list, queryId)
        block(builder)
        builder.execute()
    }

    override suspend fun handle(action: UserAction) {
        super.handle(action)
        action.listQuery?.apply {
            val q = id
            val o = offset
            val d = action.data!!
            val f = d.drop(d.length+1)
            this@ListFragment.offset = if (o == 0) 50 else offset + o
            if (f != filter) {
                filter = f
                this@ListFragment.offset = 0
            }

            queryId = q
            update()
        }
    }
}