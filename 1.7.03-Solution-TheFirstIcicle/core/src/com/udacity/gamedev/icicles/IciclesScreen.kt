package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ExtendViewport

class IciclesScreen: Screen {
    companion object {
        val TAG = IciclesScreen::class.java.toString()
    }

    // TODO: Add an ExtendViewport
    lateinit var viewport:ExtendViewport

    // TODO: Add a ShapeRenderer
    lateinit var renderer:ShapeRenderer

    // TODO: Add an Icicle
    lateinit var icicle:Icicle


    override fun show() {
        // TODO: Initialize the viewport using the world size constant
        viewport = ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE)

        // TODO: Initialize the ShapeRenderer
        renderer = ShapeRenderer()

        // TODO: Set autoShapeType(true) on the ShapeRenderer
        renderer.setAutoShapeType(true)


        // TODO: Create a new Icicle in the middle of the world
        icicle = Icicle(Vector2(Constants.WORLD_SIZE / 2, Constants.WORLD_SIZE / 2))
    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {
        // TODO: Ensure that the viewport updates correctly
        viewport.update(width, height, true)

    }

    override fun hide() {

    }

    override fun render(delta: Float) {
        // TODO: Apply the viewport
        viewport.apply()


        // TODO: Clear the screen to the background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a)


        // TODO: Set the ShapeRenderer's projection matrix
        renderer.projectionMatrix = viewport.camera.combined


        renderer.begin()
        // TODO: Draw the Icicle
        icicle.render(renderer)

        renderer.end()
    }

    override fun resume() {

    }

    // TODO: Dispose of the ShapeRenderer
    override fun dispose() {
        renderer.dispose()
    }
}