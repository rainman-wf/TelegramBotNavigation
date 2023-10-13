package navv2.abstractions

import botapi.sender.deleteMessage
import navv2.entities.ContextManager
import navv2.entities.UserAction

abstract class Fragment : Updatable, LifeCycle {

    protected val bot get() = ContextManager.bot
    private lateinit var args: NavArg

    @Suppress("UNCHECKED_CAST")
    fun <T : NavArg> navArgs() = args as T
    internal fun setArgs(args: NavArg): Fragment {
        this.args = args
        return this
    }
    override val userId: Long get() = requireActivity().userId

    lateinit var activity: Activity
        private set

    fun setArg(args: NavArg) : Fragment {
        this.args = args
        return this
    }

    internal fun attachActivity(activity: Activity) : Fragment {
        this.activity = activity
        return this
    }

    override suspend fun onCreate() {}

    protected fun requireActivity() : Activity {
        return activity
    }

    override suspend fun handle(action: UserAction) {
        action.messageId?.apply {
            val result = bot.deleteMessage(action.from.id, this)
            println(result)
        }
        when (action.data) {
            "back" -> back()
        }
    }

    override suspend fun onDestroy() {}

    protected suspend fun navigate(fragment: () -> Fragment, arg: NavArg? = null) {
        activity.navController.navigate(fragment, arg)
    }

    protected suspend fun next(fragment: () -> Fragment, arg: NavArg? = null) {
        activity.navController.next(fragment, arg)
    }

    protected suspend fun back() {
        activity.navController.back()
    }

    protected suspend fun update() {
        activity.navController.update()
    }

    override fun toString(): String {
        return this::class.simpleName.toString()
    }
}
