package main.kotlin.solutions

import main.kotlin.readLinesFromFile

fun main() {
    val lines = readLinesFromFile("./src/resources/03.txt")

    fun generalSolution(right: Int, down: Int, lines: List<String>) =
        lines.windowed(size = 1, step = down).mapIndexed { x, line ->
            line.first()[x * right % line.first().length]
        }.count { it == '#' }

    fun partOne() {
        val result = generalSolution(3, 1, lines)
        assert(result == 262)
        println("Number of trees part 1: $result")
    }

    fun partTwo() {
        val exampleLines = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
        )

        val targets = listOf(
            Pair(1, 1),
            Pair(3, 1),
            Pair(5, 1),
            Pair(7, 1),
            Pair(1, 2)
        )

        val exampleSolutions = targets.map { generalSolution(it.first, it.second, exampleLines) }
        val exampleProduct = exampleSolutions.reduce { a, b -> a * b }
        assert(exampleProduct == 336)
        println("Product of example: $exampleProduct. Partial solutions: $exampleSolutions")

        val solutions = targets.map { generalSolution(it.first, it.second, lines) }
        val solutionsProduct = solutions.map { it.toBigDecimal() }.reduce { a, b -> a * b }
        println("Product of trees part 2: $solutionsProduct. Partial solutions: $solutions")
    }

    partOne()
    partTwo()
}
