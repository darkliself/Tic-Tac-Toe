package tictactoe

import java.lang.NumberFormatException

const val FIRST_PLAYER = "X"
const val SECOND_PLAYER = "O"

var turn = SECOND_PLAYER

fun main() {
    print("Enter cells: ")
    var enterCells = "_________"
    var testCells = "X__X__X__"
    drawField(testCells)
    gameIsEnded(testCells)
    println(enterCells)
    drawField(enterCells)
    var state = 1
    while (state != 0) {
        println("Enter the coordinates: ")
        val coordinates = readLine()!!
        if (checkMove(coordinates, enterCells)) {
            enterCells = makeMove(coordinates,enterCells)

        }
        println(enterCells)
        drawField(enterCells)
    }



    gameIsEnded("_________")
}


fun drawField(patten: String) {
    println("---------")
    val list = mutableListOf<List<String>>()
    var startIndex = 0
    for (i in 0..2) {
        print("| ")
        for (j in startIndex..startIndex + 2) {
            print("${patten[j].toString().replace("_", " ")} ")
        }
        startIndex += 3
        print("|\n")
    }
    println("---------")
}

fun makeMove(coordinates: String, enterCells: String): String {
    val (a, b) = coordinates.split(" ")
    val position = (a.toInt() - 1) * 3 + b.toInt()
    if (turn == FIRST_PLAYER) turn = SECOND_PLAYER
    else turn = FIRST_PLAYER
    return enterCells.substring(0, position - 1) + turn + enterCells.substring(position, enterCells.count())
}

fun checkMove(coordinates: String, currentMoves: String): Boolean {
    val xy = mutableListOf<Int>()
    try {
        coordinates.split(" ").forEach {
            xy.add(it.toInt())
        }
    } catch (e: NumberFormatException) {
        println("You should enter numbers!")
        return false
    }
    return if (xy.any { it > 3 || it < 1 } || xy.count() < 2) {
        println("Coordinates should be from 1 to 3!")
        false
    } else {
        if (currentMoves[((xy[0] - 1) * 3 + xy[1]) - 1] != '_') {
            println("This cell is occupied! Choose another one!")
            false
        } else {
            true
        }
    }
}

fun gameIsEnded(cells: String): Boolean {
    for (i in 0..8 step 3) {
        if (cells[i + 0] == cells[i + 1] && cells[i + 1] == cells[i + 2] && cells[i + 0] != '_') {
            println(cells[i + 0] + " " + i)

        }
    }
    for (i in 0..2) {
        println(cells[i * 3] + " ")
        println(cells[i * 3] + " ")
        println(cells[i * 3] + " ")
        if (cells[i * 3] == cells[i * 3 + 1] && cells[i * 3 + 1] == cells[i * 3 + 2]) {
            println("this is fours case")
        }
    }
    if (cells[0] == cells[4] && cells[4] == cells[8]) {
        println("Second Case")
    }
    if (cells[2] == cells[4] && cells[4] == cells[6]) {
        println("Third Case")
    }

    return false
}




