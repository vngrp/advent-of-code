package aoc2023

import Day
import INCLUDE_DIAGONAL
import getAdjacents
import grab
import readChars
import readLine
import java.io.File

fun main() = Day3.solve(4361, 467835)

object Day3: Day<File>(3, 2023) {
    override fun parse(file: File) = file
    override fun part2(input: File) = input
        .readLines()
        .grabWithAdjacents(input, "(\\*)")
        .onEach { println(it) }
        .let { 5 }

    override fun part1(input: File) = input
        .readLines()
        .grabWithAdjacents(input, "(\\d+)")
        .filter { it.second.any { char -> char.isSymbol() } }
        .sumOf { it.first.toInt() }

    private fun Char.isSymbol() = !isDigit() && this != '.'
    
    private fun List<String>.grabWithAdjacents(file: File, regex: String) = grab(regex)
        .map { (number, points) ->
            number to points.flatMap { point ->
                file
                    .readChars()
                    .getAdjacents(
                        point,
                        boundary = file.readLine().length,
                        INCLUDE_DIAGONAL
                    )
            }
        }
}