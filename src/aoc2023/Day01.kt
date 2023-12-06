package aoc2023

import Day
import arrow.core.fold
import firstValid
import joinToInt
import lastValid
import java.io.File

fun main() = Day1.solve(8, 2286)

typealias Calibrations = List<String>

object Day1 : Day<Calibrations>(1, 2023) {
    override fun parse(file: File) = file.readLines()
    
    override fun part1(input: Calibrations) = input
        .sumOf {
            listOf(
                it.firstValid(Char::isDigit),
                it.lastValid(Char::isDigit)
            )
                .joinToInt()
        }
    
    override fun part2(input: Calibrations) =
        input
            .map(::replaceWords)
            .let(::part1)

    private fun replaceWords(line: String): String {
        val words = mapOf(
                "zero" to "ze0ro",
                "one" to "o1ne",
                "two" to "tw2o",
                "three" to "thr3ee",
                "four" to "fo4ur",
                "five" to "fi5ve",
                "six" to "si6x",
                "seven" to "se7ven",
                "eight" to "ei8ght",
                "nine" to "ni9ne"
        )

        return words.fold(line) { acc, (word, wordJumble) ->
            acc.replace(word, wordJumble)
        }
    }
}


