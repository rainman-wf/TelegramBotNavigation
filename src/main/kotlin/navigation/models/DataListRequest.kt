package navigation.models

interface DataListRequest <T> : Request {

    val adapter: DataListAdapter<T>

    suspend fun execute(list: List<T>) : Response? {
        return send((this as DataList).toRequest(adapter))
    }
}