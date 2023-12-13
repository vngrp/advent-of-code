package aoc2023

import Day
import foldUntil
import mapLine
import java.io.File
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

typealias Network = Pair<List<Direction>, Node>
typealias Node = Map<String, Pair<String, String>>
typealias Direction = Char

val Network.directions get() = first
val Network.nodes get() = second

fun main() = runBlocking {
    object : Day<Network>(8, 2023) {
        override fun parse(input: File): Network {
            val directions = input.mapLine { it.toList() }
            val nodes = input.readLines().drop(2).associate {
                val (node, left, right) = it.split(" = (", ", ", ")")

                node to (left to right)
            }

            return directions to nodes
        }

        override suspend fun part1(input: Network) = coroutineScope {
            var answerIsFound = false
            val startValues = input.nodes.keys.filter { it.endsWith('A') }
            var answer = 0

            while (!answerIsFound) {
                answer = input.directions
                    .foldUntil(
                        { acc -> acc.all { it.endsWith('Z') }.also { answerIsFound = it } },
                        startValues
                    ) { acc, direction ->
                        val count = acc.count { it.endsWith('Z') }
                        if (count > 0) {
                            println("$count -> $acc")
                        }
                        acc.map { node ->
                            val newNode = input.nodes[node] ?: error("No left node found")

                            when (direction) {
                                'L' -> newNode.first
                                'R' -> newNode.second
                                else -> error("Invalid direction")
                            }
                        }
                    }
                    .first
            }

            answer
        }

        override suspend fun part2(input: Network) = TODO()
    }
        .solve(6, -1)
}