package main.kotlin

import java.io.File

fun readLinesFromFile(fileName: String): List<String> =
    File(fileName).readLines()

