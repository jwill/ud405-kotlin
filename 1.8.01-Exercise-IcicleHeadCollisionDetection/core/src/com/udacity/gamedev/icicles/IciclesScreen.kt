package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ExtendViewport

class IciclesScreen: Screen {
    companion object {
        val TAG = IciclesScreen::class.java.toString()
    }

    lateinit var viewport:ExtendViewport
    lateinit var renderer:ShapeRenderer

    lateinit var player:Player

    lateinit var icicles:Icicles


    override fun show() {
        viewport = ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE)

        renderer = ShapeRenderer()
        renderer.setAutoShapeType(true)

        player = Player(viewport)
        icicles = Icicles(viewport)

    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        player.init()
        icicles.init()

    }

    override fun hide() {

    }

    override fun render(delta: Float) {
        icicles.update(delta)
        player.update(delta)
        // TODO: Check if the player was hit by an icicle. If so, reset the icicles.


        viewport.apply(true)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a)


        renderer.projectionMatrix = viewport.camera.combined
        renderer.begin()

        player.render(renderer)

        icicles.render(renderer)


        renderer.end()
    }

    override fun resume() {

    }

    override fun dispose() {
        renderer.dispose()
    }
}