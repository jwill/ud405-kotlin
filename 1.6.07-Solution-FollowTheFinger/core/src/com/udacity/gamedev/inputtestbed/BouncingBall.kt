package com.udacity.gamedev.inputtestbed

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport

import java.util.Random


class BouncingBall(var viewport: Viewport) : InputAdapter() {
    companion object {

        private val COLOR = Color.RED
        private val DRAG = 1.0f
        private val RADIUS_FACTOR = 1.0f / 20
        private val RADIUS_GROWTH_RATE = 1.5f
        private val MIN_RADIUS_MULTIPLIER = 0.1f
        private val ACCELERATION = 500.0f
        private val MAX_SPEED = 400.0f
        private val FOLLOW_MULTIPLIER = 2.0f


        private val KICK_VELOCITY = 500.0f
    }

    var flickStart: Vector2 = Vector2()
    var flicking = false

    // TODO: Declare a Vector2 to hold the ball's target position
    var ballTarget:Vector2 = Vector2()

    // TODO: Declare a boolean to hold whether the ball is following something (and set it to false)
    var following = false

    var baseRadius = 0f
    var radiusMultiplier = 0f

    var position = Vector2()
    var velocity = Vector2()


    init {
        init()
    }

    fun init() {

        position = Vector2(viewport.worldWidth / 2, viewport.worldHeight / 2)
        velocity = Vector2()
        baseRadius = RADIUS_FACTOR * Math.min(viewport.worldWidth, viewport.worldHeight)
        radiusMultiplier = 1f
    }

    private fun randomKick() {
        val random = Random()
        val angle = MathUtils.PI2 * random.nextFloat()
        velocity.x += KICK_VELOCITY * MathUtils.cos(angle)
        velocity.y += KICK_VELOCITY * MathUtils.sin(angle)
    }


    fun update(delta: Float) {

        // Growing and shrinking
        if (Gdx.input.isKeyPressed(Keys.Z)) {
            radiusMultiplier += delta * RADIUS_GROWTH_RATE
        }
        if (Gdx.input.isKeyPressed(Keys.X)) {
            radiusMultiplier -= delta * RADIUS_GROWTH_RATE
            radiusMultiplier = Math.max(radiusMultiplier, MIN_RADIUS_MULTIPLIER)
        }

        // TODO: If we're following something, calculate the difference vector between the targetPosition and the ball's position
        if (following) {
            var followVector = Vector2(ballTarget.x - position.x, ballTarget.y - position.y)

            velocity.x = FOLLOW_MULTIPLIER * followVector.x;
            velocity.y = FOLLOW_MULTIPLIER * followVector.y
        }

        // Movement
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            velocity.x -= delta * ACCELERATION

        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            velocity.x += delta * ACCELERATION

        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            velocity.y += delta * ACCELERATION

        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            velocity.y -= delta * ACCELERATION
        }

        velocity.clamp(0f, MAX_SPEED)

        velocity.x -= delta * DRAG * velocity.x
        velocity.y -= delta * DRAG * velocity.y

        position.x += delta * velocity.x
        position.y += delta * velocity.y


        collideWithWalls(baseRadius * radiusMultiplier, viewport.worldWidth, viewport.worldHeight)
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
        renderer.set(ShapeType.Filled)
        renderer.color = COLOR
        renderer.circle(position.x, position.y, baseRadius * radiusMultiplier)
    }


    override fun keyDown(keycode: Int): Boolean {

        if (keycode == Keys.SPACE) {
            randomKick()
        }
        if (keycode == Keys.R) {
            init()
        }

        return true
    }


    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {

        val worldClick = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))

        if (worldClick.dst(position) < baseRadius * radiusMultiplier) {
            Gdx.app.log("Ball", "Click in the ball, starting flick.")
            flicking = true
            flickStart = worldClick
        } else {
            // TODO: Set the target position
            ballTarget.set(worldClick)

            // TODO: Set the following flag
            following = true

        }


        return true
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        // TODO: If we're following, then update the target position (remember to unproject the touch location)
        if (following) {
            val worldDrag = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))
            ballTarget.set(worldDrag)
        }

        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (flicking) {
            flicking = false
            val flickEnd = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))

            velocity.x += 3 * (flickEnd.x - flickStart.x)
            velocity.y += 3 * (flickEnd.y - flickStart.y)
            Gdx.app.log("Ball", "End flick")
        }

        // TODO: Reset the following flag
        following = false


        return true
    }
}
