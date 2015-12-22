package com.udacity.gamedev.drawaspiral

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2

/**
 * TODO: Start here

 * In this exercise we have a project that draws a number of concentric rectangles as specified in
 * the COILS constant. The space between the rectangles is given by xStep and yStep.

 * The rectangles are drawn using four lines between five points. Your task is to adjust the first
 * and last point such that each rectangle turns into a coil that meets up with the neighboring
 * coils inside and outside of it.
 */

class DrawASpiral : ApplicationAdapter() {
    // How many rectangles/coils to draw
    private val COILS = 20
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

        shapeRenderer.begin(ShapeType.Line)

        val screenWidth = Gdx.graphics.width
        val screenHeight = Gdx.graphics.height
        val xStep = screenWidth / 2 / COILS
        val yStep = screenHeight / 2 / COILS

        for (i in 0..COILS - 1) {

            val xOffset = xStep * i
            val yOffset = yStep * i

            // TODO: Make this coil reach back to the outer coil
            val point1 = Vector2(xOffset.toFloat(), yOffset.toFloat())
            val point2 = Vector2((screenWidth - xOffset).toFloat(), yOffset.toFloat())
            val point3 = Vector2((screenWidth - xOffset).toFloat(), (screenHeight - yOffset).toFloat())
            val point4 = Vector2(xOffset.toFloat(), (screenHeight - yOffset).toFloat())

            // TODO: Make this coil stop before connecting back to itself
            val point5 = Vector2(xOffset.toFloat(), yOffset.toFloat())

            shapeRenderer.line(point1, point2)
            shapeRenderer.line(point2, point3)
            shapeRenderer.line(point3, point4)
            shapeRenderer.line(point4, point5)
        }
        shapeRenderer.end()
    }
}

// TODO: Challenge - Add truncated corners to the spiral
