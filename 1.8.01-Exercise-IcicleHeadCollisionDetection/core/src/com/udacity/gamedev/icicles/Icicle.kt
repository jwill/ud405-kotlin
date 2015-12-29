package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

class Icicle(position: Vector2) {
    companion object {
        val TAG = Icicle::class.java.toString()
    }

    var position:Vector2

    var velocity:Vector2

    init {
        this.position = position
        velocity = Vector2()
    }

    fun update(delta:Float) {
        velocity.mulAdd(Constants.ICICLES_ACCELERATION, delta)
        position.mulAdd(velocity, delta)

    }

    fun render(renderer:ShapeRenderer) {
        renderer.triangle(position.x, position.y,
                position.x - Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT,
                position.x + Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT )
    }
}