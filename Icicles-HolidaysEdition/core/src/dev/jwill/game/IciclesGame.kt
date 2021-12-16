package dev.jwill.game

import com.badlogic.gdx.Game

class IciclesGame : Game() {

	override fun create() = setScreen(DifficultyScreen(this))

	// TODO: Show the difficulty screen
	fun showDifficultyScreen() = setScreen(DifficultyScreen(this))

	// TODO: Show the Icicles screen with the appropriate difficulty
	fun showIciclesScreen(difficulty: Constants.Difficulty) = setScreen(IciclesScreen(this, difficulty))
}
