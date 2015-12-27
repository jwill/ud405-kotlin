package com.udacity.gamedev.applicationadaptertogame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport


/**
 * First complete all the TODOs in MyScreen, then:

 * TODO: Delete the whole body of MyGame

 * TODO: Declare that MyGame extends Game (com.badlogic.gdx.Game)

 * TODO: Hit Ctrl-i to insert the create() method

 * TODO: In create(), call setScreen() with a new instance of MyScreen()

 * TODO: Run what we've created.

 * Everything should still be working, but now the drawing is happening in MyScreen. That means it
 * would be easy to swap out MyScreen for another screen containing a game world, a menu, or
 * whatever. Nice work!
 */


class MyGame : ApplicationAdapter() {

    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont
    lateinit var viewport: ScreenViewport


    override fun create() {
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

    override fun render() {
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
}

