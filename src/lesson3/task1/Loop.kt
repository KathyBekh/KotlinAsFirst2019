@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var quantityOfNumber = 0
    var newNumber = n
    do {
        quantityOfNumber++
        newNumber /= 10
    } while (newNumber != 0)
    return quantityOfNumber
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var first = 1
    var second = 1
    var next = 0
    var couter = 0
    if (n < 3) {
        return first
    }

    while (couter < n - 2) {
        next = first + second
        first = second
        second = next
        couter += 1
    }
    return next
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var numberOne = m
    var numberTwo = n
    while (numberOne != 0 && numberTwo != 0) {
        if (numberOne < numberTwo)
            numberTwo %= numberOne
        else
            numberOne %= numberTwo
    }
    return m * n / (numberOne + numberTwo)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (n < 2) return 1
    for (m in 2 until n / 2) {
        if (n % m == 0) return m
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = divisorForTowNumber(m, n) == 1

fun divisorForTowNumber(numOne: Int, numTwo: Int): Int {
    var numbrOne = numOne
    var numbrTwo = numTwo
    while (numbrOne != numbrTwo) {
        if (numbrOne < numbrTwo)
            numbrTwo -= numbrOne
        else numbrOne -= numbrTwo
    }
    return numbrOne
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val squareRootOne = sqrt(m.toDouble()).toInt()
    val squareRootTwo = sqrt(n.toDouble()).toInt()
    for (i in squareRootOne..squareRootTwo) {
        if (sqr(i) in m..n) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var quantity = 0
    var memberOfTheSequen = x
    while (memberOfTheSequen != 1) {
        quantity += 1
        if (memberOfTheSequen % 2 == 0)
            memberOfTheSequen /= 2
        else memberOfTheSequen = 3 * memberOfTheSequen + 1

    }
    return quantity
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5!- x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
//don't solved
fun sin(x: Double, eps: Double): Double = TODO()
//{
//    var sinX = x
//    var pover = 2
//    while (abs(sinX) > eps) {
//        sinX -= x.pow(2 * (pover - 1)) * (-1.0).pow(pover) / factorial(2 * (pover - 1))
//        pover += 1
//    }
//    return sinX
//}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = TODO()


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var newNumbr = 0
    var oldNumbr = n
    while (oldNumbr != 0) {
        newNumbr = newNumbr * 10 + oldNumbr % 10
        oldNumbr /= 10
    }
    return newNumbr
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 0 состоят из разных цифр, а 111 и 323 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var theStartNumb = n
    var theLastNumericOfN = theStartNumb % 10
    var penultimateNumeric = theStartNumb / 10
    for (i in 1..digitNumber(theStartNumb)) {
        while (penultimateNumeric != 0) {
            if (theLastNumericOfN == penultimateNumeric % 10) return true
            else penultimateNumeric /= 10
        }
        theStartNumb /= 10
        penultimateNumeric = theStartNumb / 10
        theLastNumericOfN = theStartNumb % 10
    }
    return false
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var i = 1
    var toSquere = 1
    while (i <= n) {
        i += digitNumber(sqr(toSquere))
        toSquere += 1
    }
    val differenceOfIndexNumber = i - n - 1
    return (sqr(toSquere - 1) / 10.0.pow(differenceOfIndexNumber.toDouble()).toInt()) % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var indexNumber = 0
    var indexNumberForFib = 0
    while (indexNumber <= n) {
        indexNumber += digitNumber(fib(indexNumberForFib))
        indexNumberForFib += 1
    }
    val differenceOfIndexNumber = (indexNumber - n) - 1
    return (fib(indexNumberForFib - 1) / 10.0.pow(differenceOfIndexNumber.toDouble()).toInt() % 10)
}

