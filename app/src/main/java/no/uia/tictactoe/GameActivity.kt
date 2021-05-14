package no.uia.tictactoe

import android.content.Intent
import android.media.MediaPlayer
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import no.uia.tictactoe.databinding.ActivityGameBinding
import kotlin.math.log
import kotlin.system.exitProcess


var userTurn = true

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var newGame: GameManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        newGame = GameManager

        val player = intent.getStringExtra("Player")
        val gameId = intent.getStringExtra("GameId")


        binding.enEn.setOnClickListener { newMove(1, binding.enEn) }
        binding.enTo.setOnClickListener { newMove(2, binding.enTo) }
        binding.enTre.setOnClickListener { newMove(3, binding.enTre) }
        binding.toEn.setOnClickListener { newMove(4, binding.toEn) }
        binding.toTo.setOnClickListener { newMove(5, binding.toTo) }
        binding.toTre.setOnClickListener { newMove(6, binding.toTre) }
        binding.treEn.setOnClickListener { newMove(7, binding.treEn) }
        binding.treTo.setOnClickListener { newMove(8, binding.treTo) }
        binding.treTre.setOnClickListener { newMove(9, binding.treTre) }
        binding.resetButton.setOnClickListener { resetGame() }

        val intent = Intent(this, MainActivity::class.java)
        binding.mainMenu.setOnClickListener { startActivity(intent) }


        setContentView(binding.root)

    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var currentPlayer = 1
    var setPlayer = 1

    fun newMove(cellId: Int, buttonSelected: Button) {

        if (currentPlayer == 1) {
            buttonSelected.text = "X"
            player1.add(cellId)
            currentPlayer = 2

        } else {
            buttonSelected.text = "O"
            player2.add(cellId)
            currentPlayer = 1
        }

        buttonSelected.isEnabled = false
        hasPlayerWon()


    }

    fun hasPlayerWon() {
        if ((player1.contains(1) && player1.contains(2) && player1.contains(3)) ||
                (player1.contains(1) && player1.contains(4) && player1.contains(7)) ||
                (player1.contains(3) && player1.contains(6) && player1.contains(9)) ||
                (player1.contains(7) && player1.contains(8) && player1.contains(9)) ||
                (player1.contains(4) && player1.contains(5) && player1.contains(6)) ||
                (player1.contains(1) && player1.contains(5) && player1.contains(9)) ||
                (player1.contains(3) && player1.contains(5) && player1.contains(7)) ||
                (player1.contains(2) && player1.contains(5) && player1.contains(8))
        ) {
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 1 Wins!")
            build.setPositiveButton("Reset") { dialog, which ->
                resetGame()

            }
            build.setNegativeButton("Main Menu") { dialog, which ->
                exitProcess(1)
            }
            build.show()
        } else if ((player2.contains(1) && player2.contains(2) && player2.contains(3)) ||
                (player2.contains(1) && player2.contains(4) && player2.contains(7)) ||
                (player2.contains(3) && player2.contains(6) && player2.contains(9)) ||
                (player2.contains(7) && player2.contains(8) && player2.contains(9)) ||
                (player2.contains(4) && player2.contains(5) && player2.contains(6)) ||
                (player2.contains(1) && player2.contains(5) && player2.contains(9)) ||
                (player2.contains(3) && player2.contains(5) && player2.contains(7)) ||
                (player2.contains(2) && player2.contains(5) && player2.contains(8))
        ) {
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 2 Wins!")
            build.setPositiveButton("Ok") { dialog, which ->
                resetGame()

            }
            build.setNegativeButton("Exit") { dialog, which ->
                exitProcess(1)
            }
            build.show()
        }
    }


    fun resetGame() {
        player1.clear()
        player2.clear()
        currentPlayer = 1

        for (i in 1..9) {
            var buttonSelected: Button?
            buttonSelected = when (i) {
                1 -> binding.enEn
                2 -> binding.enTo
                3 -> binding.enTre
                4 -> binding.toEn
                5 -> binding.toTo
                6 -> binding.toTre
                7 -> binding.treEn
                8 -> binding.treTo
                9 -> binding.treTre
                else -> {
                    binding.enEn
                }
            }
            buttonSelected.isEnabled = true
            buttonSelected.text = ""
        }

    }


}