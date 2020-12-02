package main.kotlin.solutions

import main.kotlin.readLinesFromFile

fun main(args: Array<String>) {
    val target = 2020
    val numbers = readLinesFromFile("./src/resources/01.txt").map { it.toInt() }

    fun firstHalf() {
        val substractedNumbers = numbers.map { target - it to it }.toMap()
        val solution = numbers.first { it in substractedNumbers.keys }
        println("Solution: $solution, ${substractedNumbers[solution]}, ${solution * substractedNumbers[solution]!!}")
    }

    fun secondHalf() {
        // Naive solution
         val solution = numbers.flatMap {a ->
            numbers.flatMap {b ->
                numbers.map { c ->
                    Triple(a, b, c)
                }
            }
        }.first {it.first + it.second + it.third == target}

        println("Solution a=${solution.first}, b=${solution.second}, c=${solution.third}")
        println("Solution multiplied =${solution.first * solution.second * solution.third}")
    }
    secondHalf()
}
