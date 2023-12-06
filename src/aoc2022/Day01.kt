package aoc2022

import Day
import mapValid
import readGroups
import java.io.File

fun main() = Day1.solve(24000, 45000)

typealias CalorieInventories = List<List<Int>>

object Day1 : Day<CalorieInventories>(1, 2022) {
    override fun parse(file: File) = file.readGroups(String::isNotEmpty).map { it.mapValid(String::toInt) }
    override fun part1(input: CalorieInventories) = input.maxOf { it.sum() }
    override fun part2(input: CalorieInventories) = input.map { it.sum() }.sortedDescending().take(3).sum()
}