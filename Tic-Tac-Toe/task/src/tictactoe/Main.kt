package tictactoe

import java.lang.NumberFormatException

const val FIRST_PLAYER = "X"
const val SECOND_PLAYER = "O"

var turn = SECOND_PLAYER

fun main() {
    var enterCells = "_________"
    drawField(enterCells)
    var ended = false
    while (!ended) {
        println("Enter the coordinates: ")
        val coordinates = readLine()!!
        if (checkMove(coordinates, enterCells)) {
            enterCells = makeMove(coordinates,enterCells)
        }
        println(enterCells)
        drawField(enterCells)
        if (gameIsEnded(enterCells) == 1) {
            println("$turn wins")
            ended = true
        }
        when (gameIsEnded(enterCells)) {
            1 ->  {
                println("$turn wins")
                ended = true
            }
            0 -> {
                println("its draw")
                ended = true
            }
        }

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

fun gameIsEnded(cells: String): Int {
    for (i in 0..8 step 3) {
        if (cells[i + 0] == cells[i + 1] && cells[i + 1] == cells[i + 2] && cells[i + 0] != '_') {
            return 1
        }
    }
    for (i in 0..2) {
        if (cells[i] == cells[i + 3] && cells[i + 3] == cells[i + 6] && cells[i] != '_') {
            // println("this is fours case")
            return 1
        }
    }
    if (cells[0] == cells[4] && cells[4] == cells[8] && cells[0] != '_') {
        // println("Second Case")
        return 1
    }
    if (cells[2] == cells[4] && cells[4] == cells[6] && cells[2] != '_') {
        // println("Third Case")
        return 1
    }
    if (cells.count{ it == 'X' || it == 'O'} > 8) {
        return 0
    }
    return 2
}




