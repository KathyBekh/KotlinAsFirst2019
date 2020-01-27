package practice.kt

import apartments
import birthday
import findNumberPhone
import footballMatchStatistics
import intersectionOfMany
import maxRainfall
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import rangeAverage
import stockProducts
import transfer
import java.io.IOException


class Tests {
    @Test
    fun birthday() {
        val ololo = birthday("Андрей 23 февраля, Ольга 8 марта, Мария 29 февраля, Михаил 30 сентября")
        Assertions.assertEquals(listOf<String>(), ololo)

        Assertions.assertEquals(
            listOf("Ольга 32 марта", "Михаил 31 тября"),
            birthday("Андрей 23 февраля, Ольга 32 марта, Мария 28 февраля, Михаил 31 тября")
        )

        Assertions.assertEquals(
            listOf("Ольга 8"),
            birthday("Андрей 23 февраля, Ольга 8, Мария 20 февраля, Михаил 11 сентября")
        )
    }

    @Test
    fun findNumberPhone() {
        Assertions.assertEquals(
            setOf("+79211234567", "+78217654321"),
            findNumberPhone("input/temp.txt", "Михаил *")
        )

        Assertions.assertEquals(
            setOf("+79116543456", "+79318239475"),
            findNumberPhone("input/temp.txt", "Антон Мобильный")
        )

        Assertions.assertEquals(
            setOf<String>(),
            findNumberPhone("input/temp.txt", "Антон Служебный")
        )
    }

    @Test
    fun transfer() {
        Assertions.assertEquals(
            "LD210 > JA4590",
            transfer("input/plane", "Peterburg", "Tokyo")
        )

        Assertions.assertEquals(
            "NO",
            transfer("input/plane", "Peterburg", "")
        )

        Assertions.assertEquals(
            "NO",
            transfer("input/plane", "London", "Frankfurt")
        )
        Assertions.assertThrows(IOException::class.java) {
            transfer("aaotehutaoheuooh8", "aeou", "henho")
        }
    }

    @Test
    fun stockProducts() {
        Assertions.assertEquals(
            listOf(
                "яблоки, достаточно, общая стоимость 2646 р",
                "молоко, достаточно, общая стоимость 5328 р",
                "хлеб, достаточно, общая стоимость 1770 р"
            ),
            stockProducts("input/products", "* 20")
        )

        Assertions.assertEquals(
            listOf("хлеб, достаточно, общая стоимость 1770 р"),
            stockProducts("input/products", "008243 20")
        )

        Assertions.assertEquals(
            listOf("ничего не найдено. уточните запрос и попробуйте еще раз."),
            stockProducts("input/products", "245136 1")
        )
        Assertions.assertThrows(IOException::class.java) {
            stockProducts("aaotehutaoheuooh8", "aeou")
        }
    }

    @Test
    fun footballMatchStatistics() {
        Assertions.assertEquals(
            listOf(
                "Арсенал: забитые мячи - 6; пропущенные мячи - 5;",
                "Бавария: забитые мячи - 6; пропущенные мячи - 14;",
                "Интер: забитые мячи - 3; пропущенные мячи - 3;",
                "Барселона: забитые мячи - 11; пропущенные мячи - 4;"
            ),
            footballMatchStatistics("input/football")
        )
        Assertions.assertThrows(IOException::class.java) {
            footballMatchStatistics("aaotehutaoheuooh8")
        }
    }

    @Test
    fun apartments() {
        Assertions.assertEquals(
            listOf(
                "Пионерская 9-17: комната 18, комната 14, кухня 7, коридор 4",
                "Садовая 19-1-55: комната 12, комната 19, кухня 9, коридор 5",
                "Железнодорожная 3-6: комната 21, кухня 6, коридор 4"
            ),
            apartments("input/apartments", "коридор 4")
        )

        Assertions.assertEquals(
            listOf<String>(),
            apartments("input/apartments", "кухня 10, коридор 5")
        )

        Assertions.assertEquals(
            listOf("Садовая 19-1-55: комната 12, комната 19, кухня 9, коридор 5"),
            apartments("input/apartments", "кухня 9, комната 18")
        )
        Assertions.assertThrows(IOException::class.java) {
            apartments("aaotehutaoheuooh8", "aeou, uhon")
        }
    }

    @Test
    fun rangeAverage() {
        Assertions.assertEquals(
            2.2,
            rangeAverage("input/rangeAverage", "B3:D3"),
            1e-3
        )

        Assertions.assertEquals(
            4.1175,
            rangeAverage("input/rangeAverage", "B1:A2"),
            1e-3
        )

        Assertions.assertEquals(
            1.561,
            rangeAverage("input/rangeAverage", "E1:B2"),
            1e-3
        )

        Assertions.assertThrows(IOException::class.java) {
            rangeAverage("aaotehutaoheuooh8", "aeou")
        }
    }

    @Test
    fun intersectionOfMany() {
        Assertions.assertEquals(
            setOf(-1),
            intersectionOfMany("input/arrays", "X & Y")
        )

        Assertions.assertEquals(
            setOf(3, 5, -9),
            intersectionOfMany("input/arrays", "A & !C")
        )

        Assertions.assertEquals(
            setOf(7, 28),
            intersectionOfMany("input/arrays", "A & C & X & !Y")
        )

        Assertions.assertThrows(IOException::class.java) {
            intersectionOfMany("aaotehutaoheuooh8", "aeou")
        }
    }

    @Test
    fun maxRainfall() {
        Assertions.assertEquals(
            listOf("42 мм,  апрель - 9, 12, 15"),
            maxRainfall("input/rainfall", "апрель 9...15")
        )

        Assertions.assertEquals(
            listOf("48"),
            maxRainfall("input/rainfall", "Март 22...Май 8")
        )

        Assertions.assertEquals(
            listOf("48"),
            maxRainfall("input/rainfall", "апрель 17...май 30")
        )

        Assertions.assertThrows(IOException::class.java) {
            maxRainfall("aaotehutaoheuooh8", "aeou")
        }
    }
}
