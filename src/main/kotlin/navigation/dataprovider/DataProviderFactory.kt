package navigation.dataprovider

import navigation.frame.Frame
import kotlin.reflect.KProperty

interface DataProviderFactory<F: Frame, P: DataProvider> {
    operator fun getValue(frame: F, property: KProperty<*>): P
}