package navv2

import botapi.Bot
import botapi.models.Update
import navv2.abstractions.Activity
import navv2.entities.ContextManager
import navv2.entities.UserAction

object NavigationApi {

    fun init(botInstance: Bot, activities: InitActivities.() -> Unit) {
        ContextManager.bot = botInstance
        val builder = InitActivities()
        activities(builder)
        ContextManager.setActivities(builder.activities)
    }

    suspend fun listen(update: Update) {
        update.apply {
            (message?.from
                ?: editedMessage?.from
                ?: callbackQuery?.from
                ?: inlineQuery?.from
                ?: chosenInlineResult?.from)
                ?.let {
                    ContextManager.handle(UserAction.from(update, it))
                }
        }

    }

    class InitActivities {
        val activities = mutableMapOf<String, () -> Activity>()
        fun register(command: String, activity: () -> Activity) {
            activities[command] = activity
        }
    }
}