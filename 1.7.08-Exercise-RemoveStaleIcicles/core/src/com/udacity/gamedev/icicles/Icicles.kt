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

    var viewport:Viewport
    lateinit var iciclesArray:Array<Icicle>

    init {
        this.viewport = viewport
        init()
    }

    fun init() {
        // TODO: Initialize the DelayedRemovalArray
        iciclesArray = Array<Icicle>(false, 100)
    }


    fun update(delta:Float) {
        if (MathUtils.random() < delta * Constants.ICICLE_SPAWNS_PER_SECOND) {
            val randomX = Random().nextFloat()
            var p = Vector2()

            p.x = randomX * viewport.worldWidth
            p.y = viewport.worldHeight

            var icicle = Icicle(p)
            iciclesArray.add(icicle)
        }

        for (icicle in iciclesArray) {
            icicle.update(delta)
        }

        // TODO: begin a removal session


        // TODO: Remove any icicle completely off the bottom of the screen


        // TODO: End removal session
    }

    fun render(renderer:ShapeRenderer) {
        renderer.color = Constants.ICICLE_COLOR
        renderer.set(ShapeRenderer.ShapeType.Filled)

        for (icicle in iciclesArray)
            icicle.render(renderer)

    }
}