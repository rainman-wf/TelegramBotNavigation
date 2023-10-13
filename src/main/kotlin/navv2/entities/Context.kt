package navv2.entities

import botapi.Bot
import navigation.log
import navv2.entities.Context.Condition.*
import navv2.abstractions.Activity
import navv2.abstractions.Updatable

class Context(override val userId: Long) : Updatable {

    var activity: Activity? = null
        private set

    val bot: Bot get() = ContextManager.bot

    override suspend fun handle(action: UserAction) {
        log("before handle: $activity : ${activity?.fragmentManager?.stack?.joinToString (" -> ")}")
        when (calcCondition(action)) {
            ROOT -> createOrReplaceActivity(requireNotNull(action.data), requireNotNull(action.messageId))
            LAST_BACK -> destroyActivity()
            HOME -> destroyActivity()
            IGNORE -> return
            HANDLE -> activity?.handle(action)
        }
        log("after handle:  $activity : ${activity?.fragmentManager?.stack?.joinToString (" -> ")}")
    }

    private suspend fun destroyActivity() {
        activity?.onDestroy()
        activity = null
    }

    suspend fun destroySelf() {
        destroyActivity()
    }

    private suspend fun createOrReplaceActivity(command: String, msgId: Long) {
        activity =
            if (activity != null)
                ContextManager.requireActivityConstructor(command)
                    .invoke()
                    .setMsgId(requireNotNull(activity?.msgId))
                    .passContext(this)
            else
                ContextManager.requireActivityConstructor(command)
                    .invoke()
                    .passContext(this)
        requireNotNull(activity).onCreate()
        requireNotNull(activity).onStart()
        requireNotNull(activity).onStarted(msgId)
    }

    private enum class Condition {
        LAST_BACK, ROOT, HOME, IGNORE, HANDLE
    }

    private fun calcCondition(update: UserAction): Condition {
        return activity?.let {
            update.data?.let { data ->
                when {
                    data == "back" && it.fragmentManager.stack.size == 1 -> LAST_BACK
                    ContextManager.isActivityRegistered(data) -> ROOT
                    data == "home" -> HOME
                    else -> HANDLE
                }
            }
        } ?: update.data?.let { data ->
            when {
                ContextManager.isActivityRegistered(data) -> ROOT
                else -> IGNORE
            }
        } ?: IGNORE
    }
}