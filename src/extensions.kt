import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap

/**
 * Add safe mutability to immutable datastructures
 */
fun <K, V> Map<K, V>.put(index: K, block: Map<K, V>.(currentValue: V) -> V) = toMutableMap().apply { put(index, block(getValue(index))) }.toImmutableMap()
fun <T> List<T>.put(index: Int, block: List<T>.(currentValue: T) -> T) = toMutableList().apply { set(index, block(get(index))) }.toImmutableList()