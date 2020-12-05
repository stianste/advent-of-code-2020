package main.kotlin.solutions

import main.kotlin.readLinesFromFile
import java.lang.IllegalArgumentException
import kotlin.math.min
import kotlin.test.assertEquals

fun main() {
    val lines = readLinesFromFile("./src/resources/05.txt")
    val numberOfColumnIndicators = 3
    val numberOfRows = 127
    val numberOfColumns = 7

    fun IntRange.size() = this.count() - 1
    fun IntRange.getHalfSize() = this.size() / 2

    fun parseLine(line: String): Pair<String, String> {
        return Pair(
            line.substring(0 until line.length - numberOfColumnIndicators),
            line.takeLast(3)
        )
    }

    fun getUpperHalfOfRange(range: IntRange) = (range.first + range.getHalfSize() + 1 .. range.last)

    fun getLowerHalfOfRange(range: IntRange) = (range.first .. range.first + range.getHalfSize())

    fun recurseDown(range: IntRange, line: String, index: Int): Int {
        if (range.first == range.last) {
            return range.first
        }

        return when(line[index]) {
            'F', 'L' -> recurseDown(getLowerHalfOfRange(range), line, index + 1)
            'B', 'R' -> recurseDown(getUpperHalfOfRange(range), line, index + 1)
            else -> throw IllegalArgumentException("Incorrect input letter ${line[index]} in $line")
        }
    }


    fun getSeatIDFromLine(line: String): Int {
        val (rowCoors, columnCoords) = parseLine(line)
        val row = recurseDown(0..numberOfRows, rowCoors, 0)
        val column = recurseDown(0..numberOfColumns, columnCoords, 0)
        return row * 8 + column
    }

    val seatIDs = lines.map { getSeatIDFromLine(it) }

    fun partOne() {
        val parsedLine = parseLine("BFFFBBFRRR")
        println(parsedLine)

        assertEquals(getLowerHalfOfRange(0..127), (0..63))
        assertEquals(getUpperHalfOfRange(0..63), (32..63))
        assertEquals(getLowerHalfOfRange(32..63), (32..47))

        assert(parsedLine.first == "BFFFBBF" && parsedLine.second == "RRR")
        assertEquals(getSeatIDFromLine("FBFBBFFRLR"), 357)
        assertEquals(getSeatIDFromLine("BFFFBBFRRR"), 567)
        assertEquals(getSeatIDFromLine("FFFBBBFRRR"), 119)
        assertEquals(getSeatIDFromLine("BBFFBBFRLL"), 820)

        println("Solution part 1: ${seatIDs.max() }")
    }

    fun partTwo() {
        fun findMissingSeat(): Int {
            val sortedSeatIDs = seatIDs.sorted()
            return sortedSeatIDs.filterIndexed{ index, ID ->
                sortedSeatIDs[min(index + 1, sortedSeatIDs.size - 1)] != ID + 1 }
                .first() + 1
        }

        val solution = findMissingSeat()
        println("Solution part 2: $solution")
    }

    partOne()
    partTwo()
}
