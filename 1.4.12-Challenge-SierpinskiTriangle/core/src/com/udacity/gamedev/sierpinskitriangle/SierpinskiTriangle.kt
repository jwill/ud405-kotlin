package com.udacity.gamedev.sierpinskitriangle

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20

/**
 * TODO: Start here

 * Your challenge, should you choose to accept it, is to draw a Serpinski Triangle. I offer no hints
 * beyond the fact that ShapeRenderer has a very convenient triangle() function, and that using a
 * FitViewport can simplify matters considerably. Good luck!
 */


class SierpinskiTriangle : ApplicationAdapter() {

    override fun create() {

    }

    override fun dispose() {

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    }

    companion object {

        private val SIZE = 10f
        private val RECURSIONS = 7
    }


}
