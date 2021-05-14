package no.uia.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.dialog_join_game.*
import no.uia.tictactoe.api.GameService
import no.uia.tictactoe.api.data.Game
import no.uia.tictactoe.dialogs.CreateGameDialog
import no.uia.tictactoe.dialogs.GameDialogListener
import no.uia.tictactoe.databinding.ActivityMainBinding
import no.uia.tictactoe.dialogs.JoinGameDialog


class MainActivity : AppCompatActivity(), GameDialogListener {

    val TAG: String = "MainActivity"

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startGameButton.setOnClickListener {
            createNewGame()
        }

        binding.joinGameButton.setOnClickListener {
            joinGame()
        }
    }

    private fun createNewGame() {
        val dlg = CreateGameDialog()
        dlg.show(supportFragmentManager, "CreateGameDialogFragment")
    }

    private fun joinGame() {
        val dlg = JoinGameDialog()
        dlg.show(supportFragmentManager, "JoinGameDialogFragment")
    }

    override fun onDialogCreateGame(player: String) {
        Log.d(TAG, player)
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("Player", player)
        intent.putExtra("gameId", "")
        startActivity(intent)
    }

    override fun onDialogJoinGame(player: String, gameId: String) {
        Log.d(TAG, "$player $gameId")
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("Player", player)
        intent.putExtra("gameId", "")
        startActivity(intent)
    }

}

