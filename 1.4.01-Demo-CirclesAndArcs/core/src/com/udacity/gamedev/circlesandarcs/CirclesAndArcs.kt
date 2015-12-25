package com.udacity.gamedev.circlesandarcs

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

/**
 * TODO: Start here!

 * Everything in this class should seem pretty familiar. We're just creating a ShapeRenderer, and
 * asking it to draw us some shapes. The interesting new things are, one, drawing circles and arcs
 * (partial circles), and two, using multiple ShapeRenderer batches!

 * Circles can be drawn using either ShapeType.Filled, or ShapeType.Line. The former draws a solid
 * circle, the latter draws just the outline of the circle.

 * If we want to draw both filled and outlined circles in the same frame, we need to use two
 * different batches. We just start one batch with ShapeType.Filled, draw our shapes, and end the
 * batch. Then we start another batch, this time with ShapeType.Line, draw again, and again,
 * remember to end the batch.

 * Circles have a dirty little secret though. They're not really circles. They're a fan of
 * triangles, where all the triangles have their points at the center of the circle. ShapeRenderer
 * will pick how many triangles to use so the circle looks smooth, but if you make super small
 * circles, it might choose to use too few. You can use the optional last parameter to bump up the
 * number of segments.
 */

class CirclesAndArcs : ApplicationAdapter() {

    lateinit var renderer: ShapeRenderer

    override fun create() {
        renderer = ShapeRenderer()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        renderer.begin(ShapeType.Filled)
        renderer.color = Color.WHITE

        // The most basic circle you can draw, with the segment count set for you
        renderer.circle(100f, 100f, 90f)
        renderer.color = Color.YELLOW

        // We can also draw partial circle, or arc
        renderer.arc(300f, 100f, 90f, 45f, 270f)

        // What happens when we set the segments count too low
        renderer.circle(500f, 100f, 90f, 10)
        renderer.end()

        // Circles can be drawn in either Filled or Line mode!
        renderer.begin(ShapeType.Line)
        renderer.circle(100f, 300f, 90f)

        // Let's draw target rings
        var radius = 80
        while (radius > 0) {
            renderer.circle(100f, 300f, radius.toFloat())
            radius -= 10
        }

        // We can also draw the outline of an arc
        renderer.arc(300f, 300f, 90f, 0f, 90f)

        // Let's draw some a funky snail shell
        val arcs = 20
        for (i in 1..arcs - 1) {
            renderer.arc(300f, 300f, (1 - 1.0f * i / arcs) * 90, 360.0f * i / arcs, 90f)
        }
        renderer.end()
    }
}
