package navv2.entities

class NotificationManager {
    val channels = mutableMapOf<String, Notification>()


}

class Notification(
    val content: String,
    val count: Int,
    val msgId: Long
)

