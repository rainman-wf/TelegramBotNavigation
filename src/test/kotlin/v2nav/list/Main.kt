package v2nav.list

import botapi.Bot
import botapi.common.ButtonType
import navv2.NavigationApi
import navv2.abstractions.Activity
import navv2.abstractions.Fragment
import navv2.abstractions.ListAdapter
import navv2.abstractions.ListFragment
import navv2.entities.ListItem
import navv2.entities.UserAction
import navv2.presenters.text
import token

suspend fun main() {
    val bot = Bot(token)

    bot.setLoggingLevel(Bot.LoggingLevel.NONE)

    val  api = NavigationApi(bot) {
        register("/start", :: MainActivity)

    }

    bot.updates {
        api.listen(this)
    }
}

data class Item(
    val id: Int,
    val name: String,
    val description: String
)

val list = (1..80).map {
    Item(
        it,
        "Item #$it",
        "Description #$it"
    )
}

class MyAdapter : ListAdapter<Item>() {
    override fun bind(list: List<Item>): List<ListItem> {
        return list.map {
            ListItem(
                it.id.toLong(),
                it.name,
                it.description
            )
        }
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
            content { "HU" }
            keyboard {
                button("here", "item", ButtonType.INLINE_QUERY_CURRENT_CHAT)
            }
        }
    }

    override suspend fun handle(action: UserAction) {
        super.handle(action)
        action.listQuery?.let {
            navigate({ItemListFragment(it.id)})
        }
    }
}

class ItemListFragment(queryId: String) : ListFragment(queryId) {
    override suspend fun onStart() {
        list(list) {
            adapter = MyAdapter()
        }
    }

    override suspend fun handle(action: UserAction) {
        super.handle(action)
        action.listItem?.let {
            replace({ Result(it.toLong()) })
        }
    }
}

class Result(val id: Long) : Fragment() {
    override suspend fun onStart() {
        text {
            content { id.toString() }
            keyboard {
                homeButton()
            }
        }
    }
}

