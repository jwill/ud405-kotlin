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

    lateinit var viewport:ExtendViewport
    lateinit var renderer:ShapeRenderer

    lateinit var player:Player

    // TODO: Add an instance of Icicles
    lateinit var icicles:Icicles


    override fun show() {
        viewport = ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE)

        renderer = ShapeRenderer()
        renderer.setAutoShapeType(true)

        player = Player(viewport)
        // TODO: Initialize icicles
        icicles = Icicles(viewport)

    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        player.init()
        // TODO: Reset icicles
        icicles.init()

    }

    override fun hide() {

    }

    override fun render(delta: Float) {
        // TODO: Update icicles
        icicles.update(delta)

        player.update(delta)

        viewport.apply(true)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a)


        renderer.projectionMatrix = viewport.camera.combined
        renderer.begin()

        // TODO: Render icicles
        icicles.render(renderer)

        player.render(renderer)


        renderer.end()
    }

    override fun resume() {

    }

    // TODO: Dispose of the ShapeRenderer
    override fun dispose() {
        renderer.dispose()
    }
}