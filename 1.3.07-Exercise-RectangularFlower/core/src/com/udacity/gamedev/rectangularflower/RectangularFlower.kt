package com.udacity.gamedev.rectangularflower

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

/**
 * TODO: Start Here

 * In this exercise, we're going to draw a flower using only rectangles! We've already started off
 * with a green rectline for the stem. First you'll draw a couple leaves using rotated rectangles.

 * Then you'll draw the head of the flower by drawing a a bunch of rotated squares!
 */

class RectangularFlower : ApplicationAdapter() {

    lateinit  var shapeRenderer: ShapeRenderer

    override fun create() {
        shapeRenderer = ShapeRenderer()
    }

    override fun dispose() {
        super.dispose()
        shapeRenderer.dispose()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        with(shapeRenderer) {
            begin(ShapeType.Filled)
            color = Color.GREEN
            rectLine(100f, 0f, 100f, 300f, 20f)

            // TODO: Draw two leaves on the stem

            // TODO: Set the active color to yellow

            // TODO: Use a loop to draw 20 of these petals in a circle

            val petalAngle = 45.0f
            rect(100f, 300f, 0f, 0f, 40f, 40f, 1f, 1f, petalAngle)

            end()
        }
    }
}
