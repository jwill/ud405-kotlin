package com.udacity.gamedev.dragoncurve

import com.badlogic.gdx.math.Vector2

import java.util.LinkedList

object DragonCurveGenerator {

    internal enum class Direction {
        LEFT,
        RIGHT;


        companion object {

            fun turn(heading: Vector2, turn: Direction): Vector2 {
                val newHeading = Vector2()
                when (turn) {
                    LEFT -> {
                        newHeading.x = -heading.y
                        newHeading.y = heading.x
                    }
                    RIGHT -> {
                        newHeading.x = heading.y
                        newHeading.y = -heading.x
                    }
                }
                return newHeading
            }
        }
    }

    internal fun dragonTurns(recursions: Int): LinkedList<Direction> {
        val turns = LinkedList<Direction>()
        turns.add(Direction.LEFT)

        for (i in 0..recursions - 1) {
            // TODO: Create a reversed copy of turns


            // TODO: Add a left turn to turns


            // TODO: Add reflected version of reversed to turns

        }
        return turns
    }

    internal fun generateDragonCurve(width: Int, height: Int, recursions: Int): FloatArray {

        val turns = DragonCurveGenerator.dragonTurns(recursions)

        val head = Vector2((width / 2).toFloat(), (height / 2).toFloat())
        val heading = Vector2(5f, 0f)

        val curve = FloatArray((turns.size + 1) * 2)

        var i = 0
        curve[i++] = head.x
        curve[i++] = head.y

        //TODO: Convert the list of turns into the actual path


        return curve

    }


}
