package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport

class DifficultyScreen(game:IciclesGame): Screen, InputAdapter() {
    companion object {
        val TAG = DifficultyScreen::class.java.toString()
    }

    var game:IciclesGame
    lateinit var renderer:ShapeRenderer
    lateinit var batch:SpriteBatch
    lateinit var viewport:FitViewport

    lateinit var font:BitmapFont

    init {
        this.game = game
    }


    override fun show() {
        renderer = ShapeRenderer()
        batch = SpriteBatch()

        // TODO: Initialize a FitViewport with the difficulty world size constant

        Gdx.input.inputProcessor = this

        font = BitmapFont()
        // TODO: Set the font scale using the constant we defined

        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {
        // TODO: Update the viewport

    }

    override fun hide() {
        batch.dispose()
        font.dispose()
        renderer.dispose()
    }

    override fun render(delta: Float) {
        // TODO: Apply the viewport

        with(Constants.BACKGROUND_COLOR) {
            Gdx.gl.glClearColor(r, g, b, a)
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // TODO: Set the ShapeRenderer's projection matric

        // TODO: Use ShapeRenderer to draw the buttons

        // TODO: Set the SpriteBatche's projection matrix


        // TODO: Use SpriteBatch to draw the labels on the buttons
        // HINT: Use GlyphLayout to get vertical centering
    }

    override fun resume() {

    }

    override fun dispose() {

    }

    override fun touchDown(screenX:Int, screenY:Int, pointer:Int, button:Int):Boolean {

        // TODO: Unproject the touch from the screen to the world


        // TODO: Check if the touch was inside a button, and launch the icicles screen with the appropriate difficulty


        return true
    }
}