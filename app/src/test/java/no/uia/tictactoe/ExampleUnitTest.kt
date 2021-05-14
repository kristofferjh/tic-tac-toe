package no.uia.tictactoe

import no.uia.tictactoe.api.GameService
import no.uia.tictactoe.api.data.Game
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    var gameState: Game? = null
    val firstPlayer:String = "Kristoffer"
    val secondPlayer:String = "KristofferJ"
    val initState = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))



    @Test
    fun createGame(){
        GameService.createGame(firstPlayer, initState ){ state:Game?, err:Int? ->
            gameState = state
            assertNotNull(state)
            assertNotNull(state?.gameId)
            assertEquals(firstPlayer, state?.players?.get(0))
        }
    }
}