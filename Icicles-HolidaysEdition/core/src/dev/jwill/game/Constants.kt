package dev.jwill.game;


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

    val EASY_LABEL = "Cold"
    val MEDIUM_LABEL = "Colder"
    val HARD_LABEL = "Coldest"

    val EASY_SPAWNS_PER_SECOND = 5f
    val MEDIUM_SPAWNS_PER_SECOND = 15f
    val HARD_SPAWNS_PER_SECOND = 25f

    // TODO: Add constants for the color of each difficulty select circle
    val EASY_COLOR = Color(0.2f, 0.2f, 1f, 1f)
    val MEDIUM_COLOR = Color(0.5f, 0.5f, 1f, 1f)
    val HARD_COLOR = Color(0.7f, 0.7f, 1f, 1f)

    // TODO: Add constant for the size of the difficulty world
    val DIFFICULTY_WORLD_SIZE = 480.0f

    // TODO: Add constant for the radius of the difficulty select "buttons"
    val DIFFICULTY_BUBBLE_RADIUS = DIFFICULTY_WORLD_SIZE / 9

    // TODO: Add constant for the scale of the difficulty labels (1.5 works well)
    val DIFFICULTY_LABEL_SCALE = 1.5f

    // TODO: Add Vector2 constants for the centers of the difficulty select buttons
    val EASY_CENTER = Vector2(DIFFICULTY_WORLD_SIZE / 4, DIFFICULTY_WORLD_SIZE / 2)
    val MEDIUM_CENTER = Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2)
    val HARD_CENTER = Vector2(DIFFICULTY_WORLD_SIZE * 3 / 4, DIFFICULTY_WORLD_SIZE / 2)

    enum class Difficulty(val spawnRate:Float, val label:String) {
        EASY(EASY_SPAWNS_PER_SECOND, EASY_LABEL),
        MEDIUM(MEDIUM_SPAWNS_PER_SECOND, MEDIUM_LABEL),
        HARD(HARD_SPAWNS_PER_SECOND, HARD_LABEL)
    }
}