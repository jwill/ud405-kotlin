package com.udacity.gamedev.fpscounter

import com.badlogic.gdx.Game

class FPSCounterGame : Game() {

    override fun create() {
        setScreen(FPSCounterScreen())
    }
}
