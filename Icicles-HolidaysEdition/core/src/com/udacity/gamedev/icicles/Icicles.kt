package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.DelayedRemovalArray
import java.util.*

class Icicles(viewport:Viewport, difficulty: Constants.Difficulty) {
    companion object {
        val TAG = Icicle::class.java.toString()
    }

    // TODO: Add a Difficulty
    var difficulty: Constants.Difficulty

    var iciclesDodged = 0
    var viewport:Viewport
    lateinit var iciclesArray:DelayedRemovalArray<Icicle>

    init {
        // TODO: Set difficulty
        this.difficulty = difficulty

        this.viewport = viewport
        init()
    }

    fun init() {
        iciclesArray = DelayedRemovalArray<Icicle>(false, 100)
        iciclesDodged = 0
    }


    fun update(delta:Float) {
        // TODO: Use the difficulty's spawn rate
        if (MathUtils.random() < delta * difficulty.spawnRate) {
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

        iciclesArray.begin()

        for (icicle in iciclesArray) {
            if (icicle.position.y < 0) {
                iciclesDodged++
                iciclesArray.removeValue(icicle, true)
            }
        }

        iciclesArray.end()
    }

    fun render(renderer:ShapeRenderer) {
        renderer.color = Constants.ICICLE_COLOR
        renderer.set(ShapeRenderer.ShapeType.Filled)

        for (icicle in iciclesArray)
            icicle.render(renderer)

    }

    fun render(batch:SpriteBatch) {
        for (icicle in iciclesArray)
            icicle.render(batch)
    }
}