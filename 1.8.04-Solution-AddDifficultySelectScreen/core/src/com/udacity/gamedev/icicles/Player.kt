package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Input.Keys
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
    var deathCounter = 0


    init {
        this.viewport = viewport
        deathCounter = 0

        init()
    }

    fun init() {
        position = Vector2(viewport.worldWidth / 2, Constants.PLAYER_HEAD_HEIGHT)
    }

    fun update(delta:Float) {
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            position.x -= delta * Constants.PLAYER_SPEED
        } else if(Gdx.input.isKeyPressed((Keys.RIGHT))) {
            position.x += delta * Constants.PLAYER_SPEED
        }

        val accelerometerInput = -Gdx.input.accelerometerY / (Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY)

        position.x += -delta * accelerometerInput * Constants.PLAYER_SPEED

        ensureInBounds()
    }

    private fun ensureInBounds() {
        if (position.x - Constants.PLAYER_HEAD_RADIUS < 0) {
            position.x = Constants.PLAYER_HEAD_RADIUS
        }
        if (position.x + Constants.PLAYER_HEAD_RADIUS > viewport.worldWidth) {
            position.x = viewport.worldWidth - Constants.PLAYER_HEAD_RADIUS
        }
    }

    fun hitByIcicle(icicles:Icicles) : Boolean {
        var isHit = false

        for (icicle in icicles.iciclesArray) {
            if (icicle.position.dst(position) < Constants.PLAYER_HEAD_RADIUS) {
                isHit = true
            }
        }

        if (isHit) {
            deathCounter++
            Gdx.app.log(TAG, "Death Count: $deathCounter")
        }

        return isHit
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