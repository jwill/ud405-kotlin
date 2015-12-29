package com.udacity.gamedev.icicles

import com.badlogic.gdx.Game

class IciclesGame : Game() {

	// TODO: Create IciclesScreen with a Difficulty
	override fun create() = setScreen(IciclesScreen(Constants.Difficulty.HARD))
}
