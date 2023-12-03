import java.io.File

abstract class Day<T>(val day: Int, val year: Int) {
    abstract fun parse(file: File): T
    abstract fun part1(input: T): Number
    abstract fun part2(input: T): Number

    fun solve(example1: Number, example2: Number) {
        process(::part1, example1)
        process(::part2, example2)
    }

    private fun process(part: (input: T) -> Number, example: Number) = with(part) {
        try {
            calculate(exampleInput) validate example
            calculate(actualInput) then answer()
        }
        catch (e: NotImplementedError) { e.printNotImplemented() }
        catch (e: IncorrectAlgorithmError) { e.printIncorrectAlgorithm() }
    }
}