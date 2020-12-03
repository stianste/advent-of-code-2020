package main.kotlin.solutions

import main.kotlin.readLinesFromFile

fun main() {
    val lines = readLinesFromFile("./src/resources/03.txt")
    val width = lines.first().length
    val widthIncrement = 3

    fun partOne() {
        val result = lines.mapIndexed{ index, line ->
            line[index * widthIncrement % width]
        }.count { it == '#' }
        println("Number of trees part 1: $result")
    }

    fun partTwo() {
    }

    partOne()
    partTwo()
}
