package com.udacity.gamedev.dragoncurve

import com.badlogic.gdx.math.Vector2

import java.util.Collections
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
            val reversed = LinkedList<Direction>()
            reversed.addAll(turns)
            Collections.reverse(reversed)

            // TODO: Add a left turn to turns
            turns.add(Direction.LEFT)

            // TODO: Add reflected version of reversed to turns
            for (turn in reversed) {
                when (turn) {
                    DragonCurveGenerator.Direction.LEFT -> turns.add(Direction.RIGHT)
                    DragonCurveGenerator.Direction.RIGHT -> turns.add(Direction.LEFT)
                }
            }
        }
        return turns
    }

    internal fun generateDragonCurve(width: Int, height: Int, recursions: Int): FloatArray {

        val turns = DragonCurveGenerator.dragonTurns(recursions)

        val head = Vector2((width / 2).toFloat(), (height / 2).toFloat())
        var heading = Vector2(5f, 0f)

        val curve = FloatArray((turns.size + 1) * 2)

        var i = 0
        curve[i++] = head.x
        curve[i++] = head.y

        //TODO: Convert the list of turns into the actual path
        for (turn in turns) {
            heading = Direction.turn(heading, turn)
            head.x += heading.x
            head.y += heading.y
            curve[i++] = head.x
            curve[i++] = head.y
        }

        return curve

    }


}
