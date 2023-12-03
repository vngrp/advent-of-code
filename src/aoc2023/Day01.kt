package aoc2023

import Day
import arrow.core.fold
import java.io.File

fun main() = Day1().solve(8, 2286)

class Day1 : Day<List<String>>(1, 2023) {
    override fun parse(file: File) = file.readLines()
    
    override fun part1(input: List<String>) = input
        .sumOf {
            "${it.first { char -> char.isDigit() }}${it.last { char -> char.isDigit() }}".toInt()
        }
    
    override fun part2(input: List<String>) =
        input
            .sumOf { line ->
                val edgeCases = mapOf(
                    "nineight" to "9",
                    "eightwo" to "8",
                    "eighthree" to "8",
                    "sevenine" to "7",
                    "fiveight" to "5",
                    "threeight" to "3",
                    "twone" to "2",
                    "oneight" to "1",
                    "zerone" to "0",
                    )
                val edgeCasesReversed = mapOf(
                    "nineight" to "8",
                    "eightwo" to "2",
                    "eighthree" to "3",
                    "sevenine" to "9",
                    "fiveight" to "8",
                    "threeight" to "8",
                    "twone" to "1",
                    "oneight" to "8",
                    "zerone" to "1",
                    )
                val words = mapOf(
                    "zero" to "0",
                    "one" to "1",
                    "two" to "2",
                    "three" to "3",
                    "four" to "4",
                    "five" to "5",
                    "six" to "6",
                    "seven" to "7",
                    "eight" to "8",
                    "nine" to "9"
                )

                val withoutEdgeCases = edgeCases
                    .fold(line) { acc, (key, value) -> acc.replace(key, value) }

                val withoutEdgeCasesReversed = edgeCasesReversed
                    .fold(line) { acc, (key, value) -> acc.replace(key, value) }

                val first = words
                    .fold(withoutEdgeCases) { acc, (key, value) ->
                        acc.replace(key, value)
                    }
                        .first { char -> char.isDigit() }

                val last = words
                    .fold(withoutEdgeCasesReversed) { acc, (key, value) ->
                        acc.replace(key, value)
                    }
                        .last { char -> char.isDigit() }

                "$first$last".toInt()
            }
}


