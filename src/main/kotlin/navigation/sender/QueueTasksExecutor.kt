package navigation.sender

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class QueueTasksExecutor<T, R>(private val delay: Long? = null, private val block: suspend (T) -> R) {

    private val que: Deque<Pair<T, Callback<R>>> = LinkedList()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                if (que.isNotEmpty()) {
                    val data = que.poll()
                    data.second.result(block(data.first))
                }
                delay?.let { delay(it) }
            }
        }
    }

    suspend fun emit(data: T) = suspendCoroutine { cont ->
        que.add(data to object : Callback<R> {
            override fun result(data: R) {
                cont.resume(data)
            }
        })
    }

    suspend fun emitPriority(data: T) = suspendCoroutine { cont ->
        que.addFirst(data to object : Callback<R> {
            override fun result(data: R) {
                cont.resume(data)
            }
        })
    }

    private interface Callback<R> {
        fun result(data: R)
    }
}