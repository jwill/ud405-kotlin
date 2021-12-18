package dev.jwill

import com.badlogic.gdx.Game

class ScreenSaver : Game() {

    override fun create() {
		setScreen(BallScreen())
    }
}
