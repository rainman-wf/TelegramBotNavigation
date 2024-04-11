package nav

import botapi.models.InlineKeyboardButton
import navigation.frame.Frame
import navigation.frame.popUp
import navigation.frame.text
import navigation.models.NavResponse


data class Model(
    val id: Long,
    val title: String
)

val list = (1..10).map {
    Model(
        it.toLong(),
        it.toString()
    )
}

class MyFrame : Frame() {
    override suspend fun show() {
        text {
            content { "Any" }
            keyboard {
                grid(list,3) {
                    button(it.title, it.id.toString())
                }
            }
        }
    }

    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse, false)
        popUp(navResponse.callbackId, navResponse.data)
    }
}

