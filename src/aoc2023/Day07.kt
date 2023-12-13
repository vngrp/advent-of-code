package aoc2023

import Day
import mapLines
import print
import java.io.File
import kotlinx.coroutines.runBlocking

typealias PokerHands = List<Pair<Hand, Bid>>
typealias Bid = Int
typealias Hand = Map<Char, Int>

enum class Type(val strength: Int) {
    HighCard(0),
    OnePair(1),
    TwoPair(2),
    ThreeOfAKind(3),
    FullHouse(4),
    FourOfAKind(5),
    FiveOfAKind(6)
}

fun main() = runBlocking {
    object : Day<PokerHands>(7, 2023) {
        override fun parse(input: File) = input
            .mapLines { line ->
                val (cards, bid) = line.split(" ")

                cards.groupingBy { it }.eachCount() to bid.toInt()
            }

        override suspend fun part1(input: PokerHands) = input
            .map { showHandStrength(it.first) }
            .sortedBy { TODO() }
            .print
            .let { 5 }

        override suspend fun part2(input: PokerHands) = TODO()
    }
        .solve(6440, -1)
}

fun showHandStrength(hand: Hand) {

}

val Hand.type
    get() = when {
        5 in values -> Type.FiveOfAKind
        4 in values -> Type.FourOfAKind
        3 in values && 2 in values  -> Type.FullHouse
        3 in values -> Type.ThreeOfAKind
        2 in values && 2 in (values - 2) -> Type.TwoPair
        2 in values -> Type.OnePair
        else -> Type.HighCard
    }

/**
 * Train an AI model on the belastingdienst!
 */

/**
 * At some point I should try to get in the top 100, by:
 * - Downloading the personal input automatically and placing in "dayN.txt"
 * - Copying the first?last? code block on the page and placing in "test-dayN.txt"
 * - Copyying the last code>em block as the example result and placing it in the solve function "example1.txt"?
 * - Making a code snippet of the template in intellij and hop to the places where I need to change the code
 * - Solving it using the day template I currently use, then when it solves:
 *   - Process with the actual data
 *   - Automatically copy the result to clipboard
 *   - Open AoC page and fill the input (only one on page?)
 *
 * Check how effective this would be on old puzzles and prepare to win a place in top 100 next year :)
 */