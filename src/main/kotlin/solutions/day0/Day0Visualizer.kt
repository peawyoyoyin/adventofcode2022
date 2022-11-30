package solutions.day0

import processing.core.PApplet

object Day0Visualizer : PApplet() {
    init {
        setSize(1000, 1000)
    }

    override fun setup() {
        frameRate(60f)
    }

    override fun draw() {
        background(255)
        textFont(createFont("Arial", 28f))
        fill(0)
        text("hello world", 200f, 200f)
    }

    fun run() {
        runSketch()
    }
}

fun main() {
    Day0Visualizer.run()
}