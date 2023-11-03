package navv2.entities

import kotlinx.coroutines.*
import navv2.abstractions.*
import navigation.log
import java.util.Deque

class NavController(
    private val activity: Activity
) : Navigatable {

    private val stack: Deque<Fragment> get() = activity.fragmentManager.stack

    override suspend fun navigate(constructor: () -> Fragment, arg: NavArg?) {
        stack.addLast(createFragment(constructor, arg))
        println(stack)
        stack.last?.apply {
            onCreate()
            onStart()
        }
    }

    override suspend fun back() {
        stack.last.onDestroy()
        if (stack.size == 1) activity.home()
        else {
            stack.removeLast()
            stack.last?.apply {
                onCreate()
                onStart()
            }
        }
    }

    override suspend fun replace(constructor: () -> Fragment, arg: NavArg?) {
        stack.last.onDestroy()
        stack.removeLast()
        stack.addLast(createFragment(constructor, arg))
        stack.last?.apply {
            onCreate()
            onStart()
        }
    }

    suspend fun next(constructor: () -> Fragment, arg: NavArg?) {
        stack.last.onDestroy()
        stack.removeLast()
        stack.addLast(createFragment(constructor, arg))
        stack.last?.apply {
            activity.msgId = null
            onCreate()
            onStart()
        }
    }

    override suspend fun update() {
        stack.last.apply {
            onStart()
            if (this is AutoCloseable) {
                activity.autoClose = CoroutineScope(Dispatchers.Default).launch {
                    delay(timeout * 1000L)
                    activity.home()
                }
            }
        }
    }

    private fun createFragment(constructor: () -> Fragment, arg: NavArg?) : Fragment {
        activity.autoClose.cancel()
        val fragment = constructor.invoke().attachActivity(activity).apply { arg?.let { setArg(it) } }
        fragment.let {
            when (it) {
                is AutoCloseable -> activity.autoClose = CoroutineScope(Dispatchers.Default).launch {
                    delay(it.timeout * 1000L)
                    activity.home()
                }
            }
        }
        return fragment
    }
}