package com.udacity.gamedev.drawingrectangles

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

/**
 * TODO: Start here!

 * In this demo, we're going to use ShapeRenderer to draw some rectangles! The API for drawing
 * rectangles gets a little complicated, just because there are so many options. As always, the
 * Javadocs are super useful.

 * Also, if you want to figure out what the arguments are for a particular invocation of a function,
 * hold Command and mouse over it!

 * One tricky argument to figure out is the origin. That's only relevant when we're using one of the
 * versions of rect that can be rotated and scaled, and it determined about which point the rotation
 * and scaling is going to happen.

 * In addition to ShapeRenderer.rect(), we're also going to explore ShapeRenderer.rectLine(). One of
 * the quirks of OpenGL is that it knows how to draw filled shapes, and it knows how to draw lines
 * that are a single pixel wide, but it doesn't know how to draw thick lines. To get around this
 * restriction, we can make a thick line out of a skinny filled in rectangle.
 */


class DrawingRectangles : ApplicationAdapter() {

    private lateinit  var shapeRenderer: ShapeRenderer

    override fun create() {
        shapeRenderer = ShapeRenderer()
    }

    override fun dispose() {
        super.dispose()
        shapeRenderer.dispose()
    }

    override fun render() {
        // As I'm sure you're used to by now, we always have to clear the screen first
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        with(shapeRenderer) {
            // Rectangles can be drawn with either ShapeType.Filled or ShapeType.Line
            begin(ShapeType.Filled)
            color = Color.WHITE

            // Let's just draw a simple square to start
            rect(10f, 10f, 90f, 90f)

            // We can do even more interesting things with colors, like specifying a color for each corner!
            rect(110f, 10f, 90f, 90f, Color.BLUE, Color.BLACK, Color.GREEN, Color.MAGENTA)
            rect(10f, 110f, 90f, 90f, Color.RED, Color.RED, Color.BLACK, Color.BLACK)

            // What happens when we draw two filled in shapes where they overlap?
            rect(210f, 10f, 90f, 90f, Color.RED, Color.RED, Color.RED, Color.RED)
            rect(230f, 30f, 90f, 90f, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN)

            // We can also rotate and scale rectangles!
            // We can put the rotation origin on the corner
            color = Color.YELLOW
            rect(10f, 300f, 50f, 50f, 100f, 100f, 0.5f, 1f, 45f)
            color = Color.GREEN
            rect(10f, 300f, 50f, 50f, 100f, 100f, 0.5f, 1f, 135f)

            // Or we can put the rotation origin in the center
            color = Color.YELLOW
            rect(200f, 300f, 0f, 0f, 100f, 100f, 0.5f, 1f, 45f)
            color = Color.GREEN
            rect(200f, 300f, 0f, 0f, 100f, 100f, 0.5f, 1f, 225f)

            // Let's try making a thick line
            color = Color.PURPLE
            rectLine(0f, 200f, 200f, 250f, 10f)
        }
        // Alright, time for some silliness. Let's make a rainbow flower
        val steps = 25
        val rgbColor = Color()
        for (i in 0..steps - 1) {
            // This mess converts from a position on the rainbow to an RGB color
            Color.argb8888ToColor(rgbColor, java.awt.Color.HSBtoRGB(1.0f * i / steps, 1f, 1f))

            // Each rectangle is a little bit rotated from the previous one
            shapeRenderer.rect(300f, 300f, 50f, 50f, 100f, 100f, 1f, 1f, (i * 90 / steps).toFloat(), rgbColor, rgbColor, rgbColor, rgbColor)
        }

        // Always remember to end your batches!
        shapeRenderer.end()
    }
}
