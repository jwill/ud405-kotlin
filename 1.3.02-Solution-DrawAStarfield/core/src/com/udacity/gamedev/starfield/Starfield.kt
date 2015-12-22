package com.udacity.gamedev.starfield

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array

import java.util.Random

/**
 * TODO: Start here!

 * In this exercise we'll draw a star field of white points on a black background. The number of
 * points will be defined by a density parameter that states what proportion of the pixels should be
 * white.

 * TODO: Run what you've got before making any changes

 * One thing to note is we're using two new LibGDX classes, Array, and Vector2. We're using a custom
 * Array type so LibGDX can control the memory, and avoid unfortunate garbage collection events.
 * Vector2 is a super simple class for holding a 2D position. You can find more information in the
 * LibGDX Javadocs, or just by right clicking on the class name, and selecting Go To > Declaration.

 * One new utility class we'll be using in this exercise is com.badlogic.gdx.math.Vector2. You can
 * find more information in the LibGDX Javadocs.

 * Remember you can set up a Desktop run configuration using the dropdown in the toolbar, or you can
 * open the terminal at the bottom of the screen and run

 * $ ./gradlew desktop:run
 */


class Starfield : ApplicationAdapter() {
    lateinit var shapeRenderer: ShapeRenderer
    lateinit var stars: Array<Vector2>
    private val STAR_DENSITY = 0.01f

    override fun create() {
        shapeRenderer = ShapeRenderer()
        initStars(0.9f)

    }

    fun initStars(density: Float) {
        // TODO: Figure out how many stars to draw. You'll need the screen dimensions, which you can get using Gdx.graphics.getWidth() and Gdx.graphics.getHeight().
        val width = Gdx.graphics.getWidth()
        val height = Gdx.graphics.getHeight()
        // TODO: Create a new array of Vector2's to hold the star positions
        val size = (10000 * density).toInt()
        stars = Array<Vector2>(size)
        // TODO: Use java.util.Random to fill the array of star positions
        val r = Random()
        for (i in 0..size - 1) {
            val x = r.nextFloat() * width
            val y = r.nextFloat() * height
            val point = Vector2(x, y)
            stars.add(point)
        }

    }

    override fun render() {
        // TODO: Make the night sky black
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // TODO: Begin a shapeRenderer batch using ShapeType.Point
        with(shapeRenderer) {
            setAutoShapeType(true)
            begin()
            // TODO: Loop through the star positions and use shapeRenderer to draw points
            for (p in stars) {
                point(p.x, p.y, 0f)
            }
            // TODO: End the shapeRenderer batch
            end()
        }
    }

    override fun dispose() {
        // TODO: Dispose of our ShapeRenderer
        super.dispose()
    }
}
