package dev.jwill.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array

import java.util.Arrays

class ConnectTheDotsGenerator : ApplicationAdapter(), InputProcessor {
    lateinit private var shapeRenderer: ShapeRenderer
    lateinit private var spriteBatch: SpriteBatch
    lateinit private var bitmapFont: BitmapFont
    lateinit var dots: Array<Vector2>

    val LOG_TAG = "ConnectTheDotsGenerator"
    private val DOT_RADIUS = 3.0f


    override fun create() {
        Gdx.input.inputProcessor = this
        spriteBatch = SpriteBatch()
        shapeRenderer = ShapeRenderer()
        bitmapFont = BitmapFont()
        resetDots()
    }

    private fun resetDots() {
        dots = Array<Vector2>()
    }

    private fun dotsAsFloatArray(): FloatArray {

        val floatDots = FloatArray(dots.size * 2)
        var i = 0

        for (dot in dots) {
            floatDots[i++] = dot.x
            floatDots[i++] = dot.y
        }

        return floatDots
    }

    private fun logDotsAsFloatArray() {
        Gdx.app.log(LOG_TAG, Arrays.toString(dotsAsFloatArray()))
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)


        shapeRenderer.begin(ShapeType.Filled)
        for (dot in dots) {
            shapeRenderer.circle(dot.x, dot.y, DOT_RADIUS)
        }
        shapeRenderer.end()

        shapeRenderer.begin(ShapeType.Line)
        val floatDots = dotsAsFloatArray()
        if (floatDots.size > 3) {
            shapeRenderer.polyline(floatDots)
        }
        shapeRenderer.end()

        spriteBatch.begin()
        var i = 1
        for (dot in dots) {
            bitmapFont.draw(spriteBatch, i.toString(), dot.x + DOT_RADIUS, dot.y - DOT_RADIUS)
            i++
        }
        spriteBatch.end()


        //        batch.begin();
        //        batch.draw(img, 0, 0);
        //        batch.end();
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Keys.R) {
            resetDots()
        } else if (keycode == Keys.F) {
            logDotsAsFloatArray()
        }
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        dots.add(Vector2(screenX.toFloat(), (Gdx.graphics.height - screenY).toFloat()))
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        return false
    }


}
