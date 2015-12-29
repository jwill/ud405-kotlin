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

    // TODO: Add ScreenViewport for HUD


    // TODO: Add SpriteBatch


    // TODO: Add BitmapFont

    lateinit var player:Player

    lateinit var icicles:Icicles

    // TODO: Add int to hold the top score


    override fun show() {
        viewport = ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE)

        renderer = ShapeRenderer()
        renderer.setAutoShapeType(true)

        // TODO: Initialize the HUD viewport


        // TODO: Initialize the SpriteBatch


        // TODO: Initialize the BitmapFont


        // TODO: Give the font a linear TextureFilter

        player = Player(viewport)
        icicles = Icicles(viewport)

        // TODO: Set top score to zero

    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)

        // TODO: Update HUD viewport


        // TODO: Set font scale to min(width, height) / reference screen size

        player.init()
        icicles.init()

    }

    override fun hide() {

    }

    override fun render(delta: Float) {
        icicles.update(delta)
        player.update(delta)
        // TODO: Check if the player was hit by an icicle. If so, reset the icicles.
        if (player.hitByIcicle(icicles)) {
            icicles.init()
        }

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

        // TODO: Set the top score to max(topScore, iciclesDodges)


        // TODO: Apply the HUD viewport


        // TODO: Set the SpriteBatch's projection matrix


        // TODO: Begin the SpriteBatch


        // TODO: Draw the number of player deaths in the top left


        // TODO: Draw the score and top score in the top right


        // TODO: End the SpriteBatch
    }

    override fun resume() {

    }

    override fun dispose() {
        renderer.dispose()
        // TODO: Dispose of the SpriteBatch
    }
}