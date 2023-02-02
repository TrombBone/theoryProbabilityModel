
import javax.swing.WindowConstants
import kotlin.math.E
import kotlin.math.abs
import kotlin.math.pow
import kotlin.random.Random

fun main() {
    println(task1115())
}

fun task146() {
    // in pycharm
}

fun task125(delta: Double = 1.0, size: Int) {
    fun myF(x: Double) = 1 - E.pow(-x.pow(2) / (2 * delta.pow(2)))
    fun myFDiff(x: Double) = (x / delta.pow(2)) * E.pow(-x.pow(2) / (2 * delta.pow(2)))

    val example = 0.5
    println("Значение плотности в точке x = $example: ${myFDiff(example)}")

    val median = 1.18 * delta
    println("Значение плотности в медиане должно быть 0.5: ${myF(median)}")

    val listX = mutableListOf<Double>()
    val listY = mutableListOf<Double>()
    var x = 0.01
    while (x < 5) {
        listY.add(myFDiff(x))
        listX.add(x)
        x += 0.1
    }

    val plot = MyKotlinPlot("График плотности вероятности", listX, listY)
    plot.pack()
    RefineryUtilities.centerFrameOnScreen(plot)
    plot.isVisible = true
    plot.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
}

fun task1110(p: Double): Double {
    var congrats = 0.0
    val n = 1000000
    for (i in 1..n) {
        while (true) {
            val prob = Random.nextDouble()
            congrats++
            if (prob <= p) break
        }
    }
    println(1 / p)
    return congrats / n
}

fun task1115(): Double {
    val pA = 0.1
    val pB = 0.2
    val pC = 0.2
    var congrats = 0.0
    val n = 1000000
    for (i in 1..n) {
        var countParticles = 0
        var isBBreak = false
        var isCBreak = false
        while (true) {
            countParticles++
            when (Random.nextDouble()) {
                in 0.0..pA -> break
                in pA..(pA + pB) -> isBBreak = true
                in (pA + pB)..(pA + pB + pC) -> isCBreak = true
            }
            if (isBBreak && isCBreak) break
        }
        congrats += countParticles
    }
    return congrats / n
}

/*
fun task1025b(a: Int, p: Double): Double {
    fun P(r: Int, a: Int) = a.toDouble().pow(r) / factorial(r) * E.pow(-a)
    var congrats = 0
    val attempts = 1000000
    for (i in 1..attempts) {
        //
    }
    println(1 - E.pow(-a * p))
    return congrats / attempts.toDouble()
}

fun task1025a(a: Double, p: Double, k: Int): Double {
    fun P(r: Int, a: Double) = (a.pow(r) / factorial(r)) * E.pow(-a)
    var congrats = 0
    val attempts = 1000000
    for (i in 1..attempts) {
        val needP = Random.nextDouble()
//        var min = 100.0
//        var nowR = 0
//        for (r in 0..12) {
//            if (abs(P(r, a) - needP) < min) {
//                min = P(r, a)
//                nowR = r
//            }
//        }
        var M = 0
        for (r in 0..12) {
            M += round(P(r, a) * r).toInt()
        }
//        println(M)

//        val nowR = (needP * needR).toInt()
//        println(nowR)

//        if (P(M.toInt(), a) > needP) { }

        var countR = 0
        for (b in 1..M) {
            if (P(b, a) < needP) {
                countR++
            }
        }

        var countParticles = 0
        for (j in 1..M) {
            val prob = Random.nextDouble()
//            val prob2 = Random.nextDouble()
            if (prob <= p) countParticles++
        }
        if (countParticles == k) congrats++
    }
//    println("needP: $needP")
//    for (j in 0..10) {
//        println("for r=$j P= ${ P(j, a) }")
//    }t
    println(E.pow(-a * p) * (p * a).pow(k) / factorial(k))
    return congrats / attempts.toDouble()
}
*/

fun task924(n: Int): Double {
    var congrats = 0
    val attempts = 1000000
    nextAttempt@ for (i in 1..attempts) {
        var countCoin2 = 0
        var countCoin1 = 0
        for (j in 1..n) {
            val coin1 = Random.nextInt(0, 2) == 0
            val coin2 = Random.nextInt(0, 2) == 0
            if (coin1) countCoin1++
            if (coin2) countCoin2++
            if (countCoin1 == countCoin2 && j != n) continue@nextAttempt
        }
        if (countCoin1 == countCoin2) congrats++
    }
    println((factorial(2 * n - 2)) / (2.0.pow(2 * n - 1) * factorial(n) * factorial(n - 1)))
    return congrats / attempts.toDouble()
}

