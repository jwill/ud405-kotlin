package com.udacity.gamedev.inputtestbed

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.utils.viewport.ExtendViewport


class BallScreen : ScreenAdapter() {
    companion object {
        val WORLD_SIZE = 480.0f
    }

    lateinit var renderer: ShapeRenderer
    lateinit var viewport: ExtendViewport
    lateinit var ball: BouncingBall

    override fun show() {
        renderer = ShapeRenderer()
        renderer.setAutoShapeType(true)
        viewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        ball = BouncingBall(viewport)
        Gdx.input.inputProcessor = ball
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        ball.init()
    }

    override fun dispose() {
        renderer.dispose()
    }

    override fun render(delta: Float) {
        viewport.apply()

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT)

        renderer.projectionMatrix = viewport.camera.combined
        ball.update(delta)

        renderer.begin(ShapeType.Filled)
        ball.render(renderer)
        renderer.end()
    }
}
