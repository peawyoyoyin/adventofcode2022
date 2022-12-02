package solutions.day2

data class Round(val opponentMove: Move, val playerMove: Move) {
    companion object {
        fun parseByPart1Logic(roundInput: String) =
            roundInput.split(" ").let {
                Round(Move.parse(it[0]), Move.parseByPart1Logic(it[1]))
            }

        fun parseByPart2Logic(roundInput: String) =
            roundInput.split(" ").let {
                val expectedOutcome = Outcome.parse(it[1])
                val opponentMove = Move.parse(it[0])

                Round(opponentMove, opponentMove.getMoveForOutcome(expectedOutcome))
            }
    }

    fun calculatePlayerScore() =
        (playerMove against opponentMove).score + playerMove.moveScore
}
