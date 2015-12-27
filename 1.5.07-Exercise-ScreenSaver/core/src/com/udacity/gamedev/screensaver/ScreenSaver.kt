package com.udacity.gamedev.screensaver

import com.badlogic.gdx.Game

class ScreenSaver : Game() {

    override fun create() {
		setScreen(BallScreen())
    }
}
