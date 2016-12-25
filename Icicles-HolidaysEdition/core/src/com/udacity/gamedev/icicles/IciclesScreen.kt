package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport

// TODO: Accept a IciclesGame in the constructor
class IciclesScreen(game: IciclesGame, difficulty: Constants.Difficulty = Constants.Difficulty.EASY): Screen, InputAdapter() {
    companion object {
        val TAG = IciclesScreen::class.java.toString()
    }

    val game:IciclesGame
    val difficulty:Constants.Difficulty

    lateinit var viewport:ExtendViewport
    lateinit var renderer:ShapeRenderer

    lateinit var hudViewport:ScreenViewport
    lateinit var batch:SpriteBatch
    lateinit var font:BitmapFont

    lateinit var player:Player
    lateinit var icicles:Icicles

    var topScore = 0

    init {
        // TODO: Save the IciclesGame
        this.game = game
        this.difficulty = difficulty
    }


    override fun show() {
        viewport = ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE)

        renderer = ShapeRenderer()
        renderer.setAutoShapeType(true)

        hudViewport = ScreenViewport()


        batch = SpriteBatch()
        font = BitmapFont()

        font.region.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear)


        player = Player(viewport)
        icicles = Icicles(viewport, difficulty)

        Gdx.input.inputProcessor = this

        topScore = 0

    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)

        hudViewport.update(width, height, true)

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
        renderer.end()

        batch.projectionMatrix = viewport.camera.combined
        batch.begin()
        icicles.render(batch)
        batch.end()

        topScore = Math.max(topScore, icicles.iciclesDodged)


        hudViewport.apply()
        batch.projectionMatrix = hudViewport.camera.combined
        batch.begin()


        font.draw(batch, "Deaths: " + player.deathCounter + "\nDifficulty: ${difficulty.label}",
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
        batch.dispose()
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        // TODO: Tell IciclesGame to show the difficulty screen
        game.showDifficultyScreen()
        return true
    }
}