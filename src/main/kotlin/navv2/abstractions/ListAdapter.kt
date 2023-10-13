package navv2.abstractions

import navv2.entities.ListItem

abstract class ListAdapter<T> {
    abstract fun bind(list: List<T>) : List<ListItem>
}