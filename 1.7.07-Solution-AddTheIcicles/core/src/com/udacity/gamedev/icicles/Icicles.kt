package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.badlogic.gdx.utils.Array
import java.util.*

class Icicles(viewport:Viewport) {
    companion object {
        val TAG = Icicle::class.java.toString()
    }

    // TODO: Add an array of icicles and a viewport

    var viewport:Viewport
    lateinit var iciclesArray:Array<Icicle>

    init {
        this.viewport = viewport
        init()
    }

    fun init() {
        // TODO: Initialize the array of icicles
        iciclesArray = Array<Icicle>(false, 100)
    }


    fun update(delta:Float) {
        // TODO: Replace hard-coded spawn rate with a constant
        if (MathUtils.random() < delta * Constants.ICICLE_SPAWNS_PER_SECOND) {
            // TODO: Add a new icicle at the top of the viewport at a random x position
            val randomX = Random().nextFloat()
            var p = Vector2()

            p.x = randomX * viewport.worldWidth
            p.y = viewport.worldHeight

            var icicle = Icicle(p)
            iciclesArray.add(icicle)
        }

        // TODO: Update each icicle
        for (icicle in iciclesArray) {
            icicle.update(delta)
        }

    }

    fun render(renderer:ShapeRenderer) {
        // TODO: Set ShapeRenderer Color
        renderer.color = Constants.ICICLE_COLOR

        renderer.set(ShapeRenderer.ShapeType.Filled)

        // TODO: Render each icicle
        for (icicle in iciclesArray)
            icicle.render(renderer)

    }
}
