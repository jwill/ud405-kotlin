package com.udacity.gamedev.sierpinskitriangle

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * TODO: Start here

 * Your challenge, should you choose to accept it, is to draw a Serpinski Triangle. I offer no hints
 * beyond the fact that ShapeRenderer has a very convenient triangle() function, and that using a
 * FitViewport can simplify matters considerably. Good luck!
 */


class SierpinskiTriangle : ApplicationAdapter() {
    lateinit var renderer: ShapeRenderer
    lateinit var viewport: FitViewport

    override fun create() {
        renderer = ShapeRenderer()
        viewport = FitViewport(SIZE, SIZE)
    }

    override fun dispose() {
        renderer.dispose()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        viewport.apply()
        renderer.projectionMatrix = viewport.camera.combined
        renderer.begin(ShapeType.Filled)
        inscribeSierpinskiTriangle(renderer, SIZE, RECURSIONS)
        renderer.end()
    }

    private fun inscribeSierpinskiTriangle(shapeRenderer: ShapeRenderer, size: Float, recursions: Int) {
        val corner1 = Vector2(0f, 0f)
        val corner2 = Vector2(size, 0f)
        val corner3 = Vector2(size / 2, size)
        drawSierpinskiTriangle(shapeRenderer, corner1, corner2, corner3, recursions)
    }

    private fun drawSierpinskiTriangle(shapeRenderer: ShapeRenderer, corner1: Vector2, corner2: Vector2, corner3: Vector2, recursions: Int) {

        val midpoint12 = Vector2((corner1.x + corner2.x) / 2, (corner1.y + corner2.y) / 2)
        val midpoint23 = Vector2((corner2.x + corner3.x) / 2, (corner2.y + corner3.y) / 2)
        val midpoint31 = Vector2((corner3.x + corner1.x) / 2, (corner3.y + corner1.y) / 2)

        if (recursions == 1) {
            shapeRenderer.triangle(corner1.x, corner1.y, midpoint12.x, midpoint12.y, midpoint31.x, midpoint31.y)
            shapeRenderer.triangle(corner2.x, corner2.y, midpoint23.x, midpoint23.y, midpoint12.x, midpoint12.y)
            shapeRenderer.triangle(corner3.x, corner3.y, midpoint31.x, midpoint31.y, midpoint23.x, midpoint23.y)
        } else {
            drawSierpinskiTriangle(shapeRenderer, corner1, midpoint12, midpoint31, recursions - 1)
            drawSierpinskiTriangle(shapeRenderer, corner2, midpoint23, midpoint12, recursions - 1)
            drawSierpinskiTriangle(shapeRenderer, corner3, midpoint31, midpoint23, recursions - 1)
        }

    }

    companion object {

        private val SIZE = 10f
        private val RECURSIONS = 7
    }


}