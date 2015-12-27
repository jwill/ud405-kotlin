package com.udacity.gamedev.fallingobjects

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.DelayedRemovalArray
import com.badlogic.gdx.utils.viewport.Viewport

import java.util.Random

class Avalanche {
    companion object {

        private val SPAWNS_PER_SECOND = 10f
    }

    var boulders = DelayedRemovalArray<Boulder>()

    fun update(delta: Float, viewport: Viewport) {
        val random = Random()
        if (random.nextFloat() < delta * SPAWNS_PER_SECOND) {
            boulders.add(Boulder(viewport))
        }

        boulders.begin()
        for (i in 0..boulders.size - 1) {
            val boulder = boulders.get(i)
            boulder.update(delta)
            if (boulder.isBelowScreen) {
                boulders.removeIndex(i)
            }
        }
        boulders.end()
    }

    fun render(renderer: ShapeRenderer) {
        for (boulder in boulders) {
            boulder.render(renderer)
        }
    }
}
