package navv2.entities

import botapi.sender.deleteMessage
import kotlinx.coroutines.Job
import navigation.log
import navv2.entities.Context.Condition.*
import navv2.abstractions.Activity
import navv2.abstractions.FinalFragment
import navv2.abstractions.Updatable

class Context(override val userId: Long) : Updatable {

    var activity: Activity? = null
        internal set

    val notificationManager: NotificationManager = NotificationManager()

    internal fun attachContextManager(contextManager: ContextManager) : Context {
        manager = contextManager
        return this
    }

    private lateinit var manager: ContextManager

    override suspend fun handle(action: UserAction) {
        when (calcCondition(action.also { println(it) })) {
            ROOT -> createOrReplaceActivity(requireNotNull(action.data), action.messageId)
            LAST_BACK -> destroyActivity()
            HOME -> destroyActivity()
            IGNORE -> return
            HANDLE -> activity?.handle(action)
        }
    }

    internal suspend fun deleteInput(msgId: Long) {
        manager.bot.deleteMessage(userId, msgId)
    }

    private suspend fun destroyActivity() {
        activity?.onDestroy()
        activity = null
    }

    suspend fun destroySelf() {
        destroyActivity()
    }

    private suspend fun createOrReplaceActivity(command: String, msgId: Long?) {
        activity =
            if (activity != null)
                manager.requireActivityConstructor(command)
                    .invoke()
                    .setMsgId(requireNotNull(activity?.msgId))
                    .attachContext(this)
                    .attachBot(manager.bot)
            else
                manager.requireActivityConstructor(command)
                    .invoke()
                    .attachContext(this)
                    .attachBot(manager.bot)
        requireNotNull(activity).apply {
            onCreate()
            onStart()
            onStarted(msgId)
            if (fragmentManager.stack.last is FinalFragment)
                activity = null
        }

    }

    private enum class Condition {
        LAST_BACK, ROOT, HOME, IGNORE, HANDLE
    }

    private fun calcCondition(update: UserAction): Condition {
        return activity?.let {
            update.data?.let { data ->
                when {
                    data == "back" && it.fragmentManager.stack.size == 1 -> LAST_BACK
                    manager.isActivityRegistered(data) -> ROOT
                    data == "home" -> HOME
                    else -> HANDLE
                }
            }
        } ?: update.data?.let { data ->
            when {
                manager.isActivityRegistered(data) -> ROOT
                else -> IGNORE
            }
        } ?: IGNORE
    }
}