package com.udacity.gamedev.inputtestbed

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport

class BouncingBall(viewport: Viewport) : InputAdapter() {
    companion object {

        private val COLOR = Color.RED
        private val DRAG = 1.0f

        private val RADIUS_FACTOR = 1 / 20f
        private val RADIUS_GROWTH_RATE = 2.5f
        private val MIN_RADIUS_MULTIPLIER = 0.1f
        private val ACCELERATION = 500.0f
        private val MAX_SPEED = 1000f

        private val KICK_VELOCITY = 500.0f
        private val FLICK_MULTIPLIER = 5.0f
    }

    lateinit var flickStart:Vector2
    var flicking = false

    var baseRadius = 0f
    var radiusMultiplier = 0f

    var position: Vector2 = Vector2()
    var velocity: Vector2 = Vector2()

    lateinit var viewport:Viewport

    init {
        this.viewport = viewport
        init()
    }

    fun init() {
        position = Vector2(viewport.worldWidth / 2, viewport.worldHeight / 2)
        velocity = Vector2()
        radiusMultiplier = 1f
        baseRadius = RADIUS_FACTOR * Math.min(viewport.worldWidth, viewport.worldHeight)
        radiusMultiplier = 1f
    }

    private fun randomKick() {
        val angle = MathUtils.PI2 * MathUtils.random()
        velocity.x = KICK_VELOCITY * MathUtils.cos(angle)
        velocity.y = KICK_VELOCITY * MathUtils.sin(angle)
    }

    fun update(delta: Float, viewport: Viewport) {

        // Growing and shrinking
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            radiusMultiplier += delta * RADIUS_GROWTH_RATE;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            radiusMultiplier -= delta * RADIUS_GROWTH_RATE;
            radiusMultiplier = Math.max(radiusMultiplier, MIN_RADIUS_MULTIPLIER);
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

        // Drag is proportional to the current velocity
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
        // This takes advantage of AutoShapeType
        renderer.set(ShapeType.Filled)
        renderer.color = COLOR
        renderer.circle(position.x, position.y, baseRadius * radiusMultiplier)
    }

    override fun keyDown(keycode:Int) : Boolean {
        when(keycode) {
            Keys.SPACE -> randomKick()

            Keys.R -> init()
        }
        return true
    }

    /**
     * TODO: Check out what happens when a touch starts
     *
     * When a touch starts, we first need to translate the point that the user touched from screen
     * coordinates to world coordinates. Since the viewport handles the projection from world
     * coordinates to screen coordinates, it also has an unproject() method that does the opposite.
     *
     * Next we use the Vector2.dst() method to see if the distance between the touch and the
     * position of the ball is smaller than the ball's radius. If the touch is inside the radius,
     * then we start a flick, and save the world coordinates of the touch.
     */
    override fun touchDown(screenX:Int, screenY:Int, pointer:Int, button:Int) : Boolean {
        val worldClick = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))
        if (worldClick.dst(position) < baseRadius * radiusMultiplier) {
            flicking = true
            flickStart = worldClick
        }
        return true
    }

    /**
     * TODO: Check out what happens when a touch ends
     *
     * If we were in the process of flicking the ball, we calculate the vector between the start of
     * the flick and the end of the flick. Remember that the incoming position of the touch is in
     * screen coordinates, so we need to use the viewport to unproject that position into world
     * coordinates.
     *
     * Then we add that flick vector to the velocity of the ball, times some multiplier. Give it a
     * try!
     */
    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int) : Boolean {
        if (flicking) {
            flicking = false;
            val flickEnd = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()));
            val flickVector = Vector2(flickEnd.x - flickStart.x, flickEnd.y - flickStart.y);
            velocity.mulAdd(flickVector, FLICK_MULTIPLIER);

        }

        return true;
    }
}
