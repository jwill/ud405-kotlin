package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport

// TODO: Accept a Difficulty in the constructor
class IciclesScreen: Screen {
    companion object {
        val TAG = IciclesScreen::class.java.toString()
    }

    // TODO: Add Difficulty

    lateinit var viewport:ExtendViewport
    lateinit var renderer:ShapeRenderer

    lateinit var hudViewport:ScreenViewport
    lateinit var batch:SpriteBatch
    lateinit var font:BitmapFont

    lateinit var player:Player
    lateinit var icicles:Icicles

    var topScore = 0


    override fun show() {
        viewport = ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE)

        renderer = ShapeRenderer()
        renderer.setAutoShapeType(true)

        // TODO: Initialize the HUD viewport
        hudViewport = ScreenViewport()


        // TODO: Initialize the SpriteBatch
        batch = SpriteBatch()

        // TODO: Initialize the BitmapFont
        font = BitmapFont()

        // TODO: Give the font a linear TextureFilter
        font.region.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear)


        player = Player(viewport)
        icicles = Icicles(viewport)

        // TODO: Set top score to zero
        topScore = 0

    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)

        // TODO: Update HUD viewport
        hudViewport.update(width, height, true)


        // TODO: Set font scale to min(width, height) / reference screen size
        font.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE)

        player.init()
        icicles.init()

    }

    override fun hide() {

    }

    override fun render(delta: Float) {
        icicles.update(delta)
        player.update(delta)
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

        topScore = Math.max(topScore, icicles.iciclesDodged)


        hudViewport.apply()
        batch.projectionMatrix = hudViewport.camera.combined
        batch.begin()


        // TODO: Show Difficulty level in the top left
        
        font.draw(batch, "Deaths: " + player.deathCounter,
                Constants.HUD_MARGIN, hudViewport.worldHeight - Constants.HUD_MARGIN)
        font.draw(batch, "Score: " + icicles.iciclesDodged + "\nTop Score: " + topScore,
                hudViewport.worldWidth - Constants.HUD_MARGIN, hudViewport.worldHeight - Constants.HUD_MARGIN,
                0f, Align.right, false)

        batch.end()
    }

    override fun resume() {

    }

    override fun dispose() {
        renderer.dispose()
        // TODO: Dispose of the SpriteBatch
        batch.dispose()
    }
}