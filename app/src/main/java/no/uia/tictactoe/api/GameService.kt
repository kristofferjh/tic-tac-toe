package no.uia.tictactoe.api

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import no.uia.tictactoe.R
import com.google.gson.Gson
import no.uia.tictactoe.App
import no.uia.tictactoe.api.GameService.context
import no.uia.tictactoe.api.data.Game
import no.uia.tictactoe.api.data.GameState
import org.json.JSONArray
import org.json.JSONObject

typealias GameServiceCallback = (state: Game?, errorCode: Int?) -> Unit

object GameService {

    private val context = App.context

    var currentGameId: String? = null

    private val requestQue: RequestQueue = Volley.newRequestQueue(context)

    private enum class APICreateGame(val url: String) {
        CREATE_GAME(
                "%1s%2s%3s".format(
                        context.getString(R.string.protocol),
                        context.getString(R.string.domain),
                        context.getString(R.string.base_path),
                        context.getString(R.string.create_game_path)
                )
        )
    }

    private enum class APIJoinGame(val url: String) {
        JOIN_GAME(
                "%1s%2s%3s".format(
                        context.getString(R.string.protocol),
                        context.getString(R.string.domain),
                        context.getString(R.string.base_path),
                        context.getString(R.string.join_game_path).format(currentGameId)
                )
        )
    }

    private enum class APIUpdateGame(val url: String) {
        UPDATE_GAME(
                "%1s%2s%3s".format(
                        context.getString(R.string.protocol),
                        context.getString(R.string.domain),
                        context.getString(R.string.base_path),
                        context.getString(R.string.update_game_path).format(currentGameId)
                )
        )
    }

    private enum class APIPollGame(val url: String) {
        POLL_GAME(
                "%1s%2s%3s".format(
                        context.getString(R.string.protocol),
                        context.getString(R.string.domain),
                        context.getString(R.string.base_path),
                        context.getString(R.string.poll_game_path).format(currentGameId)
                )
        )
    }


    fun createGame(playerId: String, state: GameState, callback: GameServiceCallback) {

        val url = APICreateGame.CREATE_GAME.url

        val requestData = JSONObject()
        requestData.put("player", playerId)
        requestData.put("state", JSONArray(state))

        val request = object : JsonObjectRequest(Request.Method.POST, url, requestData,
                {
                    // Success game created.
                    val game = Gson().fromJson(it.toString(0), Game::class.java)
                    callback(game, null)
                }, {
            // Error creating new game.
            callback(null, it.networkResponse.statusCode)
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQue.add(request)
    }

    fun joinGame(playerId: String, gameId: String, callback: GameServiceCallback) {

        val url = APIJoinGame.JOIN_GAME.url

        val requestData = JSONObject()
        requestData.put("player", playerId)
        requestData.put("game", gameId)


        val request = object : JsonObjectRequest(Request.Method.POST, url, requestData,
                {
                    // Success game created.
                    val game = Gson().fromJson(it.toString(0), Game::class.java)
                    callback(game, null)
                }, {
            // Error creating new game.
            callback(null, it.networkResponse.statusCode)
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQue.add(request)
    }

    fun updateGame(players: MutableList<String>, gameId: String, state: GameState, callback: GameServiceCallback) {

        val url = APIUpdateGame.UPDATE_GAME.url

        val requestData = JSONObject()
        requestData.put("players", players)
        requestData.put("game", gameId)
        requestData.put("state", JSONArray(state))

        val request = object : JsonObjectRequest(Request.Method.POST, url, requestData,
                {
                    // Success game created.
                    val game = Gson().fromJson(it.toString(0), Game::class.java)
                    callback(game, null)
                }, {
            // Error creating new game.
            callback(null, it.networkResponse.statusCode)
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQue.add(request)
    }

    fun pollGame(gameId: String, callback: GameServiceCallback) {

        val url = APIPollGame.POLL_GAME.url

        val requestData = JSONObject()
        requestData.put("game", gameId)


        val request = object : JsonObjectRequest(Request.Method.GET, url, requestData,
                {
                    // Success game created.
                    val game = Gson().fromJson(it.toString(0), Game::class.java)
                    callback(game, null)
                }, {
            // Error creating new game.
            callback(null, it.networkResponse.statusCode)
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQue.add(request)
    }
}
