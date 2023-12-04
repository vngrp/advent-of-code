package aoc2023

import Day
import put
import java.io.File

fun main() = Day4().solve(13, 30)

typealias Card = Pair<Set<Int>, Set<Int>>

class Day4 : Day<List<Card>>(4, 2023) {
    override fun parse(file: File) = file
        .readLines()
        .map { line ->
            val winning = line.substringAfter(": ").substringBefore(" |").split(" ").mapNotNull { it.toIntOrNull() }
            val owned = line.substringAfter(": ").substringAfter("| ").split(" ").mapNotNull { it.toIntOrNull() }

            winning.toSet() to owned.toSet()
        }

    override fun part1(input: List<Card>) = input.sumOf {
        if (it.winningNumbers().isEmpty()) {
            0
        } else {
            it.winningNumbers().drop(1).fold(1L) { acc, _ -> acc * 2 }
        }
    }
    
    override fun part2(input: List<Card>) = input
        .foldIndexed(input.indices.associateWith { 1 }) { cardIndex, acc, card ->
            card
                .winningNumbers()
                .foldIndexed(acc) { winningNumberIndex, copies, _  ->
                    copies.put(cardIndex + winningNumberIndex + 1) { (it ?: 0) + getOrDefault(cardIndex, 0) }
                }
        }.values.sum()

    private fun Card.winningNumbers() = first.intersect(second)
}
