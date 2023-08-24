package navigation.frame

suspend fun Frame.text(block: Frame.Message.() -> Unit) {
    val msgId = controller.getNavSession(userId)
    val builder = Message(msgId)
    block(builder)
    val response = builder.execute()
    setNavSession(response.result?.messageId)
}

suspend fun Frame.photo(block: Frame.Photo.() -> Unit) {
    val msgId = controller.getNavSession(userId)
    val builder = Photo(msgId)
    block(builder)
    val response = builder.execute()
    setNavSession(response.result?.messageId)
}

suspend fun Frame.file(block: Frame.Document.() -> Unit) {
    val msgId = controller.getNavSession(userId)
    val builder = Document(msgId)
    block(builder)
    val response = builder.execute()
    setNavSession(response.result?.messageId)
}


suspend fun Frame.chain(body: suspend () -> Unit) {
    setChainMode()
    body()
    resetChainMode()
}

