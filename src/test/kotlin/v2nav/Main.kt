package v2nav

import botapi.Bot
import navv2.*
import navv2.abstractions.Activity
import navv2.abstractions.Fragment
import navv2.abstractions.NavArg
import navv2.entities.UserAction
import navv2.presenters.text
import token

val bot = Bot(token)

suspend fun main() {

    bot.setLoggingLevel(Bot.LoggingLevel.NONE)

    NavigationApi.init(bot) {
        register("/start", ::MainActivity)
        register("/settings", ::SettingsActivity)
    }

    bot.updates {
        NavigationApi.listen(this)
    }
}

class MainActivity : Activity() {
    override suspend fun onStart() {
        navigate(::MainFragment)
    }
}

class MainFragment : Fragment() {
    override suspend fun onStart() {
        text {
            content {
                "MAIN FRAGMENT"
            }
            keyboard {
                button("Next", "next")
                homeButton()
            }
        }
    }

    override suspend fun handle(action: UserAction) {
        super.handle(action)
        when (action.data) {
            "next" -> navigate(::SecondFragment)
        }
    }
}

class SecondFragment : Fragment() {
    override suspend fun onStart() {
        text {
            content {
                "SECOND FRAGMENT"
            }
            keyboard {
                backButton()
                homeButton()
            }
        }
    }
}

class SettingsActivity : Activity() {
    override suspend fun onStart() {
        navigate(::SettingsMainFragment)
    }

}

class SettingsMainFragment : Fragment() {
    override suspend fun onStart() {
        text {
            content {
                "SETTINGS"
            }
            keyboard {
                button("Option #1", "one")
                button("Option #2", "two")
                homeButton()
            }
        }
    }

    override suspend fun handle(action: UserAction) {
        super.handle(action)
        when (action.data) {
            "one", "two" -> navigate(::OptionOneFragment, OptionOneFragment.Arg(action.data!!))
        }
    }
}

class OptionOneFragment : Fragment() {

    class Arg (
        val option: String
    ) : NavArg

    private val arg: Arg by lazy { navArgs() }

    private lateinit var option: String

    override suspend fun onCreate() {
        option = arg.option
    }

    override suspend fun onStart() {
        text {
            content {
                "OPTION #$option"
            }
            keyboard {
                backButton()
            }
        }
    }
}


