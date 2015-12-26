package com.udacity.gamedev.viewportsdemo

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.utils.viewport.*


/**
 * TODO: Start Here!

 * In this demo we'll explore the effect of using a Viewport to manage a camera.

 * We start with a world containing a 16x9 checkerboard, with an apron of neon green.
 */


class ViewportsDemo : ApplicationAdapter() {

    lateinit var camera: OrthographicCamera
    lateinit var viewport: Viewport

    lateinit var renderer: ShapeRenderer

    /**
     * Uncomment the following viewports one at a time, and check out the effect when you resize the desktop window.
     */
    override fun create() {
        camera = OrthographicCamera()

        // Makes the size of the world match the size of the screen
        viewport = ScreenViewport(camera)

        // Make the world fill the screen, regardless of aspect ratio
        //        viewport = StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)

        // Make the world fill the screen, maintaining aspect ratio, but bits of the world may be cut off
        //        viewport = FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)

        // Fit the world inside the screen, adding black bars to pad out the extra space, maintaining aspect ratio
        //        viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)

        // Make the short axis of the world larger to fill the screen, maintaining aspect ratio
        //        viewport = ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)


        viewport.setScreenBounds(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        renderer = ShapeRenderer()
    }

    override fun dispose() {
        renderer.dispose()
    }

    /**
     * When the screen is resized, we need to inform the viewport. Note that when using an
     * ExtendViewport, the world size might change as well.
     */
    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        Gdx.app.log(TAG, "Viewport world dimensions: (" + viewport.worldHeight + ", " + viewport.worldWidth + ")")
    }

    /**
     * When using a viewport, instead of calling camera.update(), we just call viewport.apply()
     */
    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        with(renderer) {
            viewport.apply()
            projectionMatrix = camera.combined
            begin(ShapeType.Filled)
            color = Color.GREEN
            rect(-10f, -10f, WORLD_WIDTH + 20, WORLD_HEIGHT + 20)
            renderWorld()
            end()
        }
    }

    internal fun renderWorld() {
        var yStart = 0
        while (yStart < WORLD_HEIGHT) {
            var xStart = 0
            while (xStart < WORLD_WIDTH) {
                if ((yStart + xStart) % 2 == 0) {
                    renderer.color = Color.WHITE
                } else {
                    renderer.color = Color.BLACK
                }
                renderer.rect(xStart.toFloat(), yStart.toFloat(), 1f, 1f)
                xStart += 1
            }
            yStart += 1
        }
    }

    companion object {

        val TAG = ViewportsDemo::class.java.name

        private val WORLD_WIDTH = 16f
        private val WORLD_HEIGHT = 9f
    }
}