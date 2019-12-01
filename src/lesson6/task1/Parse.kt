@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
//fun main() {
//    println(bestHighJump("220 + 224 %+ 228 %- 230 + 232 %%- 234 %"))
//    println(bestLongJump("700 - 700"))
//    println("Введите время в формате ЧЧ:ММ:СС")
//    val line = readLine()
//    if (line != null) {
//        val seconds = timeStrToSeconds(line)
//        if (seconds == -1) {
//            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
//        } else {
//            println("Прошло секунд с начала суток: $seconds")
//        }
//    } else {
//        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
//    }
//}


/**
 * Средняя
 *
 * Дата представлена строкой вида "29 февраля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
//локальные тесты проходит
fun dateStrToDigit(str: String): String {
    val months = listOf(
        "января",
        "февраля",
        "марта",
        "апреля",
        "мая",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октября",
        "ноября",
        "декабря"
    )
    if (!str.matches(Regex("""(\d+\s[а-я]+\s\d+)"""))) {
        return ""
    }

    val partsOfStr = str.split(" ")
    val day = partsOfStr[0].toInt()
    val month = partsOfStr[1]
    val year = partsOfStr[2].toInt()

    if (month !in months) {
        return ""
    }

    val monthToInt = months.indexOf(month) + 1
    return if (day in 0..daysInMonth(monthToInt, year)) {
        String.format("%02d.%02d.$year", day, monthToInt)
    } else {
        ""
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
//локальные тесты проходит
fun dateDigitToStr(digital: String): String {
    if (!digital.matches(Regex("""(\d+[.]+\d+[.]+\d+)"""))) return ""
    val partsOfStr = digital.split(".")
    val months = mapOf(
        1 to "января",
        2 to "февраля",
        3 to "марта",
        4 to "апреля",
        5 to "мая",
        6 to "июня",
        7 to "июля",
        8 to "августа",
        9 to "сентября",
        10 to "октября",
        11 to "ноября",
        12 to "декабря"
    )
    val day = partsOfStr[0].toInt()
    val month = partsOfStr[1].toInt()
    val year = partsOfStr[2].toInt()
    var monthToStr = ""

    if (!months.containsKey(month)) {
        return ""
    } else monthToStr = months[month].toString()

    return if (day in 0..daysInMonth(month, year)) {
        String.format("$day $monthToStr $year")
    } else {
        ""
    }
}


/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
//локальные тесты проходит
fun flattenPhoneNumber(phone: String): String {
    val symbol = listOf('+', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0')
    return if (!phone.matches(Regex("""(\+*\d*(\(\d+\s*[-]*\s*\d*\))?\s*[-]*)*"""))) ""
    else phone.filter { it in symbol }
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
//локальные тесты проходит
fun bestLongJump(jumps: String): Int {
    var result = -1
    if (!jumps.matches(Regex("""(\d*\s*[-%]*\s*)*""")) || jumps == "") return -1
    val jump = jumps.split(" ")
    var numb: Int
    for (i in jump) {
        if (i == "-" || i == "%") continue
        else {
            numb = i.toInt()
            if (numb > result)
                result = numb
        }
    }
    return result
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
//локальные тесты проходит
fun bestHighJump(jumps: String): Int {
    if (!jumps.matches(Regex("""(\d*\s*[-+%]*\s*)*"""))) return -1
    var result = 0
    var numberToInt: Int
    val jump = jumps.split(" ")
    for (i in jump.indices) {
        if (jump[i] == "+" && numberOrNot(jump[i - 1])) {
            numberToInt = jump[i - 1].toInt()
            if (result < numberToInt)
                result = numberToInt
        }
    }
    return result
}

fun numberOrNot(string: String): Boolean =
    try {
        string.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }


/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
//локальные тесты проходит
fun plusMinus(expression: String): Int {
    if (!expression.matches(Regex("""(\d+\s[-+]\s)*\d+"""))) throw IllegalArgumentException()
    val soursList = expression.split(" ")
    var result = soursList[0].toInt()
    for (i in soursList.indices) {
        if (soursList[i] == "+") result += soursList[i + 1].toInt()
        else if (soursList[i] == "-") result -= soursList[i + 1].toInt()
        else continue
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
//локальные тесты проходит
fun firstDuplicateIndex(str: String): Int {
    val listOfString = str.toUpperCase().split(" ")
    var result = -1
    if (listOfString.isEmpty() || listOfString.size == 1) return result
    else {
        var rep = listOfString[0]
        var repeat = 0
        for (i in 1 until listOfString.size) {
            if (listOfString[i] == rep) repeat += 1
            result += listOfString[i].length + 1
            rep = listOfString[i]
        }
        if (repeat > 0) return result + 1
        else
            result = -1
    }
    return result
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
//локальные тесты проходит
fun mostExpensive(description: String): String {
    if (!description.matches(Regex("""([а-яА-Я]+\s+[0-9]*\.+[0-9]*+[;]*\s*)*"""))) return ""
    if (description == "") return ""
    val listOfProducts = description.split("; ")
    var max = 0.0
    var expensive = "yes"
    for (i in listOfProducts) {
        val productAndCost = i.split(" ")
        val cost = productAndCost[1].toDouble()
        if (cost > max) {
            max = cost
            expensive = productAndCost[0]
        }
    }
    return expensive
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
//еще не решенная
fun fromRoman(roman: String): Int {
    val romanNumber = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )
    var result = 0;
    for (i in roman) {
        if (i !in romanNumber) return -1
//        else {
//            if (romanNumber[i] == romanNumber[i])
//        }
    }

    return result
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
//что-то не догоняю. надо ещё подцмать!
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    var result = mutableListOf<Int>()
    val symbols = listOf<String>(">", "<", "+", "-", "[", "]", " ")

    for (i in commands) {
        require(i.toString() in symbols)
    }
    for (i in 0 until cells)
        result.add(0)



    return result.toList()
}

fun main() {
    println(bestHighJump("220 + 224 %+ 228 %-2546 232 %%- 234 %"))
//    println(fromRoman("MCMLXXVII"))
//    println(mostExpensive("356 Вино"))
//    println(plusMinus("2 + 31 - 40 + 13"))
}