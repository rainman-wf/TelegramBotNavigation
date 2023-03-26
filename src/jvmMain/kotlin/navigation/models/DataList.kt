package navigation.models

data class DataList<T>(
    val list: List<T>,
    val queryId: String,
    override val toUserId: UserId = UserId(0),
) : DataListRequest<T> {
    override lateinit var adapter: DataListAdapter<T>
}