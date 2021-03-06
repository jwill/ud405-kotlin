package com.udacity.gamedev.cyclicoverlap

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * TODO: Start here

 * In this project we have three overlapping rectangles. They'd look nice and symmetrical if the
 * left end of the red one was on top of the blue one, but, constrained by the painter's algorithm
 * as we are, that would seem to be impossible. Can you figure out a way to make it happen?
 */


class CyclicOverlap : ApplicationAdapter() {

    lateinit var renderer: ShapeRenderer
    lateinit var viewport: FitViewport

    override fun create() {
        renderer = ShapeRenderer()
        viewport = FitViewport(WORLD_SIZE, WORLD_SIZE)
    }

    override fun dispose() {
        renderer.dispose()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        viewport.apply()
        with(renderer) {
            projectionMatrix = viewport.camera.combined

            begin(ShapeType.Filled)
            color = Color.RED
            rect(2f, 3.5f, 3f, 1.5f, 6f, 1f, 1f, 1f, 0f)
            color = Color.GREEN
            rect(2f, 3.5f, 3f, 1.5f, 6f, 1f, 1f, 1f, 120f)
            color = Color.BLUE
            rect(2f, 3.5f, 3f, 1.5f, 6f, 1f, 1f, 1f, 240f)

            // TODO: Make it look like the left end of RED is on top of BLUE
            color = Color.RED
            rect(2f, 3.5f, 3f, 1.5f, 3f, 1f, 1f, 1f, 0f)
            end()
        }
    }

    companion object {

		private val WORLD_SIZE = 10f
    }
}
