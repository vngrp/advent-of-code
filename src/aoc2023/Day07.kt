package aoc2023

import Day
import java.io.File

fun main() = object : Day<Input>(7, 2023) {
    override fun parse(file: File) = TODO()
    override fun part1(input: Input) = TODO()
    override fun part2(input: Input) = TODO()
}
    .solve(-1, -1)

typealias Input = List<String>

/**
 * At some point I should try to get in the top 100, by:
 * - Downloading the personal input automatically and placing in "dayN.txt"
 * - Copying the first?last? code block on the page and placing in "test-dayN.txt"
 * - Copyying the last code>em block as the example result and placing it in the solve function "example1.txt"?
 * - Making a code snippet of the template in intellij and hop to the places where I need to change the code
 * - Solving it using the day template I currently use, then when it solves:
 *   - Process with the actual data
 *   - Automatically copy the result to clipboard
 *   - Open AoC page and fill the input (only one on page?)
 *
 * Check how effective this would be on old puzzles and prepare to win a place in top 100 next year :)
 */