package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport

class Player (viewport:Viewport){
    companion object {
        val TAG = Player::class.java.toString()
    }

    // TODO: Add a position (add constants to Constants.java first)
    lateinit var position:Vector2

    // TODO: Add a viewport
    var viewport:Viewport

    // TODO: Add constructor that accepts and sets the viewport, then calls init()
    // TODO: Add init() function that moves the character to the bottom center of the screen
    init {
        this.viewport = viewport
        init()
    }

    fun init() {
        position = Vector2(viewport.worldWidth / 2, Constants.PLAYER_HEAD_HEIGHT)
    }



    // TODO: Create a render function that accepts a ShapeRenderer and does the actual drawing
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