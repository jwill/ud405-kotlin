package com.udacity.gamedev.drawthecantorgasket

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Rectangle

/*

TODO: Start here

The Cantor gasket is a fractal where we start with a white square. We devide that square up into a 3x3 grid of smaller squares, then remove the middle square. Finally, we repeat the process on each of the remaining 8 squares.

 */

class DrawTheCantorGasket : ApplicationAdapter() {

    lateinit  var shapeRenderer: ShapeRenderer
    // TODO: Set a constant for how many recursions to draw
    private val RECURSIONS = 5

    override fun create() {
        shapeRenderer = ShapeRenderer()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        val bounds = findLargestSquare()

        with(shapeRenderer) {
            // TODO: Begin a filled shapeRenderer batch
            begin(ShapeType.Filled)

            // TODO: Draw a white square matching the bounds
            color = Color.WHITE
            rect(bounds.x, bounds.y, bounds.width, bounds.height)

            // TODO: Set the working color to black, and call punchCantorGasket with the bounds
            color = Color.BLACK
            punchCantorGasket(bounds.x, bounds.y, bounds.width, RECURSIONS)

            // TODO: End the batch
            end()
        }
    }

    private fun punchCantorGasket(x: Float, y: Float, size: Float, recursions: Int) {
        // TODO: Base case, if recursions = 0, return
        if (recursions == 0) {
            return
        }
        // TODO: Draw a black square in the middle third
        shapeRenderer.rect(x + size / 3, y + size / 3, size / 3, size / 3)

        // TODO: Call punchCantorGasket on all the other thirds
        val newSize = size / 3

        punchCantorGasket(x, y, newSize, recursions - 1)
        punchCantorGasket(x + newSize, y, newSize, recursions - 1)
        punchCantorGasket(x + 2 * newSize, y, newSize, recursions - 1)
        punchCantorGasket(x, y + newSize, newSize, recursions - 1)
        punchCantorGasket(x + 2 * newSize, y + newSize, newSize, recursions - 1)
        punchCantorGasket(x, y + 2 * newSize, newSize, recursions - 1)
        punchCantorGasket(x + newSize, y + 2 * newSize, newSize, recursions - 1)
        punchCantorGasket(x + 2 * newSize, y + 2 * newSize, newSize, recursions - 1)
    }

    private fun findLargestSquare(): Rectangle {
        val largestSquare = Rectangle()
        val screenWidth = Gdx.graphics.width.toFloat()
        val screenHeight = Gdx.graphics.height.toFloat()

        if (screenWidth > screenHeight) {
            largestSquare.x = (screenWidth - screenHeight) / 2
            largestSquare.y = 0f
            largestSquare.width = screenHeight
            largestSquare.height = screenHeight
        } else {
            largestSquare.x = 0f
            largestSquare.y = (screenHeight - screenWidth) / 2
            largestSquare.width = screenWidth
            largestSquare.height = screenWidth
        }
        return largestSquare
    }
}
