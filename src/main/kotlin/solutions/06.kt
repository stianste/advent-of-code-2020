package main.kotlin.solutions

import main.kotlin.readLinesSeparatedByBlankLine

fun partOne(answers: List<String>) = answers.map { it.replace(" ", "").toCharArray().toSet().size }.sum()

fun partTwo(answers: List<String>) =
    answers.map {
        it.trim().split(" ")
            .map { answer -> answer.toCharArray().toSet() }
            .reduce { acc, nextSet -> acc.intersect(nextSet) }
            .size
    }.sum()

fun main() {
    val answers = readLinesSeparatedByBlankLine("./src/resources/06.txt")
    answers.take(10).forEach { println(it) }

    println(partOne(answers))
    println(partTwo(answers))

}