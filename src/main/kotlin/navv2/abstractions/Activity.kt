package navv2.abstractions

import botapi.Bot
import botapi.sender.deleteMessage
import kotlinx.coroutines.flow.StateFlow
import navv2.entities.Context
import navv2.entities.ContextManager
import navv2.entities.NavController
import navv2.entities.UserAction
import java.util.*

abstract class Activity : Updatable, LifeCycle {

    protected val bot: Bot get() = ContextManager.bot
    lateinit var navController: NavController
        private set

    var msgId: Long? = null

    final override val userId: Long get() = context.userId

    lateinit var fragmentManager: FragmentManager
        private set

    lateinit var context: Context private set

    internal fun passContext(context: Context): Activity {
        this.context = context
        return this
    }

    internal fun setMsgId(msgId: Long) : Activity {
        this.msgId = msgId
        return this
    }

    final override suspend fun onCreate() {
        fragmentManager = object : FragmentManager {
            override val stack: Deque<Fragment> = LinkedList()
            override val navigationResults: Map<String, StateFlow<NavArg>> = mutableMapOf()
        }
        navController = NavController(this)

    }

    final override suspend fun onDestroy() {
        msgId?.let { bot.deleteMessage(userId, it) }
    }

    suspend fun onStarted(msgId: Long) {
        bot.deleteMessage(userId, msgId)
    }

    final override suspend fun handle(action: UserAction) {
        fragmentManager.stack.last.handle(action)
    }

    suspend fun navigate(fragment: () -> Fragment, arg: NavArg? = null) {
        navController.navigate(fragment, arg)
    }

    override fun toString(): String {
        return this::class.simpleName.toString() + " #$msgId"
    }

    suspend fun home() {
        context.destroySelf()
    }

}