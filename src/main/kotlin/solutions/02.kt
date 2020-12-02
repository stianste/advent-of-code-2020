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

    fun lineIsValidAccordingToSecondPolicy(line: String): Boolean {
        val (range, letter, password) = line.split(" ")
        val (firstIndex, secondIndex) = range.split("-").map { it.toInt() - 1 }
        val char = letter.toCharArray().first()
        return (password[firstIndex] == char && password[secondIndex] != char)
                || (password[firstIndex] != char && password[secondIndex] == char)
    }

    fun partOne() {
        assert(lineIsValid("1-3 a abcde"))
        assert(lineIsValid("2-9 c ccccccccc"))

        assert(!lineIsValid("1-3 b cdefg"))
        println("Solution part 1: ${lines.filter { lineIsValid(it) }.count()}")
    }

    fun partTwo() {
        assert(lineIsValidAccordingToSecondPolicy("1-3 a abcde"))

        assert(!lineIsValidAccordingToSecondPolicy("2-9 c ccccccccc"))
        assert(!lineIsValidAccordingToSecondPolicy("1-3 b cdefg"))

        println("Solution part 2: ${lines.filter { lineIsValidAccordingToSecondPolicy(it) }.count()}")
    }

    partOne()
    partTwo()
}
