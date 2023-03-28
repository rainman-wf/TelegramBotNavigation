package navigation.models

interface GridAdapter<T> {
    fun map(data: List<T>): List<Button>
}