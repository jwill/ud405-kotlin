package dev.jwill

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.ExtendViewport

class BallScreen : ScreenAdapter(), InputProcessor {
    companion object {

        val WORLD_SIZE = 480.0f

        // TODO: When a single ball is working try a bunch of balls.
        // TODO: See how many balls you can add before your computer starts slowing down.
        private val BALL_COUNT = 1
    }

    lateinit var renderer: ShapeRenderer
    lateinit var viewport: ExtendViewport
    lateinit var ball: BouncingBall
    var balls = Array<BouncingBall>()

    override fun show() {
        renderer = ShapeRenderer()
        renderer.setAutoShapeType(true)
        viewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        ball = BouncingBall(viewport)
        balls = Array<BouncingBall>()
        for (i in 0..BALL_COUNT - 1) {
            balls.add(BouncingBall(viewport))
        }
        Gdx.input.inputProcessor = this
    }

    private fun initBalls() {
        ball.init(viewport)
        for (ball in balls) {
            ball.init(viewport)
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        initBalls()
    }

    override fun dispose() {
        renderer.dispose()
    }

    override fun render(delta: Float) {
        viewport.apply()

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT)


        renderer.projectionMatrix = viewport.camera.combined
        renderer.begin(ShapeType.Filled)
        ball.update(delta, viewport)
        ball.render(renderer)


        for (ball in balls) {
            ball.update(delta, viewport)
            ball.render(renderer)
        }

        renderer.end()
    }


    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        if (keycode == Keys.SPACE) {
            initBalls()
        }
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        return false
    }


}
