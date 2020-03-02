@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

import kotlin.math.abs

/**
 * Класс "табличная функция".
 *
 * Общая сложность задания -- средняя.
 * Объект класса хранит таблицу значений функции (y) от одного аргумента (x).
 * В таблицу можно добавлять и удалять пары (x, y),
 * найти в ней ближайшую пару (x, y) по заданному x,
 * найти (интерполяцией или экстраполяцией) значение y по заданному x.
 *
 * Класс должен иметь конструктор по умолчанию (без параметров).
 */
class TableFunction() {
    private val table = mutableMapOf<Double, Double>()
    /**
     * Количество пар в таблице
     */
    val size: Int get() = table.size

    /**
     * Добавить новую пару.
     * Вернуть true, если пары с заданным x ещё нет,
     * или false, если она уже есть (в этом случае перезаписать значение y)
     */
    fun add(x: Double, y: Double): Boolean {
        return if (x in table) {
            table[x] = y
            false
        } else {
            table[x] = y
            true
        }
    }

    /**
     * Удалить пару с заданным значением x.
     * Вернуть true, если пара была удалена.
     */
    fun remove(x: Double): Boolean {
        return if (x in table) {
            table.remove(x)
            true
        } else {
            false
        }
    }

    /**
     * Вернуть коллекцию из всех пар в таблице
     */
    fun getPairs(): Collection<Pair<Double, Double>> = table.entries.map { it.toPair() }

    /**
     * Вернуть пару, ближайшую к заданному x.
     * Если существует две ближайшие пары, вернуть пару с меньшим значением x.
     * Если таблица пуста, бросить IllegalStateException.
     */
    fun findPair(x: Double): Pair<Double, Double>? {
        if (table.isEmpty()) throw IllegalStateException("Table is empty!")
        var minDistance = Double.MAX_VALUE
        var nearestX = 0.0
        for (valueX in table.keys) {
            val distance = abs(x - valueX)
            if (distance < minDistance) {
                minDistance = distance
                nearestX = valueX
            }
        }
        return Pair(nearestX, table[nearestX]!!)
    }

    /**
     * Вернуть значение y по заданному x.
     * Если в таблице есть пара с заданным x, взять значение y из неё.
     * Если в таблице есть всего одна пара, взять значение y из неё.
     * Если таблица пуста, бросить IllegalStateException.
     * Если существуют две пары, такие, что x1 < x < x2, использовать интерполяцию.
     * Если их нет, но существуют две пары, такие, что x1 < x2 < x или x > x2 > x1, использовать экстраполяцию.
     */
    fun getValue(x: Double): Double {
        TODO()
//        if (table.isEmpty()) throw IllegalStateException("Table is empty!")
//        for (valueX in table.keys) {
//            if (table.size == 1 || valueX == x) {
//                return table[valueX]!!
//            }
//
//            //fixme
//        }
//        return 0.0
    }

    /**
     * Таблицы равны, если в них одинаковое количество пар,
     * и любая пара из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean {
        if (other !is TableFunction) {
            return false
        }
        if (table.size != other.table.size) {
            return false
        }

        for (pair in other.getPairs()) {
            if (pair.first !in table || pair.second != table[pair.first]) {
                return false
            }
        }
        return true
    }

    override fun toString(): String {
        var s = ""
        for ((key, value) in table) {
            s += "$key -> $value;\n"
        }
        return s
    }

    override fun hashCode(): Int = table.hashCode()
}
