package com.udacity.gamedev.dragoncurve

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

/*

TODO: Start here

The Dragon Curve is a fractal made by a single line. It is formed of a series of turns, which can be constructed in the following way:

0: L
1: L + L + R
2: LLR + L + LRR
3: LLRLLRR + L + LLRRLRR

The nth dragon curve is the n-1th dragon curve plus L, plus the n-1th dragon curve reversed and reflected.

In this project we have split up the tasks of generating and drawing the dragon curve into separate classes.

 */

class DragonCurve : ApplicationAdapter() {

    private var dragonCurve: FloatArray? = null

    private lateinit  var shapeRenderer: ShapeRenderer

    override fun create() {
        dragonCurve = DragonCurveGenerator.generateDragonCurve(Gdx.graphics.width, Gdx.graphics.height, RECURSIONS)
        shapeRenderer = ShapeRenderer()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        shapeRenderer.begin(ShapeType.Line)
        shapeRenderer.polyline(dragonCurve)
        shapeRenderer.end()
    }

    companion object {
        // Any more than 10 and we'll need to break up the polyline into multiple lines
        private val RECURSIONS = 10
    }
}
