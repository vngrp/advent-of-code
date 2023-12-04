import java.io.File

/**
 * Code style functions to prettify the day template
 */
context((T) -> Number)
fun <T> Day<T>.calculate(input: File): Number = invoke(parse(input))

val Day<*>.actualInput
 get() = File("src/aoc$year/input/day$day.txt")

val Day<*>.exampleInput
 get() = File("src/aoc$year/input/test-day$day.txt")

val <T> ((T) -> Number).part
    get() = this::class.java.name.last().let { Character.getNumericValue(it) }

context(Day<T>, (T) -> Number)
fun <T> NotImplementedError.printNotImplemented() = println("💻 Year $year - Day $day.$part is not yet implemented")

context(Day<T>, (T) -> Number)
fun <T> IncorrectAlgorithmError.printIncorrectAlgorithm() = println("🚧 Year $year - Day $day.$part is incorrect. Expected $expected, got $actual")

infix fun <T> T.then(block: (input: T) -> Answer) = block(this).also { (year, day, part, result) ->
 println("✅  Year $year - Day $day.$part answer: $result")
}

context(Day<T>)
fun <T> ((T) -> Number).answer() =
    fun(result: Number) =
     Answer(year, day, part, result)

data class Answer(
 val year: Int,
 val day: Int,
 val part: Int,
 val answer: Number
)

data class IncorrectAlgorithmError(val expected: String, val actual: String) : Exception("Expected $expected to equal $actual.")
infix fun <T : Number> T.validate(expected: T) {
 if (this.toLong() != expected.toLong()) {
  throw IncorrectAlgorithmError(expected.toString(), this.toString())
 }
}