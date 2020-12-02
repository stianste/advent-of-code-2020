package main.kotlin.solutions

import main.kotlin.readLinesFromFile

fun main() {
    val lines = readLinesFromFile("./src/resources/02.txt")

    fun getNumberOfOccurencesInString(s: String, char: Char) = s.filter { it == char }.count()

    fun lineIsValid(line: String): Boolean {
        val (range, letter, password) = line.split(" ")
        val (minValue, maxValue) = range.split("-").map { it.toInt() }
        return getNumberOfOccurencesInString(password, letter.toCharArray().first()) in (minValue..maxValue)
    }

    fun partOne() {
        assert(lineIsValid("1-3 a abcde"))
        assert(lineIsValid("2-9 c ccccccccc"))

        assert(!lineIsValid("1-3 b cdefg"))
        println(lines.filter { lineIsValid(it) }.count())
    }

    fun partTwo() {
    }

    partOne()
}
