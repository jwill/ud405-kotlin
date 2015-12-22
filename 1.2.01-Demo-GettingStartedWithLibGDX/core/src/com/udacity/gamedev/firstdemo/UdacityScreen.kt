package com.udacity.gamedev.firstdemo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.*
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.DelayedRemovalArray


class UdacityScreen : Screen, InputAdapter() {
    lateinit var batch:SpriteBatch
    lateinit var logo:Texture
    lateinit var touchEffectPool:ParticleEffectPool
    var effects = DelayedRemovalArray<PooledEffect>()

    private val UDACITY_ORANGE = Color(228.0f / 225.0f, 127.0f / 225.0f, 57.0f / 225.0f, 1.0f)
    private val UDACITY_BLUE = Color(36.0f / 225.0f, 73.0f / 225.0f, 96.0f / 225.0f, 1.0f)
    private val LOGO_WIDTH = 200.0f
    private var logoHeight: Float = 0.toFloat()

    override fun show() {
        batch = SpriteBatch()
        logo = Texture("udacity_logo_white.png")
        logoHeight = logo.height * LOGO_WIDTH / logo.width

        val touchEffect = ParticleEffect()
        touchEffect.load(Gdx.files.internal("UdacityEmitter.p"), Gdx.files.internal(""))
        touchEffect.setEmittersCleanUpBlendFunction(false)
        touchEffectPool = ParticleEffectPool(touchEffect, 1, 2)
        Gdx.input.inputProcessor = this
    }

    override fun render(delta:Float){
        // TODO: Make this UDACITY_BLUE instead
        clearScreen(UDACITY_ORANGE)
        batch.begin()
        effects.begin()
        batch.draw(logo,
                (Gdx.graphics.width - LOGO_WIDTH) / 2,
                (Gdx.graphics.height - logoHeight) / 2,
                LOGO_WIDTH,
                logoHeight)
        for (effect in effects) {
            effect.draw(batch, delta)
            if (effect.isComplete) {
                effect.free()
                effects.removeValue(effect, true)
            }
        }
        effects.end()
        batch.end()
    }

    private fun clearScreen(color: Color) {
        with(Gdx.gl) {
            with(color) {
                glClearColor(r, g, b, a)
                glClear(GL20.GL_COLOR_BUFFER_BIT)
            }
        }
    }

    private fun spawnParticleEffect(x:Int, y:Int) {
        var effect = touchEffectPool.obtain()
        effect.setPosition(x.toFloat(), (Gdx.graphics.getHeight() - y).toFloat() )
        effects.add(effect)
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        spawnParticleEffect(screenX, screenY)
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        spawnParticleEffect(screenX, screenY)
        return false
    }

    override fun dispose() {
        batch.dispose()
        logo.dispose()
        for (effect in effects)
            effect.free()
        effects.clear()
    }

    override fun hide() { }

    override fun pause() { }

    override fun resize(width: Int, height: Int) { }

    override fun resume() { }
}
