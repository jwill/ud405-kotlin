package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.Viewport

class Icicles(viewport:Viewport) {
    companion object {
        val TAG = Icicle::class.java.toString()
    }

    // TODO: Add an array of icicles and a viewport

    var viewport:Viewport

    init {
        this.viewport = viewport
        init()
    }

    fun init() {
        // TODO: Initialize the array of icicles
    }


    fun update(delta:Float) {
        // TODO: Replace hard-coded spawn rate with a constant
        if (MathUtils.random() < delta * 5) {
            // TODO: Add a new icicle at the top of the viewport at a random x position
        }

        // TODO: Update each icicle

    }

    fun render(renderer:ShapeRenderer) {
        // TODO: Set ShapeRenderer Color


        // TODO: Render each icicle

    }
}