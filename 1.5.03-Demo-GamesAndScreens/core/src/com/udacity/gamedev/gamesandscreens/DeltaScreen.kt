package com.udacity.gamedev.gamesandscreens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Align


/**
 * TODO: 2 Check out this screen class second

 * Screens are very similar to ApplicationListeners, with a couple differences. Instead of create(),
 * screens get a show() call. Instead of pause(), screens get a hide() call, and the render() call
 * received by a screen includes an argument called delta.

 * The argument delta is the number of seconds after the previous frame that this frame is expected
 * to hit the screen. In a normal game, running at 60 frames per second, this will almost always be
 * 0.0167 seconds, or 16.7 milli-seconds. However, if your game is running on old hardware, or is
 * just drawing way too much stuff, you may end up with a delta that is larger.

 * In this screen, we're creating a BitmapFont and a SpriteBatch, and displaying the delta passed
 * into the render function. Also, we've included logging on all the Screen callbacks.

 * Let's check out FPSScreen.
 */

class DeltaScreen : Screen {

    lateinit var font: BitmapFont
    lateinit var batch: SpriteBatch

    override fun show() {
        Gdx.app.log(TAG, "show() called")
        batch = SpriteBatch()
        font = BitmapFont()
        font.region.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear)
        font.data.setScale(FONT_SCALE)
    }

    override fun dispose() {
        Gdx.app.log(TAG, "dispose() called")
        batch.dispose()
    }

    override fun resize(width: Int, height: Int) {
        Gdx.app.log(TAG, "resize($width, $height) called")
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        font.draw(batch, "Delta = " + delta, (Gdx.graphics.width / 4).toFloat(),
                (Gdx.graphics.height / 2).toFloat(), 0f, Align.left, false)
        batch.end()
    }

    override fun pause() {
        Gdx.app.log(TAG, "pause() called")
    }

    override fun resume() {
        Gdx.app.log(TAG, "resume() called")
    }

    override fun hide() {
        Gdx.app.log(TAG, "hide() called")
    }

    companion object {

        val TAG = DeltaScreen::class.java.name

        private val FONT_SCALE = 3.0f
    }
}
