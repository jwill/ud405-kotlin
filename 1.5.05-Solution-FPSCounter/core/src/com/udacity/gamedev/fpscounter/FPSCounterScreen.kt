package com.udacity.gamedev.fpscounter


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.ScreenViewport

class FPSCounterScreen : ScreenAdapter() {

    // TODO: Declare a SpriteBatch, a BitmapFont, and a ScreenViewport
    lateinit var batch:SpriteBatch
    lateinit var font:BitmapFont
    lateinit var viewport:ScreenViewport


    override fun show() {

        // TODO: Initialize the SpriteBatch
        batch = SpriteBatch()

        // TODO: Initialize the BitmapFont
        font = BitmapFont()
        font.getData().setScale(2f)
        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)

        // TODO: Initialize the ScreenViewport
        viewport = ScreenViewport()
    }

    override fun resize(width: Int, height: Int) {
        // TODO: Update the viewport. Be sure to center the camera
        viewport.update(width, height, true)
    }

    override fun dispose() {
        // TODO: Dispose of the SpriteBatch and the BitmapFont
        batch.dispose()
        font.dispose()
    }

    override fun render(delta: Float) {
        // TODO: Apply the viewport
        viewport.apply()

        // TODO: Set the clear color
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)

        // TODO: Clear the color buffer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // TODO: Set the SpriteBatch's projection matrix
        batch.projectionMatrix = viewport.camera.combined

        // TODO: Begin a new batch
        batch.begin()

        // TODO: Use delta to figure out the number of frames per second
        val fps = 1/delta

        // TODO: Use the BitmapFont to draw the FPS
        font.draw(batch,"FPS= $fps",
                viewport.getWorldWidth() / 4,
                viewport.getWorldHeight() / 2)

        // TODO: End the batch
        batch.end()
    }
}
