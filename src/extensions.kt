import java.io.File

/**
 * Add safe mutability to immutable datastructures
 */
fun <K, V> Map<K, V>.set(index: K, block: Map<K, V>.(currentValue: V) -> V): Map<K, V> = toMutableMap().apply { put(index, block(getValue(index))) }
fun <T> List<T>.set(index: Int, block: List<T>.(currentValue: T) -> T): List<T> = toMutableList().apply { set(index, block(get(index))) }

/**
 * Reading the input in a certain way
 */
 fun File.readGroups(predicate: (String) -> Boolean) = readLines().chunked { predicate(it) }

/**
 * Standard library additions
 */
fun <T> List<T>.chunked(predicate: (T) -> Boolean) = fold(listOf<List<T>>()) { chunks, element ->
 if (predicate(element)) {
  val last = chunks.lastOrNull() ?: emptyList()

  chunks.dropLast(1) + listOf(last + element)
 } else {
  chunks + listOf(emptyList())
 }
}
