package aoc2023

import Day
import set
import java.io.File

fun main() = Day4.solve(13, 30)

object Day4 : Day<List<ScratchCard>>(4, 2023) {
    override fun parse(file: File) = file
        .readLines()
        .map { line ->
            val winning = line.substringAfter(": ").substringBefore("|").split(" ").toSet()
            val owned = line.substringAfter("|").split(" ").toSet()

            winning.intersect(owned).mapNotNull { it.toIntOrNull() }
        }

    override fun part1(input: List<ScratchCard>) = input.sumOf {
        if (it.isEmpty()) 0
        else 1.shl(it.size - 1)
    }
    
    override fun part2(input: List<ScratchCard>) = input
        .foldIndexed(input.indices.toList()) { cardIndex, acc, winningNumbers ->
            winningNumbers.foldIndexed(acc) { index, copies, _  ->
                copies.set(cardIndex + index + 1) { it + get(cardIndex) }
            }
        }.sum()
}

typealias ScratchCard = List<Int>