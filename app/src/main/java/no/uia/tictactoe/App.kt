package no.uia.tictactoe

import android.app.Application
import no.uia.tictactoe.api.GameService
import no.uia.tictactoe.api.data.Game

class App : Application() {

    companion object {
        lateinit var context: App private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}