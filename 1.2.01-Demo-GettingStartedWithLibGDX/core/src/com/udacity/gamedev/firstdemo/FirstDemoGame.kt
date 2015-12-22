package com.udacity.gamedev.firstdemo

import com.badlogic.gdx.Game

class FirstDemoGame : Game() {
    override fun create() { this.setScreen(UdacityScreen()) }
}