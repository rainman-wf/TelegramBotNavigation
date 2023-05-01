package botapi.sender

import botapi.models.*


data class InlineQueryResultsBuilder(
    val results: MutableList<InlineQueryResult> = mutableListOf()
) {

    fun article(id: String, title: String, builder: InlineQueryResultArticleBuilder.() -> Unit) {
        val _builder = InlineQueryResultArticleBuilder()

        builder(_builder)

        val article = InlineQueryResultArticle(
            type = "article",
            id = id,
            title = title,
            inputMessageContent = _builder.inputMessageContent ?: throw NullPointerException("InputMessageContent must not be null!"),
            replyMarkup = _builder.replyMarkup,
            url = _builder.url,
            hideUrl = _builder.hideUrl,
            description = _builder.description,
            thumbUrl = _builder.thumbUrl,
            thumbWidth = _builder.thumbWidth,
            thumbHeight = _builder.thumbHeight,
        )

        results.add(article)
    }
}

data class InputTextMessageContentBuilder(
    var parseMode: ParseMode? = null,
    var entities: List<MessageEntity>? = null,
    var disableWebPagePreview: Boolean? = null
)

data class InlineQueryResultArticleBuilder(
    var inputMessageContent: InputMessageContent? = null,
    var replyMarkup: InlineKeyboardMarkup? = null,
    var url: String? = null,
    var hideUrl: Boolean? = null,
    var description: String? = null,
    var thumbUrl: String? = null,
    var thumbWidth: Int? = null,
    var thumbHeight: Int? = null,
) : RequestBuilder {

    fun setUrl(url: String, hideUrl: Boolean? = null) {
        this.url = url
        this.hideUrl = hideUrl
    }

    fun setThumbnail(url: String, width: Int? = null, height: Int? = null) {
        thumbUrl = url
        thumbWidth = width
        thumbHeight = height
    }



    fun text(messageText: String, builder: InputTextMessageContentBuilder.() -> Unit = {}): InputTextMessageContent {
        val _builder = InputTextMessageContentBuilder()
        builder(_builder)
        return InputTextMessageContent(
            messageText = messageText,
            parseMode = _builder.parseMode?.name,
            entities = _builder.entities,
            disableWebPagePreview = _builder.disableWebPagePreview
        )
    }
}




