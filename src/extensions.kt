import java.io.File
import java.lang.Exception

/**
 * Add safe mutability to immutable datastructures
 */
fun <K, V> Map<K, V>.set(index: K, block: Map<K, V>.(currentValue: V) -> V): Map<K, V> = toMutableMap().apply { put(index, block(getValue(index))) }
fun <T> List<T>.set(index: Int, block: List<T>.(currentValue: T) -> T): List<T> = toMutableList().apply { set(index, block(get(index))) }

/**
 * Reading the input in a certain way
 */
fun File.readGroups(predicate: (String) -> Boolean) = readLines().chunked { predicate(it) }
fun File.readChars() = readText().toList()

/**
 * File read lines additions
 */
fun <T> File.mapLines(block: (String) -> T) = readLines().map(block)
fun <T> File.mapLinesIndexed(block: (Int, String) -> T) = readLines().mapIndexed(block)
fun File.readLine() = readLines().first()

/**
 * Custom standard library additions
 */
fun <T> List<T>.chunked(predicate: (T) -> Boolean) = fold(listOf<List<T>>()) { chunks, element ->
    if (predicate(element)) {
        val last = chunks.lastOrNull() ?: emptyList()

        chunks.dropLast(1) + listOf(last + element)
    } else {
        chunks + listOf(emptyList())
    }
}

fun <T> List<T>.second() = drop(1).first()
fun <T> List<T>.third() = drop(2).first()
fun <T> List<T>.fourth() = drop(3).first()
fun <T> List<T>.fifth() = drop(4).first()

// Does the opposite of flatMap.
fun <T, U> List<T>.thickMap(block: (T) -> U) = map { listOf(block(it)) }
fun List<Int>.product() = reduce(Int::times)

fun <T, U> Collection<T>.mapValid(block: (T) -> U) = mapNotNull { try { block(it) } catch (e: Exception) { null } }
fun <T> Collection<T>.firstValid(block: (T) -> Boolean) = first { try { block(it) } catch (e: Exception) { false } }
fun <T> Collection<T>.lastValid(block: (T) -> Boolean) = last { try { block(it) } catch (e: Exception) { false } }

fun <T> String.mapValid(block: (Char) -> T) = toList().mapValid(block)
fun String.firstValid(block: (Char) -> Boolean) = toList().firstValid(block)
fun String.lastValid(block: (Char) -> Boolean) = toList().lastValid(block)

fun <T> List<T>.joinToLong() = joinToString("").toLong()
fun <T> List<T>.joinToInt() = joinToString("").toInt()

/**
 * Easy-access functional printing
 */
val <T> List<T>.print
    get() = also {
        println("===============================")
        println("List has $size elements:")
        onEach(::println)
        println("===============================")
    }
val <K, V> Map<K, V>.print
    get() = also {
        println("===============================")
        println("Map has ${entries.size} entries:")
        entries.onEach(::println)
        println("===============================")
    }
val <T> T.print
    get() = also(::println)

/**
 * String manipulations
 */
fun String.substringBetween(start: String, end: String) = substringAfter(start).substringBefore(end)

/**
 * Grab library
 * A way of quickly scooping input values in a format
 */
fun String.grabLongs() = "\\d+".toRegex().findAll(this).map { it.value.toLong() }.toList()
fun String.grabInts() = "\\d+".toRegex().findAll(this).map { it.value.toInt() }.toList()
