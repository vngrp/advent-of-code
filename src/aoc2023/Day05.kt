package aoc2023

import Day
import readGroups
import java.io.File

data class Almanac(val seeds: List<Long>, val almanacMaps: List<AlmanacMap>)
typealias AlmanacMap = Map<LongRange, Delta>
typealias Delta = Long

fun main() = Day5.solve(35, 46)

object Day5 : Day<Almanac>(5, 2023) {
    override fun parse(file: File): Almanac {
        val seeds = file
            .readLines()
            .first()
            .substringAfter(": ")
            .split(" ")
            .map { it.toLong() }
        
        val maps = file
            .readGroups(String::isNotEmpty)
            .drop(1)
            .map { map ->
                map
                    .drop(1)
                    .associate {
                        val (destination, source, rangeLength) = it.split(" ").map(String::toLong)
                        
                        (source..<source + rangeLength) to destination - source
                    }
            }
        
        return Almanac(seeds, maps)
    }

    override fun part1(input: Almanac) = input
        .seeds
        .minOf { seed ->
            input
                .almanacMaps
                .fold(seed) { acc, almanacMap ->
                    acc + almanacMap.findSeedRangeDelta(acc)
                }
        }
    
    private fun AlmanacMap.findSeedRangeDelta(seed: Long) = entries.find { seed in it.key }?.value ?: 0 
//    private fun AlmanacMap.findLowestSeedRangeDelta(seed: Long, rangeLength: Long) = entries.minOf { entry ->
//        if (entry.value < 0) {
//            // I think we need to find the lowest resulting number in each AlmanacMap range, so for every map
//            // Then intersect them with the seeds you have and only process those
//        }
//    }
    
    override fun part2(input: Almanac) = input
        .seeds
        .windowed(2, 2)
        .flatMap { (rangeStart, rangeLength) -> (rangeStart..<rangeStart+rangeLength) }
        .minOf { 
            input
                .almanacMaps
//                .fold(seedRange) { acc, almanacMap ->
//                    val seed = seed + almanacMap.findSeedRangeDelta(seed, rangeLength)
//                }

            5
        }
}