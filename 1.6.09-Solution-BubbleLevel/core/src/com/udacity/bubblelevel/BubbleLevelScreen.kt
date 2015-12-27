package com.udacity.bubblelevel

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport


class BubbleLevelScreen : ScreenAdapter() {
    companion object {

        val TAG = BubbleLevelScreen::class.java.name

        private val WORLD_SIZE = 100.0f
        private val TEXT_SCALE = 5.0f
        private val AXIS_COLOR = Color.RED
        private val AXIS_WIDTH = 1.0f
    }

    lateinit var renderer: ShapeRenderer
    lateinit var axisViewport: FitViewport

    lateinit var batch: SpriteBatch
    lateinit var textViewport: ScreenViewport
    lateinit var font: BitmapFont

    internal var maxAcceleration: Float = 0.toFloat()
    internal var minAcceleration: Float = 0.toFloat()


    override fun show() {
        axisViewport = FitViewport(WORLD_SIZE, WORLD_SIZE)
        renderer = ShapeRenderer()
        renderer.setAutoShapeType(true)
        batch = SpriteBatch()
        textViewport = ScreenViewport()
        font = BitmapFont()
        font.data.setScale(TEXT_SCALE)
        font.region.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear)
        maxAcceleration = 0f
        minAcceleration = java.lang.Float.MAX_VALUE

    }

    override fun resize(width: Int, height: Int) {
        axisViewport.update(width, height, true)
        textViewport.update(width, height, true)
    }

    override fun dispose() {
        renderer.dispose()
        batch.dispose()
    }

    override fun render(delta: Float) {
        textViewport.apply()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // TODO: First we get the raw accelerometer readings
        val xAxis = Gdx.input.accelerometerX
        val yAxis = Gdx.input.accelerometerY
        val zAxis = Gdx.input.accelerometerZ

        // TODO: We can calculate the total acceleration using the Pythagorean theorem
        val totalAcceleration = Math.sqrt(xAxis * xAxis + yAxis * yAxis + zAxis * zAxis.toDouble()).toFloat()

        maxAcceleration = Math.max(maxAcceleration, totalAcceleration)
        minAcceleration = Math.min(minAcceleration, totalAcceleration)


        batch.projectionMatrix = textViewport.camera.combined

        batch.begin()


        val message: String
        message = ("Accelerometer reads:\nx = $xAxis\ny = $yAxis\n" +
                "z = $zAxis\ntotal = $totalAcceleration\nmax = $maxAcceleration\nmin = $minAcceleration")
        font.draw(batch, message, 40f, textViewport.worldHeight - 40)
        batch.end()

        axisViewport.apply()
        renderer.projectionMatrix = axisViewport.camera.combined

        renderer.begin(ShapeType.Line);

        renderer.setColor(Color.RED);

        // TODO: Draw a circle to indicate 9.8m/s^2
        renderer.circle(WORLD_SIZE / 2,
                WORLD_SIZE / 2,
                WORLD_SIZE / 4, 64)

        renderer.setColor(Color.GREEN);

        // TODO: Draw a circle to hold the bubble when the phone is flat
        renderer.circle(WORLD_SIZE/2, WORLD_SIZE/2, WORLD_SIZE/40, 64 )


        renderer.set(ShapeType.Filled);

        // TODO: Draw the bubble
        renderer.circle(
                WORLD_SIZE * (.5f - .25f * yAxis / 9.8f),
                WORLD_SIZE * (.5f + .25f * xAxis / 9.8f),
                WORLD_SIZE / 50,
                64
        )

        renderer.end();

    }
}
