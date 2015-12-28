package com.udacity.gamedev.icicles

import com.badlogic.gdx.Screen

class IciclesScreen: Screen {
    companion object {
        val TAG = IciclesScreen::class.java.toString()
    }

    // TODO: Add an ExtendViewport


    // TODO: Add a ShapeRenderer


    // TODO: Add an Icicle

    override fun show() {
        // TODO: Initialize the viewport using the world size constant


        // TODO: Initialize the ShapeRenderer


        // TODO: Set autoShapeType(true) on the ShapeRenderer


        // TODO: Create a new Icicle in the middle of the world
    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {
        // TODO: Ensure that the viewport updates correctly

    }

    override fun hide() {

    }

    override fun render(delta: Float) {
        // TODO: Apply the viewport


        // TODO: Clear the screen to the background color


        // TODO: Set the ShapeRenderer's projection matrix


        // TODO: Draw the Icicle
    }

    override fun resume() {

    }

    // TODO: Dispose of the ShapeRenderer
    override fun dispose() {

    }
}