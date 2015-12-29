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

    // TODO: Add screen reference size for scaling the HUD (480 works well)
    var HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f

    // TODO: Add constant for the margin between the HUD and screen edge
    var HUD_MARGIN = 20.0f
}