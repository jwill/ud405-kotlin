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

    // TODO: Add a Player (complete Player.kt first)
    lateinit var player:Player


    lateinit var icicle:Icicle


    override fun show() {
        viewport = ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE)

        renderer = ShapeRenderer()
        renderer.setAutoShapeType(true)

        // TODO: Initialize the player
        player = Player(viewport)

        icicle = Icicle(Vector2(Constants.WORLD_SIZE / 2, Constants.WORLD_SIZE / 2))
    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        // TODO: Reset the player (using init())
        player.init()

    }

    override fun hide() {

    }

    override fun render(delta: Float) {
        viewport.apply(true)

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a)


        renderer.projectionMatrix = viewport.camera.combined
        renderer.begin()
        icicle.render(renderer)
        // TODO: Call render() on the player
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