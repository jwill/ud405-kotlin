package com.udacity.gamedev.drawinglines

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

/**
 * TODO: Start Here!

 * In this demo, we'll use ShapeRenderer to draw some lines! We'll use most of the line drawing
 * methods offered by ShapeRenderer, but remember to check out the Javadocs for the full story. If
 * you're lazy, you can just Google "LibGDX ShapeRenderer", and you'll find what you're looking
 * for!
 */

class DrawingLines : ApplicationAdapter() {

    lateinit var shapeRenderer: ShapeRenderer

    override fun create() {
        // Remember we want to create our ShapeRenderer outside of our render callback
        shapeRenderer = ShapeRenderer()
    }

    override fun dispose() {
        // Also remember to clean up
        shapeRenderer.dispose()
        super.dispose()
    }

    override fun render() {
        // As always, first we clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        // Then we start our shapeRenderer batch, this time with ShapeType.Line
        // You can call shapeRenderer explicitly
        shapeRenderer.begin(ShapeType.Line)
        // or you can use 'with' and Kotlin will figure out what to do
        with(shapeRenderer) {
            // A Simple white line
            color = Color.WHITE
            line(0f, 0f, 100f, 100f)
            // We can set different colors using two methods. We can use constants like so.
            color = Color.MAGENTA
            line(10f, 0f, 110f, 100f)
            // We can also set a color using RGBA values
            setColor(0f, 1f, 0f, 1f)
            line(20f, 0f, 120f, 100f)
            // We can also do fancy things like gradients
            line(30f, 0f, 130f, 100f, Color.BLUE, Color.RED)
            // The last interesting thing we can do is draw a bunch of connected line segments using polyline
            // First we set up the list of vertices, where the even positions are x coordinates, and the odd positions are the y coordinates
            val verticies = floatArrayOf(100f, 200f, 300f, 300f, 200f, 300f, 300f, 200f)
            polyline(verticies)
            // Finally, as always, we end the batch
            end()
        }
    }
}
