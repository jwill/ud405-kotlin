package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

class Icicle(position: Vector2) {
    companion object {
        val TAG = Icicle::class.java.toString()
    }

    var position:Vector2

    var velocity:Vector2
    var texture:Texture

    init {
        this.position = position
        velocity = Vector2()
        texture = Texture("foliageTree_02.png")
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

    fun render(batch: SpriteBatch) {
        batch.draw(texture, position.x, position.y, Constants.ICICLE_WIDTH * 1.2f, Constants.ICICLE_HEIGHT * 1.2f)
    }
}