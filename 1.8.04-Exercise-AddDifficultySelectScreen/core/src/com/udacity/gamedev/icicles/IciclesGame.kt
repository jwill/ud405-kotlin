package com.udacity.gamedev.icicles

import com.badlogic.gdx.Game

class IciclesGame : Game() {

	override fun create() = setScreen(DifficultyScreen(this))

	fun showDifficultyScreen() {
		// TODO: Show the difficulty screen
	}

	fun showIciclesScreen(difficulty: Constants.Difficulty) {
		// TODO: Show the Icicles screen with the appropriate difficulty
	}
}
