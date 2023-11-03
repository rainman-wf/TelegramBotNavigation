package navv2.abstractions

import navv2.entities.UserAction
import navv2.presenters.ListBuilder

abstract class ListFragment(private var queryId: String) : Fragment() {

    private var offset = 0
    private var filter = ""

    suspend fun <T> list(list: List<T>, block: ListBuilder<T>.() -> Unit) {
        val builder = ListBuilder(requireActivity().bot, list.subList(offset, offset + 49), queryId)
        block(builder)
        builder.execute()
    }

    override suspend fun handle(action: UserAction) {
        super.handle(action)
        action.listQuery?.apply {
            val q = id
            val o = offset
            val d = action.data!!
            val f = d.drop(d.length + 1)
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