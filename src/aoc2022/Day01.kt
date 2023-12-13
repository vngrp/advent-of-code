package aoc2022

import Day
import mapValid
import readGroups
import java.io.File
import kotlinx.coroutines.runBlocking

fun main() = runBlocking { Day1.solve(24000, 45000) }

typealias CalorieInventories = List<List<Int>>

object Day1 : Day<CalorieInventories>(1, 2022) {
    override fun parse(input: File) = input.readGroups(String::isNotEmpty).map { it.mapValid(String::toInt) }
    override suspend fun part1(input: CalorieInventories) = input.maxOf { it.sum() }
    override suspend fun part2(input: CalorieInventories) = input.map { it.sum() }.sortedDescending().take(3).sum()
}