package aoc2023

import Day
import put
import java.io.File

fun main() = Day4().solve(13, 30)

class Day4 : Day<List<List<Int>>>(4, 2023) {
    override fun parse(file: File) = file
        .readLines()
        .map { line ->
            val winning = line.substringAfter(": ").substringBefore("|").split(" ").toSet()
            val owned = line.substringAfter("|").split(" ").toSet()

            winning.intersect(owned).mapNotNull { it.toIntOrNull() }
        }

    override fun part1(input: List<List<Int>>) = input.sumOf {
        if (it.isEmpty()) 0
        else 1.shl(it.size - 1)
    }
    
    override fun part2(input: List<List<Int>>) = input
        .foldIndexed(input.indices.associateWith { 1 }) { cardIndex, acc, winningNumbers ->
            winningNumbers.foldIndexed(acc) { winningNumberIndex, copies, _  ->
                copies.put(cardIndex + winningNumberIndex + 1) { it + getValue(cardIndex) }
            }
        }.values.sum()
}