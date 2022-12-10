package solutions.day9

import processing.core.PApplet
import solutions.day9.logic.*

object Day9Part1Visualizer : PApplet() {
    private const val size = 1300
    private const val scale = 10

    private val logic = Part1Logic(Day9Input.moves)

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

        fill(0f, 255f, 255f)
        for(visitedPosition in visitedPositions) {
            val (x, y) = (visitedPosition + offset) * scale
            rect(x.toFloat(), y.toFloat(), scale.toFloat(), scale.toFloat())
        }

        ellipseMode(CORNER)
        val (tailX, tailY) = (logic.tailPosition + offset) * scale
        fill(0f, 0f, 255f)
        ellipse(tailX.toFloat(), tailY.toFloat(), scale.toFloat(), scale.toFloat())

        val (headX, headY) = (logic.headPosition + offset) * scale
        fill(255f, 0f, 0f)
        ellipse(headX.toFloat(), headY.toFloat(), scale.toFloat(), scale.toFloat())

        if (logic.sequence.hasNext()) {
            logic.sequence.next()
            visitedPositions.add(logic.tailPosition)
        }
    }

    fun run() {
        runSketch()
    }
}

fun main() = Day9Part1Visualizer.run()