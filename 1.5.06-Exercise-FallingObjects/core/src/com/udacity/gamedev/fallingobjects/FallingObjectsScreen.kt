package com.udacity.gamedev.fallingobjects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.utils.viewport.ExtendViewport


class FallingObjectsScreen : ScreenAdapter() {
    companion object {

        private val WORLD_SIZE = 480f
    }

    lateinit var renderer: ShapeRenderer
    lateinit var viewport: ExtendViewport

    lateinit var avalanche: Avalanche

    override fun show() {
        renderer = ShapeRenderer()
        viewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        avalanche = Avalanche()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        renderer.dispose()
    }

    override fun render(delta: Float) {
        viewport.apply()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        avalanche.update(delta, viewport)

        renderer.projectionMatrix = viewport.camera.combined
        renderer.begin(ShapeType.Filled)
        avalanche.render(renderer)

        renderer.end()

    }
}
