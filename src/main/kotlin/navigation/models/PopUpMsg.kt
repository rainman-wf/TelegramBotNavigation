package navigation.models

data class PopUpMsg(val callbackId: String, override val toUserId: UserId) : Request {
    lateinit var text: String
}