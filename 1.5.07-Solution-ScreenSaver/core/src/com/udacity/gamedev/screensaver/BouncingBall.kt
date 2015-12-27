package com.udacity.gamedev.screensaver

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport

import java.util.Random

/**


 * This class represents a ball, bouncing around the screen. It maintains a position and velocity, and it needs to knows how to update its position, based on how much time has passed.

 * and has basic physics for colliding with the "walls" (the edges of the screen).
 */


class BouncingBall(viewport: Viewport) {

    var radius: Float = 0.toFloat()
    lateinit var position: Vector2
    lateinit var velocity: Vector2

    fun init(viewport: Viewport) {
        position = Vector2(viewport.worldWidth / 2, viewport.worldHeight / 2)
        velocity = Vector2()
        radius = RADIUS_FACTOR * Math.min(viewport.worldWidth, viewport.worldHeight)
        randomKick()
    }

    private fun randomKick() {
        val random = Random()
        val angle = MathUtils.PI2 * random.nextFloat()
        velocity.x = KICK_VELOCITY * MathUtils.cos(angle)
        velocity.y = KICK_VELOCITY * MathUtils.sin(angle)
    }

    fun update(delta: Float, viewport: Viewport) {

        // TODO: Update the ball's position using its velocity
        position.x += delta * velocity.x
        position.y += delta * velocity.y

        collideWithWalls(radius, viewport.worldWidth, viewport.worldHeight)
    }

    private fun collideWithWalls(radius: Float, viewportWidth: Float, viewportHeight: Float) {
        if (position.x - radius < 0) {
            position.x = radius
            velocity.x = -velocity.x
        }
        if (position.x + radius > viewportWidth) {
            position.x = viewportWidth - radius
            velocity.x = -velocity.x
        }

        // TODO: Make the ball bounce off the bottom of the screen
        if (position.y + radius < 0) {
            position.y = radius
            velocity.y = -velocity.y
        }


        // TODO: Make the ball bounce off the top of the screen
        if (position.y + radius > viewportHeight) {
            position.y = viewportHeight - radius
            velocity.y = -velocity.y
        }
    }

    fun render(renderer: ShapeRenderer) {
        renderer.set(ShapeType.Filled)
        renderer.color = COLOR
        renderer.circle(position.x, position.y, radius)
    }

    companion object {

        private val COLOR = Color.RED
        private val RADIUS_FACTOR = 1.0f / 20
        private val KICK_VELOCITY = 500.0f
    }
}

