package navigation.frame

import kotlinx.coroutines.flow.MutableSharedFlow
import navigation.args.NavArg

class ResultListener<T : NavArg> {

    val data = MutableSharedFlow<T>()

    suspend fun listen(body: T.() -> Unit) {
        data.collect {
            body(it)
        }
    }
}

