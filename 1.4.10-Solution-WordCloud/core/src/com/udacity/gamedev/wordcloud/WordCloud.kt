package com.udacity.gamedev.wordcloud

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * TODO: Start here

 * In this exercise, we'll create a word cloud. We've created all the infrastructure like the
 * SpriteBatch and BitmapFont. Now all you need to do is actually draw the random collection of
 * words we've generated.

 * You can find the Word class at the bottom of this file. It contains 5 fields:

 * x, y - Normalized position (meaning in the range 0-1), you'll want to multiply by
 * Gdx.graphics.getWidth() and Gdx.graphics.getHeight() as appropriate.

 * scale - The size of the text.

 * color - The color of the word.

 * letters - The actual letters in the world.

 * Jump to the TODOs below to see what you'll need to do.
 */

class WordCloud : ApplicationAdapter() {


    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont

    lateinit var words: Array<Word>

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()
        font.region.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear)
        words = generateWords(WORD_COUNT)
    }

    override fun dispose() {
        batch.dispose()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.begin()

        for (word in words) {

            // TODO: Set the font's scale using font.getData().setScale()
            // Note: font.data.scale WILL NOT work
			font.data.setScale(word.scale)

            // TODO: Set the font's tint using font.setColor()
			font.color = word.color

            // TODO: Actually draw the word using font.draw()
			font.draw(batch, word.letters, word.x * Gdx.graphics.width, word.y * Gdx.graphics.height)
        }
        batch.end()
    }

    fun generateWords(count: Int): Array<Word> {
        var newWords = Array<Word>()
        for (i in 0..count - 1) {
            newWords.add(randomWord(MIN_SCALE, MAX_SCALE))
        }
        return newWords
    }

    data class Word(var x: Float, var y: Float, var scale: Float, var color: Color, var letters: String)

	companion object {
            private val WORD_COUNT = 20
            private val MIN_SCALE = 0.5f
            private val MAX_SCALE = 5.0f
            private val WORDS = arrayOf("render-farm", "refrigerator", "tiger-team", "weathered", "camera", "tattoo", "boat", "soul-delay", "nodal point", "motion augmented", "reality neon", "nano-construct", "garage", "bicycle", "rebar tanto", "modem", "concrete RAF", "industrial grade media", "realism", "drone", "post-franchise shoes", "render-farm-ware", "DIY San Francisco", "rain lights", "numinous tank-traps", "pen drone", "cyber-cardboard", "denim monofilament", "order-flow", "smart-hotdog")

            fun randomWord(minScale: Float, maxScale: Float): Word {
                val x = MathUtils.random(-.25f, .75f)
                val y = MathUtils.random()
                val scale = minScale + (maxScale - minScale) * MathUtils.random()
                val color = Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1f)
                val letters = WORDS[MathUtils.random(WORDS.size - 1)]
                return Word(x, y, scale, color, letters)
            }
	}
}
