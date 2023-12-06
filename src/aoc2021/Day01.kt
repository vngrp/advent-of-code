package aoc2021

import Day
import mapValid
import java.io.File

fun main() = Day1.solve(7, 5)

typealias Depths = List<Int>

object Day1: Day<Depths>(1, 2021) {
    override fun parse(file: File) = file.readLines().mapValid(String::toInt)
    override fun part1(input: Depths) = input.zipWithNext().count { it.first <= it.second }
    override fun part2(input: Depths) = input.windowed(4).count { it[3] > it[0] }
}