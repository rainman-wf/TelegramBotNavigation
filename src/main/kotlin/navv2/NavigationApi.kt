package navv2

import botapi.Bot
import botapi.models.Update
import botapi.sender.answerCallbackQuery
import botapi.sender.deleteMessage
import navv2.abstractions.Activity
import navv2.entities.ContextManager
import navv2.entities.UserAction

class NavigationApi(private val  botInstance: Bot, activities: InitActivities.() -> Unit) {

    private val contextManager: ContextManager = ContextManager(botInstance)
    private var deleteInput = true

    init {
        val builder = InitActivities()
        activities(builder)
        contextManager.setActivities(builder.activities)
    }

    suspend fun listen(update: Update) {
        update.apply {
            (message?.apply { if (deleteInput) from?.id?.let { botInstance.deleteMessage(it, messageId) } }?.from
                ?: editedMessage?.from
                ?: callbackQuery?.apply { botInstance.answerCallbackQuery(id) }?.from
                ?: inlineQuery?.from
                ?: chosenInlineResult?.from)
                ?.let {
                    contextManager.handle(UserAction.from(update, it))
                }
        }

    }

    fun saveInput(): NavigationApi {
        deleteInput = false
        return this
    }

    class InitActivities {
        val activities = mutableMapOf<String, () -> Activity>()
        fun register(command: String, activity: () -> Activity) {
            activities[command] = activity
        }
    }
}