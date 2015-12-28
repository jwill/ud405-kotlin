package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport

class Player (viewport:Viewport){
    companion object {
        val TAG = Player::class.java.toString()
    }

    lateinit var position:Vector2

    var viewport:Viewport

    init {
        this.viewport = viewport
        init()
    }

    fun init() {
        position = Vector2(viewport.worldWidth / 2, Constants.PLAYER_HEAD_HEIGHT)
    }

    fun update(delta:Float) {
        // TODO: Use Gdx.input.isKeyPressed() to move the player in the appropriate direction when an arrow key is pressed

        ensureInBounds()
    }

    private fun ensureInBounds() {
        // TODO: Complete this function to ensure the player is within the viewport

    }



    fun render(renderer: ShapeRenderer) {
        with(renderer) {
            color = Constants.PLAYER_COLOR
            set(ShapeType.Filled)
            circle(position.x, position.y, Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_HEAD_SEGMENTS)

            val torsoTop = Vector2(position.x, position.y - Constants.PLAYER_HEAD_RADIUS)
            val torsoBottom = Vector2(torsoTop.x, torsoTop.y - 2 * Constants.PLAYER_HEAD_RADIUS)

            rectLine(torsoTop, torsoBottom, Constants.PLAYER_LIMB_WIDTH)

            rectLine(
                    torsoTop.x, torsoTop.y,
                    torsoTop.x + Constants.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH)
            rectLine(
                    torsoTop.x, torsoTop.y,
                    torsoTop.x - Constants.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH)

            rectLine(
                    torsoBottom.x, torsoBottom.y,
                    torsoBottom.x + Constants.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH)

            rectLine(
                    torsoBottom.x, torsoBottom.y,
                    torsoBottom.x - Constants.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH)
        }
    }
}