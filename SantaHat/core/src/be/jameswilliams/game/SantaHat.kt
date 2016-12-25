package be.jameswilliams.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class SantaHat : ApplicationAdapter() {
    lateinit var batch: SpriteBatch
    lateinit var renderer: ShapeRenderer
    lateinit var img: Texture

    override fun create() {
        renderer = ShapeRenderer()
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        renderer.begin(ShapeRenderer.ShapeType.Filled)
        /*
        // Draw hat cone
        renderer.color = Color.RED
        renderer.triangle(200f, 200f, 150f, 100f, 250f, 100f)

        // Draw fuzzy end
        renderer.color = Color.WHITE
        renderer.circle(200f,200f, 20f)

        // Draw head band
        renderer.color = Color.WHITE
        renderer.rect(150f,100f, 100f, 25f)
        renderer.end()
        */
        drawHat(200f, 200f, 100f)
        drawHat(400f, 200f, 150f)
        renderer.end()
    }

    fun drawHat(centerX: Float, centerY: Float, widthHeight: Float) {
        // Draw hat cone
        renderer.color = Color.RED
        renderer.triangle(centerX, centerY, centerX - (widthHeight / 2f), centerY - widthHeight, centerX + (widthHeight / 2f), centerY - widthHeight)

        // Draw fuzzy end
        renderer.color = Color.WHITE
        renderer.circle(centerX, centerY, widthHeight * 0.2f)

        // Draw head band
        renderer.color = Color.WHITE
        renderer.rect(centerX - (widthHeight / 2f), centerY - widthHeight, widthHeight, widthHeight/4f)
    }
}