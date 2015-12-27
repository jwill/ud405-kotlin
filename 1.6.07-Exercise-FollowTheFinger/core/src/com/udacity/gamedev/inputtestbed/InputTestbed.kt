package com.udacity.gamedev.inputtestbed

import com.badlogic.gdx.Game

class InputTestbed : Game() {

    override fun create() {
        setScreen(BallScreen())
    }

}
