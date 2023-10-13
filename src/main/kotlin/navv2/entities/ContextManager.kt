package navv2.entities

import botapi.Bot
import navv2.abstractions.Activity

internal object ContextManager {

    lateinit var bot: Bot

    private val contextContainer = mutableMapOf<Long, Context>()
    private val registeredActivities = mutableMapOf<String, () -> Activity>()

    fun requireActivityConstructor(command: String): () -> Activity {
        return registeredActivities[command]
            ?: throw IllegalArgumentException("Activity with command $command is not registered")
    }
    internal fun isActivityRegistered(command: String): Boolean = registeredActivities.containsKey(command)

    suspend fun handle(update: UserAction) {
        getContext(update.from.id).handle(update)
    }

    fun setActivities(constructors: Map<String, () -> Activity>) {
        registeredActivities.putAll(constructors)
    }

    private fun getContext(userId: Long): Context {
        return contextContainer[userId] ?: run {
            val newContext = Context(userId)
            contextContainer[userId] = newContext
            newContext
        }
    }
}

