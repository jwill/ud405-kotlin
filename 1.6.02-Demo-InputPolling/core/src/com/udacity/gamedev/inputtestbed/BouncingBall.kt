package com.udacity.gamedev.inputtestbed

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * TODO: Check this out second

 * The behavior of this ball should be familiar from the screensaver example. The two new things are
 * drag, and the periodic kicks the ball relieves to show off that drag.

 * If we run this project, we see a little red ball that occasionally gets kicked in a random
 * direction, the slowly comes to a stop. It kinda looks like an air-hockey table. However, it's not
 * interactive yet. Let's fix that.
 */

class BouncingBall(viewport: Viewport) {
    companion object {

        private val COLOR = Color.RED
        private val DRAG = 1.0f

        private val BASE_RADIUS = 20f
        private val RADIUS_GROWTH_RATE = 2.5f
        private val MIN_RADIUS_MULTIPLIER = 0.1f

        private val KICK_INTERVAL = 3.0f
        private val KICK_VELOCITY = 500.0f
    }

    var lastKick: Long = 0

    var radiusMultiplier = 0f

    var radius = 0f
    var position: Vector2 = Vector2()
    var velocity: Vector2 = Vector2()



    fun init(viewport: Viewport) {
        position = Vector2(viewport.worldWidth / 2, viewport.worldHeight / 2)
        velocity = Vector2()
        radiusMultiplier = 1f
        radius = BASE_RADIUS * radiusMultiplier
        randomKick()
    }

    private fun randomKick() {
        val angle = MathUtils.PI2 * MathUtils.random()
        velocity.x = KICK_VELOCITY * MathUtils.cos(angle)
        velocity.y = KICK_VELOCITY * MathUtils.sin(angle)
    }

    fun update(delta: Float, viewport: Viewport) {

        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            radiusMultiplier += delta * RADIUS_GROWTH_RATE;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            radiusMultiplier -= delta * RADIUS_GROWTH_RATE;
            radiusMultiplier = Math.max(radiusMultiplier, MIN_RADIUS_MULTIPLIER);
        }
        radius = radiusMultiplier * BASE_RADIUS;

        val secondsSinceLastKick = MathUtils.nanoToSec * (TimeUtils.nanoTime() - lastKick)

        if (secondsSinceLastKick > KICK_INTERVAL) {
            lastKick = TimeUtils.nanoTime()
            randomKick()
        }

        // Drag is proportional to the current velocity
        velocity.x -= delta * DRAG * velocity.x
        velocity.y -= delta * DRAG * velocity.y

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
        if (position.y - radius < 0) {
            position.y = radius
            velocity.y = -velocity.y
        }
        if (position.y + radius > viewportHeight) {
            position.y = viewportHeight - radius
            velocity.y = -velocity.y
        }
    }

    fun render(renderer: ShapeRenderer) {
        // This takes advantage of AutoShapeType
        renderer.set(ShapeType.Filled)
        renderer.color = COLOR
        renderer.circle(position.x, position.y, radius)
    }


}
