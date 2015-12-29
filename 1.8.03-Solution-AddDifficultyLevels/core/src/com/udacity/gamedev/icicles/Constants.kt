package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

object Constants {
    val WORLD_SIZE = 10f
    val BACKGROUND_COLOR = Color.BLUE

    val PLAYER_HEAD_RADIUS = 0.5f
    val PLAYER_HEAD_HEIGHT = 4 * PLAYER_HEAD_RADIUS
    val PLAYER_LIMB_WIDTH = 0.1f
    val PLAYER_HEAD_SEGMENTS = 32
    val PLAYER_COLOR = Color.BLACK
    val PLAYER_SPEED = 10f

    val ACCELEROMETER_SENSITIVITY = 0.5f
    val GRAVITATIONAL_ACCELERATION = 9.8f;

    val ICICLE_HEIGHT = 1f
    val ICICLE_WIDTH = 0.5f
    val ICICLE_COLOR = Color.WHITE

    val ICICLES_ACCELERATION = Vector2(0f, -5.0f)
    val ICICLE_SPAWNS_PER_SECOND = 10.0f

    var HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f
    var HUD_MARGIN = 20.0f

    // TODO: Create constants for difficulty labels ("Cold", "Colder", "Coldest")
    val EASY_LABEL = "Cold"
    val MEDIUM_LABEL = "Colder"
    val HARD_LABEL = "Coldest"


    // TODO: Create constants for the icicle spawn rates for the various difficulties
    val EASY_SPAWNS_PER_SECOND = 5f
    val MEDIUM_SPAWNS_PER_SECOND = 15f
    val HARD_SPAWNS_PER_SECOND = 25f

    // TODO: Create Difficulty enum holding the spawn rate and label for each difficulty
    enum class Difficulty(val spawnRate:Float, val label:String) {
        EASY(EASY_SPAWNS_PER_SECOND, EASY_LABEL),
        MEDIUM(MEDIUM_SPAWNS_PER_SECOND, MEDIUM_LABEL),
        HARD(HARD_SPAWNS_PER_SECOND, HARD_LABEL)
    }
}