package no.uia.tictactoe

import no.uia.tictactoe.api.GameService
import no.uia.tictactoe.api.data.Game
import no.uia.tictactoe.api.data.GameState

object GameManager {

    var player: String = ""
    var state: GameState? = null
    var GameId: String? = ""

    val StartingGameState: GameState = listOf(listOf("0", "0", "0"), listOf("0", "0", "0"), listOf("0", "0", "0"))

    var onCreationNew: ((Game) -> Unit)? = null
    var onCreationJoin: ((Game) -> Unit)? = null

    fun createGame(player: String) {

        GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
            if (err != null) {
                ///TODO("What is the error code? 406 you forgot something in the header. 500 the server di not like what you gave it")
            } else {
                if (game != null)
                    onCreationNew?.invoke(game)
            }
        }

    }

    fun joinGame(gameId: String) {

        val newGame = Game(mutableListOf(""), GameService.currentGameId.toString(), StartingGameState)

        GameService.joinGame(player, gameId) { game: Game?, err: Int? ->
            if (err != null) {
                ///TODO("What is the error code? 406 you forgot something in the header. 500 the server di not like what you gave it")
            } else {
                onCreationJoin?.invoke(newGame)
            }
        }

    }

    fun updateGame(players: MutableList<String>, gameId: String, state: GameState) {

        GameService.updateGame(players, gameId, state) { game: Game?, err: Int? ->
            if (err != null) {
                ///TODO("What is the error code? 406 you forgot something in the header. 500 the server di not like what you gave it")
            } else {
                /// TODO("We have a game. What to do?)
            }
        }

    }

    fun pollGame(gameId: String) {

        GameService.pollGame(gameId) { game: Game?, err: Int? ->
            if (err != null) {
                ///TODO("What is the error code? 406 you forgot something in the header. 500 the server di not like what you gave it")
            } else {
                /// TODO("We have a game. What to do?)
            }
        }

    }


}