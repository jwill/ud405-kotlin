package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

class Icicle(position: Vector2) {
    companion object {
        val TAG = Icicle::class.java.toString()
    }


    // TODO: Add a Vector2 position
    var position:Vector2

    // TODO: Add a constructor that sets the position
    init {
        this.position = position
    }

    // TODO: Add a render function that takes a ShapeRenderer
    fun render(renderer:ShapeRenderer) {
        // TODO: Set the ShapeRenderer's color
        renderer.color = Constants.ICICLE_COLOR
        // TODO: Set the ShapeType
        renderer.set(ShapeRenderer.ShapeType.Filled)
        // TODO: Draw the icicle using the size constants
        renderer.triangle(position.x, position.y,
                position.x - Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT,
                position.x + Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT )
    }
}