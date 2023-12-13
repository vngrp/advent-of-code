package aoc2023

import Day
import readGroups
import readLine
import java.io.File
import kotlinx.coroutines.runBlocking

data class Almanac(val seeds: List<Long>, val maps: List<AlmanacMap>)
typealias AlmanacMap = Map<LongRange, LongRange>

fun main() = runBlocking { Day5.solve(35, 46) }

object Day5 : Day<Almanac>(5, 2023) {
    override fun parse(input: File): Almanac {
        val seeds = input
            .readLine()
            .substringAfter(": ")
            .split(" ")
            .map { it.toLong() }
        
        val maps = input
            .readGroups(String::isNotEmpty)
            .drop(1)
            .map { map ->
                map
                    .drop(1)
                    .associate {
                        val (destination, source, rangeLength) = it.split(" ").map(String::toLong)
                        
                        (source ..< source + rangeLength) to (destination ..< destination + rangeLength)
                    }
            }
        
        return Almanac(seeds, maps)
    }

    override suspend fun part1(input: Almanac) = input
        .seeds
        .minOf { seed ->
            input
                .maps
                .fold(seed) { acc, map ->
                    acc + map.findSeedRangeDelta(acc)
                }
        }

    private fun Map<LongRange, LongRange>.findSeedRangeDelta(seed: Long) =
        entries.find { seed in it.key }?.delta() ?: 0

    override suspend fun part2(input: Almanac): Number {
        val lowestLocation = input.maps.last().minBy { it.value.first }
        val range = input.maps
            .dropLast(1)
            .reversed()
//            .fold(lowestLocation) { acc, map ->
//                map.entries.find { (it.destination + it.delta()) in acc.destination } ?: error("not found")
//            }

        return 5
    }
}

fun Map.Entry<LongRange, LongRange>.delta() = value.first - key.first
val Map.Entry<LongRange, LongRange>.source
    get() = key
val Map.Entry<LongRange, LongRange>.destination
    get() = value

operator fun LongRange.plus(delta: Long) = (first + delta) .. (last + delta)