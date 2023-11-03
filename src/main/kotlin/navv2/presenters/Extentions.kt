package navv2.presenters

import botapi.models.Message
import botapi.sender.answerCallbackQuery
import navv2.abstractions.Fragment
import navv2.entities.ContextManager


suspend fun Fragment.text(block: TextMsgBuilder.() -> Unit): Message? {
    val builder = TextMsgBuilder(_requireActivity().bot, userId, activity.msgId)
    block(builder)
    return builder.execute().result?.also {
        activity.setMsgId(it.messageId)
    }
}

suspend fun Fragment.photo(block: PhotoMsgBuilder.() -> Unit): Message? {
    val builder = PhotoMsgBuilder(_requireActivity().bot, userId, activity.msgId)
    block(builder)
    return builder.execute().result?.also {
        activity.setMsgId(it.messageId)
    }
}

suspend fun Fragment.file(block: DocumentMsgBuilder.() -> Unit): Message? {
    val builder = DocumentMsgBuilder(_requireActivity().bot, userId, activity.msgId)
    block(builder)
    return builder.execute().result?.also {
        activity.setMsgId(it.messageId)
    }
}

suspend fun Fragment.home() {
    activity.home()
}

suspend fun Fragment.popUp(callbackId: String?, text: String, okButton: Boolean = true) {
    callbackId?.let {
        _requireActivity().bot.answerCallbackQuery(it) {
            this.text = text
            this.showAlert = okButton
        }
    }
}


