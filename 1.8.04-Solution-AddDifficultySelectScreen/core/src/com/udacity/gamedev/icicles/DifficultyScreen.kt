package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
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
        viewport = FitViewport(Constants.DIFFICULTY_WORLD_SIZE, Constants.DIFFICULTY_WORLD_SIZE)

        Gdx.input.inputProcessor = this

        font = BitmapFont()
        // TODO: Set the font scale using the constant we defined
        font.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE)
        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {
        // TODO: Update the viewport
        viewport.update(width, height, true)

    }

    override fun hide() {
        batch.dispose()
        font.dispose()
        renderer.dispose()
    }

    override fun render(delta: Float) {
        // TODO: Apply the viewport
        viewport.apply()

        with(Constants.BACKGROUND_COLOR) {
            Gdx.gl.glClearColor(r, g, b, a)
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // TODO: Set the ShapeRenderer's projection matrix
        renderer.projectionMatrix = viewport.camera.combined

        // TODO: Use ShapeRenderer to draw the buttons
        renderer.begin(ShapeRenderer.ShapeType.Filled)

        renderer.color = Constants.EASY_COLOR
        renderer.circle(Constants.EASY_CENTER.x, Constants.EASY_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS)

        renderer.color = Constants.MEDIUM_COLOR
        renderer.circle(Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS)

        renderer.color = Constants.HARD_COLOR
        renderer.circle(Constants.HARD_CENTER.x, Constants.HARD_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS)

        renderer.end()

        // TODO: Set the SpriteBatch's projection matrix
        batch.projectionMatrix = viewport.camera.combined


        // TODO: Use SpriteBatch to draw the labels on the buttons
        // HINT: Use GlyphLayout to get vertical centering
        batch.begin()

        val easyLayout = GlyphLayout(font, Constants.EASY_LABEL)
        val mediumLayout = GlyphLayout(font, Constants.MEDIUM_LABEL)
        val hardLayout = GlyphLayout(font, Constants.HARD_LABEL)

        font.draw(batch, Constants.EASY_LABEL, Constants.EASY_CENTER.x, Constants.EASY_CENTER.y + easyLayout.height / 2, 0f, Align.center, false)
        font.draw(batch, Constants.MEDIUM_LABEL, Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y + mediumLayout.height / 2, 0f, Align.center, false)
        font.draw(batch, Constants.HARD_LABEL, Constants.HARD_CENTER.x, Constants.HARD_CENTER.y + hardLayout.height / 2, 0f, Align.center, false)

        batch.end()
    }

    override fun resume() {

    }

    override fun dispose() {

    }

    override fun touchDown(screenX:Int, screenY:Int, pointer:Int, button:Int):Boolean {

        // TODO: Unproject the touch from the screen to the world
        val worldTouch = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))

        // TODO: Check if the touch was inside a button, and launch the icicles screen with the appropriate difficulty

        if (worldTouch.dst(Constants.EASY_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS) {
            game.showIciclesScreen(Constants.Difficulty.EASY)
        }

        if (worldTouch.dst(Constants.MEDIUM_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS) {
            game.showIciclesScreen(Constants.Difficulty.MEDIUM)
        }

        if (worldTouch.dst(Constants.HARD_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS) {
            game.showIciclesScreen(Constants.Difficulty.HARD)
        }
        return false
    }
}