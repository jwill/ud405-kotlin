package com.udacity.gamedev.orthographiccamera

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.TimeUtils


/**
 * TODO: Start here

 * In this exercise, you'll create an OrthographicCamera, and use it to zoom in on a moving circle
 */
class OrthographicCameraExercise : ApplicationAdapter() {

    lateinit var renderer: ShapeRenderer
	var timeCreated: Long = 0
    lateinit var camera: OrthographicCamera

    //TODO: Declare an OrthographicCamera


    override fun create() {
        renderer = ShapeRenderer()
        timeCreated = TimeUtils.millis()

        // TODO: Initialize the camera
        camera = OrthographicCamera()

        // TODO: Set the camera's position to the center of the circle's movement (X_CENTER, Y_CENTER)
        camera.position.set(X_CENTER, Y_CENTER, 0f)
    }

    override fun dispose() {
        renderer.dispose()
    }

    override fun resize(width: Int, height: Int) {

        // Calculate the aspect ratio (width / height)
        val aspectRatio = Gdx.graphics.width / Gdx.graphics.height

        // Set the camera's viewport height taking into account the ball's movement and radius
        // Added 50 units to mak sure ball stays on the screen
        camera.viewportHeight = 2*( Y_AMPLITUDE + BALL_RADIUS) + 50

        // Set the camera's viewport width to maintain the aspect ratio
        camera.viewportWidth = aspectRatio * camera.viewportHeight

    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // Call update() on the camera
        camera.update()


        // Set the SceneRenderer's projection matrix equal to the camera's combined matrix
        renderer.setProjectionMatrix(camera.combined)

        renderer.begin(ShapeType.Filled)
        val interval = TimeUtils.timeSinceMillis(timeCreated).toFloat()
        val x = X_CENTER + X_AMPLITUDE * MathUtils.sin(MathUtils.PI2 * interval / PERIOD)
        val y = Y_CENTER + Y_AMPLITUDE * MathUtils.sin(2f * MathUtils.PI2 * interval / PERIOD)
        renderer.circle(x, y, BALL_RADIUS)
        renderer.end()
    }

    companion object {

        private val BALL_RADIUS = 20f
        private val PERIOD = 2000f
        private val X_AMPLITUDE = 40f
        private val Y_AMPLITUDE = 20f
        private val X_CENTER = 100f
        private val Y_CENTER = 100f
    }
}