fun task826(): Double {
    var congrats = 0
    for (n in 1..1000000) {
        var countBreakLampI = 0
        var countBreakLampII = 0
        for (lamp in 1..5) {
            val lampType = Random.nextInt(0, 10)
            if (lampType == 0 || lampType == 1 || lampType == 2) countBreakLampII++ else countBreakLampI++
        }
        if (countBreakLampI >= 5 || countBreakLampII >= 2) congrats++
    }
    return congrats / 1000000.0
}

fun task71(): Double {
    var urns = mutableListOf(
        mutableListOf(0, 0, 1, 1), mutableListOf(0, 0, 1, 1), mutableListOf(0, 0, 1, 1), mutableListOf(0, 0, 1, 1),
        mutableListOf(0, 0, 1, 1), mutableListOf(0, 0, 1, 1), mutableListOf(0, 0, 1, 1), mutableListOf(0, 0, 1, 1),
        mutableListOf(0, 0, 1, 1), mutableListOf(0, 0, 0, 0, 0, 1)
    )
    var congrats = 0
    var countWhiteBall = 0
    for (n in 1..1000000) {
        urns.shuffle()
        urns = urns.onEach { it.shuffle() }
        if (urns[0][0] == 0) countWhiteBall++
        if (urns[0].size == 6 && urns[0][0] == 0) congrats++
    }
    return congrats / countWhiteBall.toDouble()
}

fun task62(): Double {
    val domino = mutableListOf(
        (0 to 0), (0 to 1), (0 to 2), (0 to 3), (0 to 4), (0 to 5), (0 to 6),
        (1 to 1), (1 to 2), (1 to 3), (1 to 4), (1 to 5), (1 to 6),
        (2 to 2), (2 to 3), (2 to 4), (2 to 5), (2 to 6),
        (3 to 3), (3 to 4), (3 to 5), (3 to 6),
        (4 to 4), (4 to 5), (4 to 6),
        (5 to 5), (5 to 6),
        (6 to 6)
    )
    var congrats = 0
    for (n in 1..1000000) {
        domino.shuffle()
        if (domino[0].first == domino[1].first ||
            domino[0].first == domino[1].second ||
            domino[0].second == domino[1].first ||
            domino[0].second == domino[1].second
        ) congrats++
    }
    return congrats / 1000000.0
}

fun task537(): Double {
    var congrats = 0
    var countInformTrue = 0
    for (i in 1..1000000) {
        var inform = true
        var firstTrue = false

        if (Random.nextInt(0, 3) != 0) inform = false else firstTrue = true
        if (Random.nextInt(0, 3) != 0) inform = inform.not()
        if (Random.nextInt(0, 3) != 0) inform = inform.not()
        if (Random.nextInt(0, 3) != 0) inform = inform.not()

        if (inform) countInformTrue++
        if (firstTrue && inform) congrats++
    }
    return congrats / countInformTrue.toDouble()
}

fun task47(): Pair<Int, Double> {
    var p = 0.0
    var countNumbers = 1
    while (p < 0.9) {
        var congrats = 0
        for (i in 1..1000000) {
            for (j in 1..countNumbers) {
                if (Random.nextInt(0, 1000000) % 2 == 0) {
                    congrats++
                    break
                }
            }
        }
        countNumbers++
        p = congrats / 1000000.0
    }

    return Pair(countNumbers - 1, p)
}

fun task38(): Double {
    var congrats = 0
    for (i in 1..1000000) {
        val x = Random.nextInt(0, 61)
        val y = Random.nextInt(0, 61)
        if (abs(x - y) <= 20) congrats++
    }
    return congrats / 1000000.0
}

fun task226(l: Int, k: Int): Pair<Double, Double> {
    val numbers = IntArray(l) { 1 }
    for (i in 1 until numbers.size) numbers[i] += numbers[i - 1]
    numbers.shuffle()

    var congrats = 0
    for (n in 1..1000000) {
        var count = 0
        for (i in numbers.indices) if (numbers[i] == i + 1) count++
        if (k == count) congrats++
        numbers.shuffle()
    }

    var f = 0.0
    for (j in 0..l - k) {
        f += (-1.0).pow(j) / factorial(j)
    }
    val p = (1 / factorial(k)) * f

    return Pair(p, congrats / 1000000.0)
}

fun factorial(n: Int): Double = if (n < 2) 1.0 else n * factorial(n - 1)

