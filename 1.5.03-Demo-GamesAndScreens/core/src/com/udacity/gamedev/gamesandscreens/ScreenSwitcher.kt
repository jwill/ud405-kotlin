package com.udacity.gamedev.gamesandscreens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.Screen

/**
 * We'll talk about input handling in the next level!
 */

class ScreenSwitcher(var game: Game, var screen1: Screen, var screen2: Screen) : InputAdapter() {
    var currentScreen: Int = 1

    override fun keyUp(keycode: Int): Boolean {
        if (keycode == Keys.SPACE) {
            if (currentScreen == 1) {
                currentScreen = 2
                game.screen = screen2
            } else {
                currentScreen = 1
                game.screen = screen1
            }
        }
        return true
    }
}
