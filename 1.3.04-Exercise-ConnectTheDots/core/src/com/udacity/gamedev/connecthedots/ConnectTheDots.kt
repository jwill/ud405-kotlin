package com.udacity.gamedev.connecthedots

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array


/**
 * TODO: Start here

 * In this exercise we're going to connect the dots, but instead of drawing by hand, we're going to
 * use ShapeRenderer and polyline.

 * TODO: Run what we've got so far

 * As you can see, we've got some dots to work with, but no lines yet. Let's fix that!
 */

class ConnectTheDots : ApplicationAdapter() {
    private val DOT_RADIUS = 3.0f
    private val dots = Dots.dots()
    private lateinit var spriteBatch: SpriteBatch
    private lateinit var bitmapFont: BitmapFont
    private var floatDots: FloatArray? = null
    private lateinit var shapeRenderer: ShapeRenderer


    override fun create() {
        spriteBatch = SpriteBatch()
        bitmapFont = BitmapFont()
        floatDots = vector2ArrayToFloatArray(dots)
        shapeRenderer = ShapeRenderer()

    }

    /**
     * TODO: Complete this function to translate Array to float[]

     * The first problem is that the dot positions we have to work with are in an array of vectors,
     * and polyLine wants a flat array of floats. We've set up the array of floats for you, now all
     * you need to do is iterate over the Array of vectors, and put the x and y components into the
     * float array. Remember to check out the solution directory if you need help. This is a tricky
     * one!
     */
    private fun vector2ArrayToFloatArray(dots: Array<Vector2>): FloatArray {

        val floatDots = arrayListOf<Float>()

        return floatDots.toFloatArray()
    }

    override fun render() {
        // Make the background black
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // Draw the dots
        with(shapeRenderer) {
            begin(ShapeType.Filled)
            for (dot in dots) {
                circle(dot.x, dot.y, DOT_RADIUS)
            }
            end()
        }

        // Draw the numbers
        spriteBatch.begin()
        var i = 1
        for (dot in dots) {
            bitmapFont.draw(spriteBatch, i.toString(), dot.x + DOT_RADIUS, dot.y - DOT_RADIUS)
            i++
        }
        spriteBatch.end()

        // TODO: Start a batch with Shapetype.Line

        // TODO: Draw a polyline using the dot positions as a float array

        // TODO: End the batch


    }
}
