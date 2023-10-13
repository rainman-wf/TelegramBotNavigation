package v2nav.chellange

import botapi.Bot
import navv2.NavigationApi
import navv2.abstractions.Activity
import navv2.abstractions.Fragment
import navv2.entities.UserAction
import navv2.presenters.popUp
import navv2.presenters.text
import token

suspend fun main () {

    val bot = Bot(token)

    bot.setLoggingLevel(Bot.LoggingLevel.NONE)

    NavigationApi.init(bot) {
        register("/start", ::MainActivity)
    }

    bot.updates {
        NavigationApi.listen(this)
    }
}

class MainActivity : Activity() {
    override suspend fun onStart() {
        navigate(::FirstQuestionFragment)
    }
}

class FirstQuestionFragment : Fragment() {
    override suspend fun onStart() {
        text {
            content {
                "hello"
            }
            keyboard {
                button("Case #1", "ONE")
                button("Case #2", "TWO")
            }
        }
    }

    override suspend fun handle(action: UserAction) {
        super.handle(action)
        when (action.data) {
            "ONE" -> next(::SecondQuestion)
            "TWO" -> popUp(action.popupQuery, "Bla bla bla")
        }
    }
}

class SecondQuestion : Fragment() {
    override suspend fun onStart() {
        text {
            content {
                "ama second"
            }
            keyboard {
                button("Case #1", "ONE")
                button("Case #2", "TWO")
            }
        }
    }

    override suspend fun handle(action: UserAction) {
        super.handle(action)
        when (action.data) {
            "ONE" -> next(::FinalFragment)
            "TWO" -> popUp(action.popupQuery, "Bla bla bla")
        }
    }
}

class FinalFragment : Fragment() {
    override suspend fun onStart() {
        text {
            content {
                "Final"
            }
        }
    }
}
