package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.DelayedRemovalArray
import java.util.*

class Icicles(viewport:Viewport) {
    companion object {
        val TAG = Icicle::class.java.toString()
    }

    // TODO: Add a Difficulty

    var iciclesDodged = 0
    var viewport:Viewport
    lateinit var iciclesArray:DelayedRemovalArray<Icicle>

    init {
        // TODO: Set difficulty


        this.viewport = viewport
        init()
    }

    fun init() {
        iciclesArray = DelayedRemovalArray<Icicle>(false, 100)
        iciclesDodged = 0
    }


    fun update(delta:Float) {
        // TODO: Use the difficulty's spawn rate
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
        iciclesArray.begin()


        // TODO: Remove any icicle completely off the bottom of the screen
        for (icicle in iciclesArray) {
            if (icicle.position.y < 0) {
                iciclesDodged++
                iciclesArray.removeValue(icicle, true)
            }
        }

        // TODO: End removal session
        iciclesArray.end()
    }

    fun render(renderer:ShapeRenderer) {
        renderer.color = Constants.ICICLE_COLOR
        renderer.set(ShapeRenderer.ShapeType.Filled)

        for (icicle in iciclesArray)
            icicle.render(renderer)

    }
}