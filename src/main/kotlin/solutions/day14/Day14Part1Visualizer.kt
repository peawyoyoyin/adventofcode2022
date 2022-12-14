package solutions.day14

import processing.core.PApplet
import solutions.day14.logic.Day14Input
import solutions.day14.logic.Part1Logic

object Day14Part1Visualizer : PApplet() {
    val logic = Part1Logic(Day14Input.rockLines)
    private const val scale = 5f
    private const val offsetX = -400f
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
            rect((x.toFloat() + offsetX) * scale, (y.toFloat() + offsetY) * scale, scale, scale)
        }

        fill(210)
        if (logic.current != null) {
            val (x, y) = logic.current!!
            rect((x.toFloat() + offsetX) * scale, (y.toFloat() + offsetY) * scale, scale, scale)
        }

        stroke(140f, 140f, 0f)
        fill(255f, 255f, 0f)
        if (logic.newSandPosition != null) {
            val (x, y) = logic.newSandPosition!!
            rect((x.toFloat() + offsetX) * scale, (y.toFloat() + offsetY) * scale, scale, scale)
        }

        for ((x, y) in logic.sands) {
            rect((x.toFloat() + offsetX) * scale, (y.toFloat() + offsetY) * scale, scale, scale)
        }

        if (logic.hasNext()) { logic.next() }
    }

    fun run() {
        runSketch()
    }
}

fun main() = Day14Part1Visualizer.run()