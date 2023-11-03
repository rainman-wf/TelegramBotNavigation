package v2nav.chellange

import botapi.Bot
import botapi.common.ButtonType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import navv2.NavigationApi
import navv2.abstractions.*
import navv2.entities.UserAction
import navv2.presenters.photo
import navv2.presenters.popUp
import navv2.presenters.text
import photo1
import photo2
import token

suspend fun main() {

    val bot = Bot(token)

    bot.setLoggingLevel(Bot.LoggingLevel.NONE)

    val api = NavigationApi(bot) {
        register("/start", ::MainActivity)
        register("/link", ::LinkActivity)
        register("/payment", ::PaymentActivity)
    }

    bot.updates {
        api.listen(this)
    }

}

class MainActivity : Activity() {
    override suspend fun onStart() {
        navigate(::HelloFragment)
    }
}

class HelloFragment : FinalFragment() {
    override suspend fun onStart() {
        photo {
            photo = photo1
            content { "hello fragment" }
            keyboard {
                button("Link", "/link")
                button("pay", "/payment")
            }
        }
    }
}

class LinkActivity : Activity() {
    override suspend fun onStart() {
        navigate(::LinkFragment)
    }

}

class LinkFragment: Fragment(), AutoCloseable {
    override suspend fun onStart() {
        text {
            content {
                "take your link"
            }
            keyboard {
                button("Pay", "/payment")
                button("Take it", "http://link.ru", ButtonType.URL)
            }
        }
    }
}

class PaymentActivity : Activity() {

    override suspend fun onStart() {
       navigate(::TariffPlaneFragment)
    }
}

class TariffPlaneFragment : Fragment () {

    override suspend fun onStart() {
        photo {
            photo = photo2
            content {
                "tariffs"
            }
            keyboard {
                button("3", "1")
                button("3", "2")
                button("3", "3")
                backButton()
            }
        }
    }

    override suspend fun handle(action: UserAction) {
        super.handle(action)
        when (action.data){
            "1", "2", "3" -> navigate({CheckoutFragment(action.data!!.toInt())})
        }
    }
}

class CheckoutFragment (val value: Int): Fragment() {

    override suspend fun onStart() {
        photo {
            photo = photo1
            content {
                "tariff $value"
            }
            keyboard {
                button("pay $value", "http://sadfasdf.sd", ButtonType.URL)
                backButton()
            }
        }
    }
}
