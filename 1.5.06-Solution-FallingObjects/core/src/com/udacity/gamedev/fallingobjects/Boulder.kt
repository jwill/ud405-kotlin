package com.udacity.gamedev.fallingobjects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport

import java.util.Random

class Boulder(viewport: Viewport) {
    companion object {

        private val RADIUS_RATIO = 0.01f
        private val COLOR = Color.RED
    }

    // TODO: Declare a constant holding the acceleration due to gravity. -20 works well
    val GRAVITY = -20

    lateinit var position: Vector2
    lateinit var velocity: Vector2
    var radius: Float = 0.toFloat()

    init {
        init(viewport)

    }

    fun init(viewport: Viewport) {
        position = Vector2()

        // TODO: Set the initial velocity to zero in both directions
        velocity = Vector2(0f, -200f)

        radius = viewport.worldWidth * RADIUS_RATIO
        position.y = viewport.worldHeight + radius
        val random = Random()
        position.x = random.nextFloat() * (viewport.worldWidth - 2 * radius) + radius
    }


    fun update(delta: Float) {
        // TODO: Apply gravitational acceleration to the vertical velocity
        velocity.y += delta * GRAVITY

        position.x += delta * velocity.x
        position.y += delta * velocity.y
    }

    val isBelowScreen: Boolean
        get() = position.y < -radius

    fun render(renderer: ShapeRenderer) {
        renderer.set(ShapeType.Filled)
        renderer.color = COLOR
        renderer.circle(position.x, position.y, radius)
    }

    // TODO: Challenge - Add wind blowing from the side
}
