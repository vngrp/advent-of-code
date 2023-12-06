package aoc2023

import Day
import grabLongs
import joinToLong
import mapLines
import product
import second
import thickMap
import java.io.File

fun main() = Day6.solve(288, 71503)

typealias Races = List<List<Long>>

object Day6 : Day<Races>(6, 2023) {
    override fun parse(file: File) = file.mapLines { it.grabLongs() }
    override fun part1(input: Races) = input.first().zip(input.second(), ::countWins).product()
    override fun part2(input: Races) = input.thickMap { it.joinToLong() }.let(::part1)

    private fun countWins(time: Long, distance: Long) = (1 ..< time).count { it.times(time - it) > distance }
}
