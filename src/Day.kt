import java.io.File
import kotlin.system.measureTimeMillis

abstract class Day<T>(val day: Int, val year: Int) {
    abstract fun parse(input: File): T
    abstract suspend fun part1(input: T): Number
    abstract suspend fun part2(input: T): Number

    suspend fun solve(example1: Number, example2: Number) {
        process(::part1, example1)
        process(::part2, example2)
    }

    private suspend fun process(part: suspend (input: T) -> Number, example: Number) = with(part) {
        try {
//            part(parse(exampleInput)) validate example
            measureTimeMillis { part(parse(actualInput)) then answer() }.also { println(" (⏳ $it ms)") }
        }
        catch (e: NotImplementedError) { e.printNotImplemented() }
        catch (e: IncorrectAlgorithmError) { e.printIncorrectAlgorithm() }
    }
}