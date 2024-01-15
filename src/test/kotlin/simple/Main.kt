package simple

import botapi.Bot
import botapi.models.ParseMode
import botapi.sender.sendMessage
import myId
import token
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

suspend fun main() {

    val bot = Bot(token)

//    bot.sendMessage(myId, "Hello\\! [inline URL](https://www.youtube.com/embed/BM97FPUv3Rc)") {
//        protectContent = true
//        parseMode = ParseMode.MarkdownV2
//    }.let {
//        println(it)
//    }

    val text = String.format(
        PAYMENT_ADMINS_NOTIFY.trimIndent(),
        "1234567",
        123456789L,
        "Too long name fo card with many !@)(#*%$%".take(16),
        "username",
        "+79870054290",
        "120",
        null ?: "нет",
        LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale("ru"))),
        "ДА"
    )


    println(text)

    bot.sendMessage(myId, text) {
        parseMode = ParseMode.MarkdownV2
    }.let { println(it) }


}

private val PAYMENT_ADMINS_NOTIFY =
    """       
        *Поступила оплата* `№``%s`

        *Данные:*
        `——————————┬————————————————————`
        ` id       │`` %18d`
        ` имя      │`` %18s`
        ` username │`` %18s`
        ` тел.     │`` %18s`
        ` сумма    │`` %18s`
        `——————————┴————————————————————`
        
        *Дата подписки:*
        `——————————┬————————————————————`
        ` до       │`` %18s`
        ` после    │`` %18s`
        `——————————┴————————————————————`
        
        Уведомление доставлено\: *%s*

    """
