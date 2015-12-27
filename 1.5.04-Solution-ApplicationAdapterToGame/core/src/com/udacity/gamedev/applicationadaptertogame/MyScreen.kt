package com.udacity.gamedev.applicationadaptertogame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport

/**
 * TODO: Start Here!

 * TODO: First run what we've got so far.

 * TODO: Declare that MyScreen implements Screen (com.badlogic.gdx.Screen), and hit Ctrl-i to insert all required methods.

 * TODO: Move all member variables from MyGame to MyScreen

 * TODO: Move everything from MyGame.create() to MyScreen.show()

 * TODO: Move everything from MyGame.dispose() to MyScreen.dispose()

 * TODO: Move everything from MyGame.resize() to MyScreen.resize()

 * TODO: Move everything from MyGame.render() to MyScreen.render()

 * MyScreen is now ready. Next we'll set up MyGame to make use of MyScreen
 */

class MyScreen: Screen {
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont
    lateinit var viewport: ScreenViewport

    override fun show() {
        batch = SpriteBatch()
        font = BitmapFont()
        font.data.setScale(2f)
        font.region.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear)
        viewport = ScreenViewport()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }

    override fun render(delta: Float) {
        viewport.apply()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.projectionMatrix = viewport.camera.combined
        batch.begin()

        font.draw(batch, "Hello from " + this.javaClass.simpleName,
                viewport.worldWidth / 2,
                viewport.worldHeight / 2,
                0f,
                Align.center,
                false)

        batch.end()
    }

    override fun pause() {
        return
    }

    override fun hide() {
        return
    }

    override fun resume() {
        return
    }
}