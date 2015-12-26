package com.udacity.gamedev.circularmotion

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * TODO: Start here

 * In this demo, we'll explore how to create basic motion. Really all we need to know how to do is
 * find out how much time has passed since we started rendering the scene. And then figure out what
 * our scene should look like after that much time has passed.

 * To accomplish the first task, LibGDX provides a great TimeUtils class, which has two methods of
 * interest. TimeUtils.millis() returns the number of milliseconds (thousandths of a second) that
 * have passed since January 1, 1970 UTC. This is great if you want to know the wall-clock time, but
 * individual milliseconds start to matter when games are rendered at 60 frames per second.

 * If we want more precision when comparing times (which we generally do), we can use
 * TimeUtils.nanoTime(), which will give the number of nanoseconds (billionths of a second), since
 * (or until) some arbitrary reference time. Again, you can't tell the wall-clock time using
 * nanoTime. You can only tell time intervals, which is usually all we want.
 */


class CircularMotion : ApplicationAdapter() {

    lateinit var renderer: ShapeRenderer
    lateinit var viewport: FitViewport

    // We set up a variable to hold the nanoTime at which the application was created.
    private var initialTime: Long = 0

    override fun create() {
        renderer = ShapeRenderer()
        viewport = FitViewport(WORLD_SIZE, WORLD_SIZE)

        // Set the initialTime
        initialTime = TimeUtils.nanoTime()
    }

    override fun dispose() {
        renderer.dispose()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        viewport.apply()

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        renderer.projectionMatrix = viewport.camera.combined
        renderer.begin(ShapeType.Filled)

        val elapsedNanoseconds = (TimeUtils.nanoTime() - initialTime).toFloat()
        val elapsedSeconds = MathUtils.nanoToSec * elapsedNanoseconds
        val elapsedPeriods = elapsedSeconds / PERIOD
        val cyclePosition = elapsedPeriods % 1

        val x = WORLD_SIZE / 2 + MOVEMENT_RADIUS * MathUtils.cos(MathUtils.PI2 * cyclePosition)
        val y = WORLD_SIZE / 2 + MOVEMENT_RADIUS * MathUtils.sin(MathUtils.PI2 * cyclePosition)

        renderer.circle(x, y, CIRCLE_RADIUS)

        // Uncomment the next line to see the sort of beautiful things you can create with simple movement
        //         drawFancyCircles(renderer, elapsedPeriods, 20);
        renderer.end()
    }

    private fun drawFancyCircles(renderer: ShapeRenderer, elapsedPeriods: Float, circleCount: Int) {
        for (i in 1..circleCount) {
            val centerX = WORLD_SIZE / 2 + WORLD_SIZE / 4 * MathUtils.cos(MathUtils.PI2 * i / circleCount)
            val centerY = WORLD_SIZE / 2 + WORLD_SIZE / 4 * MathUtils.sin(MathUtils.PI2 * i / circleCount)

            val x = centerX + WORLD_SIZE / 5 * MathUtils.cos(MathUtils.PI2 * (elapsedPeriods * i / circleCount))
            val y = centerY + WORLD_SIZE / 5 * MathUtils.sin(MathUtils.PI2 * (elapsedPeriods * i / circleCount))

            renderer.circle(x, y, 10f)
        }
    }

    companion object {

        val TAG = CircularMotion::class.java.name
        private val WORLD_SIZE = 480.0f
        private val CIRCLE_RADIUS = WORLD_SIZE / 20
        private val MOVEMENT_RADIUS = WORLD_SIZE / 4

        // How many seconds until the circular motion repeats
        private val PERIOD = 1.0f
    }
}