package solutions.day10

import processing.core.PApplet
import processing.core.PFont

object Day10Visualizer : PApplet() {
    init {
        setSize(1000, 400)
    }
    private lateinit var font: PFont
    private lateinit var smallFont: PFont

    private val cpu = CPU(Day10Input.instructions)

    private val litPixels = mutableListOf<Pair<Int, Int>>()
    // HUD Text
    private const val hudOffsetY = 50f
    private const val hudOffsetX = 50f

    private const val hudCycleTextWidth = 150f
    private const val hudXTextWidth = 200f

    // CRT display
    private const val cellSize = 20f
    private const val crtOffsetY = 80f
    private const val crtOffsetX = 50f

    private fun rowToY(row: Int) = row * cellSize + crtOffsetY
    private fun colToX(col: Int) = col * cellSize + crtOffsetX

    private val currentRow get() = (cpu.cycle - 1) / 40
    private val currentCol get() = (cpu.cycle - 1) % 40
    private fun isInSprite(col: Int) = abs(cpu.x - col) <= 1

    private fun drawHUDText() {
        fill(0)
        textFont(font)
        // cycle text
        text("cycle ${cpu.cycle}", hudOffsetX, hudOffsetY)

        // x value
        text("x: ${cpu.x}", hudOffsetX + hudCycleTextWidth, hudOffsetY)

        // current instruction
        if (cpu.isExecutingAddX) {
            fill(130f)
        }
        text("executing: ${cpu.currentInstruction}", hudOffsetX + hudCycleTextWidth + hudXTextWidth, hudOffsetY)
    }
    private fun drawCRTBackground() {
        fill(230)
        stroke(255)
        for (row in 0 until 6) {
            for (col in 0 until 40) {
                val x = colToX(col)
                val y = rowToY(row)

                rect(x, y, cellSize, cellSize)
            }
        }
        noStroke()
    }
    private fun highlightX() {
        fill(0f, 255f, 255f, 50f)
        for (diff in -1..1) {
            val col = cpu.x+diff

            if (col in 0..39) {
                for (row in 0 until 6) {
                    val x = colToX(col)
                    val y = rowToY(row)
                    rect(x, y, cellSize, cellSize)
                }
            }
        }
    }
    private fun drawCurrentPixel() {
        fill(255f, 0f, 0f, 100f)
        rect(colToX(currentCol), rowToY(currentRow), cellSize, cellSize)
    }
    private fun drawLitPixels() {
        fill(125f)
        for (litPixel in litPixels) {
            val (row, col) = litPixel
            val x = colToX(col)
            val y = rowToY(row)
            rect(x, y, cellSize, cellSize)
        }
    }
    private fun drawCRTLabels() {
        fill(210f)
        textFont(smallFont)
        textAlign(CENTER, CENTER)
        for (row in 0 until 6) {
            if (row == currentRow) {
                fill(210f, 0f, 0f)
            }
            text(row.toString(), colToX(-1)+10f, rowToY(row)+10f)
            fill(210f)
        }

        for (col in 0 until 40) {
            if (isInSprite(col)) {
                fill(0f, 210f, 210f)
            }
            text(col.toString(), colToX(col)+10f, rowToY(-1)+10f)
            fill(210f)
        }
        textAlign(LEFT, BASELINE)
    }

    override fun setup() {
        font = createFont("Consolas", 28f)
        smallFont = createFont("Consolas", 11f)

        background(255)
        frameRate(24f)
        textFont(font)
    }

    override fun draw() {
        val isLit = isInSprite(currentCol)
        if (cpu.cycle > 0 && isLit) {
            litPixels.add(Pair(currentRow, currentCol))
        }

        background(255)
        fill(0)

        drawCRTBackground()

        drawHUDText()
        highlightX()
        drawCurrentPixel()
        drawLitPixels()
        drawCRTLabels()

        if (cpu.sequence.hasNext()) { cpu.sequence.next() }
    }

    fun run() {
        runSketch()
    }
}

fun main() = Day10Visualizer.run()