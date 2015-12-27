package com.udacity.gamedev.fallingobjects

import com.badlogic.gdx.Game

class FallingObjectsGame : Game() {
    override fun create() {
        setScreen(FallingObjectsScreen())
    }
}
