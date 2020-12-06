package main.kotlin

import java.io.File

fun readLinesFromFile(fileName: String): List<String> =
    File(fileName).readLines()

fun readLinesSeparatedByBlankLine(fileName: String) =
    readLinesFromFile(fileName)
    .joinToString(" ") { if (it == "") "<SEP>" else it.trim() }
    .split("<SEP>")

