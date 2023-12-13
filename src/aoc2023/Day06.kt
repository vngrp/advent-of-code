package aoc2023

import Day
import grabLongs
import joinToLong
import mapLines
import product
import second
import thickMap
import java.io.File
import kotlinx.coroutines.runBlocking

typealias Races = List<List<Long>>

fun main() = runBlocking {
    object : Day<Races>(6, 2023) {
        override fun parse(input: File) = input.mapLines { it.grabLongs() }
        override suspend fun part1(input: Races) = input.first().zip(input.second(), ::countWins).product()
        override suspend fun part2(input: Races) = input.thickMap { it.joinToLong() }.let { part1(it) }

        private fun countWins(time: Long, distance: Long) = (1..<time).count { it.times(time - it) > distance }
    }
        .solve(288, 71503)
}
