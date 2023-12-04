package aoc2023

import Day
import regexGroups
import java.io.File

fun main() = Day2.solve(8, 2286)

object Day2: Day<List<Game>>(2, 2023) {
    override fun parse(file: File) = file
        .readLines()
        .mapIndexed { index, line ->
            line
                .substringAfter(": ")
                .split(";")
                .map { drawing ->
                    "((\\d+) (red|blue|green))"
                        .regexGroups(drawing)
                        .map { (_, _, amount, color) ->
                            CubeSet(amount.toInt(), Color.valueOf(color.uppercase()))
                        }
                        .let { Drawing(it) }
                }
                .let { Game(index + 1, it) }
        }

    override fun part1(input: List<Game>) = input
        .filter { game ->
            game.canBePlayedWith(listOf(
                CubeSet(12, Color.RED),
                CubeSet(13, Color.GREEN),
                CubeSet(14, Color.BLUE)
            ))
        }
        .sumOf { it.id }

    override fun part2(input: List<Game>) = input
        .sumOf { game ->
            game
                .drawings
                .flatMap { it.subsets }
                .groupBy { it.color }
                .map { it.value.maxOf { color -> color.amount } }
                .reduce(Int::times)
        }
    
    private fun Game.canBePlayedWith(bag: List<CubeSet>) = bag.all { bagItem ->
        drawings.all { drawing ->
            drawing.subsets
                .filter { subset -> subset.color == bagItem.color }
                .sumOf { it.amount } <= bagItem.amount }
    }
}

data class Game(val id: Int, val drawings: List<Drawing>)
data class Drawing(val subsets: List<CubeSet>)
data class CubeSet(val amount: Int, val color: Color)
enum class Color { RED, GREEN, BLUE }