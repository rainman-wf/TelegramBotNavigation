package navv2.entities

import navv2.abstractions.Activity
import navv2.abstractions.Fragment
import navv2.abstractions.NavArg
import navv2.abstractions.Navigatable
import java.util.Deque

class NavController (
    private val activity: Activity
) : Navigatable {

    private val stack: Deque<Fragment> get() = activity.fragmentManager.stack

    override suspend fun navigate(constructor: () -> Fragment, arg: NavArg?) {
        stack.addLast(constructor.invoke().attachActivity(activity).apply { arg?.let { setArg(it) } })
        stack.last?.apply {
            onCreate()
            onStart()
        }
    }

    override suspend fun back() {
        stack.last.onDestroy()
        stack.removeLast()
        stack.last?.apply {
            onCreate()
            onStart()
        }
    }

    override suspend fun replace(constructor: () -> Fragment, arg: NavArg?) {
        stack.last.onDestroy()
        stack.removeLast()
        stack.addLast(constructor.invoke().attachActivity(activity).apply { arg?.let { setArg(it) } })
        stack.last?.apply {
            onCreate()
            onStart()
        }
    }

    suspend fun next(constructor: () -> Fragment, arg: NavArg?) {
        stack.last.onDestroy()
        stack.removeLast()
        stack.addLast(constructor.invoke().attachActivity(activity).apply { arg?.let { setArg(it) } })
        stack.last?.apply {
            activity.msgId = null
            onCreate()
            onStart()
        }
    }

    override suspend fun update() {
        stack.last.onStart()
    }
}