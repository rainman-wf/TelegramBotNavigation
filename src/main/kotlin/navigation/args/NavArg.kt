package navigation.args

interface NavArg

@JvmInline
value class IntArg(val value: Int) : NavArg

@JvmInline
value class LongArg(val value: Long) : NavArg

@JvmInline
value class StringArg(val value: String) : NavArg

@JvmInline
value class BooleanArg(val value: Boolean) : NavArg

@JvmInline
value class DoubleArg(val value: Double) : NavArg

fun intArg(name: ArgName, value: Int) = Pair(name, IntArg(value))
fun longArg(name: ArgName, value: Long) = Pair(name, LongArg(value))
fun stringArg(name: ArgName, value: String) = Pair(name, StringArg(value))
fun booleanArg(name: ArgName, value: Boolean) = Pair(name, BooleanArg(value))
fun doubleArg(name: ArgName, value: Double) = Pair(name, DoubleArg(value))

