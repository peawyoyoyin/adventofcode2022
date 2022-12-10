package solutions.day10

import utils.measureAndLogTime
import kotlin.math.abs

fun main() = measureAndLogTime {
    val cyclesToTraces = mutableListOf(20, 60, 100, 140, 180, 220)

    var ans = 0

    val cpu = CPU(Day10Input.instructions)

    val pixels = mutableListOf<Boolean>()
    while (cpu.sequence.hasNext()) {
        cpu.sequence.next()

        if (cyclesToTraces.isNotEmpty()) {
            if (cpu.cycle == cyclesToTraces.first()) {
                ans += cpu.cycle * cpu.x
                cyclesToTraces.removeFirst()
            }
        }

        val currentPixel = (cpu.cycle - 1) % 40

        val isLit = abs(cpu.x - currentPixel) <= 1
        pixels.add(isLit)
    }

    println("part 1 ans: $ans")

    println("part 2 ans:")
    pixels.chunked(40)
        .map { line -> line.map { if (it) 'X' else ' ' } }
        .forEach { println(it.joinToString("")) }
}