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

    // TODO: Add constant for icicle acceleration
    val ICICLES_ACCELERATION = Vector2(0f, -5.0f)

    // TODO: Add constant for icicle spawns per second
    val ICICLE_SPAWNS_PER_SECOND = 10.0f
}