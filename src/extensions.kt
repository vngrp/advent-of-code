import kotlinx.collections.immutable.toImmutableMap

/**
 * Add safe mutability to immutable datastructures
 */
fun <K, V> Map<K, V>.put(index: K, block: Map<K, V>.(currentValue: V) -> V) = toMutableMap().apply { put(index, block(getValue(index))) }.toImmutableMap()