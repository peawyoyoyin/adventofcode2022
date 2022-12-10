package solutions.day9

import processing.core.PApplet
import solutions.day9.logic.*

object Day9Part2Visualizer : PApplet() {
    private const val size = 1300
    private const val scale = 10

    private val logic = Part2Logic(Day9Input.moves)

    private val offset = Position(size / (2*scale), size / (2*scale))

    private val visitedPositions = mutableSetOf<Position>()

    init {
        setSize(size, size)
    }

    override fun setup() {
        frameRate(12f)
        noStroke()
    }

    override fun draw() {
        background(255)

        fill(25f, 25f, 25f)
        for(visitedPosition in visitedPositions) {
            val (x, y) = (visitedPosition + offset) * scale
            rect(x.toFloat(), y.toFloat(), scale.toFloat(), scale.toFloat())
        }

        ellipseMode(CORNER)
        colorMode(HSB, 255f)
        for (i in (0..9).reversed()) {
            val (x, y) = (logic.knots[i] + offset) * scale

            fill(255f/(i+1), 255f, 255f)

            ellipse(x.toFloat(), y.toFloat(), scale.toFloat(), scale.toFloat())
        }
        colorMode(RGB, 255f)

        if (logic.hasNext()) {
            logic.next()
            visitedPositions.add(logic.knots[9])
        }
    }

    fun run() {
        runSketch()
    }
}

fun main() = Day9Part2Visualizer.run()