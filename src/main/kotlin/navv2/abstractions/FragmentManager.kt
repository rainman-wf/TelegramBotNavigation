package navv2.abstractions

import java.util.*

interface FragmentManager {
    val stack: Deque<Fragment>
    val navigationResults: Any
}