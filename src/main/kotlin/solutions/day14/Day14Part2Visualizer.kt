package solutions.day14

import processing.core.PApplet
import solutions.day14.logic.Day14Input
import solutions.day14.logic.Part2Logic

object Day14Part2Visualizer : PApplet() {
    val logic = Part2Logic(Day14Input.rockLines)
    private const val scaleX = 3f
    private const val scaleY = 6f
    private const val offsetX = -335f
    private const val offsetY = 0f

    init {
        setSize(1000, 1000)
    }

    override fun setup() {
        frameRate(240f)
    }

    override fun draw() {
        background(255)
        noStroke()

        fill(0)
        for ((x, y) in logic.rocks) {
            rect((x.toFloat() + offsetX) * scaleX, (y.toFloat() + offsetY) * scaleY, scaleX, scaleY)
        }

        fill(210)
        if (logic.current != null) {
            val (x, y) = logic.current!!
            rect((x.toFloat() + offsetX) * scaleX, (y.toFloat() + offsetY) * scaleY, scaleX, scaleY)
        }

        stroke(140f, 140f, 0f)
        fill(255f, 255f, 0f)
        if (logic.newSandPosition != null) {
            val (x, y) = logic.newSandPosition!!
            rect((x.toFloat() + offsetX) * scaleX, (y.toFloat() + offsetY) * scaleY, scaleX, scaleY)
        }

        for ((x, y) in logic.sands) {
            rect((x.toFloat() + offsetX) * scaleX, (y.toFloat() + offsetY) * scaleY, scaleX, scaleY)
        }

        if (logic.hasNext()) { logic.next() }
    }

    fun run() {
        runSketch()
    }
}

fun main() = Day14Part2Visualizer.run()