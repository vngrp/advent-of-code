package aoc2023

import Day
import mapLines
import mapValid
import set
import substringBetween
import java.io.File
import kotlinx.coroutines.runBlocking

fun main() = runBlocking { Day4.solve(13, 30) }

typealias ScratchCards = List<List<Int>>

object Day4 : Day<ScratchCards>(4, 2023) {
    override fun parse(input: File) = input
        .mapLines { line ->
            val winning = line.substringBetween(": ", "|").split(" ").toSet()
            val owned = line.substringAfter("|").split(" ").toSet()

            winning
                .intersect(owned)
                .mapValid(String::toInt)
        }

    override suspend fun part1(input: ScratchCards) = input.sumOf {
        if (it.isEmpty()) 0
        else 1.shl(it.size - 1)
    }
    
    override suspend fun part2(input: ScratchCards) = input
        .foldIndexed(input.indices.toList()) { cardIndex, acc, winningNumbers ->
            winningNumbers.foldIndexed(acc) { index, copies, _  ->
                copies.set(cardIndex + index + 1) { it + get(cardIndex) }
            }
        }.sum()
}
