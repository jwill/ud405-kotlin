package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color
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

    fun drawHat(renderer: ShapeRenderer, centerX: Float, centerY: Float, widthHeight: Float) {
        // Draw hat cone
        renderer.color = Color.RED
        renderer.triangle(centerX, centerY, centerX - (widthHeight / 2f), centerY - widthHeight, centerX + (widthHeight / 2f), centerY - widthHeight)

        // Draw fuzzy end
        renderer.color = Color.WHITE
        renderer.circle(centerX, centerY, widthHeight * 0.2f, 20)

        // Draw head band
        renderer.color = Color.WHITE
        renderer.rect(centerX - (widthHeight / 2f), centerY - widthHeight, widthHeight, widthHeight/4f)
    }

    fun render(renderer: ShapeRenderer) {
            renderer.color = Constants.PLAYER_COLOR
            renderer.set(ShapeType.Filled)


            renderer.circle(position.x, position.y, Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_HEAD_SEGMENTS)

            val torsoTop = Vector2(position.x, position.y - Constants.PLAYER_HEAD_RADIUS)
            val torsoBottom = Vector2(torsoTop.x, torsoTop.y - 2 * Constants.PLAYER_HEAD_RADIUS)

            renderer.rectLine(torsoTop, torsoBottom, Constants.PLAYER_LIMB_WIDTH)

            renderer.rectLine(
                    torsoTop.x, torsoTop.y,
                    torsoTop.x + Constants.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH)
            renderer.rectLine(
                    torsoTop.x, torsoTop.y,
                    torsoTop.x - Constants.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH)

            renderer.rectLine(
                    torsoBottom.x, torsoBottom.y,
                    torsoBottom.x + Constants.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH)

            renderer.rectLine(
                    torsoBottom.x, torsoBottom.y,
                    torsoBottom.x - Constants.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH)

        drawHat(renderer, position.x, position.y + 1.2f, 1f)

    }
}